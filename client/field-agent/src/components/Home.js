import { Link } from "react-router-dom";

function Home() {
  return (
    <nav className="navigation">
      <h1>Agent Registry</h1>
      <h2 className="homeH2">Welcome to the Agent Registry</h2>
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

export default Home;