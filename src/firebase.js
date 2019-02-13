import { initializeApp } from 'firebase'

const app =  initializeApp({
    apiKey: "AIzaSyDy9hJRWVBPVQOvC-WT32F86DLTQtwtGJ8",
    authDomain: "covit-webapp.firebaseapp.com",
    databaseURL: "https://covit-webapp.firebaseio.com",
    projectId: "covit-webapp",
    storageBucket: "covit-webapp.appspot.com",
    messagingSenderId: "604748508353"
  })

  export const db = app.database()
  export const ingresosRef = db.ref('ingresos')