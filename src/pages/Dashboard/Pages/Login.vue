<template>
  <div class="col-md-4 ml-auto mr-auto">
    <form @submit.prevent>
      <card class="card-login card-plain">

        <div slot="header">
          <div class="logo-container">
            <img src="@/assets/img/now-logo.png" alt="">
          </div>
        </div>

        <div>
          <fg-input class="no-border form-control-lg"
                    placeholder="Usuario"
                    addon-left-icon="now-ui-icons users_circle-08"
                    type="text"
                    v-model="user.usuario">
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

            <pre>
              <h6>
                {{ loggedUser }}
              </h6>
            </pre>
            
            <div>
              <h1>Hello!</h1>
              <router-link to="/bye">Bye</router-link>
              <span>|</span>
              <router-link to="/secret">Secret</router-link>
              <p>
                <input
                  type="radio"
                  name="role"
                  value="admin"
                  v-model="role"
                  @change="onRoleChange" />
                <label>Admin</label>
              </p>
              <p>
                <input
                  type="radio"
                  name="role"
                  value="user"
                  v-model="role"
                  @change="onRoleChange" />
                <label>Regular User</label>
              </p>
            </div>



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
import { mapState, mapMutations } from 'vuex'
import firebase from 'firebase'
import { usuariosRef } from 'src/firebase'

  export default {
    firebase: {
      usuariosRef
    },
    data() {
      return {
        role: 'admin',
        user: {
          usuario: '',
          clave: '',
          authUser: null
        }
      }
    },
    mounted() {
      this.role = this.loggedUser.role;
    },
    computed: {
      ...mapState({
        loggedUser: state => state.user.info
      }),
    },
    methods: {
      // ...mapMutations([
      //   'user'
      // ]),
      ...mapMutations([
        'changeRole',
      ]),
      onRoleChange() {
        this.changeRole(this.role);
      },
      login() {
        firebase.auth().signInWithEmailAndPassword(this.user.usuario, this.user.clave)
        .then((user) => {
          // if (this.$store.dispatch('setUser', this.user)) {
          //   this.$router.replace('/dashboard')
          // }
          this.getUserfromFirebase(this.user.usuario)
          this.$router.push('/dashboard')
        }).catch((err) => {
          alert(err.message)
        })
      },
      getUserfromFirebase(loggedUser) {
        let usuarios = this.usuariosRef
        usuarios.forEach(element => {
          if(element.usuario === loggedUser) {
            this.setUser(element)
          }
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
</style>
