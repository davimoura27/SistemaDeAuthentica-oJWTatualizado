import { toast } from "react-toastify"
import styles from "./Toast.module.css"

export const ToastNotify = {
    success:(msg) => toast.success(msg),
    error:(msg) => toast.error(msg),

    confirm:(message) => toast.info(message)
}