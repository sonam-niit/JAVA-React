import './App.css';
import { BrowserRouter as Router, Route, Link, Switch } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import UserList from './compo/userlist';
import AddUser from './compo/adduser';
import EditUser from './compo/edituser';
import UserDetail from './compo/userdetail';

function App() {
  return (  
    <div className="App">  
     <Router>    
      <div className="container">    
        <nav className="btn btn-warning navbar navbar-expand-lg navheader">    
          <div className="collapse navbar-collapse" >    
            <ul className="navbar-nav mr-auto">    
              <li className="nav-item">    
                <Link to={'/Createuser'} className="nav-link">Add User</Link>    
              </li>    
              <li className="nav-item">    
                <Link to={'/UserList'} className="nav-link">User List</Link>    
              </li>    
            </ul>    
          </div>    
        </nav> <br />    
        <Switch>    
          <Route exact path='/Createuser' component={AddUser} />    
          <Route path='/edit/:id' component={EditUser} />    
          <Route exact path='/UserList' component={UserList} /> 
          <Route path='/view/:id' component={UserDetail} />    
        </Switch>    
      </div>    
    </Router>    
    </div>  
  );  
}

export default App;
