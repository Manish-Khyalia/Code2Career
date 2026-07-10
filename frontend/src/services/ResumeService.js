import axios from "axios";

export const BASE_URL = "https://code2career-production.up.railway.app";

export const getAllResumes = async () => {

    const token = localStorage.getItem("token");

    const response = await axios.get(
        `${BASE_URL}/resumes`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;

};

export const uploadResume = async (file) => {

    const token = localStorage.getItem("token");

    const formData = new FormData();

    formData.append("file", file);

    const response = await axios.post(
        `${BASE_URL}/resumes`,
        formData,
        {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "multipart/form-data"
            }
        }
    );

    return response.data;

};

export const deleteResume = async (id) => {

    const token = localStorage.getItem("token");

    await axios.delete(
        `${BASE_URL}/resumes/${id}`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

};
