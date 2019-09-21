<template>
  <div class="col-md-4 ml-auto mr-auto">
    <form @submit.prevent>
      <card class="card-login">
        <div slot="header">
          <div class="logo-container">
            <img src="@/assets/img/logo.png" alt="">
          </div>
        </div>

        <div v-if="showLogin">
          <div class="emial">
            <fg-input
              class="form-control-lg"
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
              class="form-control-lg"
              data-vv-name="password"
              placeholder="Contraseña"
              v-validate="`required`"
              v-mask="'NNNNNNNN'"
              v-model.trim="user.pass"
              :error="getError('password')"
              :class="{ 'is-invalid': errors.has('password') }"
              :type="passwordType"
              @keyup.enter="login"
            >
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
        perfil: null,
        uid: null,
        condominio: null
      },
      passwordType: 'password',
      showLogin: true,
      status: '',
      user: {
        email: "",
        pass: ""
      },
      token: null
    }
  },
  computed: {
    ...mapGetters([
      'getUser'
    ])
  },
  methods: {
    ...mapActions([
      'getCondominiums',
      'getToken',
      'getUserInfo'
    ]),
    ...mapMutations([
      "setUser"
    ]),
    async onGetUserInfo() {
      try {
        const params = {
          authorization: this.token,
          condominium: this.myUser.condominio.endpoint,
          access: this.user
        }

        const userInfo = await this.getUserInfo(params)
        this.myUser.perfil = userInfo
          ? userInfo.result.role
          : ''
      } catch (error) {

        this.$notify({
          message: `Hubo un problema al obtener el perfil del usuario ${error}`,
          type: 'danger'
        })
      }
    },

    async onGetToken() {
      const params = {
        condominium: this.myUser.condominio.endpoint,
        access: this.user
      }

      this.token = await this.getToken(params)
    },
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
          // await setUserToLocalStorage(this.myUser)
          // Mutation
          await this.setUser(this.myUser)
          // Si la cantidad de condominios que responde el API es igual a 1
          if (this.numCondominiums === 1) {
            // Se guarda el condominio
            this.myUser.condominio = this.condominiums.condominiums[0]

            await this.setUser(this.myUser) // Mutation
            await this.onGetToken() // Action
            await this.onGetUserInfo()
            await this.setUser(this.myUser) // Mutation
            await setUserToLocalStorage(this.myUser) // localStorage
            await this.goDashboard()
            this.showLogin = false
            this.loading = false
          } else if (this.numCondominiums === 0) {
            this.loading = false

            // Notificacion
            this.$notify({
              message: 'El usuario no tiene condominios asociados',
              type: 'danger'
            })
          }
          this.showLogin = false
        }
      } else {
        const status = await this.condominiums.status
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
      color: #333;
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
  color: #333;
}
.logo-container {
    width: 70%;
}
.card.card-login.card-plain {
  background-color: #fff;
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
