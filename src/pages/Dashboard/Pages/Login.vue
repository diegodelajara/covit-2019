<template>
  <div class="col-md-4 ml-auto mr-auto">
    <form @submit.prevent>
      <card class="card-login card-plain">
        <div slot="header">
          <div class="logo-container">
            <img src="@/assets/img/logo.png" alt="">
          </div>
        </div>

        <div v-if="condominiums == null">
          <div>
            <fg-input class="no-border form-control-lg"
                      placeholder="Email"
                      addon-left-icon="now-ui-icons users_circle-08"
                      type="text"
                      v-model="user.email"
                      @keyup.enter="login">
            </fg-input>

            <fg-input class="no-border form-control-lg"
                      placeholder="Contraseña"
                      addon-left-icon="now-ui-icons text_caps-small"
                      type="password"
                      v-model="user.pass"
                      @keyup.enter="login">
            </fg-input>
          </div>

          <div>
            
            <n-button type="primary" round block @click.native="login">
              <span v-if="!loading">Entrar</span>
              <span v-else class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
            </n-button>
            <!-- <Loader
              text="Entrar"
              :loading="loading"
              :status="status"
              @on-submit="login"
              /> -->

            <div class="pull-right">
              <!--h6><a href="#pablo" class="link footer-link">Need Help?</a></h6-->
            </div>
          </div>
        </div>
        <div v-else>
          <div class="condominiums-list"></div>
          <!-- <pre>
            {{ condominiums }}
          </pre> -->
          <ul class="list-group">
            <li v-for="(condominium, key) in condominiums" :key="key" class="list-group-item">
              <p v-for="(place, i) in condominium.condominiums" :key="i">{{ place.name }}</p>
            </li>
          </ul>
        </div>
      </card>
    </form>
  </div>
</template>
<script>
import swal from "sweetalert2"
import { setUserToLocalStorage } from "src/utils/auth"
import { getErrorMessage } from 'src/utils/functions'
import { mapGetters, mapMutations, mapActions } from "vuex"
import { firebaseAuth } from "src/firebase/firebaseAuth"
import { usuariosRef, userInfoRef} from "src/firebase/firebase"

import Loader from 'src/components/Utils/Loader'

export default {
  firebase: {
    usuariosRef,
    userInfoRef
  },
  components: {
    Loader
  },
  data() {
    return {
      condominiums: null,
      loading: false,
      list: [
        { 
          email: 'diego@gmail.com',
          condominiums: [
            { id: 1, name: 'Condominio 1' },
            { id: 2, name: 'Condominio 2' },
            { id: 3, name: 'Condominio 3' },
            { id: 4, name: 'Condominio 4' }
          ] 
        }
      ],
      myUser: {
        nombre: null,
        username: null,
        apellido: null,
        email: null,
        perfil: null,
        uid: null
      },
      status: '',
      user: {
        email: "",
        pass: ""
      }
    }
  },
  computed: {
    ...mapGetters([
      'getUser'
    ])
  },
  methods: {
    ...mapActions([
      'getCondominiums'
    ]),
    ...mapMutations([
      "setUser"
    ]),
    async login() {
      this.loading = true
      try {
        // Valido con firebase, mi user y pass
        const auth = await firebaseAuth.signInWithEmailAndPassword(this.user.email, this.user.pass)
        if (auth) {
          await this.getUserFromFirebase(this.user.email)
          await setUserToLocalStorage(this.myUser)
          await this.setUser(this.myUser)
          this.condominiums = await this.getCondominiums([this.user.email, this.list])
          // this.$router.push("/dashboard")
          this.loading = false
        }
      } catch (error) {
        if (error.code) {
          const errorMsg = await getErrorMessage(error.code)
          swal({
            title: errorMsg.title,
            text: errorMsg.msg,
            type: "error",
            confirmButtonClass: "btn btn-success btn-fill",
            buttonsStyling: false
          })
        } else {
          swal({
            title: error,
            text: error,
            type: "error",
            confirmButtonClass: "btn btn-success btn-fill",
            buttonsStyling: false
          })
        }
        this.loading = false
      }
    },
    async getUserFromFirebase(loggedUser) {
      const firebaseUsers = await this.usuariosRef
      const firebaseUsersInfo = await this.userInfoRef
    
      let users = await firebaseUsers.filter(item => item.email === loggedUser)
      let usersInfo = await firebaseUsersInfo.filter(item => item.email === loggedUser)

      const _self = this
      userInfoRef.on("value", function(snapshot) {
        let userFromFireBase = snapshot.val()[firebaseAuth.currentUser.uid]
        if (userFromFireBase) {
          _self.myUser.nombre = userFromFireBase.firstName,
          _self.myUser.email = userFromFireBase.email,
          _self.myUser.perfil = userFromFireBase.perfil
          _self.myUser.uid = firebaseAuth.currentUser.uid
        }

      }, function (errorObject) {
          swal({
            title: 'Error',
            text: errorObject.code,
            type: "error",
            confirmButtonClass: "btn btn-success btn-fill",
            buttonsStyling: false
          })
      })
    }
  }
}
</script>
<style>
.navbar-nav .nav-item p {
  line-height: inherit;
  margin-left: 5px;
}
input.form-control {
  height: auto;
}
.login-page .card-login .logo-container,
.register-page .card-login .logo-container,
.pricing-page .card-login .logo-container,
.lock-page .card-login .logo-container {
  width: 100%;
  margin: 0 auto;
  margin-bottom: 5px;
}
</style>
