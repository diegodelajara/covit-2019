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
import { getUserFromLocalStorage } from 'src/utils/auth'
import { firebaseAuth } from "src/firebase/firebaseAuth"
import { userInfoRef } from "src/firebase/firebase"

export default {
  firebase: {
    userInfoRef
  },
  data() {
    return {
      userId: null,
      user: {
        condominium: 'Jardines de Independencia',
        company: "Creative Code Inc.",
        username: "michael23",
        email: "",
        firstName: "Mike",
        lastName: "Andrew",
        address: "Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
      }
    };
  },
  mounted () {
    this.userId = getUserFromLocalStorage().uid
    // console.log(this.userId);
    // return

    userInfoRef.on("value", snapshot => {
      console.log(snapshot.val())
    }, function (errorObject) {
      console.log("The read failed: " + errorObject.code)
    })
  },
  methods: {
    updateProfile() {
      const uid = firebaseAuth.currentUser.uid
      const email = firebaseAuth.currentUser.email
      this.user.email = email
      userInfoRef.child(uid).set(this.user)
      // alert("Your data: " + JSON.stringify(this.user));
    }
  }
};
</script>
<style>
</style>
