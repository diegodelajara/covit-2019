
import Vue from 'vue'
import Vuex from 'vuex'

// import getters from './getters'
// import mutations from './mutations'
// import actions from './actions'

Vue.use(Vuex)

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
    },
    setFormData(state) {
      state.formData
    }
  },
  actions: {
    setUser(context, user) {
      context.commit('setUser', user)
    }
  }
  //getters,
  //mutations,
  //actions
})

