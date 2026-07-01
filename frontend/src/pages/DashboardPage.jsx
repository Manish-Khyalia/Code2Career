import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getDashboard } from "../services/DashboardService";
import Navbar from "../components/Navbar";
import "./DashboardPage.css";

function DashboardPage() {

    const [dashboard, setDashboard] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {

        fetchDashboard();

    }, []);

    const fetchDashboard = async () => {

        try {

            const response = await getDashboard();

            console.log(response);

            setDashboard(response);

        }
        catch (error) {

            console.log(error);

        }

    };
    const hour = new Date().getHours();

    let greeting = "Welcome Back 👋";

    if (hour < 12) {

        greeting = "Good Morning ☀️";

    }
    else if (hour < 18) {

        greeting = "Good Afternoon 🌤️";

    }
    else {

        greeting = "Good Evening 🌙";

    }

    const quotes = [

        "Consistency beats intensity.",

        "One problem solved today is one step closer to your dream job.",

        "Practice daily. Success follows.",

        "Dream big. Start small. Stay consistent.",

        "Your future package depends on today's effort."

    ];

    const quote = quotes[Math.floor(Math.random() * quotes.length)];

    return (

        <>
            <Navbar />

            <div className="dashboard-container">

                <div className="dashboard-header">

                    <h1>{greeting}</h1>

                    <p>

                        {new Date().toLocaleDateString("en-IN", {

                            weekday: "long",

                            day: "numeric",

                            month: "long",

                            year: "numeric"

                        })}

                    </p>

                    <p>

                        Track your placement preparation in one place.

                    </p>

                </div>

                {

                    dashboard &&

                    <>

                        <div className="dashboard-cards">

                            <div className="card blue">
                                <h2>💼 Total Jobs</h2>
                                <p>{dashboard.totalJobs}</p>
                            </div>

                            <div className="card green">
                                <h2>Applications</h2>
                                <p>{dashboard.totalApplications}</p>
                            </div>

                            <div className="card orange">
                                <h2>Problems</h2>
                                <p>{dashboard.totalProblems}</p>
                            </div>

                            <div className="card red">
                                <h2>Revision</h2>
                                <p>{dashboard.revisionProblems}</p>
                            </div>

                        </div>

                        <div className="dashboard-cards second-row">

                            <div className="card purple">
                                <h2>Easy</h2>
                                <p>{dashboard.easyProblems}</p>
                            </div>

                            <div className="card blue">
                                <h2>Medium</h2>
                                <p>{dashboard.mediumProblems}</p>
                            </div>

                            <div className="card green">
                                <h2>Hard</h2>
                                <p>{dashboard.hardProblems}</p>
                            </div>

                        </div>

                        <div className="quick-actions">

                            <h2>Quick Actions</h2>

                            <div className="action-buttons">

                                <button onClick={() => navigate("/jobs")}>
                                    💼 Add Job
                                </button>

                                <button onClick={() => navigate("/problems")}>
                                    💻 Add Problem
                                </button>

                                <button onClick={() => navigate("/resumes")}>
                                    📄 Upload Resume
                                </button>

                            </div>

                        </div>

                        <div className="quote-card">

                            <h2>Daily Motivation</h2>

                            <p>

                                {quote}

                            </p>

                        </div>

                    </>

                }

            </div>

        </>

    );

}

export default DashboardPage;