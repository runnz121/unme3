import React,{useEffect,useState} from 'react'
import styled from "styled-components";
import "../App.css";

import PageTitle from "../components/PageTitle";

import { useLocation } from "react-router-dom";
import { useForm } from "react-hook-form";
import FormBox from "../components/Auth/FormBox";
import Fonts from "../components/Fonts";
import Input from "../components/Auth/Input";
import Btn from "../components/Auth/Btn";

import { withRouter } from "react-router-dom";


import {request} from "../utils/UtilsApi"


const Container = styled.div`
  display: flex;
  height: 100vh;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  border: solid 1px black;
`;

function MyProfile(props) {

    
const API_BASE_URL = "http://localhost:8080";
const ACCESS_TOKEN = "accessToken";

  const [userId, setUserId] = useState("");


  const location = useLocation();
  //console.log(location);
  const { register, handleSubmit, formState, getValues, clearErrors } = useForm(
    {
      mode: "onChange",
      defaultValues: {
        name: location?.state?.name || "",
        email: location?.state?.email || "",
        password: location?.state?.password || "",
      },
    }
  );


  async function getCurrentUser() {
    if (!localStorage.getItem(ACCESS_TOKEN)) {
      console.log("not okenset");
    }
    const res = await request({
      url: API_BASE_URL + "/user/me",
      method: "GET",
    });
    //console.log(res.id)
    setUserId(res.id);
    console.log(res);
  }

  useEffect(() => {
    getCurrentUser();
  }, []);



   const onSubmit = () => {
       const {name, email, password, phonenumber} = getValues();
       
        let data = {
          id: userId,
          name: name,
          email: email,
          password: password,
          phonenumber: phonenumber,
        };
         console.log(data);
        const res = request({
            url: API_BASE_URL + "/user/update",
            method:"PUT",
            body:JSON.stringify(data)
        })
        .then(props.history.push("/"))
        .catch(error =>{
            console.log(error)
        })
        console.log(res)
    }
   




//    };

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
            type="password"
            placeholder="Password"
          />
          <Input
            {...register("phonenumber", {})}
            onChange={clearLoginError}
            name="phonenumber"
            type="text"
            placeholder="Phonenumber"
          />

          <Btn
            type="submit"
            value={"Submit to Update"}
            disabled={formState.isValid || !formState.isDirty}
          />
        </form>
      </FormBox>
    </Container>
  );
}

export default withRouter(MyProfile)
