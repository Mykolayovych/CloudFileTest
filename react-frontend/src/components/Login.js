import React, {Component} from 'react';
import '../Login.css';


class Login extends Component {

    state = {
        email: '',
        password: ''
    };

    render() {
        return (
            <div>
                <input type="email" className="form-control" placeholder="Email" required autoFocus
                       onChange={(event) => {
                           this.setState({
                               email: event.target.value
                           });
                       }}/>
                <br/>
                <input type="password" className="form-control" placeholder="Password" required
                       onChange={(event) => {
                           this.setState({
                               password: event.target.value
                           });
                       }}/>

                <br/>
                <button className="btn btn-primary btn-block" type="submit"
                        onClick={() => this.props.login(this.state)}>
                    Sign in
                </button>
                <br/>

                <a href="" className="pull-right need-help"
                >Need help? </a><span className="clearfix"></span>
                <a href="#" className="text-center new-account" onClick={() => this.props.loginOrSignup("SU")}>Create New User</a>


            </div>
        );
    }
}

export default Login;