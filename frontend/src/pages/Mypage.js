import { faTruckLoading } from "@fortawesome/free-solid-svg-icons";
import Header from "../components/Header";
import Tab from "../components/Tab"


function Mypage() {

  console.log(localStorage.getItem('accessToken'))


  return (
    <div>
 
        <Header />

      <Tab />
    </div>
  );
}

export default Mypage;
