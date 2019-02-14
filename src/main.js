import './firebase'
import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import DashboardPlugin from './dashboard-plugin'

// Firebase
import VueFire from 'vuefire'
import Firebase from 'firebase'
import 'firebase/firestore'

import App from './App.vue'
import routes from './routes/routes'
import store  from './store'

Vue.use(VueFire)
Vue.use(Firebase)

// plugin setup
Vue.use(VueRouter)
Vue.use(DashboardPlugin)
Vue.use(Vuex)

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkActiveClass: 'active'
});

router.beforeEach((to, from, next) => {
  let usuario = Firebase.auth().currentUser  
  let autorizacion = to.matched.some(record => record.meta.autentificado)

  if (autorizacion && !usuario) {
    next('login')
  } else if (!autorizacion && usuario) {
    next('dashboard')
  } else {
    next()
  }
})

new Vue({
  store,
  el: '#app',
  render: h => h(App),
  router
});

import './assets/sass/element_variables.scss'
