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
                    v-model="user.clave">
          </fg-input>
        </div>

        <div slot="footer">
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
import { setUserToLocalStorage } from 'src/utils/auth'
import { mapState, mapMutations } from 'vuex'
import firebase from 'firebase'
import { usuariosRef } from 'src/firebase'

  export default {
    firebase: {
      usuariosRef
    },
    data() {
      return {
        user: {
          email: '',
          clave: ''
        },
        fireBaseUser: null
      }
    },
    mounted() {
      this.user.role = this.loggedUser.role;
    },
    computed: {
      ...mapState({
        loggedUser: state => state.user
      }),
    },
    methods: {
      ...mapMutations([
        'setUser'
      ]),
      login() {
        firebase.auth().signInWithEmailAndPassword(this.user.email, this.user.clave)
        .then((user) => {
          
          this.getUserFromFirebase(this.user.email)
          
          this.setUser(this.fireBaseUser)
          setUserToLocalStorage(this.fireBaseUser)
          this.$router.replace('/dashboard')
        }).catch((err) => {
          alert(err.message)
        })
      },
      getUserFromFirebase(loggedUser) {
        let tempData = []
        let usuarios = this.usuariosRef

        usuarios.forEach(element => {
          if(element.email === loggedUser) {
            tempData.push({
              email: element.email,
              lastName: element.apellido,
              role: element.perfil,
              name: element.nombre,
              session: true
            })            
          }          
          if(this.fireBaseUser = tempData[0])
            return true
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
  .login-page .card-login .logo-container, .register-page .card-login .logo-container, .pricing-page .card-login .logo-container, .lock-page .card-login .logo-container {
    width: 100%;
    margin: 0 auto;
    margin-bottom: 5px;
  }
</style>
