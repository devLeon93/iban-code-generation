import React, { useState, useEffect } from "react";
import logo from './logo.svg';
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import AuthService from "./services/AuthService";
import Login from "./components/Login";
import Profile from "./components/Profile";
import BoardAdmin from "./components/BoardAdmin";

import EventBus from "./common/EventBus";
import {Link} from "react-router-dom";
import {Route, Routes} from "react-router-dom";

const App = () => {
    const [showAdminBoard, setShowAdminBoard] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined);

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentUser(user);
            setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
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
                    <img src={logo} className="App-logo" alt="logo" />
                    IBAN Code Generator
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
                                {currentUser.username}
                            </Link>
                        </li>
                        <li className="nav-item ">
                            <a href="/login" className="nav-link" onClick={logOut}>
                                LogOut
                            </a>
                        </li>
                    </div>
                ) : (
                    <div className="navbar-nav ml-auto ">
                        <li className="nav-item">
                            <Link to={"/login"} className="nav-link">
                                <button type="button" className="btn btn-success w-150">Login</button>
                            </Link>
                        </li>
                    </div>
                )}
            </nav>

            <div className="container mt-3">
                <Routes>
                    <Route path="/" element={<Login/>} />
                    <Route path="/login" element={<Login/>} />
                    <Route path="/profile" element={<Profile/>} />
                    <Route path="/admin" element={<BoardAdmin/>} />
                </Routes>
            </div>
        </div>
    );
};

export default App;
