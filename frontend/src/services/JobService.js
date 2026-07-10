import axios from "axios";

export const BASE_URL = "https://code2career-production.up.railway.app";

export const getAllJobs = async () => {

    const token = localStorage.getItem("token");

    const response = await axios.get(
        `${BASE_URL}/jobs`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const deleteJob = async (id) => {

    const token = localStorage.getItem("token");

    await axios.delete(
        `${BASE_URL}/jobs/${id}`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

};

export const createJob = async (jobData) => {

    const token = localStorage.getItem("token");

    const response = await axios.post(
        `${BASE_URL}/jobs`,
        jobData,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;

};

export const updateJob = async (id, jobData) => {

    const token = localStorage.getItem("token");

    const response = await axios.put(

        `${BASE_URL}/jobs/${id}`,
        jobData,

        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }

    );

    return response.data;

};