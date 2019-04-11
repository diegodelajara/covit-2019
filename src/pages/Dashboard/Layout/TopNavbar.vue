<template>
  <navbar :show-navbar="showNavbar">
    <div class="navbar-wrapper">
      <div class="navbar-toggle" :class="{toggled: $sidebar.showSidebar}">
        <navbar-toggle-button @click.native="toggleSidebar">
        </navbar-toggle-button>
      </div>
      <a class="navbar-brand" href="#pablo">
        {{$route.name}}
      </a>
    </div>
    <button @click="toggleNavbar" class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navigation"
            aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-bar navbar-kebab"></span>
      <span class="navbar-toggler-bar navbar-kebab"></span>
      <span class="navbar-toggler-bar navbar-kebab"></span>
    </button>

    <template slot="navbar-menu">

      <!-- <form>
        <div class="input-group no-border">
          <fg-input placeholder="Search..." addon-right-icon="now-ui-icons ui-1_zoom-bold">
          </fg-input>
        </div>
      </form> -->

      <ul class="navbar-nav">
        
        <drop-down tag="li"
                   position="right"
                   class="nav-item"
                   icon="now-ui-icons users_single-02">

          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <a class="dropdown-item" href="#">Something else here</a>
          <a class="dropdown-item" href="#" @click="logout">Salir</a>
        </drop-down>
      </ul>

    </template>
  </navbar>
</template>
<script>
import { mapMutations } from 'vuex'
import firebase from 'firebase/app'
import 'firebase/auth'
import {RouteBreadCrumb, Navbar, NavbarToggleButton} from 'src/components';
import {CollapseTransition} from 'vue2-transitions'
import { unsetUser } from 'src/utils/auth'

  export default {
    components: {
      RouteBreadCrumb,
      Navbar,
      NavbarToggleButton,
      CollapseTransition
    },
    computed: {
      routeName() {
        const {name} = this.$route
        return this.capitalizeFirstLetter(name)
      }
    },
    data() {
      return {
        activeNotifications: false,
        showNavbar: false,
        user: {
          email: '',
          session: false,
          name: '',
          role: null
        }
      }
    },
    methods: {
      ...mapMutations([
        'setUser'
      ]),
      capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1)
      },
      toggleNotificationDropDown() {
        this.activeNotifications = !this.activeNotifications
      },
      closeDropDown() {
        this.activeNotifications = false
      },
      toggleSidebar() {
        this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
      },
      toggleNavbar() {
        this.showNavbar = !this.showNavbar;
      },
      hideSidebar() {
        this.$sidebar.displaySidebar(false)
      },
      async logout() {
        await unsetUser()
        await firebase.auth().signOut().then(() => this.$router.push('/login'))
      }
    }
  }

</script>
<style>

</style>
