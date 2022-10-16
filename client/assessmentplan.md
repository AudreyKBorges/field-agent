
# React Field Agent Assessment

## Tasks

_TODO_ Add time estimates to each of the top-level tasks

* [x] Continue working in the repo from last week's Field Agent API repository (#.# hours)
  * [x] Add a README in the `client` folder with the contents from this file

* [x] Review the requirements (#.# hours)

* [x] Identify any research that I need to do (#.# hours)

### Part 1: Project Setup and Agents List

* [x] Create a new React project with CRA (create-react-app)
  * [x] Remove the cruft (refer back to the Components and JSX exercise for instructions)

* [x] Add Bootstrap (or other CSS framework) to the `public/index.html` file
  * [x] Add a link to the Bootstrap CSS using the [CDN from the official docs](https://getbootstrap.com/docs/4.6/getting-started/introduction/#css)
  * [x] Add the [`container` CSS class](https://getbootstrap.com/docs/4.6/layout/overview/#containers) to the `<div id="root"></div>` element

* [x] Create `Agents` component (stub)
  * [x] Update `App` component to render `Agents`

* [x] Update `Agents` to render list of agents
  * [x] Use `fetch` to `GET` a list of agents from the Field Agent API when the component is first loaded
  * [x] Write JSX to render the agents array
  * [ ] Stub out click event handlers ("Add Agent", "Edit Agent", "Delete Agent") as necessary

**Commit all changes and push to GitHub**

### Part 2: Add Agent and Delete Agent

* [x] Create a form to add an agent
  * [x] Add form JSX
  * [x] Decide between using individual state variables for input elements or a single object
  * [x] Add onChange event handlers to input elements
  * [x] Add onSubmit event handler to form element (be sure to prevent the form from submitting!)
  * [x] Create agent object
  * [x] Use `fetch` to `POST` the new agent's information to the Field Agent API
  * [x] On success, update the agents array (don't modify the original array!), or on failure, display any validation errors from the API in the UI

* [x] Support deleting agents
  * [x] Confirm the deletion with the user
  * [x] Use `fetch` to `DELETE` the agent from the Field Agent API
  * [ ] On success, update the agents array (don't modify the original array!)

* [x] Conditionally render sections of the component
  * [x] Add state variable to track the current view
  * [x] Add conditional logic to the JSX to display the appropriate view

**Commit all changes and push to GitHub**

### Part 3: Edit Agent

* [x] Support editing agents
  * [x] Store the "edit agent ID" in a new state variable
  * [x] Retrieve the agent to edit
  * [x] Update form state variable(s)
  * [x] Add form JSX
  * [x] Add onChange event handlers to input elements
  * [x] Add onSubmit event handler to form element (be sure to prevent the form from submitting!)
  * [x] Create agent object
  * [x] Use `fetch` to `PUT` the updated agent's information to the Field Agent API
  * [x] On success, update the agents array (don't modify the original array!), or on failure, display any validation errors from the API in the UI

* [ ] Apply Bootstrap styling (as needed)
  * [ ] Update the agents list
  * [ ] Update the add agent form
  * [ ] Update the edit agent form
  * [ ] Update the delete agent confirmation

**Commit all changes and push to GitHub**

### Part 4: Client-Side Routes

* [x] Implement the required client-side routes (#.# hours)
  * [x] Install `react-router-dom`
  * [x] Define the necessary client-side routes (see the list of routes below)
  * [x] Stub out any components that are needed to support the client-side routes
    * _Note: Stub out the individual Agents CRUD UI components but hold off on moving any code from last week's monolithic Agents CRUD UI component to the individual components_
  * [ ] Display a "Not Found" message if a route doesn't match one of the defined routes

### Part 5: Agents CRUD UI Component Refactoring

* [x] Update the "Agents" list component (#.# hours)
  * [x] Update the "Add Agent" button to redirect the user to the "Add Agent" route (if not already completed)
  * [x] Update the individual agent "Edit" buttons to redirect the user to the appropriate route (if not already implemented)

* [x] Update the "Add Agent" form component (#.# hours)
  * [x] Move code from the "Agents" list component into the "Add Agent" form component
  * [x] After a successful `POST` to the Field Agent API, redirect the user to the "Agents" route

* [x] Update the "Edit Agent" form component (#.# hours)
  * [x] Move code from the "Agents" list component into the "Edit Agent" form component
  * [x] Use the `useParams` hook to get the agent's ID from the route
  * [x] Use `fetch` to `GET` the agent from the Field Agent API when the component is first loaded
  * [x] After a successful `PUT` to the Field Agent API, redirect the user to the "Agents" route

_Note: A single form component can be used for both "Add Agent" and "Edit Agent"._

## High-Level Requirements

* Implement a full CRUD UI for agents (display, add, update, and delete).
* Implement the required client-side routes.
* Display a "Not Found" message if a route doesn't match one of the defined routes.
* Create React components as needed to support the required client-side routes.

## Technical Requirements

* Use Create React App.
* Use `fetch` for async HTTP.
* Use React Router to implement the client-side routes.
* Use React Router's `useHistory` hook to programmatically redirect users and `useParams` hook to access parameters, paths, and other data.
* You are not allowed to change the Field Agent HTTP Service or database (unless there's a confirmed bug and your instructor approves).
* Use a CSS framework.

## Client-Side Routes

- "Home" `/` - Renders a component that displays a welcome message and a link to the "Agents" route
  - Links to other parts of the website could be added in the future
- "Agents" `/agents` - Renders a component that displays a list of agents
- "Add Agent" `/agents/add` - Renders a component that displays a form to add an agent
- "Edit Agent" `/agents/edit/:id` - Renders a component that displays a form to edit the agent specified by the `:id` route parameter
- "Delete Agent" `/agents/delete/:id` (optional) - Renders a component that displays a confirmation message to delete the agent specified by the `:id` route parameter
  - _Note: If this route isn't implemented, handle agent deletion within the "Agents" route._
- "Not Found" - Renders a component that displays a friendly "not found" message if the requested route doesn't match one of the defined routes