import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {

    return (

        <div className="navbar">

            <Link to="/dashboard">Dashboard</Link>

            <Link to="/jobs">Jobs</Link>

            <Link to="/applications">Applications</Link>

            <Link to="/problems">Problems</Link>

            <Link to="/resumes">Resumes</Link>

        </div>

    );
}

export default Navbar;