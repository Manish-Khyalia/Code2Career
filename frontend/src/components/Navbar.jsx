import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";

function Navbar() {

    const navigate = useNavigate();

    const handleLogout = () => {

        const confirmLogout = window.confirm(
            "Are you sure you want to logout?"
        );

        if (!confirmLogout) {
            return;
        }

        localStorage.removeItem("token");

        navigate("/");

    };

    return (

        <div className="navbar">

            <div className="nav-links">

                <Link to="/dashboard">Dashboard</Link>

                <Link to="/jobs">Jobs</Link>

                <Link to="/applications">Applications</Link>

                <Link to="/problems">Problems</Link>

                <Link to="/resumes">Resumes</Link>

                <Link to="/ai-interview">AI Interview</Link>

            </div>

            <button
                className="logout-btn"
                onClick={handleLogout}
            >
                Logout
            </button>

        </div>

    );

}

export default Navbar;