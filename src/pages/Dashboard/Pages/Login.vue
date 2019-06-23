<template>
  <div class="col-md-4 ml-auto mr-auto">
    <form @submit.prevent>
      <card class="card-login card-plain">

        <div slot="header">
          <div class="logo-container">
            <img src="@/assets/img/logo.png" alt="">
          </div>
        </div>

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
          <!-- <n-button type="primary" round block @click.native="login">
            Entrar
          </n-button> -->
          
          <Loader
            text="Entrar"
            :isLoading="isLoading"
            :status="status"
            @on-submit="login"
             />

          <div class="pull-right">
            <!--h6><a href="#pablo" class="link footer-link">Need Help?</a></h6-->
          </div>
        </div>
      </card>
    </form>
  </div>
</template>
<script>
import swal from "sweetalert2"
import { setUserToLocalStorage } from "src/utils/auth"
import { getErrorMessage } from 'src/utils/functions'
import { mapGetters, mapMutations } from "vuex"
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
      user: {
        email: "",
        pass: ""
      },
      myUser: {
        nombre: null,
        username: null,
        apellido: null,
        email: null,
        perfil: null,
        uid: null
      },
      isLoading: false,
      status: ''
    }
  },
  computed: {
    ...mapGetters([
      'getUser'
    ])
  },
  methods: {
    ...mapMutations([
      "setUser"
    ]),
    async login() {
      this.isLoading = true
      try {
        const auth = await firebaseAuth.signInWithEmailAndPassword(this.user.email, this.user.pass)
        if (auth) {
          this.isLoading = false
          await this.getUserFromFirebase(this.user.email)
          await setUserToLocalStorage(this.myUser)
          await this.setUser(this.myUser)
          this.$router.push("/dashboard")
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
          console.log('%c User', 'color: cyan;', error)
        }
        
      }
    },
    async getUserFromFirebase(loggedUser) {
      const firebaseUsers = await this.usuariosRef
      const firebaseUsersInfo = await this.userInfoRef
    
      let users = await firebaseUsers.filter(item => item.email === loggedUser)
      let usersInfo = await firebaseUsersInfo.filter(item => item.email === loggedUser)


      let _self = this
      userInfoRef.on("value", function(snapshot) {
        const userFromFireBase = snapshot.val()[firebaseAuth.currentUser.uid]
        // Seteo de los datos del usuario
        _self.myUser.nombre = userFromFireBase.firstName,
        _self.myUser.username = userFromFireBase.username,
        _self.myUser.apellido = userFromFireBase.lastName,
        _self.myUser.email = userFromFireBase.email,
        _self.myUser.perfil = userFromFireBase.profile
        _self.myUser.uid = firebaseAuth.currentUser.uid
        console.log('%c userFromFireBase', 'color: tomato;', userFromFireBase)
      }, function (errorObject) {
        console.log("The read failed: " + errorObject.code)
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
