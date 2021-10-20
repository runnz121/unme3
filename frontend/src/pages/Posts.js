import React,{useEffect,useState, useRef} from 'react'
import styled from "styled-components"
import useFetch from "../components/PostComponent.js"
import { Link, useHistory, useLocation } from "react-router-dom";
import Modal from "../components/Modal.js"

const Container = styled.div`
  width: 850px;
  position: ${props => props.modalOpen ? 'fixed':'absolute'};
  overflow: ${props => props.modalOpen ? 'hidden' : 'visible'};
  left: 50%;
  transform: translate(-50%);
`;

// const Wrapper = styled.div`
//   position: fixed;
//   width: 100%;
//   height : 100%;
//   overflow:hidden;
//   `;


const Content = styled.div`
  height: 100px;
  width : 100%;
  border : 1px solid black;
  &:hover{
    cursor:pointer;
  }
`;


const Loading = styled.div`
  fontweight: 600;
`;

const Posts = () => {
  const [pageNum, setPageNum] = useState(0);
  const { list, hasMore, isLoading } = useFetch(pageNum); //뿌려주는 데이터, 갖고올 남은 데이터 갯수, 로딩중인지(boolean값)

  //modal state
  const [modalState, setModalState] = useState(false);
  // console.log(modalState)

  const [postId, setPostId] = useState();

  console.log("postId", postId);

  const history = useHistory();
  const location = useLocation();

  const observerRef = useRef();
  // console.log(pageNum);
  // console.log("posts " + list);
  // console.log("hasmore " + hasMore);
  // console.log("isloading " + isLoading);

  // useEffect(()=>{
  //   useFetch();
  // },[])

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

  // useEffect(() => {
  //   const modalHandling = (event) => {
  //     if (event) { //modalState대신 event로 바꿈 (type error undefined preventdefualt)
  //       event.preventDefault();
  //       // console.log("event")
  //     }
  //     //  console.log("?????")
  //   };
  //   window.addEventListener("touchmove", modalHandling, {
  //     passive: false,
  //   });
  //   modalHandling();
  //   return () => {
  //     window.removeEventListener("touchmove", modalHandling);
  //   };
  // }, [modalState]);




  const openModal = (prop) => {
    setModalState(true);
    setPostId(prop);

  };

  const closeModal = () => {
    setModalState(false);
  };


  //   <Container>
  //   {list?.map((post, idx) => {
  //       setPostId(post.id);
  //       return(
  //     <Content key={idx} onClick={openModal}>
  //       {post.id}
  //       {()=>setPostId(post.id)}
  //       {post.title}
  // </Content>
  // )})}

  // <Container>
  //   {
  //     list?.map((post, idx) => {
  //       setPostId(post.id);
  //       return (
  //         <Content key={idx} onClick={openModal}>
  //           {post.id}
  //           {post.title}
  //         </Content>
  //       )
  //     })
  //   }

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
}

export default Posts


    // const Wrapper = styled.div`
    //   display: grid;
    //   grid-template-columns: repeat(3, minmax(100px, 1fr));
    //   gird-gap: px;
    //   grid-auto-flow: dense;
    //   justify-items: center;
    //   align-content: center;
    //   justify-content: center;
    // `;

    // const Sub = styled.div`
    //   border: 1px solid;
    //   margin: 15px;
    //   height: 240px;
    //   width: 250px;
    // `;



//     const [Posts, setPosts] = useState([]);


//     async function getPosts() {
//       const res = await request({
//         url: API_BASE_URL + "/post/all",
//         method: "GET",
//       })
//       setPosts(res)
//       console.log(res)
//     }

//     useEffect(()=>{
//         getPosts()
//     },[])




//     return (
//         <div>
//             <p>posts</p>
//             <Container>
//                 <Wrapper>
//                     {/* Posts.map((post,index) => (<sub key = {index})) 는 안티 패턴으로 비추  */}
//                     {Posts.map((post) => (
//                         <Sub key ={post.id}>
//                             <p>{post.title}</p>
//                             <p>{post.content}</p>
//                             <p>{post.member.username}</p>
//                             <p>{post.member.password}</p>
//                             <p>{post.member.email}</p>
//                         </Sub>
//                     ))}
//                 </Wrapper>
//             </Container>
//         </div>
//     )