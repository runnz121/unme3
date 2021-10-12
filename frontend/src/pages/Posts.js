import React,{useEffect,useState} from 'react'
import axios from 'axios'
import styled from "styled-components"

function Posts() {
    const [Posts, setPosts] = useState([]);

    useEffect(()=> {
        axios
            .get("http://localhost:8080/post/all")
            //.then((res) => console.log(res))
            .then(({data}) => setPosts(data));
    }, []);


    const Container = styled.div`
        width : 850px;
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
          width : 250px;
          
    `;


    return (
        <div>
            <p>posts</p>
            <Container>
                <Wrapper>
                    {Posts.map((post, index) => (
                        <Sub key ={index}>
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
    )
}

export default Posts
