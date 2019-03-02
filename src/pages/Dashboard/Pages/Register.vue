<template>
    <div class="row">
      <div class="col-lg-6 m-auto">
        <card class="card-signup text-center" no-footer-line>

          <template slot="header">
            <h4 class="card-title">Registrar</h4>
            <!-- <div class="social">
              <button class="btn btn-icon btn-round btn-twitter">
                <i class="fa fa-twitter"></i>
              </button>
              <button class="btn btn-icon btn-round btn-dribbble">
                <i class="fa fa-dribbble"></i>
              </button>
              <button class="btn btn-icon btn-round btn-facebook">
                <i class="fa fa-facebook"> </i>
              </button>
              <h5 class="card-description"> or be classical </h5>
            </div> -->
          </template>

          <form @submit.prevent="signUp()">

            <fg-input placeholder="Nombre"
                      addon-left-icon="now-ui-icons users_circle-08"
                      v-model="createUser.nombre">
            </fg-input>

            <fg-input placeholder="Apellido"
                      addon-left-icon="now-ui-icons users_circle-08"
                      v-model="createUser.apellido">
            </fg-input>

            <fg-input placeholder="Correo"
                      addon-left-icon="now-ui-icons users_circle-08"
                      v-model="createUser.email">
            </fg-input>

            <fg-input placeholder="Contraseña"
                      addon-left-icon="now-ui-icons text_caps-small"
                      v-model="createUser.password">
            </fg-input>

            <fg-input placeholder="Reeingresa la contraseña"
                      addon-left-icon="now-ui-icons text_caps-small">
            </fg-input>

            <select name="" id="" v-model="createUser.perfil">
              <option value="seleccione">Seleccione un perfil</option>
              <option value="user">Usuario normal</option>
              <option value="admin">Administrador</option>
            </select>

            <!-- <checkbox class="text-left">
              I agree to the <a href="#something">terms and conditions</a>.
            </checkbox> -->
          </form>
          <n-button type="primary" round block @click.native="signUp">
            Registrar
          </n-button>

        </card>
      </div>
    </div>
</template>
<script>
import { Checkbox } from "src/components"
import { mapMutations } from 'vuex'
import firebase from 'firebase'
import { usuariosRef } from 'src/firebase'

export default {
  firebase: {
    usuariosRef
  },
  components: {
    Checkbox
  },
  data() {
    return {
      createUser: {
        nombre: null,
        apellido: null,
        email: null,
        password: null,
        perfil: 'seleccione'
      }
    }
  },
  methods: {
    ...mapMutations([
      'setRegisterUser'
    ]),
    signUp () {
      firebase.auth().createUserWithEmailAndPassword(this.createUser.email, this.createUser.password)
      .then((user) => {
        // TODO - pasar funcionalidad a Vuex
        let response = usuariosRef.push(this.createUser)
        if (response) {
          alert('usuario creado exitosamente')
          this.$router.replace('/login')
        } else 
          alert('Hubo un problema al crear el usuario')
        // this.setRegisterUser(this.createUser)
      }).catch((err) => {
        alert(err.message)
      })
    }
  }
};
</script>
<style>
</style>
