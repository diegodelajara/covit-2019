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
            <fg-input
              class="no-border form-control-lg"
              data-vv-name="email"
              placeholder="Email"
              type="text"
              v-model="user.email"
              v-validate="`required|email`"
              :class="{ 'is-invalid': errors.has('email') }"
              :error="getError('email')"
              @keyup.enter="login">
            </fg-input>

            <fg-input
              class="no-border form-control-lg"
              data-vv-name="password"
              placeholder="Contraseña"
              type="password"
              v-validate="`required`"
              v-model="user.pass"
              :class="{ 'is-invalid': errors.has('password') }"
              :error="getError('password')"
              @keyup.enter="login">
            </fg-input>
          </div>
          <br>
          <br>
          <n-button type="primary" round block @click.native="login" :disabled="loading">
            <span v-if="!loading">Entrar</span>
            <span v-else class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
          </n-button>
        </div>
        <div v-else>
          <div class="condominiums-list"></div>
          <!-- <pre>
            {{ condominiums }}
          </pre> -->
          <ul class="list-group">
            <li
              class="list-group-item pointer"
              v-for="(condominium, key) in condominiums"
              @click="goDashboard()"
              :key="key"
            >
              {{ condominium.name }}
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
      submitted: false,
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
    getError (fieldName) {
      return this.errors.first(fieldName)
    },
    async goDashboard() {
      await this.login()
      this.$router.push("/dashboard")
    },
    async login() {
      this.loading = true
      this.submitted = true
      try {
        // Validar formulario de login
        const valid = await this.$validator.validate().then(valid => valid)

        if (valid) {
          // Valido con firebase, mi user y pass
          const auth = await firebaseAuth.signInWithEmailAndPassword(this.user.email, this.user.pass)
          // Obtengo los condominios asociados al usuario
          this.condominiums = await this.getCondominiums([this.user.email, this.list])
          // Obtengo el usuario de firebase
          await this.getUserFromFirebase(this.user.email)
          // Lo guardo en localStorage
          await setUserToLocalStorage(this.myUser)
          await this.setUser(this.myUser)
          this.loading = false
        } else {
          this.loading = false
          this.submitted = false
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
          this.loading = false
          return
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
            title: 'Errorsito',
            text: errorObject.code,
            type: "error",
            confirmButtonClass: "btn btn-success btn-fill",
            buttonsStyling: false
          })
      })
    }
  },
  mounted() {
    this.$validator.localize('es', {
      messages: {
        required: (field) => 'Requerido',
        email: (field) => 'No es válido'
      },
      attributes: {
        email: 'Email',
        password: 'Contraseña'
      }
    })
  }
}
</script>
<style scoped>
.has-danger:after {
    content: "";
    color: transparent;
}
.has-success:after {
    content: "";
    color: transparent;
}
.pointer {
  cursor: pointer;
  background-color: #0000000d;
  color: #fff;
}
.logo-container {
    width: 70%;
}
.card.card-login.card-plain {
  background-color: #00000012;
}
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
