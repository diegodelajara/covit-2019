import DashboardLayout from 'src/pages/Dashboard/Layout/DashboardLayout.vue'
import AuthLayout from 'src/pages/Dashboard/Pages/AuthLayout.vue'
// GeneralViews
import NotFound from 'src/pages/GeneralViews/NotFoundPage.vue'

// Page Headers
import DefaultHeader from 'src/pages/Dashboard/DefaultHeader'
import DashboardHeader from 'src/pages/Dashboard/Dashboard/DashboardHeader.vue'
import SweetAlertHeader from 'src/pages/Dashboard/Components/Headers/SweetAlertHeader'
import CalendarHeader from 'src/pages/Dashboard/Calendar/CalendarHeader'
import VectorMapsHeader from 'src/pages/Dashboard/Maps/VectorMapsHeader'

// Dashboard pages
import Dashboard from 'src/pages/Dashboard/Dashboard/Dashboard'

// Pages
import User from 'src/pages/Dashboard/Pages/UserProfile'
import Pricing from 'src/pages/Dashboard/Pages/Pricing.vue'
import TimeLine from 'src/pages/Dashboard/Pages/TimeLinePage.vue'
import Login from 'src/pages/Dashboard/Pages/Login.vue'
import Register from 'src/pages/Dashboard/Pages/Register.vue'
import Lock from 'src/pages/Dashboard/Pages/Lock.vue'

// Components pages
import Buttons from 'src/pages/Dashboard/Components/Buttons.vue'
import GridSystem from 'src/pages/Dashboard/Components/GridSystem.vue'
import Panels from 'src/pages/Dashboard/Components/Panels.vue'
import SweetAlert from 'src/pages/Dashboard/Components/SweetAlert.vue'
import Notifications from 'src/pages/Dashboard/Components/Notifications.vue'
import Icons from 'src/pages/Dashboard/Components/Icons.vue'
import Typography from 'src/pages/Dashboard/Components/Typography.vue'

// Forms pages
import RegularForms from 'src/pages/Dashboard/Forms/RegularForms.vue'
import ExtendedForms from 'src/pages/Dashboard/Forms/ExtendedForms.vue'
import ValidationForms from 'src/pages/Dashboard/Forms/ValidationForms.vue'
import Wizard from 'src/pages/Dashboard/Forms/Wizard.vue'

// TableList pages
import RegularTables from 'src/pages/Dashboard/Tables/RegularTables.vue'
import ExtendedTables from 'src/pages/Dashboard/Tables/ExtendedTables.vue'
import PaginatedTables from 'src/pages/Dashboard/Tables/PaginatedTables.vue'
// Maps pages
import GoogleMaps from 'src/pages/Dashboard/Maps/GoogleMaps.vue'
import FullScreenMap from 'src/pages/Dashboard/Maps/FullScreenMap.vue'
import VectorMaps from 'src/pages/Dashboard/Maps/VectorMaps.vue'

// Calendar
import Calendar from 'src/pages/Dashboard/Calendar/CalendarRoute.vue'
// Charts
import Charts from 'src/pages/Dashboard/Charts.vue'

// Covit
  // Contabilidad
  import ContabilidadIngresos  from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Ingresos'
  import ContabilidadEgresos   from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Egresos'
  import ContabilidadMultas   from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Multas'
  import ContabilidadCondonaciones   from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Condonaciones'

  // GGCC
  import GastosComunes from 'src/pages/Dashboard/Pages/Covit/Gastos-Comunes'

  // Nomina y Remuneraciones
  import NyrNomina  from 'src/pages/Dashboard/Pages/Covit/Nomina-y-Remuneraciones/Nomina'
  import NyrRemuneraciones   from 'src/pages/Dashboard/Pages/Covit/Nomina-y-Remuneraciones/Remuneraciones'
  import NyrHorasTrabajadas   from 'src/pages/Dashboard/Pages/Covit/Nomina-y-Remuneraciones/Horas-trabajadas'
  
  

  // Proveedores y Mantenciones
  import PymProveedores  from 'src/pages/Dashboard/Pages/Covit/Proveedores-y-Mantenciones/Proveedores'
  import PymMantenciones   from 'src/pages/Dashboard/Pages/Covit/Proveedores-y-Mantenciones/Mantenciones'

  

  // Residentes y Visitas
  import RyvResidentes  from 'src/pages/Dashboard/Pages/Covit/Residentes-y-Visitas/Residentes'
  import RyvVisitas   from 'src/pages/Dashboard/Pages/Covit/Residentes-y-Visitas/Visitas'

  // Mensajes
  import Mensajes from 'src/pages/Dashboard/Pages/Covit/Mensajes'


  // Alertas
  import SeguridadyAlertas from 'src/pages/Dashboard/Pages/Covit/Seguridad-y-Alertas'


  // Espacios Comunes
  import EspaciosComunes from 'src/pages/Dashboard/Pages/Covit/Espacios-Comunes/'


import Widgets from 'src/pages/Dashboard/Widgets.vue'

let componentsMenu = {
  path: '/components',
  component: DashboardLayout,
  redirect: '/components/buttons',
  name: 'Components',
  children: [
    {
      path: 'buttons',
      name: 'Buttons',
      components: {default: Buttons, header: DefaultHeader}
    },
    {
      path: 'grid-system',
      name: 'Grid System',
      components: {default: GridSystem, header: DefaultHeader}
    },
    {
      path: 'panels',
      name: 'Panels',
      components: {default: Panels, header: DefaultHeader}
    },
    {
      path: 'sweet-alert',
      name: 'Sweet Alert',
      components: {default: SweetAlert, header: SweetAlertHeader}
    },
    {
      path: 'notifications',
      name: 'Notifications',
      components: {default: Notifications, header: DefaultHeader}
    },
    {
      path: 'icons',
      name: 'Icons',
      components: {default: Icons, header: DefaultHeader}
    },
    {
      path: 'typography',
      name: 'Typography',
      components: {default: Typography, header: DefaultHeader}
    }

  ]
}
let formsMenu = {
  path: '/forms',
  component: DashboardLayout,
  redirect: '/forms/regular',
  name: 'Forms',
  children: [
    {
      path: 'regular',
      name: 'Regular Forms',
      components: {default: RegularForms, header: DefaultHeader}
    },
    {
      path: 'extended',
      name: 'Extended Forms',
      components: {default: ExtendedForms, header: DefaultHeader}
    },
    {
      path: 'validation',
      name: 'Validation Forms',
      components: {default: ValidationForms, header: DefaultHeader}
    },
    {
      path: 'wizard',
      name: 'Wizard',
      components: {default: Wizard, header: DefaultHeader}
    }
  ]
}
// Contabilidad
let contabilidadMenu = {
  path: '/contabilidad',
  component: DashboardLayout,
  redirect: '/contabilidad/ingresos',
  name: 'Contabilidad',
  children: [
    {
      path: 'ingresos',
      name: 'contabilidadingresos',
      components: {default: ContabilidadIngresos, header: DefaultHeader}
    },
    {
      path: 'egresos',
      name: 'contabilidadegresos',
      components: {default: ContabilidadEgresos, header: DefaultHeader}
    },
    {
      path: 'multas',
      name: 'contabilidadmultas',
      components: {default: ContabilidadMultas, header: DefaultHeader}
    },
    {
      path: 'condonaciones',
      name: 'contabilidadcondonaciones',
      components: {default: ContabilidadCondonaciones, header: DefaultHeader}
    }
  ]
}

// Nomina y Remuneraciones
let nyrMenu = {
  path: '/nominayremuneraciones',
  component: DashboardLayout,
  redirect: '/nominayremuneraciones/nomina',
  name: 'nominayremuneraciones',
  children: [
    {
      path: 'nomina',
      name: 'nomina',
      components: {default: NyrNomina, header: DefaultHeader}
    },
    {
      path: 'remuneraciones',
      name: 'remuneraciones',
      components: {default: NyrRemuneraciones, header: DefaultHeader}
    },
    {
      path: 'horastrabajadas',
      name: 'Horastrabajadas',
      components: {default: NyrHorasTrabajadas, header: DefaultHeader}
    }
  ]
}
// Nomina y Remuneraciones
let pymMenu = {
  path: '/proveedoresymantenciones',
  component: DashboardLayout,
  redirect: '/proveedores-y-mantenciones/proveedores',
  name: 'proveedoresymantenciones',
  children: [
    {
      path: 'proveedores',
      name: 'Proveedores',
      components: {default: PymProveedores, header: DefaultHeader}
    },
    {
      path: 'mantenciones',
      name: 'Mantenciones',
      components: {default: PymMantenciones, header: DefaultHeader}
    }
  ]
}

// Residentes y Visitas
let ryvMenu = {
  path: '/residentes-y-visitas',
  component: DashboardLayout,
  redirect: '/residentes-y-visitas/residentes',
  name: 'Residentes y Visitas',
  children: [
    {
      path: 'residentes',
      name: 'Residentes',
      components: {default: RyvResidentes, header: DefaultHeader}
    },
    {
      path: 'visitas',
      name: 'Visitas',
      components: {default: RyvVisitas, header: DefaultHeader}
    }
  ]
}

let tablesMenu = {
  path: '/table-list',
  component: DashboardLayout,
  redirect: '/table-list/regular',
  name: 'Tables',
  children: [
    {
      path: 'regular',
      name: 'Regular Tables',
      components: {default: RegularTables, header: DefaultHeader}
    },
    {
      path: 'extended',
      name: 'Extended Tables',
      components: {default: ExtendedTables, header: DefaultHeader}
    },
    {
      path: 'paginated',
      name: 'Paginated Tables',
      components: {default: PaginatedTables, header: DefaultHeader}
    }]
}

let mapsMenu = {
  path: '/maps',
  component: DashboardLayout,
  name: 'Maps',
  redirect: '/maps/google',
  children: [
    {
      path: 'google',
      name: 'Google Maps',
      components: {default: GoogleMaps, header: DefaultHeader}
    },
    {
      path: 'full-screen',
      name: 'Full Screen Map',
      meta: {
        hideContent: true,
        hideFooter: true
      },
      components: {default: FullScreenMap}
    },
    {
      path: 'vector-map',
      name: 'Vector Map',
      components: {default: VectorMaps, header: VectorMapsHeader}
    }
  ]
}

let pagesMenu = {
  path: '/pages',
  component: DashboardLayout,
  name: 'Pages',
  redirect: '/pages/user',
  children: [
    {
      path: 'user',
      name: 'User Page',
      components: {default: User, header: DefaultHeader}
    },
    {
      path: 'timeline',
      name: 'Timeline Page',
      components: {default: TimeLine, header: DefaultHeader}
    }
  ]
}

let authPages = {
  path: '/',
  component: AuthLayout,
  name: 'Authentication',
  children: [
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/pricing',
      name: 'Pricing',
      component: Pricing
    },
    {
      path: '/lock',
      name: 'Lock',
      component: Lock
    }
  ]
}

const routes = [
  {
    path: '/',
    redirect: '/login',
    name: 'Login'
  },
  authPages,
  contabilidadMenu,
  nyrMenu,
  pymMenu,
  ryvMenu,
  {
    path: '/',
    component: DashboardLayout,
    redirect: '/dashboard',
    name: 'Dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'dashboard',
        components: {default: Dashboard, header: DashboardHeader}
      },
      {
        path: 'gastoscomunes',
        name: 'gastoscomunes',
        components: {default: GastosComunes, header: DefaultHeader}
      },
      {
        path: 'mensajes',
        name: 'Mensajes',
        components: {default: Mensajes, header: DefaultHeader}
      },
      {
        path: 'seguridad-y-alertas',
        name: 'Seguridad y Alertas',
        components: {default: SeguridadyAlertas, header: DefaultHeader}
      },
      {
        path: 'espacios-comunes',
        name: 'Espacios Comunes',
        components: {default: EspaciosComunes, header: DefaultHeader}
      }
    ]
  },
  {path: '*', component: NotFound}
]

export default routes
