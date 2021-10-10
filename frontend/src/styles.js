import React from 'react'
import {createGlobalStyle} from "styled-components"
import reset from 'styled-reset'
import Ephesis from "./styles/fonts/Ephesis-Regular.woff";


export const lightTheme = {
  accent: "#0095f6",
  bgColor: "#FAFAFA",
  fontColor: "rgb(38, 38, 38)",
  borderColor: "rgb(219, 219, 219)",
};

export const darkTheme = {
  fontColor: "white",
  bgColor: "#000",
};

export const GlobalStyles = createGlobalStyle`
    ${reset}
       }
    input {
      all:unset;
    }
    * {
      box-sizing:border-box;
    }
    body {
        background-color:${(props) => props.theme.bgColor};
        font-size:14px;
        font-family:'Open Sans', sans-serif;
        color:${(props) => props.theme.fontColor};
    }
    a {
      text-decoration: none;
      color:inherit;
    }
  
    @font-face {
    font-family: "Ephsis"
    src: local("Ephsis"), url('${Ephesis}') format('woff');
    font-weight:600;
    font-style: normal;


`;
