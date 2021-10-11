import Header from "../components/Header";
import Tab from "../components/Tab"


function Mypage() {

  console.log(localStorage.getItem)


  return (
    <div>

      <Header/>
      <Tab/>
    </div>
  );
}

export default Mypage;
