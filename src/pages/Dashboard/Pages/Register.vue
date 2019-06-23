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
                      v-model="userInfo.name">
            </fg-input>

            <fg-input placeholder="Apellido"
                      addon-left-icon="now-ui-icons users_circle-08"
                      v-model="userInfo.lastName">
            </fg-input>

            <fg-input placeholder="Correo"
                      addon-left-icon="now-ui-icons users_circle-08"
                      v-model="createUser.email">
            </fg-input>

            <fg-input placeholder="Contraseña"
                      type="password"
                      addon-left-icon="now-ui-icons text_caps-small"
                      v-model="createUser.password">
            </fg-input>

            <fg-input placeholder="Reeingresa la contraseña"
                      type="password"
                      addon-left-icon="now-ui-icons text_caps-small"
                      v-model="repitPassword">
            </fg-input>

            <select name="" id="" v-model="userInfo.profile">
              <option value="seleccione">Seleccione un perfil</option>
              <option value="admin">Administrador</option>
              <option value="concierge">Conserje</option>
              <option value="coowner">Copropietario</option>
              <option value="owner">Dueño</option>
              <option value="user">Usuario normal</option>
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
import swal from 'sweetalert2'
import { Checkbox } from "src/components"
import { mapMutations } from "vuex"
import firebase from "firebase/app"
import "firebase/auth"
import { usuariosRef, userInfoRef, db } from "src/firebase/firebase"
import { CREATE_USER_SUCCESS } from "src/constants/alerts"

export default {
  firebase: {
    usuariosRef,
    userInfoRef
  },
  components: {
    Checkbox
  },
  data() {
    return {
      createUser: {
        email: null,
        password: null
      },
      userInfo: {
        name: null,
        lastName: null,
        profile: "seleccione"
      },
      repitPassword: null
    };
  },
  methods: {
    ...mapMutations([
      "setRegisterUser"
    ]),
    async signUp() {
      if (this.createUser.password === this.repitPassword) {
        await firebase.auth().createUserWithEmailAndPassword(this.createUser.email, this.createUser.password)
        .then(user => {
          // Se agrega el usuario a usuariosRef
          const userResponse = usuariosRef.push(this.createUser)
          // Si la respuesta es correcta se agrega la informacion del usuario 
          if (userResponse) {
            const info = {
              email: this.createUser.email,
              name: this.userInfo.name,
              lastName: this.userInfo.lastName,
              profile: this.userInfo.profile
            }
            const userInfoResponse = userInfoRef.push(info)
            if (userInfoResponse) {
              swal({
                title: CREATE_USER_SUCCESS,
                buttonsStyling: false,
                confirmButtonClass: 'btn btn-success btn-fill',
                type: 'success'
              })  
            }
          }
          // this.setRegisterUser(this.createUser)
        })
        .catch(err => {
          alert(err.message)
        })
      } else {
        swal({
          title: `Las contraseñas no coinciden`,
          text: 'Reeingrese la misma contraseña para los dos campos',
          buttonsStyling: false,
          confirmButtonClass: 'btn btn-success btn-fill',
          type: 'error'
        })
      }
    }
  }
};
</script>
<style>
</style>
