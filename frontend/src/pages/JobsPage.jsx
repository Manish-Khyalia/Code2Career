import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import {
    getAllJobs,
    deleteJob,
    createJob,
    updateJob
} from "../services/JobService";
import "./JobsPage.css";

function JobsPage() {

    const [jobs, setJobs] = useState([]);

    const [companyName, setCompanyName] = useState("");
    const [jobRole, setJobRole] = useState("");
    const [location, setLocation] = useState("");
    const [salary, setSalary] = useState("");
    const [status, setStatus] = useState("");
    const [applicationLink, setApplicationLink] = useState("");
    const [deadline, setDeadline] = useState("");

    const [editingId, setEditingId] = useState(null);

    useEffect(() => {

        fetchJobs();

    }, []);

    const fetchJobs = async () => {

        try {

            const response = await getAllJobs();

            console.log(response);

            setJobs(response);

        }
        catch (error) {

            console.log(error);

        }

    };

    const handleDelete = async (id) => {

        try {

            if (!window.confirm("Are you sure you want to delete this job?")) {
                return;
            }

            await deleteJob(id);

            alert("Job Deleted Successfully");

            fetchJobs();

        }
        catch (error) {

            console.log(error);

        }

    };

    const handleEdit = (job) => {

        setEditingId(job.id);

        setCompanyName(job.companyName);
        setJobRole(job.jobRole);
        setLocation(job.location);
        setSalary(job.salary);
        setStatus(job.status);
        setApplicationLink(job.applicationLink);
        setDeadline(job.deadline);

    };

    const handleCancel = () => {

        setEditingId(null);

        setCompanyName("");
        setJobRole("");
        setLocation("");
        setSalary("");
        setStatus("");
        setApplicationLink("");
        setDeadline("");

    };

    const handleCreate = async () => {

        try {

            if (
                !companyName ||
                !jobRole ||
                !location ||
                !salary
            ) {
                alert("Please fill all required fields");
                return;
            }

            const jobData = {

                companyName,
                jobRole,
                location,
                salary,
                status,
                applicationLink,
                deadline

            };

            if (editingId !== null) {

                await updateJob(editingId, jobData);

                alert("Job Updated Successfully");

            }
            else {

                await createJob(jobData);

                alert("Job Added Successfully");

            }

            fetchJobs();

            setEditingId(null);

            setCompanyName("");
            setJobRole("");
            setLocation("");
            setSalary("");
            setStatus("");
            setApplicationLink("");
            setDeadline("");

        }
        catch (error) {

            console.log(error);

        }

    };

    return (

        <div className="jobs-container">

            <Navbar />

            <h1>Jobs Page</h1>

            <div className="job-form">

                <input
                    type="text"
                    placeholder="Company Name"
                    value={companyName}
                    onChange={(e) => setCompanyName(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Job Role"
                    value={jobRole}
                    onChange={(e) => setJobRole(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Location"
                    value={location}
                    onChange={(e) => setLocation(e.target.value)}
                />

                <input
                    type="number"
                    placeholder="Salary"
                    value={salary}
                    onChange={(e) => setSalary(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Status"
                    value={status}
                    onChange={(e) => setStatus(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Application Link"
                    value={applicationLink}
                    onChange={(e) => setApplicationLink(e.target.value)}
                />

                <input
                    type="date"
                    value={deadline}
                    onChange={(e) => setDeadline(e.target.value)}
                />

                <button
                    className="add-btn"
                    onClick={handleCreate}
                >
                    {editingId !== null ? "Update Job" : "Add Job"}
                </button>

                {
                    editingId !== null && (

                        <button
                            className="cancel-btn"
                            onClick={handleCancel}
                        >
                            Cancel
                        </button>

                    )
                }

            </div>

            <table className="jobs-table">

                <thead>

                    <tr>

                        <th>Company</th>
                        <th>Role</th>
                        <th>Location</th>
                        <th>Salary</th>
                        <th>Status</th>
                        <th>Edit</th>
                        <th>Delete</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        jobs.map((job) => (

                            <tr key={job.id}>

                                <td>{job.companyName}</td>
                                <td>{job.jobRole}</td>
                                <td>{job.location}</td>
                                <td>{job.salary}</td>
                                <td>{job.status}</td>

                                <td>

                                    <button
                                        className="edit-btn"
                                        onClick={() => handleEdit(job)}
                                    >
                                        Edit
                                    </button>

                                </td>

                                <td>

                                    <button
                                        className="delete-btn"
                                        onClick={() => handleDelete(job.id)}
                                    >
                                        Delete
                                    </button>

                                </td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

        </div>

    );

}

export default JobsPage;