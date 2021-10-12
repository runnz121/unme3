
/* eslint-disable import/no-anonymous-default-export */
/* eslint-disable no-unreachable */
import {
    LOGIN_USER, REGISTER_USER, AUTH_USER, LOGIN_USER2
} from '../_actions/types'


export default function(state ={}, action){
    switch (action.type) {
      case LOGIN_USER:
        return { ...state, loginSuccess: action.payload };
        break;
      case LOGIN_USER2:
        return { ...state, loginSuccess: action.payload };
        break;
      case REGISTER_USER:
        return { ...state, register: action.payload };
        break;
      case AUTH_USER:
        return { ...state, userDate: action.payload };
        break;
      default:
        return state;
    }
}