import axios from 'axios';
import React ,{useState} from 'react'
import styled from 'styled-components';
import Input from './Input.js'
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHome, faSignOutAlt, faUser } from "@fortawesome/free-solid-svg-icons";
import { history } from "react-router-dom";


    const Wrapper = styled.div`
      max-width: 930px;
      width: 100%;
      display: flex;
      justify-content: space-between;
      align-itmes: center;
    `;

    const Form = styled.form``;

    const Btn = styled.span`
      background-color: ${(props) => props.theme.accent};
      color: white;
      border-radius: 4px;
      padding: 5px 15px;
      font-weight: 600;
    `;

    const IconsContainer = styled.div`
      display: flex;
      align-items: center;
    `;

    const SHeader = styled.header`
      width: 100%;
      border-bottom: 1px solid ${(props) => props.theme.borderColor};
      background-color: ${(props) => props.theme.bgColor};
      padding: 18px 0px;
      display: flex;
      align-items: center;
      justify-content: center;
    `;

    const Column = styled.div``;

    const Icon = styled.span`
      margin-left: 15px;
    `;

      const MouserHover = styled.div`
        &:hover {
          cursor: pointer;
        }
      `;
      

function Header(props) {


    const [search, setSearch] = useState("")
    const onSearchandler = (e) =>{
        setSearch(e.currentTarget.value)
    }

    const onSubmitHandler = (e) => {
         e.preventDefault()

    let data = {
            username: search
        }
        axios
        .post('/data',  JSON.stringify(data), {
          headers: {
            "Content-Type": `application/json`,
          },
        })
        .then((res) => {
          console.log(res);
        });
    }

    const LogOutHandler = (e) =>{
        const token = localStorage.getItem("accessToken")
        if(token){
            localStorage.removeItem("accessToken");
             alert("Sucessfully Log Out");
        } else{
            alert("You alerady logout")
        }
       
    }


    return (
      <SHeader>
        <Wrapper>
          <form onSubmit={onSubmitHandler}>
            <Input
              type="search"
              value={search}
              onChange={onSearchandler}
            ></Input>
          </form>
          <Column>
            <IconsContainer>
              <Icon>
                <Link to="/">
                  <FontAwesomeIcon icon={faHome} size="lg" />
                </Link>
              </Icon>
              <Icon>
                <Link to="/myprofile">
                  <FontAwesomeIcon icon={faUser} size="lg" />
                </Link>
              </Icon>
              <Icon>
                <Link to="/login">
                  <FontAwesomeIcon
                    icon={faSignOutAlt}
                    size="lg"
                    onClick={LogOutHandler}
                  />
                </Link>
              </Icon>
              <Icon></Icon>
            </IconsContainer>
          </Column>
        </Wrapper>
      </SHeader>
    );
}

export default Header
