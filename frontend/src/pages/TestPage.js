import React,{useEffect, useState, useRef} from "react"
import useFetch from "../components/TestComponent"
import styled from "styled-components"

const CardListContainer = styled.div`
      width : 100%;
      height : 100%;
`;

const Cardd = styled.div`
      height: 100px;
      width : 100%;
      border : 1px solid black;

`;

const Loading  =styled.div`
    fontweight: 600;

`;


const CardList = () => {
  const [pageNum, setPageNum] = useState(0);
  const { list, hasMore, isLoading } = useFetch(pageNum); //뿌려주는 데이터, 갖고올 남은 데이터 갯수, 로딩중인지(boolean값)

  const observerRef = useRef();

  console.log(pageNum)
  console.log("list "+list)
  console.log("hasmore "+hasMore)
  console.log("isloading "+isLoading)


  const options = {
    root: null,
    rootMargin: "10px",
    threshold: 0.5,
  };

  const observer = (node) => {
    if (isLoading) return;
    if (observerRef.current) observerRef.current.disconnect();

    observerRef.current = new IntersectionObserver(([entry]) => {
      if (entry.isIntersecting) {//entry.isIntersection : 프로퍼티가 관찰하려는 dom객체의  threshold초과했는지
        setPageNum((page) => page + 1);
      }
    }, options);

    node && observerRef.current.observe(node);
  };

  return (
    <CardListContainer>
      {list?.map((card,idx) => (
        <Cardd key = {idx}>
          {card.title}
          {card.content}
          {card.member.username}
          {card.member.emial}
          </Cardd>
      ))}
      <div ref={observer} />
      <>{isLoading && <Loading />}</>
    </CardListContainer>
  );
};
export default CardList;
