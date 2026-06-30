import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const getAllProblems = async () => {

    const token = localStorage.getItem("token");

    const response = await axios.get(
        `${BASE_URL}/problems`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const createProblem = async (problemData) => {

    const token = localStorage.getItem("token");

    const response = await axios.post(
        `${BASE_URL}/problems`,
        problemData,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const updateProblem = async (id, problemData) => {

    const token = localStorage.getItem("token");

    const response = await axios.put(
        `${BASE_URL}/problems/${id}`,
        problemData,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};

export const deleteProblem = async (id) => {

    const token = localStorage.getItem("token");

    await axios.delete(
        `${BASE_URL}/problems/${id}`,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

};