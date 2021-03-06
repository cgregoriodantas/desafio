import { FIND_ERROR, FIND_SUCCESS, FIND_USER_SUCCESS } from "../constants/action-types";

export function searchStarred(payload) {    
    return function (dispatch) {
        let user = fetch(`https://api.github.com/users/${payload.user}`)
                    .then(response => response.json())
                    .then(json => {
                        dispatch({ type: FIND_USER_SUCCESS, payload: json });
                    });

        let repo = fetch(`https://api.github.com/users/${payload.user}/starred`)
            .then(response => response.json())
            .then(json => {
                dispatch({ type: FIND_SUCCESS, payload: json });
            });
        
        return [user, repo]
    };
}


export function searchUser(payload) {
    console.log(payload.repo)
    return function (dispatch) {
        return fetch(`https://api.github.com/users/${payload.user}`)
            .then(response => response.json())
            .then(json => {
                dispatch({ type: FIND_USER_SUCCESS, payload: json });
            });
    };
}

export function findUserSuccess(payload) {
    return { type: FIND_USER_SUCCESS, payload };
}

export function findSuccess(payload) {
    return { type: FIND_SUCCESS, payload };
}

export function findError(payload) {
    return { type: FIND_ERROR, payload };
}

export function searchRepos(payload) {
    return function (dispatch) {
        let user = fetch(`https://api.github.com/users/${payload.user}`)
        .then(response => response.json())
        .then(json => {
            dispatch({ type: FIND_USER_SUCCESS, payload: json });
        });


        let repo = fetch(`https://api.github.com/users/${payload.user}/repos`)
            .then(response => response.json())
            .then(json => {
                dispatch({ type: FIND_SUCCESS, payload: json });
            });
        
        return [user, repo]        
    };
}


