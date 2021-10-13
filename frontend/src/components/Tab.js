import React,{useState} from 'react'
import styled from 'styled-components';

//mypage import
import Movies from "../pages/Movies";
import MyFeeds from '../pages/MyFeeds';
import Posts from "../pages/Posts";
import Saves from "../pages/Saves";
import Tags from "../pages/Tags";
import Upload from '../pages/Upload';

    const TabMenu = styled.ul`
      padding: 18px 0px;
      display: flex;
      align-items: center;
      justify-content: center;
      list-style: none;
    `;

    const TabSub = styled.li`
      padding: 0px 20px;
      &:hover {
        cursor: pointer;
      }
    `;



function Tab(){

    const [currentTab, setCurrentTab] = useState(0);
    const ClickHandler = (id) => {
        setCurrentTab(id);
    }

    const arr = [
        {name : 'posts', content : <Posts/>},
        {name : 'movies', content : <Movies/>},
        {name : 'Feed', content : <MyFeeds/>},
        {name : 'tags', content : <Tags/>},
        {name : 'upload', content : <Upload/>}
]


    return (
        <>
          <TabMenu>
              {arr.map((v, idx)=> {
                  return <TabSub key={idx} onClick ={()=>{ClickHandler(idx)}}>{v.name}</TabSub>
              })}
          </TabMenu>
              <div>
                  {arr[currentTab].content}
              </div>
        </>
      );
}



export default Tab
