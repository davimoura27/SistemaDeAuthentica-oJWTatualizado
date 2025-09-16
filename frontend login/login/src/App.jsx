import { BrowserRouter } from 'react-router-dom'
import styles from"./App.module.css"
import { Router } from './components/Routes/Router'

export function App() { 
  return (
    <>
    <div className={styles.container} >
      <BrowserRouter>
          <Router/>
      </BrowserRouter>
    </div>
    </>
  )
}

