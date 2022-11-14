import React, {useState, useEffect} from "react";

import axios from "axios";
import {Link, useParams} from "react-router-dom";

const BoardAdmin = () => {

    const [users, setUsers] = useState([]);
    const {id} = useParams();

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        const result = await axios.get("http://localhost:8080/api/user");
        setUsers(result.data);
    };

    const deleteUser = async (id) => {
        await axios.delete(`http://localhost:8080/api/user/${id}`);
        await loadUsers();
    };

    return (

        <div className="container">
            <div>
                <button
                    className="navbar-toggler "
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>

                <Link className="btn btn-primary" to="/adduser">
                    Add User
                </Link>

            </div>


            <div className="py-4">
                <table className="table shadow">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Username</th>
                        <th scope="col">Email</th>
                        <th scope="col">Role</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users.map((user, index) => (
                        <tr>
                            <th scope="row" key={index}>
                                {index + 1}
                            </th>

                            <td>{user.username}</td>
                            <td>{user.email}</td>
                            <td>{user.role.map((item) => {
                                return <p> [{item.name}]</p>
                            })}</td>
                            <td>
                                <Link
                                    className="btn btn-primary mx-2"
                                    to={`/viewuser/${user.id}`}

                                >
                                    View
                                </Link>
                                <Link
                                    className="btn btn-primary mx-2"
                                    to={{
                                        pathname: `/edituser/${user.id}`,
                                        state: { currentUser: user }
                                    }}
                                >
                                    Edit
                                </Link>
                                <button
                                    className="btn btn-danger mx-2"
                                    onClick={() => deleteUser(user.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};
export default BoardAdmin;