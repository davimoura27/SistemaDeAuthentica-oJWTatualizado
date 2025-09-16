import { useState } from "react";
import { testApi } from "../Api/Api";
import styles from "./TesteRequisicao.module.css"
import { useNavigate } from "react-router-dom"

export function TesteRequisicao(){
    const navigate = useNavigate();
    const[mensagem, setMensagem] = useState('');
    const[error, setError] = useState('');

    const handle = async (e) =>{
        e.preventDefault();            
        const result = await testApi();
          if(result.status === 200 && result.data){       
            setMensagem(result.data.mensagem) 
          }else{
            setError(result.response?.data?.error) 
          }            
    }
    return(
        <>
        <div className={styles.container}>
          <div className={styles.body}>
            {mensagem.length === 0 && error.length === 0 ? 
            <div>             
              <div className={styles.containerButton}>
                <h2 className={styles.status}>Usuario logado!</h2>
                <button onClick={(e) => handle(e)}>Enviar requisição</button>
               </div>
            </div>
              :
              mensagem.length ?                          
                <div className={styles.message}><h2>{mensagem}</h2></div>
              :
                <div className={styles.message}><h2>{error}</h2></div>
              }
            <div className={styles.buttonSair}>
              <button onClick={(e) => navigate("/")}>Sair</button>
            </div>
          </div>
        </div>
        </>
    )
}