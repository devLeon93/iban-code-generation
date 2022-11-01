import store from '../store/Index';
import axios from 'axios';
import { clearCurrentUser } from '../store/actions/UserAction';



export const authHeader = () => {
    const currentUser = store.getState().user;

    return {
        'Content-Type': 'application/json',
        'authorization': 'Bearer ' + currentUser?.token,
    };
};

export function handleResponseWithLoginCheck() {
    axios.interceptors.response.use(
        response => response,
        error => {
            const currentUser = store.getState().user;
            const isLoggedIn = currentUser?.token;
            const status = error?.response?.status;

            if (isLoggedIn && [401,403].includes(status)) {
                store.dispatch(clearCurrentUser());

            }

            return Promise.reject(error);
        }
    );
};
