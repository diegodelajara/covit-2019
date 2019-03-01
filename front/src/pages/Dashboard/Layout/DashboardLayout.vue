<template>
  <div class="wrapper" :class="{'nav-open': $sidebar.showSidebar}">
    <notifications></notifications>
    <side-bar>
      <template slot-scope="props" slot="links">
        <user-menu></user-menu>
        <!--sidebar-item :link="{name: 'Dashboard', icon: 'now-ui-icons design_app', path: '/dashboard'}">
        </sidebar-item-->
        <!-- Mi menu -->
          <!-- Contabilidad -->
          <sidebar-item :link="{name: 'Contabilidad', icon: 'now-ui-icons business_chart-bar-32'}">
            <sidebar-item :link="{name: 'Ingresos', icon: 'now-ui-icons business_chart-bar-32', path: '/contabilidad/ingresos'}"></sidebar-item>
            <sidebar-item :link="{name: 'Egresos', icon: 'now-ui-icons business_chart-bar-32', path: '/contabilidad/egresos'}"></sidebar-item>
            <sidebar-item :link="{name: 'Multas', icon: 'now-ui-icons business_chart-bar-32', path: '/contabilidad/multas'}"></sidebar-item>
            <sidebar-item :link="{name: 'Condonaciones', icon: 'now-ui-icons business_chart-bar-32', path: '/contabilidad/condonaciones'}"></sidebar-item>
          </sidebar-item>

          <!-- GGCC -->
          <sidebar-item :link="{name: 'Gastos Comunes',icon: 'now-ui-icons business_chart-pie-36', path: '/gastos-comunes'}"></sidebar-item>

          <!-- Nómina y Remuneraciones -->
          <sidebar-item :link="{name: 'Nómina y Remuneraciones', icon: 'now-ui-icons files_single-copy-04'}">
            <sidebar-item :link="{name: 'Nómina', icon: 'now-ui-icons files_single-copy-04', path: '/nomina-y-remuneraciones/nomina'}"></sidebar-item>
            <sidebar-item :link="{name: 'Remuneraciones', icon: 'now-ui-icons files_single-copy-04', path: '/nomina-y-remuneraciones/remuneraciones'}"></sidebar-item>
            <sidebar-item :link="{name: 'Horas trabajadas', icon: 'now-ui-icons files_single-copy-04', path: '/nomina-y-remuneraciones/horas-trabajadas'}"></sidebar-item>
          </sidebar-item>


          <!-- Proveedores y Mantenciones -->
          <sidebar-item :link="{name: 'Proveedores y Mantenciones', icon: 'now-ui-icons ui-2_settings-90'}">
            <sidebar-item :link="{name: 'Proveedores', icon: 'now-ui-icons objects_diamond', path: '/proveedores-y-mantenciones/proveedores'}"></sidebar-item>
            <sidebar-item :link="{name: 'Mantenciones', icon: 'now-ui-icons objects_diamond', path: '/proveedores-y-mantenciones/mantenciones'}"></sidebar-item>
          </sidebar-item>



          <!-- Residentes y Visitas -->
          <sidebar-item :link="{name: 'Residentes y Visitas', icon: 'now-ui-icons users_single-02'}">
            <sidebar-item :link="{name: 'Residentes', icon: 'now-ui-icons objects_diamond', path: '/residentes-y-visitas/residentes'}"></sidebar-item>
            <sidebar-item :link="{name: 'Visitas', icon: 'now-ui-icons objects_diamond', path: '/residentes-y-visitas/visitas'}"></sidebar-item>
          </sidebar-item>

          <!-- Mensajes -->
          <sidebar-item :link="{name: 'Mensajes',icon: 'now-ui-icons ui-2_chat-round', path: '/mensajes'}"></sidebar-item>

          <!-- Seguridad y Alertas -->
          <sidebar-item :link="{name: 'Seguridad y Alertas',icon: 'now-ui-icons ui-1_bell-53', path: '/seguridad-y-alertas'}"></sidebar-item>

          <!-- EECC -->
          <sidebar-item :link="{name: 'Espacios Comunes',icon: 'now-ui-icons location_pin', path: '/espacios-comunes'}"></sidebar-item>


        <!-- Fin mi menu -->

      </template>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>
      <router-view name="header"></router-view>

      <div :class="{content: !$route.meta.hideContent}" @click="toggleSidebar">
        <zoom-center-transition :duration="200" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </zoom-center-transition>
      </div>
      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>
<script>
  /* eslint-disable no-new */
  import PerfectScrollbar from 'perfect-scrollbar'
  import 'perfect-scrollbar/css/perfect-scrollbar.css'

  function hasElement (className) {
    return document.getElementsByClassName(className).length > 0;
  }

  function initScrollbar (className) {
    if (hasElement(className)) {
      new PerfectScrollbar(`.${className}`);
    } else {
      // try to init it later in case this component is loaded async
      setTimeout(() => {
        initScrollbar(className);
      }, 100);
    }
  }

  import TopNavbar from './TopNavbar.vue'
  import ContentFooter from './ContentFooter.vue'
  import DashboardContent from './Content.vue'
  import MobileMenu from './Extra/MobileMenu.vue'
  import UserMenu from './Extra/UserMenu.vue'
  import {SlideYDownTransition, ZoomCenterTransition} from 'vue2-transitions'

  export default {
    components: {
      TopNavbar,
      ContentFooter,
      DashboardContent,
      MobileMenu,
      UserMenu,
      SlideYDownTransition,
      ZoomCenterTransition
    },
    methods: {
      toggleSidebar () {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false)
        }
      }
    },
    mounted () {
      let docClasses = document.body.classList;
      let isWindows = navigator.platform.startsWith('Win');
      if (isWindows) {
        // if we are on windows OS we activate the perfectScrollbar function
        initScrollbar('sidebar');
        initScrollbar('sidebar-wrapper');

        docClasses.add('perfect-scrollbar-on')
      } else {
        docClasses.add('perfect-scrollbar-off')
      }
    }
  }

</script>
<style lang="scss">
  $scaleSize: .95;
  @keyframes zoomIn95 {
    from {
      opacity: 0;
      transform: scale3d($scaleSize, $scaleSize, $scaleSize);
    }
    to {
      opacity: 1;
    }
  }
  .main-panel .zoomIn {
    animation-name: zoomIn95;
  }
  @keyframes zoomOut95 {
    from {
      opacity: 1;
    }
    to {
      opacity: 0;
      transform: scale3d($scaleSize, $scaleSize, $scaleSize);
    }
  }
  .main-panel .zoomOut {
    animation-name: zoomOut95;
  }
</style>
