import AddUser from 'src/pages/Dashboard/Pages/Register'
import AuthLayout from 'src/pages/Dashboard/Pages/AuthLayout.vue'
import ContabilidadCondonaciones from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Condonaciones'
import ContabilidadEgresos from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Egresos'
import ContabilidadIngresos from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Ingresos'
import ContabilidadMultas from 'src/pages/Dashboard/Pages/Covit/Contabilidad/Multas'
import Dashboard from 'src/pages/Dashboard/Dashboard/Dashboard'
import DashboardHeader from 'src/pages/Dashboard/Dashboard/DashboardHeader.vue'
import DashboardLayout from 'src/pages/Dashboard/Layout/DashboardLayout.vue'
import DefaultHeader from 'src/pages/Dashboard/DefaultHeader'
import EspaciosComunes from 'src/pages/Dashboard/Pages/Covit/Espacios-Comunes/'
import GastosComunes from 'src/pages/Dashboard/Pages/Covit/Gastos-Comunes'
import Login from 'src/pages/Dashboard/Pages/Login.vue'
import Mensajes from 'src/pages/Dashboard/Pages/Covit/Mensajes'
import NotFound from 'src/pages/NotFound.vue'
import NyrNomina from 'src/pages/Dashboard/Pages/Covit/Nomina-y-Remuneraciones/Nomina'
import NyrRemuneraciones from 'src/pages/Dashboard/Pages/Covit/Nomina-y-Remuneraciones/Remuneraciones'
import NyrHorasTrabajadas from 'src/pages/Dashboard/Pages/Covit/Nomina-y-Remuneraciones/Horas-trabajadas'
import PymMantenciones from 'src/pages/Dashboard/Pages/Covit/Proveedores-y-Mantenciones/Mantenciones'
import PymProveedores from 'src/pages/Dashboard/Pages/Covit/Proveedores-y-Mantenciones/Proveedores'
import RyvResidentes from 'src/pages/Dashboard/Pages/Covit/Residentes-y-Visitas/Residentes'
import RyvVisitas from 'src/pages/Dashboard/Pages/Covit/Residentes-y-Visitas/Visitas'
import Register from 'src/pages/Dashboard/Pages/Register.vue'
import SeguridadyAlertas from 'src/pages/Dashboard/Pages/Covit/Seguridad-y-Alertas'
import User from 'src/pages/Dashboard/Pages/UserProfile'

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
      path: 'horas-trabajadas',
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
        path: 'agregar-usuario',
        components: { default: AddUser, header: DefaultHeader },
        name: 'agregarusuario'
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
  {
    path: '*',
    component: DashboardLayout,
    children: [
      {
        path: '*',
        name: 'notfound',
        components: { default: NotFound, header: DefaultHeader }
      }
    ]
  }
]

export default routes
