import React, { useState, useEffect } from "react";
import logo from './logo.svg';
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import AuthService from "./services/AuthService";
import Login from "./components/Login";
import Home from "./components/Home";
import Profile from "./components/Profile";
import BoardUser from "./components/BoardUser";
import BoardModerator from "./components/BoardModerator";
import BoardAdmin from "./components/BoardAdmin";

import EventBus from "./common/EventBus";
import {Link} from "react-router-dom";
import {Route, Routes} from "react-router";

const App = () => {
    const [showModeratorBoard, setShowModeratorBoard] = useState(false);
    const [showAdminBoard, setShowAdminBoard] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined);

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentUser(user);
            setShowModeratorBoard(user.roles.includes("ROLE_OPERATOR_RAION"));
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
        setShowModeratorBoard(false);
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
                    <li className="nav-item">
                        <Link to={"/home"} className="nav-link">
                            Home
                        </Link>
                    </li>

                    {showModeratorBoard && (
                        <li className="nav-item ">
                            <Link to={"/operator-raion"} className="nav-link">
                                Operator - Raion
                            </Link>
                        </li>
                    )}

                    {showAdminBoard && (
                        <li className="nav-item ">
                            <Link to={"/admin"} className="nav-link">
                                Admin Panel
                            </Link>
                        </li>
                    )}

                    {currentUser && (
                        <li className="nav-item  ">
                            <Link to={"/operator"} className="nav-link">
                                Operator
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
                    <Route path="/" element={<Home/>} />
                    <Route path="/home" element={<Home/>} />
                    <Route path="/login" element={<Login/>} />
                    <Route path="/profile" element={<Profile/>} />
                    <Route path="/operator" element={<BoardUser/>} />
                    <Route path="/operator-raion" element={<BoardModerator/>} />
                    <Route path="/admin" element={<BoardAdmin/>} />
                </Routes>
            </div>

        </div>
    );
};

export default App;
