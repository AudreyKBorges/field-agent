import { useState, useEffect } from "react";

function Agents() {
  const endpoint = "http://localhost:8080/api/agent/";
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
      (fieldAgent) => fieldAgent.id === agentId
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
      <h2>Field Agents</h2>

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
              <td>{fieldAgent.dob}</td>
              <td>{fieldAgent.height}</td>
              <td className="buttonContainer">
                <button>
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