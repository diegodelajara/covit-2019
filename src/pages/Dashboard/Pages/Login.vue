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
          <div class="emial">
            <fg-input
              class="no-border form-control-lg"
              data-vv-name="email"
              placeholder="Email"
              type="text"
              v-model.trim="user.email"
              v-validate="`required|email`"
              :class="{ 'is-invalid': errors.has('email') }"
              :error="getError('email')"
              @keyup.enter="login">
            </fg-input>
          </div>
          <div class="pass" :class="{visible: passwordType === 'text' }">
            <i class="now-ui-icons ui-1_lock-circle-open" @click="showPass()"></i>
            <fg-input
              class="no-border form-control-lg"
              data-vv-name="password"
              placeholder="Contraseña"
              :type="passwordType"
              v-validate="`required`"
              v-model.trim="user.pass"
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
              v-for="(condominium, key) in condominiums.condominiums"
              @click="selectCondominium(condominium)"
              :key="key"
            >
              {{ condominium.name }}
            </li>
          </ul>
        </div>
      </card>
    </form>
    <notifications></notifications>
  </div>
</template>
<script>
import swal from "sweetalert2"
import { setUserToLocalStorage } from "src/utils/auth"
import { getErrorMessage } from 'src/utils/functions'
import { mapGetters, mapMutations, mapActions } from "vuex"

import Loader from 'src/components/Utils/Loader'

export default {
  components: {
    Loader
  },
  data() {
    return {
      condominiums: null,
      numCondominiums: null,
      loading: false,
      myUser: {
        nombre: null,
        username: null,
        apellido: null,
        email: null,
        perfil: 'admin',
        uid: null,
        condominio: null
      },
      passwordType: 'password',
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
    showPass() {
      this.passwordType = this.passwordType === 'password' ? 'text' : 'password'
    },
    selectCondominium(building) {
      this.myUser.condominio = building
      setUserToLocalStorage(this.myUser)
      this.setUser(this.myUser)
      this.goDashboard()
    },
    async goDashboard() {
      this.$router.push("/dashboard")
    },
    async login() {
      this.loading = true
      this.submitted = true

      // Validar formulario de login
      const valid = await this.$validator.validate().then(valid => valid)
      this.user.email = await this.user.email.toLowerCase()
      // Obtengo los condominios asociados al usuario
      const user = {
        email: this.user.email,
        password: this.user.pass
      }
      this.condominiums = await this.getCondominiums(user)

      if (!this.condominiums.error) {
        if (valid) {
          this.numCondominiums = await this.condominiums.condominiums.length
          // Lo guardo en localStorage
          await setUserToLocalStorage(this.myUser)
          await this.setUser(this.myUser)
          this.loading = false
          // Si la cantidad de condominios que responde el API es igual a 1
          if (this.numCondominiums === 1) {
            // Se guarda el condominio
            this.myUser.condominio = this.condominiums.condominiums[0]
            await setUserToLocalStorage(this.myUser)
            await this.setUser(this.myUser)
            this.goDashboard()
          } else if (this.numCondominiums === 0) {
            this.condominiums = null

            // Notificacion
            this.$notify({
              message: 'El usuario no tiene condominios asociados',
              type: 'danger'
            })
          }
        }
        this.loading = false
        this.submitted = false
      } else {
        const status = await this.condominiums.status
        // console.log('%c this.condominiums', 'color:cyan;', this.condominiums.status)
        this.loading = false
        this.condominiums = null
        const errorMsg = await getErrorMessage(status)
        swal({
          title: errorMsg.title,
          text: errorMsg.msg,
          type: "error",
          confirmButtonClass: "btn btn-success btn-fill",
          buttonsStyling: false
        })
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
<style scoped lang="scss">
.email, .pass {
  position: relative;
}
.pass {
  &.visible {
    i {
      color: #fff;
    }
  }
  i {
    position: absolute;
    cursor: pointer;
    right: 15px;
    top: 8px;
    z-index: 9;
    font-size: 16px;
  }
}
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
