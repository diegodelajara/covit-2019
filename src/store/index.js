
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
      role: 'user',
      info: {
        role: 'user'
      }
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
    },
    changeRole(state, newRole) {
      state.user.info.role = newRole; // eslint-disable-line no-param-reassign
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

