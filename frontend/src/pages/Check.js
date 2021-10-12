import React ,{useState,useEffect} from 'react'
import { Redirect } from 'react-router';
import { useDispatch } from 'react-redux';
import { loginUser2 } from '../_actions/user_action';

function Check(props) {

  //https://znznzn.tistory.com/64
  const current = decodeURI(window.location.href);
  const search = current.split("?")[1];
  const params = new URLSearchParams(search);
  const tokenFrom= params.get("token")
  const tokenFromBackend = tokenFrom.substr(0,tokenFrom.length-1);
  const dispatch = useDispatch();
  
  console.log(tokenFromBackend);

  // const passToken = async() => {
  //    dispatch(loginUser2(tokenFromBackend))
  //   .then(response => {
  //       console.log(response);
  //       alert("welcome with oauth2");
  //       props.history.push("/");
  //   }).catch(error => {
  //     alert("somthoe wrong")
  //     props.history.push("/login")
  //   })
  // }

  const passToken = async() => {
     const response = 
      await dispatch(loginUser2(tokenFromBackend))   
      if(response){
          console.log(response);
          alert("welcome with oauth2");
          props.history.push("/");}
      else{
        alert("somthoe wrong")
        props.history.push("/login")
      }
    }

   
  // }.catch(error =>{
  //           alert("Something wrong...");
  //   props.history.push("/login");


  useEffect(()=>{passToken()},[])


    

    // const ACCESS_TOKEN = "accessToken";



    //  const getUrlParameter = (name) => {
    //     //https://heropy.blog/2018/10/28/regexp/
    //     name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]'); //첫번쨰 : [ 를 찾아서 "["로 바꾼다, 두번째 : ]를 찾아서 "]"로 바꾼다
    //     var regex = new RegExp('[\\?&]' + name + '=([^&#]*)'); //"?" 나 "&"를 찾아서 + name + &, #을 제외한 새로운 정규식을 생성함

    //     var results = regex.exec(this.props.location.search);
    //     return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' ')); //+를 ' '로 바꿈
        
    // };

    
    //        const token = this.getUrlParameter("token");
    //        const error = this.getUrlParameter("error");

    //        console.log(token);

    //        if (token) {
    //          localStorage.setItem(ACCESS_TOKEN, token);
    //          return (
    //            <Redirect
    //              to={{
    //                pathname: "/profile",
    //                state: { from: this.props.location },
    //              }}
    //            />
    //          );
    //        } else {
    //          return (
    //            <Redirect
    //              to={{
    //                pathname: "/login",
    //                state: {
    //                  from: this.props.location,
    //                  error: error,
    //                },
    //              }}
    //            />
    //          );
    //        }


  return (

    
    <>

    
      
    </>
  );
}

export default Check
