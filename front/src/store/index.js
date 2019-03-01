
import Vue from 'vue'
import Vuex from 'vuex'
import 'src/firebase'
import Firebase from 'firebase'
import { usuariosRef } from 'src/firebase'

Vue.use(Vuex)
Vue.use(Firebase)

export default new Vuex.Store({
  state: {
    user: {
      isLogged: false,
      name: '',
      email: '',
      role: null
    },
    formData: {
      nombre: null,
      monto: 0
    }
  },
  getters: {
    getAuth(state) {
      return state.user.isLogged
    }
  },
  mutations: {
    setUser(state, user) {
      state.user.email = user.usuario
      state.user.role = user.perfil
      state.user.isLogged = true
      state.user.name = user.nombre
    },
    setFormData(state) {
      state.formData
    },
    setRegisterUser(state, newUser) {
      console.log(newUser)
      
      usuariosRef.push(this.newUser)
    }
  },
  actions: {
    setUser(context, user) {
      context.commit('setUser', user)
    }
  }
})

// import Vue from 'vue'
// import Vuex from 'vuex'

// import actions from './actions'
// import getters from './getters'
// import mutations from './mutations'
// import state from './state'
// import modules from './modules'

// Vue.use(Vuex)

// const store = () =>
//   new Vuex.Store({
//     modules,
//     actions,
//     getters,
//     mutations,
//     state
//   })

//   export default state

