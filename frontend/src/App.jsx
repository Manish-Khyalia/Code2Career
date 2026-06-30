import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import DashboardPage from "./pages/DashboardPage";
import JobsPage from "./pages/JobsPage";
import ApplicationsPage from "./pages/ApplicationsPage";
import ProblemsPage from "./pages/ProblemsPage";
import ResumesPage from "./pages/ResumesPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/jobs" element={<JobsPage />} />

        <Route path="/applications" element={<ApplicationsPage />} />

        <Route path="/problems" element={<ProblemsPage />} />

        <Route path="/resumes" element={<ResumesPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;