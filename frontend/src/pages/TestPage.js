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
  const [pageNum, setPageNum] = useState(1);
  const { list, hasMore, isLoading } = useFetch(pageNum);
  const observerRef = useRef();

  const observer = (node) => {
    if (isLoading) return;
    if (observerRef.current) observerRef.current.disconnect();

    observerRef.current = new IntersectionObserver(([entry]) => {
      if (entry.isIntersecting && hasMore) {
        setPageNum((page) => page + 1);
      }
    });

    node && observerRef.current.observe(node);
  };

  return (
    <CardListContainer>
      {list?.map((card) => (
        <Cardd>{card.id}</Cardd>
      ))}
      <div ref={observer} />
      <>{isLoading && <Loading />}</>
    </CardListContainer>
  );
};
export default CardList;
