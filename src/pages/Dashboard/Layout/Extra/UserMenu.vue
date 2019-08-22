<template>
  <div class="user">
    <div>
      <img
        alt="avatar"
        class="photo"
        :src="defaultImg" />
    </div>
    <div class="info">
      <a data-toggle="collapse" :aria-expanded="!isClosed" @click.stop="toggleMenu" href="#">
        <span v-if="user != null">
          {{ user.condominio.name }}
          <small><p>{{!user.nombre ? 'Diego de la jara' : user.nombre }}</p></small>
        </span>
      </a>
      <div class="clearfix"></div>
      <div>
        <collapse-transition>

          <!-- <sidebar-item
            v-show="!isClosed"
            :link="{
              name: 'Mi perfil',
              path: '/mi-perfil'
            }">
          </sidebar-item> -->

        </collapse-transition>
      </div>
    </div>
  </div>
</template>
<script>
import { CollapseTransition } from "vue2-transitions"
import { mapState } from "vuex"
import { MY_PROFILE } from "src/utils/urls"
import { DEFAULT_IMG } from "src/constants/config"
import { getUserFromLocalStorage } from "src/utils/auth"


export default {
  components: {
    CollapseTransition
  },
  props: {
    title: {
      type: String,
      default: "James Amos"
    }
  },
  data() {
    return {
      defaultImg: null,
      myProfileUrl: null,
      isClosed: true,
      user : null
    };
  },
  mounted() {
    this.defaultImg = DEFAULT_IMG
    this.user = getUserFromLocalStorage()
    this.myProfileUrl = MY_PROFILE
  },
  methods: {
    toggleMenu() {
      this.isClosed = !this.isClosed
    }
  },
  computed: {
    ...mapState({
      loggedUser: state => state.login.user
    })
  }
};
</script>
<style>
.collapsed {
  transition: opacity 1s;
}
.sidebar .user {
  margin: 0;
  padding: 0;
}
</style>
