import React, { Component } from "react";
import { connect } from "react-redux";
import { searchRepos, searchStarred, searchUser } from "../actions/index";


function mapDispatchToProps(dispatch) {
  return {
    searchRepos: repo => dispatch(searchRepos(repo)),
    searchStarred: starred => dispatch(searchStarred(starred)),
    searchUser: user => dispatch(searchUser(user)),
  };
}

class ConnectedForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: ""
    };
    this.handleChange = this.handleChange.bind(this);
    this.searchUser = this.searchUser.bind(this);
    this.searchStarred = this.searchStarred.bind(this);
    this.searchRepos = this.searchRepos.bind(this);
  }

  componentDidMount() {
    let userPath = window.location.href.split("/")[3]

    if(userPath){
        this.props.searchUser({ user: userPath});
        this.setState({ user:userPath}); 
    }
  }

  handleChange(event) {
    this.setState({ [event.target.id]: event.target.value });
  }

  searchUser(event) {
    event.preventDefault();
    const { user } = this.state;
    this.props.searchUser({ user });
    
  }

  searchRepos(event) {
    event.preventDefault();
    const { user } = this.state;
    this.props.searchRepos({ user });
    
  }

  searchStarred(event) {
    event.preventDefault();
    const { user } = this.state;
    this.props.searchStarred({ user });
    
  }
  render() {
    const { user } = this.state;
    return (
      <div>
        <section className="bg-info text-center p-5 mt-4">
          <div className="container p-3">
            <h3>Desafio FrontEnd</h3>
            <form onSubmit={this.searchUser}>
              <div>
                <label htmlFor="repoForm">Repo Form</label>
                <input
                  type="text"
                  id="user"
                  value={user}
                  onChange={this.handleChange}
                />
              </div>
              <button type="submit" className="btn btn-light">search</button>
              {
                    this.props.user.name ? 
                    <>
                      <button type="button" onClick={this.searchRepos} className="btn btn-light">repo</button>
                      <button type="button" onClick={this.searchStarred} className="btn btn-light">starred</button>
                    </> : <div></div>
              }
            </form>
          </div>
        </section>
      </div>
    );
  }
}

function mapStateToProps(state) {
  return {
      user: state.user
  };
}

const Form = connect(
  mapStateToProps,
  mapDispatchToProps
)(ConnectedForm);

export default Form;