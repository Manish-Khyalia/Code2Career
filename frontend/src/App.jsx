import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import DashboardPage from "./pages/DashboardPage";
import JobsPage from "./pages/JobsPage";
import ApplicationsPage from "./pages/ApplicationsPage";
import ProblemsPage from "./pages/ProblemsPage";
import ResumesPage from "./pages/ResumesPage";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {

    return (

        <BrowserRouter>

            <Routes>

                <Route
                    path="/"
                    element={<LoginPage />}
                />

                <Route
                    path="/register"
                    element={<RegisterPage />}
                />

                <Route
                    path="/dashboard"
                    element={
                        <ProtectedRoute>
                            <DashboardPage />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/jobs"
                    element={
                        <ProtectedRoute>
                            <JobsPage />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/applications"
                    element={
                        <ProtectedRoute>
                            <ApplicationsPage />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/problems"
                    element={
                        <ProtectedRoute>
                            <ProblemsPage />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/resumes"
                    element={
                        <ProtectedRoute>
                            <ResumesPage />
                        </ProtectedRoute>
                    }
                />

            </Routes>

        </BrowserRouter>

    );

}

export default App;