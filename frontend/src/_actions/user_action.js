import axios from  'axios';

import {
    LOGIN_USER, REGISTER_USER, AUTH_USER
} from './types'


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

export function auth(){

    const request = axios.get('/user/me')
    .then(response => response.data)

    return {
        type: AUTH_USER,
        payload:request
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