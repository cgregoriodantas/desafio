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
        <section id="team" className="pb-5">
          <div className="container">                         
              <div class="row">              
              {this.props.repos.map(el => (                
                <div key={el.id} class="col-xs-12 col-sm-6 col-md-4">
                  <div className="image-flip" ontouchstart="this.classList.toggle('hover');">
                    <div className="mainflip">
                      <div className="frontside">
                        <div className="card">
                          <div className="card-body text-center">                            
                            <h4 className="card-title">{el.name}</h4>
                            <p className="card-text">{el.description}</p>                            
                            <a href={el.clone_url} target="blanck" className="btn btn-danger btn-sm" rel="publisher">
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
