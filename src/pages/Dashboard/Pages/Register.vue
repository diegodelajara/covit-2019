<template>
    <div class="row">
      <div class="col-lg-5 ml-auto">
        <div class="info-area info-horizontal mt-5">
          <div class="icon icon-primary">
            <i class="now-ui-icons media-2_sound-wave"></i>
          </div>
          <div class="description">
            <h5 class="info-title">Marketing</h5>
            <p class="description">
              We've created the marketing campaign of the website. It was a very interesting collaboration.
            </p>
          </div>
        </div>

        <div class="info-area info-horizontal">
          <div class="icon icon-primary">
            <i class="now-ui-icons media-1_button-pause"></i>
          </div>
          <div class="description">
            <h5 class="info-title">Fully Coded in HTML5</h5>
            <p class="description">
              We've developed the website with HTML5 and CSS3. The client has access to the code using GitHub.
            </p>
          </div>
        </div>

        <div class="info-area info-horizontal">
          <div class="icon icon-info">
            <i class="now-ui-icons users_single-02"></i>
          </div>
          <div class="description">
            <h5 class="info-title">Built Audience</h5>
            <p class="description">
              There is also a Fully Customizable CMS Admin Dashboard for this product.
            </p>
          </div>
        </div>
      </div>
      <div class="col-lg-4 mr-auto">
        <card class="card-signup text-center" no-footer-line>

          <template slot="header">
            <h4 class="card-title">Register</h4>
            <div class="social">
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
            </div>
          </template>

          <form @submit.prevent="signUp()">

            <fg-input placeholder="Correo"
                      addon-left-icon="now-ui-icons users_circle-08"
                      v-model="email">
            </fg-input>

            <fg-input placeholder="Contraseña"
                      addon-left-icon="now-ui-icons text_caps-small"
                      v-model="password">
            </fg-input>

            <fg-input placeholder="Reeingresa la contraseña"
                      addon-left-icon="now-ui-icons text_caps-small">
            </fg-input>

            <checkbox class="text-left">
              I agree to the <a href="#something">terms and conditions</a>.
            </checkbox>
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
import firebase from 'firebase'

export default {
  components: {
    Checkbox
  },
  data() {
    return {
      email: "",
      password: ""
    };
  },
  methods: {
    signUp () {
      firebase.auth().createUserWithEmailAndPassword(this.email, this.password).then((user) => {
        this.$router.replace('/login')
      }).catch((err) => {
        alert(err.message)
      })
    }
  }
};
</script>
<style>
</style>
