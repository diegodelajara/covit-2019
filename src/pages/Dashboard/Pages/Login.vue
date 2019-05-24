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
                    v-model="user.email">
          </fg-input>

          <fg-input class="no-border form-control-lg"
                    placeholder="Contraseña"
                    addon-left-icon="now-ui-icons text_caps-small"
                    type="password"
                    v-model="user.pass">
          </fg-input>
        </div>

        <div>
          <n-button type="primary" round block @click.native="login">
            Entrar
          </n-button>
          <div class="pull-left">
            <h6>
              <router-link class="link footer-link" to="/register">
                Create Account
              </router-link>
            </h6>



          </div>

          <div class="pull-right">
            <!--h6><a href="#pablo" class="link footer-link">Need Help?</a></h6-->
          </div>
        </div>
      </card>
    </form>
  </div>
</template>
<script>
import { setUserToLocalStorage } from "src/utils/auth"
import { mapGetters, mapMutations } from "vuex"
import { firebaseAuth } from "src/firebase/firebaseAuth"
import { usuariosRef } from "src/firebase/firebase"

export default {
  firebase: {
    usuariosRef
  },
  data() {
    return {
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
    ...mapMutations([
      "setUser"
    ]),
    login() {
      
      firebaseAuth.signInWithEmailAndPassword(this.user.email, this.user.pass)
        .then(user => {
          const firebaseUser = this.getUserFromFirebase(this.user.email)
          // console.log(userFb)
          this.setUser(firebaseUser)
          setUserToLocalStorage(firebaseUser)
          this.$router.push("/dashboard")
        })
        .catch(err => {
          alert(err.message)
        })
    },
    getUserFromFirebase(loggedUser) {
      const firebaseUsers = this.usuariosRef
    
      const users = firebaseUsers.filter(item => item.email === loggedUser)
            
      const user = {
        apellido: users[0].apellido,
        email: users[0].email,
        nombre: users[0].nombre,
        perfil: users[0].perfil
      }
      return user
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
