import React, { useState, useEffect, useCallback } from "react";
import { API_BASE_URL, request } from "../utils/UtilsApi";


// const END_POINT = "http//localhost:8080/post/allposts";

//const END_POINT = "/posts/all"
const useFetch = (page) => {
  const [list, setList] = useState([]);
  const [hasMore, setHasMore] = useState(false);
  const [isLoading, setIsLoading] = useState(false); //로딩 구현 시에만 필요

  //query API 요청 보내기
  const sendQuery = useCallback(async () => {
    const URL = `${API_BASE_URL}/post/allposts/?page=${page}`;
    try {
      setIsLoading(true);
      // const response = await (await fetch(URL)).json();
     
       //const response = await (await fetch(URL)).json();

       const response = await request({
         url: URL,
         method : "GET"
       })
        console.log(response.content);

      if (!response) {
        throw new Error(`server error.`);
      }
      
      setList((prev) => [...new Set([...prev, ...response.content])]);
      setHasMore(response.length > 0);
      setIsLoading(false);
    } catch (e) {
      throw new Error(`error. ${e.message}`);
    }
  }, [page]);

  useEffect(() => {
    sendQuery();
  }, [sendQuery, page]);

  return { hasMore, list, isLoading };
};
export default useFetch;