// /* eslint-disable import/no-anonymous-default-export */
// /* eslint-disable no-unreachable */

// import Axios from "axios";
// import React, { useEffect } from "react";
// import { useDispatch } from "react-redux";
// import { auth } from "../_actions/user_action";

// //이 부분은 APP.js에서 {Auth(component, option, adminRoute)} 로 작성되는 부분이다
// //option의 종류는 다음과 같다
// // null => 아무나 출입이 가능한 페이지
// // false => 로그인한 유저는 출입 불가능한 페잊
// // true => 로그인한 유저만 출임 가능한 페이지



// //보낼때 토큰도 같이 보내야 하는데 어떤식으로 보내야되냐?

// export default function (SpecificComponent, option, adminRoute = null) {
//   //adminRoute = null => 이라는 뜻은 아무것도 안쓰면 디폴트 값이 null이다

//   //1번부분
//   function AuthenticationCheck(props) {
//     const dispatch = useDispatch();

//     const authToken = localStorage.getItem;


//     //console.log(response) => type : auth_user
//     useEffect(() => {
//       //hoc 분기 처리
//       dispatch(auth())
//       .then((response) => {
//         console.log(response)
//         console.log(response.type)
//         console.log(response.payload.id)
//         if(!response.payload.id){
//           if(option === true){
//             alert("need login")
//             props.history.push("/login");
//           }
//         }else if(adminRoute === true && response.payload.role !== "ADMIN"){
//             alert('Admin Only!')
//             props.history.push("/")
//         }
//       });
//     }, [])
//     return <SpecificComponent/>;
//   }

//   return AuthenticationCheck;
// }


        // //로그인하지 않은 상태
        // if (!response.payload) {
        //   if (option) {
        //     props.history.push("/login"); //로그인 안했음으로 로그인 페이지로 이동
        //   }
        // } else {
        //   //로그인한 상태
        //   if (adminRoute && !response.payload) {
        //     props.history.push("/");
        //   } else {
        //     if (option === false) {
        //       props.history.push("/");
        //     }
        //   }
        // }
