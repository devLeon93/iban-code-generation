import axios from "axios";
import authHeader from "./AuthHeader";

const API_URL = "http://localhost:8080/api/test/";

const getPublicContent = () => {
    return axios.get(API_URL + "all");
};

const getUserBoard = () => {
    return axios.get(API_URL + "operator", { headers: authHeader() });
};

const getModeratorBoard = () => {
    return axios.get(API_URL + "operator-raion", { headers: authHeader() });
};

const getAdminBoard = () => {
    return axios.get(API_URL + "admin", { headers: authHeader() });
};

const UserService = {
    getPublicContent,
    getUserBoard,
    getModeratorBoard,
    getAdminBoard,
};

export default UserService;
