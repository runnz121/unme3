import styled from "styled-components";

const TheFormError = styled.span`
  color: #ff5a5a;
  font-weight: 600;
  font-size: 12px;
  margin: 3px 0px 10px 0px;
  
`;

function FormError ({message}) {
    return message === "" || !message ? null : <TheFormError>{message}</TheFormError>;
}

export default FormError;