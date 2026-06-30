import { useEffect, useRef, useState } from "react";
import Navbar from "../components/Navbar";
import {
    getAllResumes,
    uploadResume,
    deleteResume
} from "../services/ResumeService";
import "./ResumesPage.css";
import axios from "axios";

function ResumesPage() {

    const [resumes, setResumes] = useState([]);

    const [selectedFile, setSelectedFile] = useState(null);
    const fileInputRef = useRef(null);

    useEffect(() => {

        fetchResumes();

    }, []);

    const fetchResumes = async () => {

        try {

            const response = await getAllResumes();

            console.log(response);

            setResumes(response);

        }
        catch (error) {

            console.log(error);

        }

    };

    const handleUpload = async () => {

        if (!selectedFile) {

            alert("Please select a PDF file");
            return;

        }

        try {

            await uploadResume(selectedFile);

            alert("Resume Uploaded Successfully");

            setSelectedFile(null);

            if (fileInputRef.current) {
                fileInputRef.current.value = "";
            }

            fetchResumes();

        }
        catch (error) {

            console.log(error);

            alert("Upload Failed");

        }

    };

    const handleDelete = async (id) => {

        try {

            if (!window.confirm("Are you sure you want to delete this resume?")) {

                return;

            }

            await deleteResume(id);

            alert("Resume Deleted Successfully");

            fetchResumes();

        }
        catch (error) {

            console.log(error);

            alert("Delete Failed");

        }

    };

   const handleView = async (id) => {

       try {

           const token = localStorage.getItem("token");

           const response = await axios.get(
               `http://localhost:8080/resumes/view/${id}`,
               {
                   responseType: "blob",
                   headers: {
                       Authorization: `Bearer ${token}`
                   }
               }
           );

           const file = new Blob([response.data], {
               type: "application/pdf"
           });

           const fileURL = URL.createObjectURL(file);

           window.open(fileURL, "_blank");

       }
       catch (error) {

           console.log(error);

           alert("Unable to open resume.");

       }

   };

    return (

        <div className="resumes-container">

            <Navbar />

            <div className="page-header">

                <h1>Resume Manager</h1>

                <p>
                    Upload and manage your resumes for different companies.
                </p>

            </div>

            <div className="upload-card">

                <div className="upload-left">

                    <h3>Upload Resume</h3>

                    <p>
                        Upload your latest resume in PDF format.
                    </p>

                </div>

                <div className="upload-right">

                    <input
                        ref={fileInputRef}
                        type="file"
                        accept=".pdf"
                        onChange={(e) => setSelectedFile(e.target.files[0])}
                    />

                    <button
                        className="upload-btn"
                        onClick={handleUpload}
                    >
                        Upload Resume
                    </button>

                </div>

            </div>

            <table className="resume-table">

                <thead>

                    <tr>

                        <th>File Name</th>
                        <th>Type</th>
                        <th>Upload Date</th>
                        <th>View</th>
                        <th>Delete</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        resumes.map((resume) => (

                            <tr key={resume.id}>

                                <td>{resume.fileName}</td>

                                <td>{resume.fileType}</td>

                                <td>{resume.uploadDate}</td>

                                <td>

                                    <button
                                        className="view-btn"
                                        onClick={() => handleView(resume.id)}
                                    >
                                        View
                                    </button>

                                </td>

                                <td>

                                    <button
                                        className="delete-btn"
                                        onClick={() => handleDelete(resume.id)}
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

export default ResumesPage;