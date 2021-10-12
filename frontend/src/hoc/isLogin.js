
const isLogin= () => {
    const checkToken = localStorage.getItem('accessToken');
    if(checkToken){
        return true;
    } else{
        return false;
    }
}

export default isLogin;