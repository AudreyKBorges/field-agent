import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Navigation from "./Navigation";

function Agents() {
  const endpoint = "http://localhost:8080/api/agents";
  const [fieldAgents, setFieldAgents] = useState([]);

  useEffect(() => {
    getFieldAgents();
  }, []);

  const getFieldAgents = () => {
    fetch(endpoint)
      .then((res) => {
        if (res.status === 200) {
          return res.json();
        } else {
          return Promise.reject(`Unexpected status code: ${res.status}`);
        }
      })
      .then((data) => {
        setFieldAgents(data);
      })
      .catch(console.error);
  };

  const handleDeleteAgent = (agentId) => {
    const fieldAgent = fieldAgents.find(
      (fieldAgent) => fieldAgent.agentId === agentId
    );

    if (
      window.confirm(
        `Delete Agent ${fieldAgent.firstName} ${fieldAgent.middleName} ${fieldAgent.lastName}?`
      )
    ) {
      const init = {
        method: "DELETE",
      };

      fetch(`${endpoint}/${agentId}`, init)
        .then((response) => {
          if (response.status === 204) {
            getFieldAgents();
          } else {
            return Promise.reject(`Unexpected status code: ${response.status}`);
          }
        })
        .catch(console.log);
    }
  };

  return (
    <>
    <Navigation />
      <h2>Field Agents</h2>
      <Link className="btn btn-primary" to="/agents/add">
        Add Agent
      </Link>
      <table>
        <thead>
          <tr>
            <th>First Name</th>
            <th>Middle Name</th>
            <th>Last Name</th>
            <th>Date of Birth</th>
            <th>Height (in inches)</th>
            <th>&nbsp;</th>
          </tr>
        </thead>
        <tbody>
          {fieldAgents.map((fieldAgent) => (
            <tr key={fieldAgent.agentId}>
              <td>{fieldAgent.firstName}</td>
              <td>{fieldAgent.middleName}</td>
              <td>{fieldAgent.lastName}</td>
              <td>{fieldAgent.dob || "unavailable"}</td>
              <td>{fieldAgent.heightInInches}</td>
              <td className="buttonContainer">
                <Link
                  className="btn btn-primary"
                  to={`/agents/add/${fieldAgent.agentId}`}
                >
                  Edit
                </Link>
                <button
                  className="btn btn-danger"
                  onClick={() => handleDeleteAgent(fieldAgent.agentId)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default Agents;