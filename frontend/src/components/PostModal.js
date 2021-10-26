import React,{useEffect, useState} from 'react';
import styled from  'styled-components'
import PropTypes from 'prop-types'
import { API_BASE_URL, request } from "../utils/UtilsApi";



const Container = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  z-index: 1;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Overlay = styled.div`
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.8);
`;

const Contents = styled.div`
  position: relative;
  top: -40vh;
  padding: 0 auto;
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  background-color: white;
  text-align: center;
  width: 50%;
  height: 600px;
`;

const Title = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.5em;
  background-color: #fcf3cf;
  width: 100%;
  overflow: auto;
  height: 10%;
`;

const Close = styled.div`
  position: absolute;
  margin-right: 15px;
  color: gray;
  right: 0;

  &:hover {
    cursor: pointer;
  }
`;

const Body = styled.div`
  margin: 10px 10px 10px 10px;
`;


const Modal = (props) => {
  const { data, state, closeModal } = props;
  const [rendering, setRendering] = useState("");


  console.log("data next: ", data);


function getPosts() {
  const res = request({
    url: API_BASE_URL + `/post/find/${data}`,
    method: "GET",
  }).then(res => setRendering(res)).catch(error => console.log(error))
  console.log(res);
}
console.log("renderig",rendering)

useEffect((data) => {
  getPosts();
}, [data]);
   
  return state ? (
    <Container>
      <Overlay onClick={(event) => closeModal(event)} />
      <Contents>
        <Title>
          {rendering.title}
          <Close onClick={(event) => closeModal(event)}>X</Close>
        </Title>
        <Body>
          {rendering.content}
          {rendering.member_id}
        </Body>
      </Contents>
    </Container>
  ) : (
    <></>
  );
};

Modal.propTypes = {
  //data: PropTypes.object.isRequired,
  data : PropTypes.number.isRequired,
  state: PropTypes.bool.isRequired,
  closeModal: PropTypes.func.isRequired,
};

export default Modal;