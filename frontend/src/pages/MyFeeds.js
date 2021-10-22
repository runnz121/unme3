import React, { useEffect, useState, useRef } from "react";
import styled from "styled-components";
import useFetch from "../components/PostComponent.js";
import { Link, useHistory, useLocation } from "react-router-dom";
import Modal from "../components/PostModal.js";

const Container = styled.div`
  width: 850px;
  position: ${(props) => (props.modalOpen ? "fixed" : "absolute")};
  overflow: ${(props) => (props.modalOpen ? "hidden" : "visible")};
  left: 50%;
  transform: translate(-50%);
`;



const Content = styled.div`
  height: 100px;
  width: 100%;
  border: 1px solid black;
  &:hover {
    cursor: pointer;
  }
`;

const Loading = styled.div`
  fontweight: 600;
`;

const Posts = () => {
  const [pageNum, setPageNum] = useState(0);
  const { list, hasMore, isLoading } = useFetch(pageNum); 

  //modal state
  const [modalState, setModalState] = useState(false);
  // console.log(modalState)

  const [postId, setPostId] = useState();

  console.log("postId", postId);

  const history = useHistory();
  const location = useLocation();

  const observerRef = useRef();


  //옵션
  const options = {
    root: null,
    rootMargin: "10px",
    threshold: 0.5,
  };

  //관찰자
  const observer = (node) => {
    if (isLoading) return;
    if (observerRef.current) observerRef.current.disconnect();

    observerRef.current = new IntersectionObserver(([entry]) => {
      if (entry.isIntersecting && hasMore) {
        setPageNum((page) => page + 1);
      }
    }, options);

    node && observerRef.current.observe(node);
  };


  const openModal = (prop) => {
    setModalState(true);
    setPostId(prop);
  };

  const closeModal = () => {
    setModalState(false);
  };



  return (
    <Container modalOpen={modalState}>
      {list?.map((post, idx) => (
        <Content
          key={idx}
          onClick={() => {
            openModal(post.id);
          }}
        >
          {post.id}
          {post.title}
        </Content>
      ))}
      <Modal data={postId} state={modalState} closeModal={closeModal} />

      <div ref={observer} />
      <div>{isLoading ? <Loading /> : "Data END"} </div>
    </Container>
  );
};

export default Posts;
