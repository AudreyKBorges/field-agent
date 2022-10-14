import { Link } from "react-router-dom";

function Navigation() {
  return (
    <nav>
      <h1>Agent Registry</h1>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/agents">Agents</Link>
        </li>
      </ul>
    </nav>
  );
}

export default Navigation;