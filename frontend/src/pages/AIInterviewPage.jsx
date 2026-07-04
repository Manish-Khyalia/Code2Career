import Navbar from "../components/Navbar";
import { useState } from "react";
import "./AIInterviewPage.css";
import { generateQuestions } from "../services/AIInterviewService";

function AIInterviewPage() {

    const [company, setCompany] = useState("");
    const [role, setRole] = useState("");
    const [experience, setExperience] = useState("");
    const [questions, setQuestions] = useState([]);
    const [loading, setLoading] = useState(false);

    const handleGenerate = async () => {

        if (!company || !role || !experience) {

            alert("Please select all fields.");
            return;
        }

        setLoading(true);

        try {

            const response = await generateQuestions(
                company,
                role,
                experience
            );

            setQuestions(response);

            document
                .querySelector(".ai-results")
                ?.scrollIntoView({
                    behavior: "smooth"
                });

        } catch (error) {

            alert("Unable to generate interview questions.");

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="ai-interview-container">

            <Navbar />

            <div className="ai-header">

                <h1>AI Interview Preparation</h1>

                <p>
                    Practice interview questions based on your target company and role.
                </p>

            </div>

            <div className="ai-form">

                <select
                    value={company}
                    onChange={(e) => setCompany(e.target.value)}
                >
                    <option value="">Select Company</option>
                    <option>Google</option>
                    <option>Microsoft</option>
                    <option>Amazon</option>
                    <option>Adobe</option>
                    <option>Oracle</option>
                    <option>Atlassian</option>
                    <option>Flipkart</option>
                    <option>Walmart</option>
                    <option>Goldman Sachs</option>
                    <option>JP Morgan Chase</option>
                    <option>Infosys</option>
                    <option>TCS</option>
                    <option>Accenture</option>
                    <option>Celebal Technologies</option>
                </select>

                <select
                    value={role}
                    onChange={(e) => setRole(e.target.value)}
                >
                    <option value="">Select Role</option>
                    <option>Software Engineer</option>
                    <option>Backend Developer</option>
                    <option>Frontend Developer</option>
                    <option>Full Stack Developer</option>
                    <option>Java Developer</option>
                    <option>Data Engineer</option>
                    <option>Data Analyst</option>
                    <option>Machine Learning Engineer</option>
                    <option>DevOps Engineer</option>
                    <option>Cloud Engineer</option>
                    <option>Software Development Engineer (SDE)</option>
                </select>

                <select
                    value={experience}
                    onChange={(e) => setExperience(e.target.value)}
                >
                    <option value="">Select Experience</option>
                    <option>Fresher</option>
                    <option>0-1 Years</option>
                    <option>1-3 Years</option>
                    <option>3+ Years</option>
                </select>

                <button
                    className="generate-btn"
                    onClick={handleGenerate}
                    disabled={loading}
                >
                    {loading ? "Generating..." : "Generate Questions"}
                </button>

            </div>

            <div className="ai-results">

                {

                    questions.length > 0 && (

                        <div className="questions-card">

                            <h2> AI Generated Interview Questions</h2>

                            <ol>

                                {

                                    questions.map((question, index) => (

                                        <li key={index}>

                                            {question}

                                        </li>

                                    ))

                                }

                            </ol>

                        </div>

                    )

                }

            </div>

        </div>

    );

}

export default AIInterviewPage;