import axios from "axios";
import { ToastNotify } from "../ToastNotify/Toast";


export const api = axios.create({
    baseURL: "http://localhost:8080",
    headers:{
        "Content-Type": "application/json"
    },
});

export const registerUser = async (userName, telephone, 
    dateBirth, email, password) =>{
        try {
            const res = await api.post('/users/register', {
                userName,
                telephone,
                dateBirth,
                email,
                password
            })
            return{sucess:true,
                   data:res.data,
                   error:false}

        } catch (error) {
            let data = error.response?.data;
            if(typeof data === "string"){
                return{sucess:false,
                       data:false,
                       error:data 
                       }
            }else if(typeof data === "object" && data !== null){
                const keys = Object.keys(data)
                if(keys.length > 0){                    
                    const errorMsg = data[keys[0]] 
                    console.log(errorMsg)             
                    return {sucess:false,
                            data:false,
                            error: errorMsg}
                    }
                }          
            }         
        }   
export const loginUser = async (email, password) => {
    try {
        const resp = await api.post('/auth/login', {
            email,
            password
        })
        if(resp.status === 200 && resp.data){
            const userData = {
                email: resp.data.email,
                token: resp.data.token
            }
            localStorage.setItem("userData", JSON.stringify(userData))
            return userData;
        }        
    } catch (error) {
        console.log(error.response.data)
        return error       
    }
}
export const testApi = async () => {
    try {
        const user = JSON.parse(localStorage.getItem("userData"))
        api.defaults.headers.common["Authorization"] = `Bearer ${user.token}`;
        const response = await api.get('/test/register');
        console.log(response.data)
        return response
    } catch (error) {
        console.log(error.response.data)
        return error
    }
}
