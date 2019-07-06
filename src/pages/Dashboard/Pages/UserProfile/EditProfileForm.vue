<template>
  <card>
    <h5 slot="header" class="title">Mi Perfil <small>{{user.condominium}}</small></h5>
    <form>

      <div class="row">
        <div class="col-md-6">
          <fg-input type="text"
                    label="Nombre"
                    placeholder="Nombre"
                    v-model="user.firstName">
          </fg-input>
        </div>
        <div class="col-md-6">
          <fg-input type="text"
                    label="Apellido"
                    placeholder="Apellido"
                    v-model="user.lastName">
          </fg-input>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">

          <fg-input type="text"
                    label="Nombre de usuario"
                    placeholder="Username"
                    v-model="user.username">
          </fg-input>
        </div>
        <div class="col-md-6">
          <fg-input type="email"
                    label="Correo electrónico"
                    placeholder="Email"
                    v-model="user.email">
          </fg-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <fg-input type="text"
                    label="Condominio"
                    placeholder="Condominio"
                    v-model="user.condominium">
          </fg-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <fg-input type="text"
                    label="Dirección"
                    placeholder="Dirección"
                    v-model="user.address">
          </fg-input>
        </div>
      </div>
      <n-button
        wide
        type="info"
        @click.native="updateProfile"
        class="btn-round btn-primary">
        Guardar
      </n-button>
    </form>
  </card>
</template>
<script>
import swal from "sweetalert2"

import { getUserFromLocalStorage } from 'src/utils/auth'
import { firebaseAuth } from "src/firebase/firebaseAuth"
import { userInfoRef } from "src/firebase/firebase"

export default {
  firebase: {
    userInfoRef
  },
  data() {
    return {
      user: {
        address: null,
        condominium: null,
        firstName: null,
        lastName: null,
        username: null
      },
      userId: null
    };
  },
  async mounted () {
    this.userId = await getUserFromLocalStorage().uid

    userInfoRef.on("value", snapshot => {
      let snap = snapshot.val()[this.userId]
      
      // console.log('%c snap', 'color: cyan;', snap)
      // return
      this.user.firstName = snap.firstName
      this.user.lastName = snap.lastName
      this.user.condominium = snap.condominium
      this.user.address = snap.address
      this.user.username = snap.username

    }, function (errorObject) {
      console.log("The read failed: " + errorObject.code)
    })
  },
  methods: {
    async updateProfile() {
      // console.log('%c this.user', 'color: cyan;', this.userId)
      // return
      try {
        var user = await userInfoRef.child(this.userId)
        user.update(
          {
            'address': this.user.address ? this.user.address : 'Dirección',
            'condominium': this.user.condominium ? this.user.condominium : 'Condominio',
            'firstName': this.user.firstName ? this.user.firstName : 'Nombre',
            'lastName': this.user.lastName ? this.user.lastName : 'Apellido',
            'username': this.user.username ? this.user.username : 'Nombre de usuario'
          }
        )
        swal({
          title: 'Excelente!',
          text: 'Información actualizada',
          type: "success",
          confirmButtonClass: "btn btn-success btn-fill",
          buttonsStyling: false
        })
      } catch (error) {
        swal({
          title: 'Error',
          text: error,
          type: "error",
          confirmButtonClass: "btn btn-success btn-fill",
          buttonsStyling: false
        })
      }
    }
  }
};
</script>
<style>
</style>
