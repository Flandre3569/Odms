<template>
  <div style="height: 100vh; position: relative">
    <n-layout position="absolute">
      <n-layout-header
        style="height: 80px; padding: 24px"
        bordered
        class="flex items-center justify-between px-3"
      >
        <div class="header-left flex items-center space-x-5">
          <div
            class="logo"
            style="
            background-image: url('https://octavia-admin.vercel.app/img/arctic-circle-logo.dc2036dd.svg')
            background-position: center center;
          "
          ></div>
          <h1 class="project-name">{{ projectName }}</h1>
        </div>
        <div class="header-right flex items-center">
          <n-space class="flex items-center border rounded-lg p-4">
            <!-- <n-badge value="5" :max="15" :show="show">
              <CloudDoneOutline class="w-8 h-8" />
            </n-badge> -->
            <n-avatar :size="48" :src="avatar" />
            <span class="user-name font-semibold text-lg"
              >Hello {{ userName }}</span
            >
          </n-space>
        </div>
      </n-layout-header>
      <n-layout has-sider position="absolute" style="top: 80px; bottom: 64px">
        <n-layout-sider
          collapse-mode="width"
          :collapsed-width="100"
          :width="240"
          show-trigger="bar"
          bordered
          @collapse="collapsed = true"
          @expand="collapsed = false"
        >
          <NavMenuVue :collapsed="collapsed"></NavMenuVue>
          <n-button
            text
            style="font-size: 40px; padding-left: 4vh"
            class="logout-btn fixed bottom-20"
            name="logout-btn"
          >
            <n-icon>
              <LogOutOutline />
            </n-icon>
            <span
              class="logout"
              v-if="!collapsed"
              @click="userStore.signOutAction"
            >
              log out</span
            >
          </n-button>
        </n-layout-sider>
        <n-layout content-style="padding: 24px;">
          <router-view></router-view>
        </n-layout>
      </n-layout>
      <n-layout-footer
        bordered
        position="absolute"
        style="height: 64px; padding: 24px"
      >
        底部
      </n-layout-footer>
    </n-layout>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import NavMenuVue from "./NavMenu.vue";
import { LogOutOutline } from "@vicons/ionicons5";
import { useUserStore } from "@/stores/user";
import { useProfileStore } from "@/stores/profile";
import localCache from "@/utils/localCache";

// 控制侧边栏是否展开
const collapsed = ref(false);

// user的状态管理
const userStore = useUserStore();
// profile的状态管理
const profileStore = useProfileStore();

const projectName = ref("Online Document MS");

const userName = ref(localCache.getCache("name"));
const avatar = ref(localCache.getCache("avatar"));

// 初始化信息
onMounted(() => {
  profileStore.initProfile(localCache.getCache("user_id"));
});
</script>

<style scoped lang="less">
@keyframes text-color-gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
.project-name {
  background-size: 400% 400%;
  background-image: linear-gradient(
    to right,
    #0037ff,
    #ff8000,
    #4d4b2e,
    #081204,
    #00d0ff,
    #9000ff,
    #ff008c
  );
  -webkit-background-clip: text;
  animation: text-color-gradient 10s ease infinite;
  color: transparent;
  font-size: 30px;
  font-weight: 700;
}

.logo {
  width: 46px;
  height: 46px;
}

.logout {
  font-size: 18px;
  transition: all 1s;
}

.header-right {
  cursor: pointer;
}

/* 实现淡入淡出动画 */
.cartoon-enter-from,
.cartoon-leave-to {
  opacity: 0;
}

.cartoon-enter-to,
.cartoon-leave-from {
  opacity: 1;
}

.cartoon-enter-active,
.cartoon-leave-active {
  transition: opacity 0.5s ease;
}
</style>
