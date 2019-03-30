<template>
  <div class="user">
    <div class="photo">
      <img src="@/assets/img/james.jpg" alt="avatar"/>
    </div>
    <div class="info">
      <a data-toggle="collapse" :aria-expanded="!isClosed" @click.stop="toggleMenu" href="#">
           <span>
             {{loggedUser.name}}
             <b class="caret"></b>
          </span>
      </a>
      <div class="clearfix"></div>
      <div>
        <collapse-transition>

          <sidebar-item
            v-show="!isClosed"
            :link="{
              name: 'Mi perfil',
              path: '/mi-perfil'
            }">
          </sidebar-item>

        </collapse-transition>
      </div>
    </div>
  </div>
</template>
<script>
import { CollapseTransition } from "vue2-transitions";
import { mapState } from "vuex";
import { MyProfile } from "src/utils/urls";

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
      myProfileUrl: null,
      isClosed: true
    };
  },
  mounted() {
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
