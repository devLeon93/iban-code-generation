import React, {useState, useEffect, useMemo} from "react";

import axios from "axios";
import {Link, useParams} from "react-router-dom";
import {Button, Modal} from "react-bootstrap";
import AuthService from "../services/AuthService";

const BoardAdmin = () => {
    const [users, setUsers] = useState([]);
    const {id} = useParams();

    const [show, setShow] = useState(false);
    const [selectedUser, setSelectedUser] = useState({
        id: 0,
        name:''
    });

    const parseJwtToken = (token) =>  {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
        return JSON.parse(jsonPayload);
    }

    const user = AuthService.getCurrentUser();
    const currentUser = parseJwtToken(user.token);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        const result = await axios.get("http://localhost:8080/api/user");
        setUsers(result.data);
    };

    const deleteUser = async (id) => {
        if (users.find(u => u.id === id).email === currentUser.email) {
            return;
        }

        await axios.delete(`http://localhost:8080/api/user/${id}`);
        await loadUsers();
    };

    const deleteModal = useMemo(() => {
        if(selectedUser.id != 0)
        return ( <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Are you sure?</Modal.Title>
            </Modal.Header>
            <Modal.Body><h6>Would you like to delete user <strong style={{color: "green"}}>{selectedUser.name}</strong> from the list ?</h6></Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Close
                </Button>
                <Button variant="primary" onClick={()=> {deleteUser(selectedUser.id); handleClose();}}>
                    Delete
                </Button>
            </Modal.Footer>
        </Modal>)
    }, [selectedUser, show]);

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
                    Create User
                </Link>

            </div>


            <div className="py-4">
                <table className="table table-stripped">
                    <thead>
                    <tr>
                        <th scope="col" style={{paddingLeft:32}}>Id</th>
                        <th scope="col">Username</th>
                        <th scope="col">Email</th>
                        <th scope="col">Role</th>
                        <th scope="col" style={{textAlign:"right"}}>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users.map(user =>
                        <tr key={user.id}>
                            <th scope="row" style={{paddingLeft:32}}>
                                {user.id}
                            </th>

                            <td>{user.username}</td>
                            <td>{user.email}</td>
                            <td>{user.role.map((item) => {
                                return <p> [{item.name}]</p>
                            })}</td>
                            <td>
                                <div className = "d-flex justify-content-end">
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
                                        className={`btn btn-danger mx-2 ${user.email === currentUser.email ? "d-none" : ""}`}
                                        onClick={() =>{ setSelectedUser({...selectedUser, name: user.username, id: user.id}); handleShow()}}
                                    >
                                        Delete
                                    </button>
                                </div>



                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
            {deleteModal}
        </div>

    );
};
export default BoardAdmin;