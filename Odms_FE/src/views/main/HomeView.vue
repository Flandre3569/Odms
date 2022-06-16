<template>
  <div style="height: 100vh; position: relative">
    <n-layout position="absolute">
      <n-layout-header
        style="height: 64px; padding: 24px"
        bordered
        class="flex items-center space-x-5"
      >
        <div
          class="logo"
          style="
            background-image: url('https://octavia-admin.vercel.app/img/arctic-circle-logo.dc2036dd.svg')
            background-position: center center;
          "
        ></div>
        <h1 class="project-name">{{ projectName }}</h1>
      </n-layout-header>
      <n-layout has-sider position="absolute" style="top: 64px; bottom: 64px">
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
            style="font-size: 40px; padding-left: 3vh"
            class="logout-btn fixed bottom-20"
            name="logout-btn"
          >
            <n-icon>
              <LogOutOutline />
            </n-icon>
            <span class="logout" v-if="!collapsed"> log out</span>
          </n-button>
        </n-layout-sider>
        <n-layout content-style="padding: 24px;"> 从这里放内容 </n-layout>
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
import { ref } from "vue";
import NavMenuVue from "./NavMenu.vue";
import { LogOutOutline } from "@vicons/ionicons5";
const collapsed = ref(false);

const projectName = ref("Online Document MS");
</script>

<style scoped lang="less">
.logo {
  width: 2vw;
  height: 2vw;
  border-radius: 50%;
}

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
    #ff0000,
    #ff8000,
    #fff200,
    #51ff00,
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

.logout {
  font-size: 18px;
}

.logout-btn {
  transition: all 1s;
}

.el-avatar {
  margin-right: 20px;
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
