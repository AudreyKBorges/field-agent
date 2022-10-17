import { useEffect, useState } from "react";
import { useHistory, Link, useParams } from "react-router-dom";

const FIELD_AGENT_DEFAULT = {
  firstName: "",
  middleName: "",
  lastName: "",
  dob: "",
  heightInInches: 0,
};

function AddAgent() {
  const endpoint = "http://localhost:8080/api/agents";
  const [fieldAgent, setFieldAgent] = useState(FIELD_AGENT_DEFAULT);
  const [editFieldAgentId, setEditFieldAgentId] = useState(0);
  const [errors, setErrors] = useState([]);
  const history = useHistory();
  const { agentId } = useParams();

  useEffect(() => {
    if (agentId) {
      setEditFieldAgentId(agentId);
      fetch(`${endpoint}/${agentId}`)
        .then((response) => response.json())
        .then((data) => setFieldAgent(data));
    }
  }, [agentId]);

  const handleChange = (event) => {
    const newFieldAgent = { ...fieldAgent };
    if (event.target.type === "checkbox") {
      newFieldAgent[event.target.name] = event.target.checked;
    } else {
      newFieldAgent[event.target.name] = event.target.value;
    }
    setFieldAgent(newFieldAgent);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    addFieldAgent();
  };

  const addFieldAgent = () => {
    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(fieldAgent),
    };
    fetch(endpoint, init)
      .then((response) => {
        if (response.status === 201 || response.status === 400) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected status code: ${response.status}`);
        }
      })
      .then((data) => {
        if (data.agentId) {
          resetState();
          history.push("/agents");
        } else {
          setErrors(data);
        }
      })
      .catch((error) => console.log(error));
  };

  const resetState = () => {
    setFieldAgent(FIELD_AGENT_DEFAULT);
    setEditFieldAgentId(0);
    setErrors([]);
  };

  return (
    <>
      <h2>Add Field Agent</h2>

      {errors.length > 0 && (
        <div>
          <h3>The following errors occured:</h3>
          <ul>
            {errors.map((error) => {
              return <li>{error}</li>;
            })}
          </ul>
        </div>
      )}

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="firstName">First Name:</label>
          <input
            id="firstName"
            name="firstName"
            type="text"
            className="form-control"
            value={fieldAgent.firstName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="middleName">Middle Name:</label>
          <input
            id="middleName"
            name="middleName"
            type="text"
            className="form-control"
            value={fieldAgent.middleName}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName">Last Name:</label>
          <input
            id="lastName"
            name="lastName"
            type="text"
            className="form-control"
            value={fieldAgent.lastName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="dob">Date of Birth:</label>
          <input
            id="dob"
            name="dob"
            type="date"
            className="form-control"
            value={fieldAgent.dob}
            onChange={handleChange}
            min="1922"
            max="2004"
          />
        </div>
        <div className="form-group">
          <label htmlFor="heightInInches">Height (in inches):</label>
          <input
            id="heightInInches"
            name="heightInInches"
            type="number"
            className="form-control"
            value={fieldAgent.heightInInches}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mt-4">
          <button className="btn btn-success mr-4" type="submit">
          Add Field Agent
          </button>
          <Link className="btn btn-warning" to="/">
            Cancel
          </Link>
        </div>
      </form>
    </>
  );
}

export default AddAgent;