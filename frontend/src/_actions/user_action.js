import axios from  'axios';

import {
    LOGIN_USER, REGISTER_USER, AUTH_USER, LOGIN_USER2
} from './types'

import BASE_URL from "../utils/UtilsApi"




export function loginUser(dataToSubmit){

    const request = axios
      .post("http://localhost:8080/auth/login", JSON.stringify(dataToSubmit), {
        headers: { "Content-Type": `application/json` },
      })
      .then((response) => response.data);

    return {
        type: LOGIN_USER,
        payload:request
    }
}


export function loginUser2(tokenFromBackend) {
    
  const request = localStorage.setItem('accessToken', tokenFromBackend);

  return {
    type: LOGIN_USER2,
    payload: request,
  };
}



export function auth(authToken){

    const request = axios.get('http://localhost:8080/user/me',JSON.stringify(authToken), {
        headers: { "Content-Type": `application/json` },
      })
    .then((response)=> response.data);
    
    return {
        type: AUTH_USER,
        payload: request
    }

}

export function registerUser(dataToSubmit) {
  //서버에 request를 보냄
  const request = axios
    .post("http://localhost:8080/auth/signup", JSON.stringify(dataToSubmit), {
      headers: { "Content-Type": `application/json` },
    })
    .then((response) => response.data);

  return {
    type: REGISTER_USER,
    payload: request,
  };
}