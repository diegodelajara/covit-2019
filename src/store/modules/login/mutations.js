import 'src/firebase/firebase'
import { usuariosRef } from 'src/firebase/firebase'

export default {
	setUser(state, user) {
		
		state.user.email = user.email
		state.user.role = user.perfil
		state.user.session = true
		state.user.name = user.nombre
		state.user.lastName = user.apellido
	},
	setRegisterUser(state, newUser) {
		usuariosRef.push(newUser)
	}
}