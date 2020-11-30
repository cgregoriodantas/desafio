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
        <section id="team" class="pb-5">
          <div class="container">                         
              <div class="row">              
              {this.props.repos.map(el => (                
                <div key={el.id} class="col-xs-12 col-sm-6 col-md-4">
                  <div class="image-flip" ontouchstart="this.classList.toggle('hover');">
                    <div class="mainflip">
                      <div class="frontside">
                        <div class="card">
                          <div class="card-body text-center">                            
                            <h4 class="card-title">{el.name}</h4>
                            <p class="card-text">{el.description}</p>                            
                            <a href={el.clone_url} target="blanck" class="btn btn-danger btn-sm" rel="publisher">
                              Link
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
