import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_URL;

export const getAllApplications = async () => {

    const token = localStorage.getItem("token");

    const response = await axios.get(
        `${BASE_URL}/applications`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const createApplication = async (applicationData) => {

    const token = localStorage.getItem("token");

    const response = await axios.post(
        `${BASE_URL}/applications`,
        applicationData,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const updateApplication = async (id, applicationData) => {

    const token = localStorage.getItem("token");

    const response = await axios.put(
        `${BASE_URL}/applications/${id}`,
        applicationData,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const deleteApplication = async (id) => {

    const token = localStorage.getItem("token");

    await axios.delete(
        `${BASE_URL}/applications/${id}`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

};