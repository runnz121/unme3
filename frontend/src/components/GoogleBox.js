import axios from "axios";
import styled from "styled-components";
import { faGoogle } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Wrapper = styled.div`
  display: flex;
  flex-direction: row;
  lign-items: center;
  justify-content: flex-start;
   input{
    margin-top: 10px;
    width: 100%;
    display: flex;
    justify-items: center;
    flex-direction: column;
    align-items: center;
`;

const GoogleOauthstyle = styled.input`
  cursor: pointer;
  border: none;
  border-radius: 3px;
  width: 120%;
  background-color: whitef;
  text-align: center;
  color: #787878;
  padding-right: 40px;
  padding-bottom : 15px;
  font-size: 15px;
  font-weight: 600;
`;

const IconInput = styled.div`
  margin-top: 35px;
  margin-left: -20px;
  margin-right: 40px;
  padding-top : 4px;
`;


// export const API_BASE_URL = "http://localhost:8080";
// export const ACCESS_TOKEN = "accessToken";

// export const OAUTH2_REDIRECT_URI = "http://localhost:3000/oauth2/redirect";

// export const GOOGLE_AUTH_URL =
//   API_BASE_URL + "/oauth2/authorize/google?redirect_uri=" + OAUTH2_REDIRECT_URI;



function GoogleOauth() {
  const onClickHandler = (e) => {
    axios.get("/api/oauth2/authorization/google").then((res) => {
      console.log(res);
    });
  };

  return (
    <Wrapper>
      <IconInput>
        <FontAwesomeIcon icon={faGoogle} size="2x" color="#d2d2d2" />
      </IconInput>
      <form onClick={onClickHandler}>
        <GoogleOauthstyle type="button" value={"Login with Google"} />
      </form>
    </Wrapper>
  );
}

export default GoogleOauth;
