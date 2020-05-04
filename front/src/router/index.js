import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../components/main/PageMain'
import Signup from '../components/member/PageSignUp'
import Signin from '../components/member/PageSignIn'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/signin',
    name: 'Signin',
    component: Signin
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
