import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_URL;

export const login = async (loginData) => {

    const response = await axios.post(
        `${BASE_URL}/auth/login`,
        loginData
    );

    return response.data;
};

export const register = async (registerData) => {

    const response = await axios.post(
        `${BASE_URL}/auth/register`,
        registerData
    );

    return response.data;
};