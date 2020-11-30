
import { SEARCH_REPOS, SEARCH_STARRED, FIND_ERROR, FIND_SUCCESS, SEARCH_USER, FIND_USER_SUCCESS} from "../constants/action-types";

const initialState = {
    repos: [],
    user: {}
};

function rootReducer(state = initialState, action) {

    if (action.type === FIND_SUCCESS) {        
        state = {...state, repos: action.payload[0]  ? action.payload : []}        
    }
    else if (action.type === FIND_USER_SUCCESS) {
        state = {...state, repos: []}        
        state = {...state, user: action.payload  ? action.payload : {}}        
    }
    else if (action.type === SEARCH_REPOS) {        
        state.repos.push(action.payload);
    }else if (action.type === SEARCH_STARRED) {
        state.repos.push(action.payload);
    }else if (action.type === SEARCH_STARRED) {
        state.repos.push(action.payload);
    }
    else if (action.type === SEARCH_USER) {
        state.repos.push(action.payload);
    }
    return state;
};

export default rootReducer;