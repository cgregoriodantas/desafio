import React, { Component } from "react";
import { connect } from "react-redux";
import { getData } from "../actions/index";

export class List extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
  }

  render() {
    return (
      <>
        {/*       
      <ul>
        {this.props.repos.map(el => (
          <li key={el.id}>{el.name}</li>
        ))}
      </ul> */}

        <section id="team" class="pb-5">
          <div class="container">
            
              {/* <h5 class="section-title h1">OUR TEAM</h5> */}
              <div class="row">
              {/* <p><img class=" img-fluid" src={el.owner.avatar_url} alt="card image" /></p> */}
              {this.props.repos.map(el => (
                // <li key={el.id}>{el.name}</li>
                <div key={el.id} class="col-xs-12 col-sm-6 col-md-4">
                  <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                    <div class="mainflip">
                      <div class="frontside">
                        <div class="card">
                          <div class="card-body text-center">                            
                            <h4 class="card-title">{el.name}</h4>
                            <p class="card-text">{el.description}</p>
                            {/* <a href={el.clone_url} target="blanck" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i></a> */}
                            <a href={el.clone_url} target="blanck" class="btn btn-danger btn-sm" rel="publisher">
                              GitHub
                            </a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </section>

      </>
    );
  }
}

function mapStateToProps(state) {
  return {
    repos: state.repos
  };
}

export default connect(
  mapStateToProps,
  null
)(List);

/*
[
  {
    "name": "30-days-of-elixir",
    "owner": {
      "login": "gilluan",
      "avatar_url": "https://avatars2.githubusercontent.com/u/2496130?v=4",
      "html_url": "https://github.com/spacedrop"
    },
    "description": "A walk through the Elixir language in 30 exercises.", //repo
    "git_url": "git://github.com/gilluan/30-days-of-elixir.git", //repo
    "clone_url": "https://github.com/gilluan/30-days-of-elixir.git", //repo
    "language": "Elixir",
  },

]
*/