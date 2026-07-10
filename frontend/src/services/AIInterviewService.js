import axios from "axios";

export const BASE_URL = "https://code2career-production.up.railway.app";

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