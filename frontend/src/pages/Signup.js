import React from "react";
import styled from "styled-components";
import "../App.css";

import PageTitle from "../components/PageTitle";

import { useLocation } from "react-router-dom";
import { useForm } from "react-hook-form";
import FormError from "../components/FormError";
import Seperator from "../components/Seperator";
import BottomBox from "../components/BottomBox";
import FormBox from "../components/Auth/FormBox";
import Fonts from "../components/Fonts";
import Input from "../components/Auth/Input";
import Btn from "../components/Auth/Btn";
import routes from "../routes";
import GoogleOauth from "../components/GoogleBox";
import { loginUser, registerUser } from "../_actions/user_action";
import { useDispatch } from "react-redux";

import { withRouter } from "react-router-dom";

const Container = styled.div`
  display: flex;
  height: 100vh;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  border: solid 1px black;
`;

function Signup(props) {

    const location = useLocation();
    console.log(location)
    const {
      register,
      handleSubmit,
      formState,
      getValues,
      clearErrors,
    } = useForm({
      mode: "onChange",
       defaultValues: {
        name: location?.state?.name || "",
        email: location?.state?.email || "",
        password: location?.state?.password || "",
       },
    });

    const dispatch = useDispatch();
    const onSubmit = () => {

        const {name, email, password} = getValues();
        dispatch(registerUser({
            name, email, password,
        }))
        .then(response => {
            if(response.payload.success){
                props.history.push("/login");
                console.log(response.payload);
            }
        });
    }

      const clearLoginError = () => {
        clearErrors("result");
      };



  return (
    <Container>
      <PageTitle title="SignUp" />
      <FormBox>
        <Fonts>EsanghaeSee</Fonts>
        <form onSubmit={handleSubmit(onSubmit)}>

          <Input
            {...register("name", {
              required: "Name is requried",
            })}
            onChange={clearLoginError}
            name="name"
            type="text"
            placeholder="Name"
          />

          <Input
            {...register("email", {
              required: "Email is requried",
            })}
            onChange={clearLoginError}
            name="email"
            type="text"
            placeholder="Email"
          />

          <Input
            {...register("password", {
              required: "Password is requried",
            })}
            onChange={clearLoginError}
            name="password"
            type="text"
            placeholder="Password"
          />

          <Btn
            type="submit"
            value={"Sign Up"}
            disabled={formState.isValid || !formState.isDirty}
          />
        </form>
        <GoogleOauth />
      </FormBox>
    </Container>
  );
}

export default withRouter(Signup);
