import { Route, Routes } from "react-router-dom";
import {Login} from "../Login/Login";
import {Register} from "../Register/Register";
import { TesteRequisicao } from "../TesteRequisicao/TesteRequisicao";

export function Router(){
    return(<>
     <Routes>
        <Route path="/" element={<Login/>}/>.
        <Route path="/register" element={<Register/>}/>
        <Route path="/teste" element={<TesteRequisicao/>}/>
    </Routes> </>)
}