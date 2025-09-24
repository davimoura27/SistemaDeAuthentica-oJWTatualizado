import { useState } from "react"
import styles from "./Register.module.css"
import { registerUser } from "../Api/Api";
import {ToastNotify} from "../ToastNotify/Toast"
import { ToastContainer } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function Register(){
   const [userName, setUsername] = useState('');
   const [telephone, setTelephone] = useState('');
   const [dateBirth, setDataBirth] = useState('');
   const [email, setEmail] = useState('');
   const [password, setPassword] = useState('');
   const [confirmPassword, setConfirmPassword] = useState('')
   const navigate = useNavigate();

   const handle = async (e) =>{
        e.preventDefault();
        const birthYear = parseInt(dateBirth.split("-")[0],10);
        const currentYear = new Date().getFullYear();
        if(isNaN(birthYear) || birthYear < 1925 || birthYear > currentYear){
            ToastNotify.error("Ano de nascimento invalido!")
            return;
        }
        const campos = [userName, telephone, dateBirth, email, password, confirmPassword]
            if(confirmPassword === password && campos.every(campo => campo.trim() !== "") ){      
                const response = await registerUser(
                    userName,
                    telephone,
                    dateBirth,
                    email,
                    password       
                )
                if(response.sucess){
                    ToastNotify.success("Usuario registrado com sucesso!")
                    setTimeout(() => {
                    navigate("/")
                    }, 4000)
                }else{
                    ToastNotify.error(response.error)
                }
                }else if(confirmPassword !== password){
                    ToastNotify.error("Verifique a confirmação de senha")
                }else{
                    ToastNotify.error("Preencha todos os campos!")
                }                   
            }
    
     return(
        <>
        <ToastContainer
        toastClassName={styles.toastCurtom}
        />
        <div className={styles.container}>
            <div className={styles.body}>
                <div className={styles.title}>
                 <h2>
                    Formulario de cadastro
                 </h2>
                </div>
                <div className={styles.formulario}>
                    <form onSubmit={handle}>
                        <div className={styles.inputContainer}>
                            <div className={styles.inputStyles}>
                                <label htmlFor="senha">Nome de usuario</label>
                                <input 
                                id="username" 
                                type="text"  
                                placeholder="Nome completo"
                                value={userName}
                                onChange={(e) => setUsername(e.target.value)}
                                />
                            </div>
                            <div className={styles.inputStyles}>
                                <label htmlFor="senha">Usuario</label>
                                <input 
                                id="email" 
                                type="text"
                                placeholder="E-mail"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                />
                            </div>
                            <div className={styles.inputStyles}>
                                <label htmlFor="senha">Tel.</label>
                                <input 
                                id="telefone" 
                                type="tel" 
                                placeholder="DDD + Telefone"
                                value={telephone}
                                onChange={(e) => setTelephone(e.target.value)}
                                />
                            </div>
                            <div className={styles.inputStyles}>
                                <label htmlFor="senha">Data de nascimento</label>
                                <input 
                                id="aniversario" 
                                type="date" 
                                placeholder="Data de aniversario"
                                value={dateBirth}
                                onChange={(e) => setDataBirth(e.target.value)}
                                />
                            </div>
                            <div className={styles.inputStyles}>
                                <label htmlFor="senha">Senha</label>
                                <input 
                                id="senha" 
                                type="password" 
                                placeholder="Senha para cadastro"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                />
                            </div>
                            <div className={styles.inputStyles}>
                                <label htmlFor="senha">Confirmar senha</label>
                                <input 
                                id="confirmsenha" 
                                type="password" 
                                placeholder="Digite a senha novamente"
                                value={confirmPassword}
                                onChange={(e) => setConfirmPassword(e.target.value)}
                                />
                            </div>                               
                        </div>                        
                        <div className={styles.buttonContainer}>
                            <button type="submit">Registrar</button>
                            <p>Já tem uma conta? <a href="/">Fazer login</a></p>
                        </div>                      
                    </form>
                </div>    
            </div>    
        </div>
    </>)
}