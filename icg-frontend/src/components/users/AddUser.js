import axios from "axios";
import React, {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import Select from "react-select";

export default function AddUser() {
    let navigate = useNavigate();

    const [user, setUser] = useState({
        username: "",
        email: "",
        password: "",
        role: [],
    });

    const {username, email, password} = user;


    const [role, setRole] = useState(null);
    const [roleSelect, setRoleSelect] = useState(null);


    const getRoles = () => {
        axios.get("http://localhost:8080/api/user/roles")
            .then((response) => {

                console.log(response.log);
                setRole(response.data.map(item => ({
                        value: item.name,
                    }))
                        .map(item => ({
                            value: item.name,
                            label: item.name
                        }))
                        .sort((a, b) => a.value.localeCompare(b.value))
                )
            }).catch((error) => {
            console.log(error)
        })
    }

    const onInputChange = (e) => {
        setUser({...user, [e.target.name]: e.target.value});
    };

    const onSubmit = async (e) => {
        e.preventDefault();


        console.log(user)
        await axios.post("http://localhost:8080/api/user", user);
        navigate("/admin");
    };

    useEffect(() => {
        axios.get("http://localhost:8080/api/user/roles")
            .then((response) => {
                console.log("response ", response.data);
                setRole(response.data
                    .map(item => ({
                        value: item.name,
                        label: item.name
                    })));
            }).catch((error) => {
            console.log(error)
        })
    }, [])


    return (
        <div className="container">
            <div className="row">
                <div className="col-md-4 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Register User</h2>

                    <form onSubmit={(e) => onSubmit(e)}>

                        <div className="mb-3">
                            <label htmlFor="Username" className="form-label">
                                Username:
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your username"
                                name="username"
                                value={username}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Email" className="form-label">
                                E-mail:
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your e-mail address"
                                name="email"
                                value={email}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="codulEco" className="form-label">Roles:</label>
                            <Select className="w-100"
                                    isMulti
                                    options={role}
                                    onChange={(e) => {
                                        console.log(e);
                                        user.role = e.value;
                                        let roles = [];
                                        e.forEach((item) => {
                                            roles.push(item.label);
                                        })
                                        setUser({...user, role: roles})
                                        setRoleSelect(e.value)}
                                    }/>
                        </div>


                        <div className="form-group">
                            <label htmlFor="password">Password:</label>
                            <input
                                style={{marginBottom:20}}
                                type="password"
                                name="password"
                                className="form-control"
                                placeholder="password"
                                value={user.password}
                                onChange={(e) => onInputChange(e)}
                                required
                            />
                            <div className="invalid-feedback">
                                Password is required.
                            </div>
                        </div>


                        <button type="submit" className="btn btn-primary">
                            Submit
                        </button>
                        <Link className="btn btn-danger mx-2" to="/admin">
                            Cancel
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}
