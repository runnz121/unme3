import * as React from "react";
import axios from "axios";

const FileUpload = () => {
  const fileList = []; // 업로드한 파일들을 저장하는 배열

  const onSaveFiles = (e) => {
    const uploadFiles = Array.prototype.slice.call(e.target.files); // 파일선택창에서 선택한 파일들

    uploadFiles.forEach((uploadFile) => {
      fileList.push(uploadFile);

      console.log(uploadFile)
      console.log(fileList)
    });
  };



  const onFileUpload = () => {
    const formData = new FormData();

    fileList.forEach((file) => {
      // 파일 데이터 저장
      formData.append("multipartFiles", file);
    });

    // 객체
    const foodDto = {
      name: "피자",
      price: 13500,
    };

    formData.append("stringFoodDto", JSON.stringify(foodDto)); // 직렬화하여 객체 저장

    axios.post("http://localhost:8080/uploadFiles", formData);
  };

  console.log(fileList)

  return (
    <div>
      <input
        type="file"
        multiple
        /* 파일 여러개 선택 가능하게 하기 */ onChange={onSaveFiles}
      />
      <button onClick={onFileUpload}>파일 업로드</button>
    </div>
  );
};

export default FileUpload;

    // const request1 = (options) => {
    //     const headers = new Headers({
    //       "Content-Type": "multipart/form-data",
    //     });

    //     if (localStorage.getItem(ACCESS_TOKEN)) {
    //       headers.append(
    //         "Authorization",
    //         "Bearer " + localStorage.getItem(ACCESS_TOKEN)
    //       );
    //     }

    //     const defaults = { headers: headers };
    //     options = Object.assign({}, defaults, options);

    //     return fetch(options.url, options).then((response) =>
    //       response.json().then((json) => {
    //         if (!response.ok) {
    //           return Promise.reject(json);
    //         }
    //         return json;
    //       })
    //     );
    //   };


