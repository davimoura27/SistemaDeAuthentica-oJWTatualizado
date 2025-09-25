import { useEffect, useState } from "react"
import styles from"./Login.module.css"
import { loginUser } from "../Api/Api"
import { ToastNotify } from "../ToastNotify/Toast"
import { ToastContainer } from "react-toastify";
import { useNavigate } from "react-router-dom"
import { EyeSlash,Eye, User} from "phosphor-react";

export function Login(){
    const navigate = useNavigate();
    const[email,setEmail] = useState('');
    const[password, setPassword] = useState('');
    const[showPassword, setShowPassword] = useState(false);

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
    const passwordEyes = () =>{
        setShowPassword((prev) => !prev)
    }
    return(<>
        <ToastContainer
            toastClassName={styles.toastCurtom}
        />
        <div className={styles.container}>
            <div className={styles.body}>
                <div className={styles.title}>
                    <h2>
                        Bem vindo ao login
                    </h2>
                    <p>
                        Preencha os campos para acessar
                    </p>
                </div>
                <div className={styles.formulario}>
                    <form onSubmit={handle}>
                        <div className={styles.inputContainer}>
                            <div className={styles.inputStyles}>
                                <label htmlFor="email">Usuario</label>
                                <div className={styles.containerIconInput}>
                                    <User weight="fill" className={styles.userIcon} />
                                    <input 
                                    id="email"
                                    type="text" 
                                    placeholder="usuario@gmail.com"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    />

                                </div>
                            </div>
                            <div className={styles.inputStyles}>
                                <label htmlFor="senha">Senha</label>
                                <div className={styles.containerIconInput}>
                                {showPassword ? (
                                    <EyeSlash
                                        weight="fill"
                                        className={styles.userIcon}
                                        onClick={passwordEyes}
                                    />
                                ) : (
                                    <Eye
                                        weight="fill"
                                        className={styles.userIcon}
                                        onClick={passwordEyes}
                                    />
                                )}
                                <input 
                                id="senha" 
                                type={showPassword ? "text":"password"} 
                                placeholder="senha de acesso"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                />                               
                            </div>    
                            </div>
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