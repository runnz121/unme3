
import React,{useEffect} from 'react'

function Userme() {

const API_BASE_URL = "http://localhost:8080";
const ACCESS_TOKEN = "accessToken";

const request = (options) => {
  const headers = new Headers({
    "Content-Type": "application/json",
  });

  if (localStorage.getItem(ACCESS_TOKEN)) {
    headers.append(
      "Authorization",
      "Bearer " + localStorage.getItem(ACCESS_TOKEN)
    );
  }

  const defaults = { headers: headers };
  options = Object.assign({}, defaults, options);

  return fetch(options.url, options).then((response) =>
    response.json().then((json) => {
      if (!response.ok) {
        return Promise.reject(json);
      }
      return json;
    })
  );
};

async function getCurrentUser(){
    if (!localStorage.getItem(ACCESS_TOKEN)) {
        console.log("not okenset");
    }
    const res = await request({
      url: API_BASE_URL + "/post/all",
      method: "GET",
    });
    console.log(res);
}

useEffect(()=>{
    getCurrentUser()
},[])
    
    return (
        <div>
            userme
        </div>
    )
}

export default Userme
