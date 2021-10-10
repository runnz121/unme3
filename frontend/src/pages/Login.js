import React from 'react'
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

import {withRouter} from "react-router-dom";
import axios from 'axios';

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
    const {
      register,
      handleSubmit,
      formState,
      getValues,
      clearErrors,
    } = useForm({
      mode: "onChange",
      defaultValues: {
        email: location?.state?.email || "",
        password: location?.state?.password || "",
      },
    });
    const dispatch = useDispatch();

    // const onSubmit = () => {

    //   const {email, password} = getValues();
    //   dispatch(loginUser({email, password})).then(response => console.log(response.payload.authToken))
    // }
    const onSubmit = () => {

        const {email, password} = getValues();
        dispatch(loginUser({
          email, password,
        }))
        .then(response => {
          const accessToken = response.payload.authToken;
          console.log(accessToken)
          if (accessToken.length != null) {
            axios.defaults.headers.common["Authorization"] = `Bearer ${accessToken}`;
            alert("welcome")
           props.history.push("/"); 
          }}).catch( error => {
            alert("not signup user");
          });
     }
    

     const clearLoginError = () => {
       clearErrors("result");
     };


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
          link={"/signup"} />
          <GoogleOauth/>
        </FormBox>
      </Container>
    );
}

export default withRouter(Login)
