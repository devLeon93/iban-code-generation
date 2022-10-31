import logo from "../logo.svg";
import {NavLink} from "react-router-dom";

const Navbar = () =>{
    return (
        <nav className="navbar navbar-expand navbar-dark bg-dark">
            <a href="https://mf.gov.md/ro/iban" className="navbar-brand ms-1">
                <img src={logo} className="App-logo" alt="logo" />
                Generating IBAN code for receipts
            </a>

            <div className="navbar-nav me-auto">
                <li className="nav-item">
                    <NavLink to="/admin" className="nav-link">
                        Admin Dashboard
                    </NavLink>
                </li>

                <li className="nav-item">
                    <NavLink to="/home" className="nav-link">
                        Home
                    </NavLink>
                </li>
            </div>

            <div className="navbar-nav ms-auto">
                <li className="nav-item">
                    <NavLink to="/register" className="nav-link">
                        Sign Up
                    </NavLink>
                </li>

                <li className="nav-item">
                    <NavLink to="/login" className="nav-link">
                        Sign In
                    </NavLink>
                </li>
            </div>

        </nav>
    )

};
export {Navbar};