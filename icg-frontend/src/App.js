import React, {useState, useEffect} from "react";
import logo from './logo.svg';
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import AuthService from "./services/AuthService";
import Login from "./components/Login";
import Profile from "./components/Profile";
import BoardAdmin from "./components/BoardAdmin";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";


import EventBus from "./common/EventBus";
import {Link} from "react-router-dom";
import {Route, Routes} from "react-router-dom";
import {faSignInAlt, faSignOutAlt} from "@fortawesome/free-solid-svg-icons";
import AddUser from "./components/users/AddUser";
import EditUser from "./components/users/EditUser";
import ViewUser from "./components/users/ViewUser";

import {ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const App = () => {
    const [showAdminBoard, setShowAdminBoard] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined);


    const parseJwtToken = (token) => {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
        return JSON.parse(jsonPayload);
    }

    useEffect(() => {
        const user = AuthService.getCurrentUser();
        if (user) {
            const userDecode = parseJwtToken(user.token);
            setCurrentUser(userDecode);
            setShowAdminBoard(userDecode.role
                .map(r => r.authority)
                .includes("ROLE_ADMIN"));
        }

        EventBus.on("logout", () => {
            logOut();
        });

        return () => {
            EventBus.remove("logout");
        };
    }, []);

    const logOut = () => {
        AuthService.logout();
        setShowAdminBoard(false);
        setCurrentUser(undefined);
    };

    return (
        <div>
            <nav className="navbar navbar-expand navbar-dark bg-dark p-2 align-items-center">
                <Link to={"/"} className="navbar-brand">
                    <img src="https://cdn-icons-png.flaticon.com/512/4930/4930607.png" className="App-logo"/>
                    <span style={{marginLeft: 15}}> IBAN Code </span>
                </Link>
                <div className="navbar-nav mr-auto d-flex w-100 justify-content-lg-start ">

                    {showAdminBoard && (
                        <li className="nav-item ">
                            <Link to={"/admin"} className="nav-link">
                                Admin Panel
                            </Link>
                        </li>
                    )}
                </div>

                {currentUser ? (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link to={"/profile"} className="nav-link">
                                {currentUser.email}
                            </Link>
                        </li>
                        <li className="nav-item ">
                            <Link to={"/login"} className="nav-link" onClick={logOut}>
                                <FontAwesomeIcon icon={faSignOutAlt}/>
                            </Link>
                        </li>
                    </div>
                ) : (
                    <div className="navbar-nav ml-auto ">
                        <li className="nav-item">
                            <Link to={"/login"} className="nav-link">
                                <FontAwesomeIcon icon={faSignInAlt}/>
                            </Link>
                        </li>
                    </div>
                )}
            </nav>

            <div className="container mt-3">
                <Routes>
                    <Route path="/" element={<Profile/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/profile" element={<Profile/>}/>
                    <Route path="/admin" element={<BoardAdmin/>}/>
                    <Route exact path="/adduser" element={<AddUser/>}/>
                    <Route exact path="/edituser/:id" element={<EditUser/>}/>
                    <Route exact path="/viewuser/:id" element={<ViewUser/>}/>
                </Routes>
            </div>
            {

            }
            <ToastContainer/>
        </div>
    );
};

export default App;
