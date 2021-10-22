import React, { useState, useEffect, useCallback } from "react";
import { API_BASE_URL, request } from "../utils/UtilsApi";

const useFetch = (page) => {
  const [list, setList] = useState([]);
  const [hasMore, setHasMore] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const sendQuery = useCallback(async () => {
    const URL = `${API_BASE_URL}/post/allposts/?page=${page}`;

    try {
      setIsLoading(true);

      const response = await request({
        url: URL,
        method: "GET",
      });
      console.log(response.content);

      if (!response) {
        throw new Error("Post를 불러올 수 없습니다");
      }

      setList((prev) => [...new Set([...prev, ...response.content])]); //나는 content로 들어옴
      setHasMore(response.content.length > 0); //데이터가 더 남아있으면
      setIsLoading(false);
    } catch (e) {
      throw new Error(`에러!! ${e.message}`);
    }
  }, [page]);

  useEffect(() => {
    sendQuery();
  }, [sendQuery, page]);

  return { hasMore, list, isLoading };
};
export default useFetch;
