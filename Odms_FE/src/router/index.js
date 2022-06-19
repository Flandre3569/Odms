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
      redirect: () => {
        if (localCache.getCache("role") === "manager") {
          return "/dashboard"
        } else {
          return "/overview"
        }
      },
      children: [
        {
          path: "/overview",
          name: "overview",
          component: () => import("@/views/overview/OverviewPage.vue")
        },
        {
          path: "/account",
          name: "account",
          component: () => import("@/views/profile/AccountPage.vue")
        },
        {
          path: "/profile",
          name: "profile",
          component: () => import("@/views/profile/ProfilePage.vue")
        },
        {
          path: "/file-display",
          name: "file-display",
          component: () => import("@/views/file/FileDisplayPage.vue")
        },
        {
          path: "/file-operate",
          name: "file-operate",
          component: () => import("@/views/file/FileOperatePage.vue")
        },
        {
          path: "/file-share",
          name: "file-share",
          component: () => import("@/views/file/FileSharePage.vue")
        },
        {
          path: "/friendship",
          name: "friendship",
          component: () => import("@/views/friendship/FriendPage.vue")
        },
        {
          path: "/dashboard",
          name: "dashboard",
          component: () => import("@/views/dashboard/DashboardPage.vue")
        },
        {
          path: "/client",
          name: "client",
          component: () => import("@/views/manage/UsersPage.vue")
        },
        {
          path: "/file-manage",
          name: "file-manage",
          component: () => import("@/views/manage/FilesPage.vue")
        }
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
