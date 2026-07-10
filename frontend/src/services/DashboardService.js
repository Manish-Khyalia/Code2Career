import axios from "axios";

export const BASE_URL = "https://code2career-production.up.railway.app";

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