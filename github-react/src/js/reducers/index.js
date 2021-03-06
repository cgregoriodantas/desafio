
import { FIND_SUCCESS, FIND_USER_SUCCESS} from "../constants/action-types";

const initialState = {
    repos: [],
    user: {}
};

function rootReducer(state = initialState, action) {

    switch(action.type){
        case FIND_SUCCESS:
            return  {...state, repos: action.payload[0]  ? action.payload : []}      
        case FIND_USER_SUCCESS:{
            let repos = [];                
            return {...state, user: action.payload  ? action.payload : {}, repos}   
        }

    }   
     return state;
};

export default rootReducer;