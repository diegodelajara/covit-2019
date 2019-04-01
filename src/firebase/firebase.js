import Firebase from 'firebase/app'
import credentials from './credentials'
import 'firebase/database'

export const firebaseApp = Firebase.initializeApp(credentials.firebase)

export const db = firebaseApp.database()
export const usuariosRef = db.ref("usuarios")
