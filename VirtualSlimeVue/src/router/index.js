import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/discover',
    name: 'Discover',
    component: () => import('../views/Discover.vue')
  },
  {
    path: '/chat',
    name: 'Chat',
    component: () => import('../views/Chat.vue')
  },
  {
    path: '/cart',
    name: 'Cart',
    component: () => import('../views/Cart.vue')
  },
  {
    path: '/user/:uid',
    name: 'User',
    component: () => import('../views/User.vue')
  },
  {
    path: '/user',
    name: 'curUser',
    component: () => import('../views/User.vue')
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/setting',
    name: 'Setting',
    component: () => import('../views/Setting.vue')
  },
  {
    path: '/secure',
    name: 'Secure',
    component: () => import('../views/Secure.vue')
  },
  {
    path: '/pay',
    name: 'Pay',
    component: () => import('../views/Pay.vue')
  },
  {
    path: '/item/:iid',
    name: 'Item',
    component: () => import('../views/Item.vue')
  },
  {
    path: '/user/item',
    name: 'MyItem',
    component: () => import('../views/MyItem.vue')
  },
  {
    path: '/user/bought',
    name: 'MyBought',
    component: () => import('../views/MyBought.vue')
  },
  {
    path: '/user/comments',
    name: 'MyComments',
    component: () => import('../views/MyComments.vue')
  },
  {
    path: '/search/:context',
    name: 'Search',
    component: () => import('../views/Search.vue')
  },
  {
    path: '/item/:iid/buy',
    name: 'ItemBuy',
    component: () => import('../views/ItemBuy.vue')
  },
  {
    path: '/otheruser/:uid',
    name: 'othUser',
    component: () => import('../views/OthUser.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
