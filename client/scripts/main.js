const endpoint = 'http://localhost:8080/api/agent';
let fieldAgents = [];
let editFieldAgentId = 0;

function displayList(){
    setCurrentView('List');
    getFieldAgents()
    .then(data =>{
        fieldAgents = data;
        renderList(data)
    });
}

function getMaxDate() {
    const eighteenYearsAgo = eighteenYearsAgo.setFullYear(eighteenYearsAgo.getFullYear() - 18);
}


async function getFieldAgents(){
    const response = await fetch(endpoint);
    return await response.json();
}

function setCurrentView(view){
    const formContainerElement = document.getElementById('form-container');
    const listContainerElement = document.getElementById('list-container');

    switch(view){
        case 'List':
            formContainerElement.style.display = 'none';
            listContainerElement.style.display = 'block';
            break;
        case 'Form':
            formContainerElement.style.display = 'block';
            listContainerElement.style.display = 'none';
    }
}

function renderList(fieldAgents){
   
    const fieldAgentsHTML = fieldAgents.map(fa => {
                return `
                <tr>
                    <td >${fa.firstName}</td>
                    <td>${fa.middleName}</td>
                    <td>${fa.lastName}</td>
                    <td>${fa.dateOfBirth}</td>
                    <td>${fa.heightInInches}</td>
                    <td class="btn-column">
                        <button class="btn btn-info" onclick="handleEditAgent(${fa.id})">Edit</button>
                        <button class="btn btn-danger" onclick="handleDeleteAgent(${fa.id})">Delete</button>
                    </td>
                </tr>
                `;
            }); 
            const tableBodyElement = document.getElementById('table-rows');
            tableBodyElement.innerHTML = fieldAgentsHTML.join('');
}

// handling submit
function handleSubmit(event){
    event.preventDefault();

    const firstName = document.getElementById('first-name').value; 
    const middleName = document.getElementById('middle-name').value;
    const lastName = document.getElementById('last-name').value;
    const d = new Date();
    let day = d.getDate();
    const dateOfBirth = document.getElementById('dob').date;
    const heightInInches = document.getElementById('height').value;

    const fieldAgent = {
        firstName,
        middleName,
        lastName,
        dateOfBirth: dateOfBirth ? date(dateOfBirth) : "",
        heightInInches: heightInInches ? parseInt(heightInInches) : 0, 
    };

    if(editFieldAgentId > 0){
        doPut(fieldAgent);
    }else{
        doPost(fieldAgent);
    }
}
// handleAdd
function handleAddAgent(){
    setCurrentView('Form');
}

// update 
function handleEditAgent(fieldAgentId){
    const fieldAgent = fieldAgents.find(fieldAgent => fieldAgent.id === fieldAgentId);

    document.getElementById('first-name').value = fieldAgent.firstName;
    document.getElementById('middle-name').value = fieldAgent.middleName;
    document.getElementById('last-name').value = fieldAgent.lastName;
    document.getElementById('dob').date = fieldAgent.dateOfBirth;
    document.getElementById('height').value = fieldAgent.heightInInches;

    document.getElementById('form-heading').innerText = "Update Field Agent";
    document.getElementById('form-submit-button').innerText = "Update Field Agent";

    editFieldAgentId = fieldAgentId;
    setCurrentView('Form');
}


// delete
function handleDeleteAgent(fieldAgentId){
    const fieldAgent = fieldAgents.find(fieldAgent => fieldAgent.id === fieldAgentId);
    if(confirm(`Delete agent ${fieldAgent.firstName} ${fieldAgent.middleName} ${fieldAgent.lastName}?`)){
        const init = {
            method: 'DELETE'
        };

        fetch(`${endpoint}/${fieldAgentId}`, init)
        .then(response => {
            if(response.status === 204){
                displayList();
                resetState();
            }else{
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .catch(console.log);
    }
}

// handling errors 
function renderErrors(errors){
    const errorsHTML = errors.map(e => `<li>${e}</li>`);
    const errorsHTMLString = `
        <p>The following errors were found:</p>
        <ul>
            ${errorsHTML.join('')}
        </ul>
    `;

    document.getElementById('errors').innerHTML = errorsHTMLString;
}

// handle posts and puts 

function doPost(fieldAgent){
    const init = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(fieldAgent)
    };
    fetch('http://localhost:8080/api/agent', init)
    .then(response =>{

        if(response.status === 201 || response.status === 400){
            return response.json();
        }else{
            return Promise.reject(`Unexpected status code: ${response.status}`);
        }
    })
    .then(data =>{
        if(data.id){
            displayList();
            resetState();
        }else{
            renderErrors(data);
        }
    })
    .catch(error => console.log(error))
}

function doPut(fieldAgent){
    fieldAgent.id = editFieldAgentId;

    const init = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(fieldAgent)
    };

    fetch(`http://localhost:8080/api/agent/${editFieldAgentId}`, init)
    .then(response => {
        if(response.status === 204){
            return null;
        }else if(response.status === 400){
            return response.json();
        }else{
            return Promise.reject(`Unexpected status code: ${response.status}`);
        }
    })
    .then(data =>{
        if(!data){
            displayList();
            resetState();
        }else{
            renderErrors(data);
        }
    })
    .catch(console.log);
}

function resetState(){
    document.getElementById('form').reset();
    document.getElementById('form-submit-button').innerText = 'Add Field Agent';
    document.getElementById('errors').innerHTML = '';
    editFieldAgentId = 0;
    setCurrentView('List');
}

displayList();