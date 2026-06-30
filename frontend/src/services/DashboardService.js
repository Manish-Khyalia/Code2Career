import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const getDashboard = async () => {

    const token = localStorage.getItem("token");

    const response = await axios.get(
        `${BASE_URL}/dashboard`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};