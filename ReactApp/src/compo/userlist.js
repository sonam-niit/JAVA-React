import axios from 'axios';
import React, { useEffect, useState } from 'react'

export default function UserList(props) {

    const [users, setUsers] = useState([]);

    const deleteuser = (id) => {
        axios.delete('http://localhost:8081/api/users/' + id)
            .then((result) => {
                props.history.push('/UserList')
            });
    };
    const edituser = (id) => {
        props.history.push({
            pathname: '/edit/' + id
        });
    };
    const viewuser = (id) => {
        props.history.push({
            pathname: '/view/' + id
        });
    };

    const tabRow = users.map((user, i) => {
        return (
            <tr key={i}>
                <td>{user.id}</td>
                <td>{user.name}</td>
                <td>{user.email}</td>
                <td>  <div className="btn-group">
                    <button className="btn btn-warning" onClick={() => { viewuser(user.id) }}>View</button>
                    <button className="btn btn-warning" onClick={() => { edituser(user.id) }}>Edit</button>
                    <button className="btn btn-warning" onClick={() => { deleteuser(user.id) }}>Delete</button>
                </div>
                </td>
            </tr>
        );
    })
    useEffect(() => {
        axios.get("http://localhost:8081/api/users")
            .then(response => { setUsers(response.data) })
            .catch(error => { console.log(error); setUsers([]) })
    }, [users]);
    return (
        <div>
            <h1>User Data</h1>
            <table className="table table-bordered table-striped">
                <thead><tr><th>Id</th><th>Name</th><th>Email</th><th>Operations</th></tr></thead>
                <tbody>
                    {tabRow}
                </tbody>
            </table>
        </div>
    )
}
