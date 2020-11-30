import React, { Component } from "react";
import { connect } from "react-redux";


export class User extends Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
    }

    render() {
        return (


            <>
                {
                    this.props.user.avatar_url ? 
                     <div>
                        <section id="team" class="pb-5">
                            <div class="container" >
                                <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                                    <div class="mainflip">
                                        <div class="frontside">
                                            <div class="card">
                                                <div class="card-body text-center">
                                                    <p><img class=" img-fluid" src={this.props.user.avatar_url} alt="card image" /></p>
                                                    <h4 class="card-title">{this.props.user.name}</h4>
                                                    <p class="card-text">{this.props.user.bio}</p>
                                                    <a href={this.props.user.html_url} target="blanck" class="btn btn-danger btn-sm" rel="publisher">
                                                        GitHub
                                                            </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </section>
                    </div> : <center><div></div></center>

                 } 
            </>
        );
    }
}

function mapStateToProps(state) {
    return {
        user: state.user
    };
}

export default connect(
    mapStateToProps,
    null
)(User);
