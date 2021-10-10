import { combineReducers } from "redux";
import user from "./user_reducer";

//리듀서를 합치는 부분
const rootReducer = combineReducers({
  user,
});

export default rootReducer;
