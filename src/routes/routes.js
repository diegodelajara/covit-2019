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

// Calendar
import Calendar from 'src/pages/Dashboard/Calendar/CalendarRoute.vue'
// Charts
import Charts from 'src/pages/Dashboard/Charts.vue'

// Covit
// Contabilidad
import ContabilidadIngresos from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Ingresos'
import ContabilidadEgresos from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Egresos'
import ContabilidadMultas from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Multas'
import ContabilidadCondonaciones from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Condonaciones'

// GGCC
import GastosComunes from 'src/pages/Dashboard/Pages/Covit/Gastos-Comunes'

// Nomina y Remuneraciones
import NyrNomina from 'src/pages/Dashboard/Pages/Covit/Nomina-y-Remuneraciones/Nomina'
import NyrRemuneraciones from 'src/pages/Dashboard/Pages/Covit/Nomina-y-Remuneraciones/Remuneraciones'
import NyrHorasTrabajadas from 'src/pages/Dashboard/Pages/Covit/Nomina-y-Remuneraciones/Horas-trabajadas'



// Proveedores y Mantenciones
import PymProveedores from 'src/pages/Dashboard/Pages/Covit/Proveedores-y-Mantenciones/Proveedores'
import PymMantenciones from 'src/pages/Dashboard/Pages/Covit/Proveedores-y-Mantenciones/Mantenciones'



// Residentes y Visitas
import RyvResidentes from 'src/pages/Dashboard/Pages/Covit/Residentes-y-Visitas/Residentes'
import RyvVisitas from 'src/pages/Dashboard/Pages/Covit/Residentes-y-Visitas/Visitas'

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
      components: { default: Buttons, header: DefaultHeader }
    },
    {
      path: 'grid-system',
      name: 'Grid System',
      components: { default: GridSystem, header: DefaultHeader }
    },
    {
      path: 'panels',
      name: 'Panels',
      components: { default: Panels, header: DefaultHeader }
    },
    {
      path: 'sweet-alert',
      name: 'Sweet Alert',
      components: { default: SweetAlert, header: SweetAlertHeader }
    },
    {
      path: 'notifications',
      name: 'Notifications',
      components: { default: Notifications, header: DefaultHeader }
    },
    {
      path: 'icons',
      name: 'Icons',
      components: { default: Icons, header: DefaultHeader }
    },
    {
      path: 'typography',
      name: 'Typography',
      components: { default: Typography, header: DefaultHeader }
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
      components: { default: RegularForms, header: DefaultHeader }
    },
    {
      path: 'extended',
      name: 'Extended Forms',
      components: { default: ExtendedForms, header: DefaultHeader }
    },
    {
      path: 'validation',
      name: 'Validation Forms',
      components: { default: ValidationForms, header: DefaultHeader }
    },
    {
      path: 'wizard',
      name: 'Wizard',
      components: { default: Wizard, header: DefaultHeader }
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
      components: { default: ContabilidadIngresos, header: DefaultHeader }
    },
    {
      path: 'egresos',
      name: 'contabilidadegresos',
      components: { default: ContabilidadEgresos, header: DefaultHeader }
    },
    {
      path: 'multas',
      name: 'contabilidadmultas',
      components: { default: ContabilidadMultas, header: DefaultHeader }
    },
    {
      path: 'condonaciones',
      name: 'contabilidadcondonaciones',
      components: { default: ContabilidadCondonaciones, header: DefaultHeader }
    }
  ]
}

// Nomina y Remuneraciones
let nyrMenu = {
  path: '/nomina-y-remuneraciones',
  component: DashboardLayout,
  redirect: '/nomina-y-remuneraciones/nomina',
  name: 'nominayremuneraciones',
  children: [
    {
      path: 'nomina',
      name: 'nomina',
      components: { default: NyrNomina, header: DefaultHeader }
    },
    {
      path: 'remuneraciones',
      name: 'remuneraciones',
      components: { default: NyrRemuneraciones, header: DefaultHeader }
    },
    {
      path: 'horastrabajadas',
      name: 'horastrabajadas',
      components: { default: NyrHorasTrabajadas, header: DefaultHeader }
    }
  ]
}
// proveedores-y-mantenciones
let pymMenu = {
  path: '/proveedores-y-mantenciones',
  component: DashboardLayout,
  redirect: '/proveedores-y-mantenciones/proveedores',
  name: 'proveedoresymantenciones',
  children: [
    {
      path: 'proveedores',
      name: 'proveedores',
      components: { default: PymProveedores, header: DefaultHeader }
    },
    {
      path: 'mantenciones',
      name: 'mantenciones',
      components: { default: PymMantenciones, header: DefaultHeader }
    }
  ]
}

// Residentes y Visitas
let ryvMenu = {
  path: '/residentes-y-visitas',
  component: DashboardLayout,
  redirect: '/residentes-y-visitas/residentes',
  name: 'residentesyvisitas',
  children: [
    {
      path: 'residentes',
      name: 'residentes',
      components: { default: RyvResidentes, header: DefaultHeader }
    },
    {
      path: 'visitas',
      name: 'visitas',
      components: { default: RyvVisitas, header: DefaultHeader }
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
      components: { default: User, header: DefaultHeader }
    },
    {
      path: 'timeline',
      name: 'Timeline Page',
      components: { default: TimeLine, header: DefaultHeader }
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
        path: 'mi-perfil',
        components: { default: User, header: DefaultHeader },
        name: 'miperfil'
      },
      {
        path: 'dashboard',
        name: 'dashboard',
        components: { default: Dashboard, header: DashboardHeader }
      },
      {
        path: 'gastos-comunes',
        name: 'gastoscomunes',
        components: { default: GastosComunes, header: DefaultHeader }
      },
      {
        path: 'mensajes',
        name: 'mensajes',
        components: { default: Mensajes, header: DefaultHeader }
      },
      {
        path: 'seguridad-y-alertas',
        name: 'seguridadyalertas',
        components: { default: SeguridadyAlertas, header: DefaultHeader }
      },
      {
        path: 'espacios-comunes',
        name: 'espacioscomunes',
        components: { default: EspaciosComunes, header: DefaultHeader }
      }
    ]
  },
  { path: '*', component: NotFound }
]

export default routes
