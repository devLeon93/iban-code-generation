import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import Select from "react-select";
import { toast } from 'react-toastify';

export default function EditUser(props) {

    console.table(">>>>> EditUser", props)
    const [role, setRole] = useState(null);
    const [roleSelect, setRoleSelect] = useState(null);

    let navigate = useNavigate();

    const { id } = useParams();

    const [user, setUser] = useState(null);

    const onInputChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
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

    useEffect(() => {
        loadUser();
    }, []);

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.put(`http://localhost:8080/api/user/${id}`, user)
        navigate("/admin");
    };

    const loadUser = async () => {
        axios.get(`http://localhost:8080/api/user/${id}`)
            .then(res=>{
                setUser(res.data);
                toast.success(res)
            })
            .catch(err=>{
                toast.error(err)
            });
    };
    console.log(user);

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Edit User</h2>

                    {user && <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Username" className="form-label">
                                Username
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your username"
                                name="username"
                                value={user.username}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Email" className="form-label">
                                E-mail
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your e-mail address"
                                name="email"
                                value={user.email}
                                onChange={(e) => {onInputChange(e)}}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="codulEco" className="form-label">Roles:</label>

{/*                            {
                                user && console.log(user.role[0])
                            }*/}
                            <Select className="w-100"
                                    isMulti
                                    options={role}
                                    defaultValue={() => {
                                        const userRoles = [];
                                        user.role.map(r => {
                                            return userRoles.push({
                                                value: r.name,
                                                label: r.name
                                            });
                                        });
                                        return userRoles;
                                    }
                                    }
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

                        <button type="submit" className="btn btn-outline-primary">
                            Submit
                        </button>
                        <Link className="btn btn-outline-danger mx-2" to="/admin">
                            Cancel
                        </Link>
                    </form>}
                </div>
            </div>
        </div>
    );
}
