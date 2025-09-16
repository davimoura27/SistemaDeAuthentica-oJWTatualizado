import { useEffect, useState } from "react"
import styles from"./Login.module.css"
import { loginUser } from "../Api/Api"
import { ToastNotify } from "../ToastNotify/Toast"
import { ToastContainer } from "react-toastify";
import { useNavigate } from "react-router-dom"

export function Login(){
    const navigate = useNavigate();
    const[email,setEmail] = useState('');
    const[password, setPassword] = useState('');

    const handle = async (e) =>{
        e.preventDefault();      
        const response = await loginUser(email, password);
        if(response.token){
            console.log(response)
            navigate("/teste")
        }else{ 
            const data = response.response?.data;           
            let error;
                data.email
                ? error = response.response.data.email                
                : data.password 
                ? error = response.response.data.password         
                : error = response.response.data 
                    
            ToastNotify.error(error)
        }                                    
    }
    return(<>
        <ToastContainer
            toastClassName={styles.toastCurtom}
        />
        <div className={styles.container}>
            <div className={styles.body}>
                <div className={styles.title}>
                    <h2>
                        Bem vido a tela de login!
                    </h2>
                </div>
                <div className={styles.formulario}>
                    <form onSubmit={handle}>
                        <div className={styles.inputContainer}>
                            <input 
                            id="email"
                            type="text" 
                            placeholder="E-mail"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            />
                            <input 
                            id="senha" 
                            type="password" 
                            placeholder="Senha"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            />
                        </div>
                        <div className={styles.buttonContainer}>
                            <button type="submit">Entrar</button>
                            <p>Ainda não tem uma conta? <a href="/register">Registre-se</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </>)
} 