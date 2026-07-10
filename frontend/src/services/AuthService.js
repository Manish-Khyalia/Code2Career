import axios from "axios";

const BASE_URL = "https://code2career-production.up.railway.app";

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