<template>
  <div class="user">
    <div v-for="(userImage, i) in userImagesRef" :key="i">
      <img
        class="photo"
        v-if="userImage.email === email"
        :src="userImage.image"
        alt="avatar"/>
    </div>
    <div class="info">
      <a data-toggle="collapse" :aria-expanded="!isClosed" @click.stop="toggleMenu" href="#">
           <span>
             {{loggedUser.name}}
             <!-- <b class="caret"></b> -->
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
import { CollapseTransition } from "vue2-transitions";
import { mapState } from "vuex";
import { MyProfile } from "src/utils/urls";

import firebase from "firebase/app";
import { getUserFromLocalStorage } from "src/utils/auth";
import { firebaseStorage } from "src/firebase/firebaseStorage";
import { userImagesRef, db } from "src/firebase/firebase";

export default {
  firebase: {
    userImagesRef
  },
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
      myProfileUrl: null,
      isClosed: true,
      email : null
    };
  },
  mounted() {
    this.email = getUserFromLocalStorage().email;
    this.myProfileUrl = MyProfile;
  },
  methods: {
    toggleMenu() {
      this.isClosed = !this.isClosed;
    }
  },
  computed: {
    ...mapState({
      loggedUser: state => state.user
    })
  }
};
</script>
<style>
.collapsed {
  transition: opacity 1s;
}
</style>
