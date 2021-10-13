import React,{useState, useEffect} from 'react'
import { request } from "../utils/UtilsApi";
import axios from "axios"
import { set } from 'react-hook-form';


function Upload() {
  const API_BASE_URL = "http://localhost:8080";
  const ACCESS_TOKEN = "accessToken";

  const [userId, setUserId] = useState("");

  const [file1, setFile1] = useState();
  const [file2, setFile2] = useState([]);

  async function getCurrentUser() {
    if (!localStorage.getItem(ACCESS_TOKEN)) {
      console.log("not okenset");
    }
    const res = await request({
      url: API_BASE_URL + "/user/me",
      method: "GET",
    });
    //console.log(res.id)
    setUserId(res.id);
  }

  useEffect(() => {
    getCurrentUser();
  }, []);

  //유저 프로필 이미지
  const onSubmit1 = (e) => {
    e.preventDefault();
    const url = API_BASE_URL + "/user/profileimage";
    const formData = new FormData();
    formData.append("file", file1);
    const config = {
      headers: {
        "content-type": "multipart/form-data",
        Authorization: "Bearer " + localStorage.getItem(ACCESS_TOKEN),
      },
    };
    return axios.post(url, formData, config);
  };

  const fileChange1 = (e) => {
    setFile1(e.target.files[0]);
  };
  // console.log("file1");
  // console.log(file1);

  const fileList = []; // 업로드한 파일들을 저장하는 배열

  const fileChange2 = (e) => {
      const uploadFiles = Array.prototype.slice.call(e.target.files); // 파일선택창에서 선택한 파일들

    uploadFiles.forEach((uploadFile) => {
      fileList.push(uploadFile);
  });
}

  //유저 피드 사진 업로드 여러개
  const onSubmit2 = (e) => {
    e.preventDefault();
    const url = API_BASE_URL + "/image/feed";
    const formData = new FormData();

    fileList.forEach((file) => {
      // 파일 데이터 저장
      formData.append("files", file);
    });
    // file2.map((eachfile) => {
    //   formData.append("file", eachfile);
    // });
    const config = {
      headers: {
        "content-type": "multipart/form-data",
        Authorization: "Bearer " + localStorage.getItem(ACCESS_TOKEN),
      },
    };

    return axios.post(url, formData, config);
  };

  //이미지 미리보기 처리 까지 같이
  //https://hyunsix.tistory.com/entry/React-input%ED%83%9C%EA%B7%B8%EB%A1%9C-%EC%82%AC%EC%A7%84-%ED%8C%8C%EC%9D%BC-%EC%97%AC%EB%9F%AC%EA%B0%9C-%EC%97%85%EB%A1%9C%EB%93%9C%ED%95%98%EA%B8%B0-%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%EC%97%90%EC%84%9C-%EB%AF%B8%EB%A6%AC%EB%B3%B4%EA%B8%B0-%EA%B0%80%EB%8A%A5%ED%95%98%EA%B2%8C-%EB%A7%8C%EB%93%A4%EA%B8%B0

  return (
    <div>
      <h1>유저 프로필 이미지 업로드</h1>
      <form onSubmit={onSubmit1}>
        <h1>File Upload</h1>
        <input type="file" onChange={fileChange1} name="file" />
        <button type="submit">Upload profile image</button>
      </form>
      <h1>feed 사진 업로드 </h1>
      <form onSubmit={onSubmit2}>
        <h1>File Upload</h1>
        <input type="file" onChange={fileChange2} name="file" />
        <button type="submit">Upload Feed</button>
      </form>
    </div>
  );
}

export default Upload







