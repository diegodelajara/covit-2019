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
          <sidebar-item :link="{name: 'Contabilidad', icon: 'now-ui-icons design_image'}">
            <sidebar-item :link="{name: 'Ingresos', icon: 'now-ui-icons objects_diamond', path: '/contabilidad/ingresos'}"></sidebar-item>
            <sidebar-item :link="{name: 'Egresos', icon: 'now-ui-icons objects_diamond', path: '/contabilidad/egresos'}"></sidebar-item>
            <sidebar-item :link="{name: 'Multas', icon: 'now-ui-icons objects_diamond', path: '/contabilidad/multas'}"></sidebar-item>
            <sidebar-item :link="{name: 'Condonaciones', icon: 'now-ui-icons objects_diamond', path: '/contabilidad/condonaciones'}"></sidebar-item>
          </sidebar-item>

          <!-- GGCC -->
          <sidebar-item :link="{name: 'Gastos Comunes',icon: 'now-ui-icons objects_diamond', path: '/gastos-comunes'}"></sidebar-item>

          <!-- Nómina y Remuneraciones -->
          <sidebar-item :link="{name: 'Nómina y Remuneraciones', icon: 'now-ui-icons design_image'}">
            <sidebar-item :link="{name: 'Nómina', icon: 'now-ui-icons objects_diamond', path: '/nomina-y-remuneraciones/nomina'}"></sidebar-item>
            <sidebar-item :link="{name: 'Remuneraciones', icon: 'now-ui-icons objects_diamond', path: '/nomina-y-remuneraciones/remuneraciones'}"></sidebar-item>
            <sidebar-item :link="{name: 'Horas trabajadas', icon: 'now-ui-icons objects_diamond', path: '/nomina-y-remuneraciones/horas-trabajadas'}"></sidebar-item>
          </sidebar-item>


          <!-- Proveedores y Mantenciones -->
          <sidebar-item :link="{name: 'Proveedores y Mantenciones', icon: 'now-ui-icons design_image'}">
            <sidebar-item :link="{name: 'Proveedores', icon: 'now-ui-icons objects_diamond', path: '/proveedores-y-mantenciones/proveedores'}"></sidebar-item>
            <sidebar-item :link="{name: 'Mantenciones', icon: 'now-ui-icons objects_diamond', path: '/proveedores-y-mantenciones/mantenciones'}"></sidebar-item>
          </sidebar-item>



          <!-- Residentes y Visitas -->
          <sidebar-item :link="{name: 'Residentes y Visitas', icon: 'now-ui-icons design_image'}">
            <sidebar-item :link="{name: 'Residentes', icon: 'now-ui-icons objects_diamond', path: '/residentes-y-visitas/residentes'}"></sidebar-item>
            <sidebar-item :link="{name: 'Visitas', icon: 'now-ui-icons objects_diamond', path: '/residentes-y-visitas/visitas'}"></sidebar-item>
          </sidebar-item>

          <!-- Mensajes -->
          <sidebar-item :link="{name: 'Mensajes',icon: 'now-ui-icons objects_diamond', path: '/mensajes'}"></sidebar-item>

          <!-- Seguridad y Alertas -->
          <sidebar-item :link="{name: 'Seguridad y Alertas',icon: 'now-ui-icons objects_diamond', path: '/seguridad-y-alertas'}"></sidebar-item>

          <!-- EECC -->
          <sidebar-item :link="{name: 'Espacios Comunes',icon: 'now-ui-icons objects_diamond', path: '/espacios-comunes'}"></sidebar-item>


        <!-- Fin mi menu -->
        <sidebar-item :link="{name: 'Pages', icon: 'now-ui-icons design_image'}">
          <sidebar-item :link="{name: 'Pricing', path: '/pricing'}"></sidebar-item>
          <sidebar-item :link="{name: 'Timeline', path: '/pages/timeline'}"></sidebar-item>
          <sidebar-item :link="{name: 'Login', path: '/login'}"></sidebar-item>
          <sidebar-item :link="{name: 'Register', path: '/register'}"></sidebar-item>
          <sidebar-item :link="{name: 'Lock Screen', path: '/lock'}"></sidebar-item>
          <sidebar-item :link="{name: 'User Profile', path: '/pages/user'}"></sidebar-item>
        </sidebar-item>
        <sidebar-item :link="{name: 'Components', icon: 'now-ui-icons education_atom'}">
          <sidebar-item :link="{name: 'Buttons', path: '/components/buttons'}"></sidebar-item>
          <sidebar-item :link="{name: 'Grid System', path: '/components/grid-system'}"></sidebar-item>
          <sidebar-item :link="{name: 'Panels', path: '/components/panels'}"></sidebar-item>
          <sidebar-item :link="{name: 'Sweet Alert', path: '/components/sweet-alert'}"></sidebar-item>
          <sidebar-item :link="{name: 'Notifications', path: '/components/notifications'}"></sidebar-item>
          <sidebar-item :link="{name: 'Icons', path: '/components/icons'}"></sidebar-item>
          <sidebar-item :link="{name: 'Typography', path: '/components/typography'}"></sidebar-item>
        </sidebar-item>
        <sidebar-item :link="{name: 'Forms', icon: 'now-ui-icons files_single-copy-04'}">
          <sidebar-item :link="{name: 'Regular Forms', path: '/forms/regular'}"></sidebar-item>
          <sidebar-item :link="{name: 'Extended Forms', path: '/forms/extended'}"></sidebar-item>
          <sidebar-item :link="{name: 'Validation Forms', path: '/forms/validation'}"></sidebar-item>
          <sidebar-item :link="{name: 'Wizard', path: '/forms/wizard'}"></sidebar-item>
        </sidebar-item>
        <sidebar-item :link="{name: 'Tables', icon: 'now-ui-icons design_bullet-list-67'}">
          <sidebar-item :link="{name: 'Regular Tables', path: '/table-list/regular'}"></sidebar-item>
          <sidebar-item :link="{name: 'Extended Tables', path: '/table-list/extended'}"></sidebar-item>
          <sidebar-item :link="{name: 'Paginated Tables', path: '/table-list/paginated'}"></sidebar-item>
        </sidebar-item>
        <sidebar-item :link="{name: 'Maps', icon: 'now-ui-icons location_pin'}">
          <sidebar-item :link="{name: 'Google Maps', path: '/maps/google'}"></sidebar-item>
          <sidebar-item :link="{name: 'Full Screen Maps', path: '/maps/full-screen'}"></sidebar-item>
          <sidebar-item :link="{name: 'Vector Maps', path: '/maps/vector-map'}"></sidebar-item>
        </sidebar-item>
        <sidebar-item
          :link="{name: 'Widgets', icon: 'now-ui-icons objects_diamond', path: '/widgets'}"></sidebar-item>
        <sidebar-item
          :link="{name: 'Charts', icon: 'now-ui-icons business_chart-pie-36', path: '/charts'}"></sidebar-item>
        <sidebar-item
          :link="{name: 'Calendar', icon: 'now-ui-icons media-1_album', path: '/calendar'}"></sidebar-item>

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
