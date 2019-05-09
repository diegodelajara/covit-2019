
import Vue from 'vue'
import Vuex from 'vuex'


import state from './state'
import getters from './getters'
import mutations from './mutations'
import actions from './actions'

import login from './modules/login'
import contabilidad from './modules/contabilidad'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    login,
    contabilidad
  },
  state,
  getters,
  mutations,
  actions
})

export default store


