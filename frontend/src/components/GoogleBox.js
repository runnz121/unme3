import axios from "axios";
import styled from "styled-components";
import { faGoogle } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {Link} from "react-router-dom"
import propTypes from "prop-types"

const Wrapper = styled.div`
  display: flex;
  flex-direction: row;
  lign-items: center;
  justify-content: flex-start;
`;

const GoogleOauthstyle = styled.div`
  cursor: pointer;
  border: none;
  border-radius: 3px;
  width: 100%;
  text-align: center;
  color: #787878;
  font-size: 15px;
  font-weight: 600;
  padding-right:10px;
  padding-top : 20px;
 
`;

const IconInput = styled.div`
  padding-right: 10px;
  width : 20%;
`;

const TextInput = styled.div`
  margin-left: 100px;
  margin-top : -23px;
  margin-left : 50px;
`;


// export const API_BASE_URL = "http://localhost:8080";
// export const ACCESS_TOKEN = "accessToken";

// export const OAUTH2_REDIRECT_URI = "http://localhost:3000/oauth2/redirect";

// export const GOOGLE_AUTH_URL =
//   API_BASE_URL + "/oauth2/authorize/google?redirect_uri=" + OAUTH2_REDIRECT_URI;


//http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:3000/oauth2/redirect
// function GoogleOauth() {
//   const onClickHandler = (e) => {
//     axios
//       .get(
//         "http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:3000/oauth2/redirect"
//       )
//       .then((res) => {
//         console.log(res);
//       });
//   };

const GOOGLE_AUTH_URL =
  "http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:3000/oauth2/redirect";

// function GoogleOauth(){
//   const onClickHandler = (e) => {
//     fetch("http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:3000/oauth2/redirect",{
//       mode:'cors',
//       credentials:'same-origin',
//     }).then((res)=>{
//       console.log(res);
//     })
//   }

function GoogleOauth({linkText}){

  return (
    <Wrapper>
      <GoogleOauthstyle>
        <IconInput>
          <FontAwesomeIcon icon={faGoogle} size="2x" color="#d2d2d2" />
        </IconInput>
        <TextInput>
          <a href={GOOGLE_AUTH_URL}>{linkText}</a>
        </TextInput>
      </GoogleOauthstyle>
    </Wrapper>
  );
}

GoogleOauth.propTypes = {
  linkText: propTypes.string.isRequired
}

export default GoogleOauth;
