import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import {
    getAllApplications,
    createApplication,
    updateApplication,
    deleteApplication
} from "../services/ApplicationService";
import "./ApplicationsPage.css";

function ApplicationsPage() {

    const [applications, setApplications] = useState([]);

    const [companyName, setCompanyName] = useState("");
    const [jobRole, setJobRole] = useState("");
    const [applicationDate, setApplicationDate] = useState("");
    const [status, setStatus] = useState("");
    const [notes, setNotes] = useState("");

    const [editingId, setEditingId] = useState(null);

    useEffect(() => {

        fetchApplications();

    }, []);

    const handleEdit = (application) => {

        setEditingId(application.id);

        setCompanyName(application.companyName);
        setJobRole(application.jobRole);
        setApplicationDate(application.applicationDate);
        setStatus(application.status);
        setNotes(application.notes);

    };

    const handleDelete = async (id) => {

        try {

            if (!window.confirm("Are you sure you want to delete this application?")) {
                return;
            }

            await deleteApplication(id);

            alert("Application Deleted Successfully");

            fetchApplications();

        }
        catch (error) {

            console.log(error);

        }

    };

    const handleCreate = async () => {

        try {

            if (
                !companyName ||
                !jobRole ||
                !applicationDate ||
                !status
            ) {
                alert("Please fill all required fields");
                return;
            }

            const applicationData = {

                companyName,
                jobRole,
                applicationDate,
                status,
                notes

            };

            if (editingId !== null) {

                await updateApplication(editingId, applicationData);

                alert("Application Updated Successfully");

            }
            else {

                await createApplication(applicationData);

                alert("Application Added Successfully");

            }

            fetchApplications();

            setEditingId(null);

            setCompanyName("");
            setJobRole("");
            setApplicationDate("");
            setStatus("");
            setNotes("");

        }
        catch (error) {

            console.log(error);

        }

    };

    const fetchApplications = async () => {

        try {

            const response = await getAllApplications();

            console.log(response);

            setApplications(response);

        }
        catch (error) {

            console.log(error);

        }

    };

    return (

        <div className="applications-container">

            <Navbar />

            <h1>Applications Page</h1>

            <div className="application-form">

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
                    type="date"
                    value={applicationDate}
                    onChange={(e) => setApplicationDate(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Status"
                    value={status}
                    onChange={(e) => setStatus(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Notes"
                    value={notes}
                    onChange={(e) => setNotes(e.target.value)}
                />

                <button
                    className="add-btn"
                    onClick={handleCreate}
                >
                    {editingId !== null ? "Update Application" : "Add Application"}
                </button>

                {
                    editingId !== null && (

                        <button
                            className="cancel-btn"
                            onClick={() => {

                                setEditingId(null);

                                setCompanyName("");
                                setJobRole("");
                                setApplicationDate("");
                                setStatus("");
                                setNotes("");

                            }}
                        >
                            Cancel
                        </button>

                    )
                }

            </div>

            <table className="applications-table">

                <thead>

                    <tr>

                        <th>Company</th>
                        <th>Role</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Notes</th>
                        <th>Edit</th>
                        <th>Delete</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        applications.map((application) => (

                            <tr key={application.id}>

                                <td>{application.companyName}</td>
                                <td>{application.jobRole}</td>
                                <td>{application.applicationDate}</td>
                                <td>{application.status}</td>
                                <td>{application.notes}</td>

                                <td>

                                    <button
                                        className="edit-btn"
                                        onClick={() => handleEdit(application)}
                                    >
                                        Edit
                                    </button>

                                </td>

                                <td>

                                    <button
                                        className="delete-btn"
                                        onClick={() => handleDelete(application.id)}
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

export default ApplicationsPage;