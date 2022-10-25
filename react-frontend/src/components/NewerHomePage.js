import React, {Component} from 'react';
import { Route, withRouter } from 'react-router-dom';
import '../Login.css';
import FileUpload from "./FileUpload";
import Container from "./Container";
import Group from "./Group";
import UserDetails from "./UserDetails";
import UserLog from "./UserLog";


class NewerHomePage extends Component {

    render() {
        return (
            <div>
                <Route exact path="/" render={() => (
                    <div className="jumbotron">
                       <div className="row justify-content-md-center">
                            <div className="col-md-6">

                                <br/><br/><br/>

                            </div>
                            <div className="col-md-4">
                                <Container/>
                            </div>
                       </div>
                    </div>
                )}/>


                <Route exact path="/files" render={() => (

                    <FileUpload/>
                )}/>

                <Route exact path="/userdetails" render={() => (


                    <UserDetails/>
                )}/>

                <Route exact path="/userlog" render={() => (


                    <UserLog/>
                )}/>

                <Route exact path="/groups" render={() => (


                    <Group/>
                )}/>

            </div>

        );

    }
}

export default withRouter(NewerHomePage);