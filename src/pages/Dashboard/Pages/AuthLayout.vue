<template>
  <div>
    <div class="wrapper wrapper-full-page">
      <div class="full-page section-image" :class="pageClass" filter-color="black" data-image="">
        <div class="content">
          <div class="container">
            <zoom-center-transition :duration="pageTransitionDuration" mode="out-in">
              <router-view></router-view>
            </zoom-center-transition>
          </div>
        </div>

        <!-- <div class="full-page-background" :style="imageStyles"></div> -->
      </div>
    </div>
  </div>
</template>
<script>
  import {Navbar, NavbarToggleButton} from 'src/components';
  import {ZoomCenterTransition} from 'vue2-transitions'

  export default {
    components: {
      Navbar,
      NavbarToggleButton,
      ZoomCenterTransition
    },
    props: {
      backgroundColor: {
        type: String,
        default: 'black'
      }
    },
    data() {
      return {
        showMenu: false,
        menuTransitionDuration: 250,
        pageTransitionDuration: 200,
        year: new Date().getFullYear(),
        pageClass: 'login-page'
      }
    },
    methods: {
      toggleNavbar() {
        document.body.classList.toggle('nav-open')
        this.showMenu = !this.showMenu
      },
      closeMenu() {
        document.body.classList.remove('nav-open')
        this.showMenu = false
      },
      setPageClass(){
        this.pageClass = `${this.$route.name}-page`.toLowerCase();
      }
    },
    beforeDestroy() {
      this.closeMenu()
    },
    beforeRouteUpdate(to, from, next) {
      // Close the mobile menu first then transition to next page
      if (this.showMenu) {
        this.closeMenu();
        setTimeout(() => {
          next()
        }, this.menuTransitionDuration)
      } else {
        next()
      }
    },
    watch: {
      $route(){
        this.setPageClass();
      }
    }
  }
</script>
<style lang="scss">
  $scaleSize: .8;
  @keyframes zoomIn8 {
    from {
      opacity: 0;
      transform: scale3d($scaleSize, $scaleSize, $scaleSize);
    }
    100% {
      opacity: 1;
    }
  }
  .wrapper-full-page .zoomIn {
    animation-name: zoomIn8;
  }
  @keyframes zoomOut8 {
    from {
      opacity: 1;
    }
    to {
      opacity: 0;
      transform: scale3d($scaleSize, $scaleSize, $scaleSize);
    }
  }
  .wrapper-full-page .zoomOut {
    animation-name: zoomOut8;
  }
</style>
