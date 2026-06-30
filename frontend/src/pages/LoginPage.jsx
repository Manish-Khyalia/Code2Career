import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../services/AuthService";

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
        <div>

            <h1>Login Page</h1>

            <input
                type="text"
                placeholder="Enter email"
                value={email}
                onChange={(e) =>
                    setEmail(e.target.value)
                }
            />

            <br /><br />

            <input
                type="password"
                placeholder="Enter password"
                value={password}
                onChange={(e) =>
                    setPassword(e.target.value)
                }
            />

            <br /><br />

            <button onClick={handleLogin}>
                Login
            </button>

        </div>
    );
}

export default LoginPage;