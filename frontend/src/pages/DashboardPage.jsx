import { useEffect, useState } from "react";
import { getDashboard } from "../services/DashboardService";
import Navbar from "../components/Navbar";
import "./DashboardPage.css";

function DashboardPage() {

    const [dashboard, setDashboard] = useState(null);

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

    return (

        <>

            <Navbar />

            <div className="dashboard-container">

                <h1>Dashboard Page</h1>

                {

                    dashboard &&

                    <div className="dashboard-cards">

                        <div className="card blue">
                            <h2>Total Jobs</h2>
                            <p>{dashboard.totalJobs}</p>
                        </div>

                        <div className="card green">
                            <h2>Total Applications</h2>
                            <p>{dashboard.totalApplications}</p>
                        </div>

                        <div className="card orange">
                            <h2>Total Problems</h2>
                            <p>{dashboard.totalProblems}</p>
                        </div>

                        <div className="card red">
                            <h2>Revision Problems</h2>
                            <p>{dashboard.revisionProblems}</p>
                        </div>

                        <div className="card purple">
                            <h2>Easy Problems</h2>
                            <p>{dashboard.easyProblems}</p>
                        </div>

                        <div className="card blue">
                            <h2>Medium Problems</h2>
                            <p>{dashboard.mediumProblems}</p>
                        </div>

                        <div className="card green">
                            <h2>Hard Problems</h2>
                            <p>{dashboard.hardProblems}</p>
                        </div>

                    </div>

                }

            </div>

        </>

    );

}

export default DashboardPage;