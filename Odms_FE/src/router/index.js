import { createRouter, createWebHistory } from 'vue-router'
import localCache from '@/utils/localCache';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/login",
      name: "login",
      component: () => import("@/views/login/LoginPage.vue")
    },
    {
      path: '/',
      name: 'home',
      redirect: "/login",
    },
    {
      path: "/main",
      name: "main",
      component: () => import("@/views/main/HomeView.vue"),
      children: [

      ]
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'not-found',
      component: () => import('@/views/NotFound.vue')
    }
  ]
})

// 守卫
router.beforeEach((to) => {
  if (to.path !== '/login') {
    const loginStatus = localCache.getCache('token');
    if (!loginStatus) {
      return '/login';
    }
  }

  // if (to.path === '/main') {
  //   return firstMenu.url;
  // }
});

export default router;
