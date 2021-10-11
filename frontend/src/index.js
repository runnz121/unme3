import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import { Provider } from 'react-redux';
import { applyMiddleware, createStore } from 'redux';
import promiseMiddleware from 'redux-promise'; //redux스토어 에서 프로미스 인식을 위한 프로미스 임포트
import ReduxThunk from 'redux-thunk' //리덕스 스토어에서 함수 인식을 위한 함수 임포트
import Reducer from './_reducers'; //리듀서 폴더에서 가져온 리듀서

// import store from "./redux/configStore";


const creatStoreWithMiddleware = applyMiddleware(
  promiseMiddleware,
  ReduxThunk
)(createStore);

ReactDOM.render(
  <React.StrictMode>
    <Provider store={creatStoreWithMiddleware(Reducer)}>
      <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById("root")
);
