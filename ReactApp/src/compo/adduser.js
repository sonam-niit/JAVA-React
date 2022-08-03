import axios from 'axios';
import React, { useState } from 'react'

export default function AddUser(props) {

    const [inputField, setInputField] = useState({
        name: '',
        email: '',
        password: '',
        country: ''
    })

    const inputsHandler = (e) => {
        setInputField((inputField) => ({
            ...inputField,
            [e.target.name]: e.target.value,
        }));
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        let newUser = inputField;
        axios.post('http://localhost:8081/api/users', newUser)
            .then(resp => props.history.push('/UserList'))
            .catch(err => console.log("error", err));
    }

    return (
        <div className='container'>
            <h1>Add Customer</h1>
            <div className='row'>
                <div className='col-md-6 offset-3'>
                    <form onSubmit={handleSubmit} className="form">
                        <label>Name</label>
                        <input type='text' name='name' className='form-control'
                            value={inputField.name || ''} onChange={inputsHandler} />
                        <label>Email</label>
                        <input type='text' name='email' className='form-control'
                            value={inputField.email || ''} onChange={inputsHandler} />
                        <label>Password</label>
                        <input type='password' name='password' className='form-control'
                            value={inputField.password || ''} onChange={inputsHandler} />
                        <label>Country</label>
                        <input type='text' name='country' className='form-control'
                            value={inputField.country || ''} onChange={inputsHandler} />
                        <input type='submit' value='Submit Data' />
                    </form>
                </div>
            </div>
        </div>
    )
}
