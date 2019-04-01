import './firebase/firebase'
import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter from 'vue-router'
import DashboardPlugin from './dashboard-plugin'
import VueKindergarten from 'vue-kindergarten'
import { createSandbox } from 'vue-kindergarten'

import RouteGoverness from './governesses/RouteGoverness'
import * as perimeters from './perimeters'
import child from './child'

// Firebase
import VueFire from 'vuefire'
import Firebase from 'firebase'

import App from './App.vue'
import routes from './routes/routes'
import store from './store'

import { getUserFromLocalStorage } from 'src/utils/auth'

Vue.config.productionTip = false

Vue.use(VueFire)
Vue.use(Firebase)

// plugin setup
Vue.use(VueRouter)
Vue.use(DashboardPlugin)
Vue.use(Vuex)

Vue.use(VueKindergarten, {
  child,
})

// configure router
const router = new VueRouter({
  mode: 'history',
  routes, // short for routes: routes
  linkActiveClass: 'active'
})

if (!child() && getUserFromLocalStorage()) {
  store.commit('setUser', getUserFromLocalStorage())
}

const showTheBugInAction = false; // ********  Set this to true to see the error
//                                   ********  "Cannot read property 'from' of undefined"
router.beforeEach((to, from, next) => {
  const perimeter = perimeters[`${to.name}Perimeter`]

  if (perimeter) {
    let sandbox = null

    if (showTheBugInAction) {
      sandbox = createSandbox(child(store), {
        perimeters: [
          perimeter,
        ],
        governess: new RouteGoverness({ from, to, next }),
      })
      return sandbox.guard('route')
    }

    sandbox = createSandbox(child(store), {
      perimeters: [
        perimeter,
      ],
    })
    if (!sandbox.isAllowed('route')) {
      return next('/')
    }
  }
  return next()
})



new Vue({
  store,
  el: '#app',
  render: h => h(App),
  router
});

import './assets/sass/element_variables.scss'
