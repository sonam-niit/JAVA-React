import React from 'react'
import { useState,useEffect } from 'react'
import UserService from '../services/userservice';

export default function UserList() {

    const [users,setUsers]=useState([]);
    useEffect(() => {
        retrieveUsers();
      }, []);
    const retrieveUsers = () => {
        UserService.getAll()
          .then(response => {
            setUsers(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      };
  return (
    <div>
        <h4>Users List</h4>
        <table className="table table-bordered table-striped">
           <thead> <tr><th>Id</th><th>Name</th><th>Email</th><th>Country</th></tr></thead>
           <tbody>
          {users &&
            users.map((user, index) => (
              <tr>
                <td>{user.id}</td>
                <td>{user.name}</td>
                <td>{user.email}</td> 
                <td>{user.country}</td>
              </tr>
            ))}
        </tbody>
        </table>
    </div>
  )
}
