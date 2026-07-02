import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const generateQuestions = async (company, role, experience) => {

    const token = localStorage.getItem("token");

    const response = await axios.get(
        `${BASE_URL}/ai/interview`,
        {
            params: {
                company,
                role,
                experience
            },
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};