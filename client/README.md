# Web Field Agent Assessment
## Tasks
_TODO_ Add time estimates to each of the top-level tasks
* [ ] Continue working in the repo from last week's Field Agent API repository (#.# hours)
  * [ ] Add a README in the `client` folder with the contents from this file
* [ ] Review the requirements (#.# hours)
* [ ] Identify any research that I need to do (#.# hours)
### Part 1: Project Setup and Agents List
* [ ] Create an `index.html` and `main.js` file as a starting point for your project
* [ ] Dislay a list of agents
  * [ ] Use `fetch` to `GET` a list of agents from the Field Agent API when the website is first loaded
  * [ ] Use HTML and JavaScript to render the agents array
  * [ ] Stub out click event handlers for the "Add Agent", "Edit Agent", and "Delete Agent" buttons
**Commit all changes and push to GitHub**
### Part 2: Add Agent and Delete Agent
* [ ] Create a form to add an agent
  * [ ] Add form HTML
  * [ ] Add onsubmit event handler to the form element (be sure to prevent the form from submitting!)
  * [ ] Create an agent object
  * [ ] Use `fetch` to `POST` the new agent's information to the Field Agent API
  * [ ] On success, refresh the agents list, or on failure, display any validation errors from the API in the UI
* [ ] Support deleting agents
  * [ ] Confirm the deletion with the user
  * [ ] Use `fetch` to `DELETE` the agent from the Field Agent API
  * [ ] On success, refresh the agents list
**Commit all changes and push to GitHub**
### Part 3: Edit Agent
* [ ] Support editing agents
  * [ ] Retrieve the agent to edit
  * [ ] Update the form with the agent's property values
  * [ ] Update the onsubmit event handler to handle both `POST` and `PUT` requests
  * [ ] Set the agent's ID on the agent object
  * [ ] Use `fetch` to `PUT` the updated agent's information to the Field Agent API
  * [ ] On success, refresh the agents list, or on failure, display any validation errors from the API in the UI
### Part 4: Refinements
* [ ] Conditionally render sections of the page
  * [ ] Add a state variable to track the current view
  * [ ] Add a method to update the current view and conditionally render the list or the form
  * [ ] Call the method to update the current where needed
* [ ] Add Bootstrap to the `public/index.html` file
  * [ ] Add a link to the Bootstrap CSS using the [CDN from the official docs](https://getbootstrap.com/docs/4.6/getting-started/introduction/#css)
  * [ ] Add the [`container` CSS class](https://getbootstrap.com/docs/4.6/layout/overview/#containers) to the `<div id="root"></div>` element
* [ ] Apply Bootstrap styling
  * [ ] Style all buttons
  * [ ] Style the agents list
  * [ ] Style the form
**Commit all changes and push to GitHub**
## High-Level Requirements
Implement a full CRUD UI for agents.
* Display all agents
* Add an agent
* Update an agent
* Delete an agent
## Technical Requirements
* Always use semantically correct markup.
* With the exception of Bootstrap (or another CSS framework) for styles, don't use any libraries or frameworks.
* Use `fetch` for async HTTP.
* You are not allowed to change the Field Agent HTTP Service or database (unless there's a confirmed bug and your instructor approves).