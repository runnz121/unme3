import React, {useState} from 'react'
import styled from "styled-components"
import '../App.css';

import PageTitle from '../components/PageTitle';

import {useLocation} from "react-router-dom";
import {useForm} from "react-hook-form";
import FormError from '../components/FormError';
import Seperator from "../components/Seperator"
import BottomBox from '../components/BottomBox';
import FormBox from "../components/Auth/FormBox";
import Fonts from "../components/Fonts"
import Input from "../components/Auth/Input"
import Btn from "../components/Auth/Btn"
import routes from '../routes';
import GoogleOauth from '../components/GoogleBox';
import { loginUser } from '../_actions/user_action';
import { useDispatch } from 'react-redux';

import {withRouter,Link} from "react-router-dom";
import axios from 'axios';


import { actionCreators as userActions } from "../redux/modules/user";


const Container = styled.div`
  display: flex;
  height: 100vh;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  border: solid 1px black;
`;



function Login(props) {
  const location = useLocation();

  const [passwordShown, setPasswordShown] = useState(false);
  const { register, handleSubmit, formState, getValues, clearErrors } = useForm(
    {
      mode: "onChange",
      defaultValues: {
        email: location?.state?.email || "",
        password: location?.state?.password || "",
      },
    }
  );
  const dispatch = useDispatch();

  const clearLoginError = () => {
    clearErrors("result");
  };

  // const onSubmit = () => {
  //   const{email, password} = getValues();
  //   dispatch(userActions.loginDB(email, password))
  // }

  const onSubmit = () => {

      const {email, password} = getValues();
      dispatch(loginUser({
        email, password,
      }))
      .then(response => {
        const tokenFromBackend = response.payload.accessToken;
        //console.log(tokenFromBackend);
        if (tokenFromBackend.length != null) {
          // const authtoken = (axios.defaults.headers.common[
          //   "Authorization"
          // ] = `Bearer ${tokenFromBackend}`);
          //console.log(authtoken)
          localStorage.setItem('accessToken', tokenFromBackend);
          alert("welcome with loginform");
          props.history.push("/mypage");
        }}).catch( error => {
          alert("not signup user");
        });
   }

  return (
    <Container>
      <PageTitle title="Login" />
      <FormBox>
        <Fonts>EsanghaeSee</Fonts>
        <form onSubmit={handleSubmit(onSubmit)}>
          <FormError message={formState.errors?.email?.message} />
          <Input
            {...register("email", {
              required: "Email is required",
              minLength: 5,
            })}
            onChange={clearLoginError}
            name="email"
            type="text"
            placeholder="Email"
            hasError={Boolean(formState.errors?.email?.message)}
          />

          <Input
            {...register("password", {
              required: "Password is required",
              //minLength: 8,
            })}
            onChange={clearLoginError}
            name="password"
            type="password"
            placeholder="Password"
            // //https://codesandbox.io/s/showhide-password-on-toggle-in-react-hooks-95qcz?file=/src/App.js:1032-1084
            // //참고해서기능 넣을것
            // type={passwordShown ? "text" : "password"}
            hasError={Boolean(formState.errors?.password?.message)}
          />
          <FormError message={formState.errors?.password?.message} />
          <Btn
            type="submit"
            value={"Login"}
            disabled={formState.isValid || !formState.isDirty}
          />
          <FormError message={formState.errors?.result?.message} />
        </form>
        <Seperator />
        <BottomBox
          cta="계정이 없으신가요?"
          linkText="Sign up"
          link={"/signup"}
        />
        <GoogleOauth linkText="Log in with Google" />
      </FormBox>
    </Container>
  );
}

export default withRouter(Login)
