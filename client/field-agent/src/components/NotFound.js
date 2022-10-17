import { Link } from "react-router-dom";
import image from './404.png'; 

function NotFound() {
  return (
    <div>
      <h2 className="notFound">Page Not Found</h2>
      <p className="notFound">
        Click <Link to="/">here</Link> to go back home.
      </p>
      <div>
        <img
          src={image}
          alt=""
        />
      </div>
    </div>
  );
}

export default NotFound;