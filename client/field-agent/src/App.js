import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Agents from "./components/Agents";
import AddAgent from "./components/AddAgent";
import EditAgent from "./components/EditAgent";
import Home from "./components/Home";
import NotFound from "./components/NotFound";

function App() {
  return (
    <Router>
      <Switch>
      <Route path="/" exact>
          <Home />
        </Route>
        <Route path="/agents" exact>
          <Agents />
        </Route>
        <Route path="/agents/add">
          <AddAgent />
        </Route>
        <Route path="/agents/edit/:agentId">
          <EditAgent />
        </Route>
        {/* <Route path="*">
          <NotFound />
        </Route> */}
      </Switch>
    </Router>
  );
}

export default App;