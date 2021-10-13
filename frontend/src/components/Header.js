import axios from 'axios';
import React ,{useState} from 'react'
import styled from 'styled-components';
import Input from './Input.js'
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHome, faUser } from "@fortawesome/free-solid-svg-icons";


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
      

function Header() {


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



    return (
      <SHeader>
        <Wrapper>
            <form onSubmit = {onSubmitHandler}>
                <Input type ='search' value = {search} onChange={onSearchandler}></Input>
            </form>
            <Column>

    <IconsContainer>
          <Icon>
            <Link to = "/">
              <FontAwesomeIcon icon={faHome} size="lg" />
            </Link>
          </Icon>
          <Icon>
            <Link to = "/myprofile">
              <FontAwesomeIcon icon={faUser} size="lg" />
            </Link>
          </Icon>
          <Icon>
         
          </Icon>
    </IconsContainer>


      
    </Column>
        </Wrapper>
      </SHeader>
    )
}

export default Header
