import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../services/AuthService";
import "./LoginPage.css";

function LoginPage() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {

        try {

            const response = await login({
                email,
                password
            });

            console.log(response);

            localStorage.setItem(
                "token",
                response.token
            );

            navigate("/dashboard");

        } catch (error) {

            alert("Invalid email or password");
        }
    };

    return (

        <div className="login-container">

            <div className="login-card">

                <h1>Code2Career</h1>

                <p className="subtitle">
                    Track Your Placement Journey in One Place
                </p>

                <h2>Welcome Back</h2>

                <input
                    type="email"
                    placeholder="Enter Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />

                <input
                    type="password"
                    placeholder="Enter Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button onClick={handleLogin}>
                    Login
                </button>

                <p className="register-text">

                    Don't have an account?

                    <span onClick={() => navigate("/register")}>
                        Register
                    </span>

                </p>

            </div>

        </div>

    );
}

export default LoginPage;