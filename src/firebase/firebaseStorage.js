import { firebaseApp } from './firebase'
import 'firebase/storage'

export const firebaseStorage = firebaseApp.storage()