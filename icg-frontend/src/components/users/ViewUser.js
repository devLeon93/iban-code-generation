import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewUser() {
    const [user, setUser] = useState({
        username: "",
        email: "",
        role:[]
    });

    const { id } = useParams();

    useEffect(() => {
        loadUser();
    }, []);

    const loadUser = async () => {
        const result = await axios.get(`http://localhost:8080/api/user/${id}`);
        setUser(result.data);
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-4 offset-md-4 border rounded p-4 mt-5 shadow">
                    <h2 className="text-center m-4">User Details</h2>

                    <div className="card">
                        <div className="card-header">
                            <h5>Details of user id : {user.id}</h5>
                            <ul className="list-group list-group">
                                <li className="list-group-item">
                                    <b>Username: </b>
                                    {user.username}
                                </li>
                                <li className="list-group-item">
                                    <b>Email: </b>
                                    {user.email}
                                </li>
                                <li className="list-group-item">
                                    <b>Role: </b>
                                    {user.role.map(role=>role.name).join(" , ")}
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div className="d-flex justify-content-center">
                        <Link className="btn btn-primary  d-flex justify-content-center" to={"/admin"} >
                            Back to Admin Panel
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}
