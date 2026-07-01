import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import {
    getAllProblems,
    createProblem,
    updateProblem,
    deleteProblem
} from "../services/ProblemService";
import "./ProblemsPage.css";

function ProblemsPage() {

    const [problems, setProblems] = useState([]);

    const [problemName, setProblemName] = useState("");
    const [platform, setPlatform] = useState("");
    const [difficulty, setDifficulty] = useState("");
    const [topic, setTopic] = useState("");
    const [revisionStatus, setRevisionStatus] = useState(false);
    const [notes, setNotes] = useState("");
    const [searchTerm, setSearchTerm] = useState("");

    const [editingId, setEditingId] = useState(null);

    useEffect(() => {

        fetchProblems();

    }, []);

    const fetchProblems = async () => {

        try {

            const response = await getAllProblems();

            console.log(response);

            setProblems(response);

        }
        catch (error) {

            console.log(error);

        }

    };

    const handleCreate = async () => {

        try {

            if (
                !problemName ||
                !platform ||
                !difficulty ||
                !topic
            ) {

                alert("Please fill all required fields");
                return;

            }

            const problemData = {

                problemName,
                platform,
                difficulty,
                topic,
                revisionStatus,
                notes

            };

            if (editingId !== null) {

                await updateProblem(editingId, problemData);

                alert("Problem Updated Successfully");

            }
            else {

                await createProblem(problemData);

                alert("Problem Added Successfully");

            }

            fetchProblems();

            setEditingId(null);

            setProblemName("");
            setPlatform("");
            setDifficulty("");
            setTopic("");
            setRevisionStatus(false);
            setNotes("");

        }
        catch (error) {

            console.log(error);

        }

    };

    const handleEdit = (problem) => {

        setEditingId(problem.id);

        setProblemName(problem.problemName);
        setPlatform(problem.platform);
        setDifficulty(problem.difficulty);
        setTopic(problem.topic);
        setRevisionStatus(problem.revisionStatus);
        setNotes(problem.notes);

    };

    const handleDelete = async (id) => {

        try {

            if (!window.confirm("Are you sure you want to delete this problem?")) {
                return;
            }

            await deleteProblem(id);

            alert("Problem Deleted Successfully");

            fetchProblems();

        }
        catch (error) {

            console.log(error);

        }

    };

    return (

        <div className="problems-container">

            <Navbar />

            <div className="page-header">

                <h1>Problems Tracker</h1>

                <p>
                    Track your coding progress, organize problems by topic and
                    difficulty, and manage revision efficiently.
                </p>


            </div>

            <div className="problem-form">

                <input
                    type="text"
                    placeholder="Problem Name"
                    value={problemName}
                    onChange={(e) => setProblemName(e.target.value)}
                />

                <select
                    value={platform}
                    onChange={(e) => setPlatform(e.target.value)}
                >

                    <option value="">Select Platform</option>
                    <option value="LeetCode">LeetCode</option>
                    <option value="GeeksforGeeks">GeeksforGeeks</option>
                    <option value="Codeforces">Codeforces</option>
                    <option value="CodeChef">CodeChef</option>
                    <option value="HackerRank">HackerRank</option>

                </select>

                <select
                    value={difficulty}
                    onChange={(e) => setDifficulty(e.target.value)}
                >

                    <option value="">Select Difficulty</option>
                    <option value="Easy">Easy</option>
                    <option value="Medium">Medium</option>
                    <option value="Hard">Hard</option>

                </select>

                <select
                    value={topic}
                    onChange={(e) => setTopic(e.target.value)}
                >

                    <option value="">Select Topic</option>
                    <option value="Arrays">Arrays</option>
                    <option value="Strings">Strings</option>
                    <option value="HashMap">HashMap</option>
                    <option value="Linked List">Linked List</option>
                    <option value="Stack">Stack</option>
                    <option value="Queue">Queue</option>
                    <option value="Tree">Tree</option>
                    <option value="BST">BST</option>
                    <option value="Graph">Graph</option>
                    <option value="Heap">Heap</option>
                    <option value="Binary Search">Binary Search</option>
                    <option value="Sliding Window">Sliding Window</option>
                    <option value="Prefix Sum">Prefix Sum</option>
                    <option value="Greedy">Greedy</option>
                    <option value="Dynamic Programming">Dynamic Programming</option>
                    <option value="Backtracking">Backtracking</option>
                    <option value="Trie">Trie</option>
                    <option value="Bit Manipulation">Bit Manipulation</option>
                    <option value="Math">Math</option>

                </select>

                <input
                    type="text"
                    placeholder="Notes"
                    value={notes}
                    onChange={(e) => setNotes(e.target.value)}
                />

                <div className="action-section">

                    <div className="revision-card">

                        <label className="checkbox-container">

                            <input
                                type="checkbox"
                                checked={revisionStatus}
                                onChange={(e) =>
                                    setRevisionStatus(e.target.checked)
                                }
                            />

                            <span>Revision Required</span>

                        </label>

                        <p>
                            Mark this if you want to revise the problem later.
                        </p>

                    </div>

                    <button
                        className="add-btn"
                        onClick={handleCreate}
                    >
                        {editingId ? "Update Problem" : "Add Problem"}
                    </button>

                </div>

            </div>

            <div className="search-section">

                <input
                    type="text"
                    placeholder="🔍 Search Problem..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                />

            </div>

            <table className="problems-table">

                <thead>

                    <tr>

                        <th>Problem</th>
                        <th>Platform</th>
                        <th>Difficulty</th>
                        <th>Topic</th>
                        <th>Revision</th>
                        <th>Edit</th>
                        <th>Delete</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        problems
                        .filter(problem =>
                            problem.problemName
                                .toLowerCase()
                                .includes(searchTerm.toLowerCase())
                        )
                        .map((problem) => (

                            <tr key={problem.id}>

                                <td>{problem.problemName}</td>

                                <td>

                                    <span
                                        className={`platform-badge ${problem.platform
                                            .toLowerCase()
                                            .replace(/\s+/g, "-")}`}
                                    >

                                        {problem.platform}

                                    </span>

                                </td>

                                <td>

                                    <span
                                        className={`difficulty-badge ${problem.difficulty.toLowerCase()}`}
                                    >

                                        {problem.difficulty}

                                    </span>

                                </td>

                                <td>

                                    <span
                                        className={`topic-badge ${problem.topic
                                            .toLowerCase()
                                            .replace(/\s+/g, "-")}`}
                                    >

                                        {problem.topic}

                                    </span>

                                </td>

                                <td>

                                    <span
                                        className={`revision-badge ${
                                            problem.revisionStatus ? "required" : "completed"
                                        }`}
                                    >
                                        {problem.revisionStatus ? "Required" : "Completed"}
                                    </span>

                                </td>

                                <td>

                                    <button
                                        className="edit-btn"
                                        onClick={() => handleEdit(problem)}
                                    >
                                        Edit
                                    </button>

                                </td>

                                <td>

                                    <button
                                        className="delete-btn"
                                        onClick={() => handleDelete(problem.id)}
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

export default ProblemsPage;