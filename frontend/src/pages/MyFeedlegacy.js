import React, { useEffect, useState } from "react";
import axios from "axios";
import styled from "styled-components";
import { API_BASE_URL, request } from "../utils/UtilsApi";

const Container = styled.div`
  width: 850px;
  position: absolute;
  left: 50%;
  transform: translate(-50%);
`;

const Wrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(3, minmax(100px, 1fr));
  gird-gap: px;
  grid-auto-flow: dense;
  justify-items: center;
  align-content: center;
  justify-content: center;
`;

const Sub = styled.div`
  border: 1px solid;
  margin: 15px;
  height: 240px;
  width: 250px;
`;

function MyFeeds() {
  const [Posts, setPosts] = useState([]);

  async function getPosts() {
    const res = await request({
      url: API_BASE_URL + "/image/feed",
      method: "GET",
    });
    setPosts(res);
    console.log(res);
  }

  useEffect(() => {
    getPosts();
  }, []);

  return (
    <div>
      <p>feed</p>
      <Container>
        <Wrapper>
          {/* Posts.map((post,index) => (<sub key = {index})) 는 안티 패턴으로 비추  */}
          {Posts.map((post) => (
            <Sub key={post.id}>
              <p>{post.title}</p>
              <p>{post.content}</p>
              <p>{post.member.username}</p>
              <p>{post.member.password}</p>
              <p>{post.member.email}</p>
            </Sub>
          ))}
        </Wrapper>
      </Container>
    </div>
  );
}
export default MyFeeds;
