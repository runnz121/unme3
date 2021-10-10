import React, { useState } from "react";
import {HelmetProvider} from "react-helmet-async";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";

import routes from "./routes"
import Home from "./pages/Home.js"
import Login from "./pages/Login.js"
import Mypage from "./pages/Mypage.js"
import Chats from "./pages/chat/Chats.js"

import './App.css'


import {ThemeProvider} from "styled-components"
import {lightTheme,darkTheme, GlobalStyles } from "./styles.js";




function App() {


  return (
    <HelmetProvider>
      {/* <ThemeProvider theme = {mode ? darkMode : lightMode}> */}
      <ThemeProvider theme = {lightTheme}>
        <GlobalStyles/>
        <Router>
          <Switch>
            <Route path={routes.home} exact>
              <Home />
            </Route>
            <Route path={routes.login} exact>
              <Login />
            </Route>
            <Route path={routes.mypage} exact>
              <Mypage />
            </Route>
            <Route path ={routes.chat} exact>
              <Chats />
            </Route>
          </Switch>
        </Router>
      </ThemeProvider>
    </HelmetProvider>
  );

}
export default App;
