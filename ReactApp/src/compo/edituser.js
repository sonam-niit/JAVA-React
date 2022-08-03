import axios from 'axios';
import { useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';
export default function EditUser(props) {

    const history= useHistory();
    const [user, setUser] = useState({ id: '', name: '', email: '', password: '', country: '' });

    const params= useParams();
    const Url = 'http://localhost:8081/api/users/' + params.id;  
    const inputsHandler = (e) => {
        setUser((user) => ({
            ...user,
            [e.target.name]: e.target.value,
        }));
    }
    useEffect(() => {
        const GetData = async () => {
            const result = await axios(Url);
            setUser(result.data);
        };
        GetData();
    }, []);

    const UpdateUser = (e) => {  
                  e.preventDefault();  
                  axios.put(Url, user)  
                    .then((result) => {  
                      props.history.push('/UserList')  
                    });  
                };  

    return (
        <div>
            <button className='btn btn-secondary' onClick={history.goBack}>go back</button>
            <h1>EditUser</h1>
            <div className='row'>
                <div className='col-md-6 offset-3'>
                    <form onSubmit={UpdateUser} className="form">
                        <label>Name</label>
                        <input type='text' name='name' className='form-control'
                            value={user.name} onChange={inputsHandler} />
                        <label>Email</label>
                        <input type='text' name='email' className='form-control'
                            value={user.email} onChange={inputsHandler} />
                        <label>Password</label>
                        <input type='password' name='password' className='form-control'
                            value={user.password} onChange={inputsHandler} />
                        <label>Country</label>
                        <input type='text' name='country' className='form-control'
                            value={user.country} onChange={inputsHandler} />
                        <input type='submit' value='Submit Data' />
                    </form>
                </div>
            </div>
        </div>
    )
}
