import { Link } from "react-router-dom";

function Home() {
  return (
    <nav>
      <h1>Agent Registry</h1>
      <p>Welcome to the Agent Registry</p>
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