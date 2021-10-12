import React, { useState, useEffect } from "react";
import { HelmetProvider } from "react-helmet-async";
import { BrowserRouter, Router, Route, Switch } from "react-router-dom";

import routes from "./routes";
import Home from "./pages/Home.js";
import Login from "./pages/Login.js";
import Mypage from "./pages/Mypage.js";
import Chats from "./pages/chat/Chats.js";
import SignUp from "./pages/Signup.js"
import Check from "./pages/Check.js"
import Admin from "./pages/Admin.js"
import Userme from "./pages/Userme.js"
import {getCurrentUser,  ACCESS_TOKEN} from "./utils/UtilsApi.js"

import AuthRoute from "./hoc/AuthRoute";

import "./App.css";

import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme, GlobalStyles } from "./styles.js";
import MyProfile from "./pages/MyProfile";

// import Auth from './hoc/auth'; 
// import { ConnectedRouter } from "connected-react-router";
// import { getCookie } from "././utils/Cookies.js";
// import { useDispatch } from "react-redux";
// import { actionCreators as userActions } from "./redux/modules/user";
// import { history } from "./redux/configStore";


function App() {

//   const [auth, setAuth] = useState({
//     currentUser: "",
//     authenticated: false,
//     loading: false,
//   });

//   console.log(auth);
//   console.log(auth.authenticated)

//   const loadCurrentLogged = () =>{

//     setAuth({
//       loading: true
//     })
    
//       getCurrentUser()
//       .then((response) => {
//         setAuth({
//           currentUser: response,
//           authenticated: true,
//           loading:false
//         })
//       }).catch(error => {
//         setAuth({
//           loading: false
//         })
//       }
//       );

//   }

// useEffect(()=>{loadCurrentLogged()},[])



  // const handleLogout =()=> {
  //   localStorage.removeItem(ACCESS_TOKEN);
  //   setAuth({
  //     authenticated: false,
  //     currentUser: null,
  //   });
  //   alert("You're safely logged out!");
  // }


// const dispatch = useDispatch();
// const token = getCookie("is_login");



// React.useEffect(() => {
//   if (token) {
//     //토큰이 존재하면 로그인을 유지 API 호출
//     dispatch(userActions.loginCheckDB());
//   }
// }, []);


  return (
    <HelmetProvider>
      {/* <ThemeProvider theme = {mode ? darkMode : lightMode}> */}
      <ThemeProvider theme={lightTheme}>
        <GlobalStyles />
        <BrowserRouter>
          <Switch>
            {/* <ConnectedRouter history={history}> */}
            <Route exact path="/" component={Home} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/signup" component={SignUp} />
            <Route exact path="/userme" component={Userme} />

            <Route path="/oauth2/redirect" component={Check} />
            {/* <Route path="/mypage" component = {Mypage}/>
            <Route path="/admin" component = {Admin}/> */}
            <AuthRoute 
            exact 
            path="/myprofile" 
            needAuth={true}
            component={MyProfile}>
            </AuthRoute>
            
            <AuthRoute
              exact
              path="/admin"
              needAuth={true}
              component={Admin}
            ></AuthRoute>
            <AuthRoute
              exact
              path="/mypage"
              needAuth={true}
              component={Mypage}
            />
            {/* <Route path={routes.chat} exact>
              <Chats />
            </Route> */}
          </Switch>
        </BrowserRouter>
        {/* </ConnectedRouter> */}
      </ThemeProvider>
    </HelmetProvider>
  );
}
export default App;
