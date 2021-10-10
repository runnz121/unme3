import styled from "styled-components";


const Input = styled.input`
  width: 120%;
  border: 0.5px solid #d2d2d2;
  border-radius: 2px;
  padding: 10px;
  margin-bottom: 8px;
  box-sizing: border-box;
  &::placeholder {
    font-size: 15px;
  }
  &:focus {
    border-color: #aab9ff;
    background-color: #f4ffff;
  }
`;

export default Input