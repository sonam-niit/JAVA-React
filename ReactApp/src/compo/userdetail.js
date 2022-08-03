import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useHistory, useParams } from 'react-router-dom'

export default function UserDetail() {
    const [user, setUser] = useState({ id: '', name: '', email: '', password: '', country: '' });

    const params= useParams();
    const Url = 'http://localhost:8081/api/users/' + params.id;  
    const history= useHistory();
    useEffect(() => {
        const GetData = async () => {
            const result = await axios(Url);
            setUser(result.data);
        };
        GetData();
    }, []);


  return (
    <div>
        <button className='btn btn-secondary' onClick={history.goBack}>go back</button>
        <h1>User Details</h1>
        <ul className='list-group'>
            <li className='list-group-item'>Name: {user.name}</li>
            <li className='list-group-item'>Email: {user.email}</li>
            <li className='list-group-item'>Password: {user.password}</li>
            <li className='list-group-item'>Country: {user.country}</li>
        </ul>
    </div>
  )
}
