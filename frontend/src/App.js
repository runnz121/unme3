import React, { useState } from "react";
import { HelmetProvider } from "react-helmet-async";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import routes from "./routes";
import Home from "./pages/Home.js";
import Login from "./pages/Login.js";
import Mypage from "./pages/Mypage.js";
import Chats from "./pages/chat/Chats.js";
import SignUp from "./pages/Signup.js"

import "./App.css";

import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme, GlobalStyles } from "./styles.js";

import Auth from './hoc/auth'; //hoc저거용 시키기 위해 import 함

function App() {
  return (
    <HelmetProvider>
      {/* <ThemeProvider theme = {mode ? darkMode : lightMode}> */}
      <ThemeProvider theme={lightTheme}>
        <GlobalStyles />
        <Router>
          <Switch>
            <Route exact path="/" component = {Auth(Home, null, true)}/>
            <Route exact path="/login" component = {Auth(Login, false)}/>
            <Route exact path="/mypage" component = {Auth(Mypage,true, true)}/>
            <Route exact path="/signup" component = {Auth(SignUp, false)}/>
            {/* <Route path={routes.chat} exact>
              <Chats />
            </Route> */}
          </Switch>
        </Router>
      </ThemeProvider>
    </HelmetProvider>
  );
}
export default App;
