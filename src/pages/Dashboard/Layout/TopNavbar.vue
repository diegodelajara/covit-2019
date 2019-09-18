<template>
  <navbar :show-navbar="showNavbar">
    <div class="navbar-wrapper">
      <div class="navbar-toggle" :class="{toggled: $sidebar.showSidebar}">
        <navbar-toggle-button @click.native="toggleSidebar">
        </navbar-toggle-button>
      </div>
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

          <span class="dropdown-item" @click="profile">Mi perfil</span>
          <span class="dropdown-item" @click="addUser" v-show="$user.isAllowed('viewParagraph')">Agregar usuario</span>
          <span class="dropdown-item" @click="logout">Salir</span>
          <span>
          </span>
        </drop-down>
      </ul>

    </template>
  </navbar>
</template>
<script>
import { mapMutations } from 'vuex'
import firebase from 'firebase/app'
import 'firebase/auth'
import {Navbar, NavbarToggleButton} from 'src/components';
import {CollapseTransition} from 'vue2-transitions'
import { unsetUser } from 'src/utils/auth'
import agregarusuarioPerimeter from "src/perimeters/agregarusuarioPerimeter"
import { MY_PROFILE, ADD_USER } from 'src/utils/urls'

  export default {
    perimeters: [
      agregarusuarioPerimeter
    ],
    components: {
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
      addUser() {
        console.log('addUser')

      },
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
        // Remover de localStorage
        await unsetUser()
        // Hacer logout de firebase
        await firebase.auth().signOut().then(() => this.$router.push('/'))
      },
      profile() {
        this.$router.push(MY_PROFILE)
      },
      addUser() {
        this.$router.push(ADD_USER)
      }
    }
  }

</script>
<style scope>
  .navbar-nav span {
    cursor: pointer;
  }
</style>
