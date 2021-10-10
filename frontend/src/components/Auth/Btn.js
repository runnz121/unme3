import styled from "styled-components";


const Btn = styled.input`
  border: none;
  border-radius: 3px;
  width: 120%;
  margin-top: 5px;
  padding: 12px 0px;
  text-align: center;
  color: white;
  background-color: #87f5f5;
  opacity: ${(props) => (props.disabled ? "0.5" : "1")};
`;

export default Btn;