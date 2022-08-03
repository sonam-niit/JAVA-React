import React, { useState } from 'react'
import UserService from '../services/userservice';
export default function AddUser() {

    const initial={ id: null, name: '', email: '', country: '' };
    const [user, setUser] = useState(initial);
    const [submitted, setSubmitted] = useState(false);
    const newUser = () => {
        setUser(initial);
        setSubmitted(false);
    };
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUser({ ...user, [name]: value });
    }
    const saveUser = () => {
        var data = {
            name: user.name,
            email: user.email,
            country: user.country
        };
        UserService.create(data)
            .then(response => {
                setSubmitted(true);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>You submitted successfully!</h4>
                    <button className="btn btn-success" onClick={newUser}>
                        Add
                    </button>
                </div>
            ) : (
                <div>
                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            required
                            value={user.name}
                            onChange={handleInputChange}
                            name="name"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email Id</label>
                        <input
                            type="text"
                            className="form-control"
                            id="email"
                            required
                            value={user.email}
                            onChange={handleInputChange}
                            name="email"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="country">Country</label>
                        <input
                            type="text"
                            className="form-control"
                            id="country"
                            required
                            value={user.country}
                            onChange={handleInputChange}
                            name="country"
                        />
                    </div>
                    <button onClick={saveUser} className="btn btn-success">
                        Submit
                    </button>
                </div>
            )}
        </div>
    )
}
