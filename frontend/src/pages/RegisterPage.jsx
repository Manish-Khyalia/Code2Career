import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../services/AuthService";
import "./LoginPage.css";

function RegisterPage() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: ""
    });

    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });

    };

    const handleRegister = async () => {

        try {

            setLoading(true);

            await register(formData);

            alert("Registration Successful!");

            navigate("/");

        } catch (error) {

              console.log(error);

              alert(
                  JSON.stringify(error.response?.data) ||
                  error.message
              );

          } finally {

            setLoading(false);

        }

    };

    return (

        <div className="login-container">

            <div className="login-card">

                <h1>Code2Career</h1>

                <p className="subtitle">
                    Create your account
                </p>

                <h2>Register</h2>

                <input
                    type="text"
                    name="name"
                    placeholder="Enter Name"
                    value={formData.name}
                    onChange={handleChange}
                />

                <input
                    type="email"
                    name="email"
                    placeholder="Enter Email"
                    value={formData.email}
                    onChange={handleChange}
                />

                <input
                    type="password"
                    name="password"
                    placeholder="Enter Password"
                    value={formData.password}
                    onChange={handleChange}
                />

                <button
                    onClick={handleRegister}
                    disabled={loading}
                >
                    {
                        loading
                            ? "Registering..."
                            : "Register"
                    }
                </button>

                <p className="register-text">

                    Already have an account?

                    <span onClick={() => navigate("/")}>
                        Login
                    </span>

                </p>

            </div>

        </div>

    );

}

export default RegisterPage;