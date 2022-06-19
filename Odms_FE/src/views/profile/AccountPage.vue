<template>
  <n-space vertical>
    <n-steps :current="current">
      <n-step title="验证身份">
        <div class="n-step-description">
          <p>请输入您的密码进行核实</p>
          <n-input
            class="pwd-input"
            type="password"
            show-password-on="click"
            :maxlength="8"
            v-model:value="user.password"
            v-if="current === 1"
          >
            <template #password-visible-icon>
              <n-icon :size="16" :component="GlassesOutline" />
            </template>
            <template #password-invisible-icon>
              <n-icon :size="16" :component="Glasses" />
            </template>
          </n-input>
          <n-button
            v-if="current === 1"
            :size="btnSize"
            @click="handleChangePwd"
          >
            {{ btnContent }}
          </n-button>
        </div>
      </n-step>
      <n-step title="修改密码">
        <div class="n-step-description">
          <p>请输入您的新密码</p>
          <n-input
            class="pwd-input"
            type="password"
            show-password-on="click"
            :maxlength="8"
            v-model:value="changePwd.password"
            v-if="current === 2"
          >
            <template #password-visible-icon>
              <n-icon :size="16" :component="GlassesOutline" />
            </template>
            <template #password-invisible-icon>
              <n-icon :size="16" :component="Glasses" />
            </template>
          </n-input>
          <n-button
            v-if="current === 2"
            :size="btnSize"
            @click="handleButtonClick"
          >
            {{ btnContent }}
          </n-button>
        </div>
      </n-step>
      <n-step title="请确认操作">
        <div class="n-step-description">
          <p>您确定要修改密码吗？</p>
          <n-button
            v-if="current === 3"
            :size="btnSize"
            @click="changePwdHandle"
          >
            {{ btnContent }}
          </n-button>
        </div>
      </n-step>
      <n-step title="操作完成">
        <div class="n-step-description">
          <p>您的操作已完成</p>
          <n-result
            class="pwd-input"
            status="success"
            title="成功"
            description="congratulation!"
            v-if="current === 4"
          >
          </n-result>
        </div>
      </n-step>
    </n-steps>
  </n-space>

  <n-message-provider placement="top-right"> </n-message-provider>
</template>

<script setup>
import { reactive, ref } from "vue";
import { GlassesOutline, Glasses } from "@vicons/ionicons5";
import { useUserStore } from "@/stores/user";
import localCache from "@/utils/localCache";

const current = ref(1);
const btnSize = ref("medium");
const btnContent = ref("continue");

// user pinia
const userStore = useUserStore();

// 验证身份
const user = reactive({
  username: localCache.getCache("username"),
  password: "",
});

// 确认身份步骤
const handleChangePwd = async () => {
  await userStore.judgePwd(user);
  if (localCache.getCache("result") === "success") {
    localCache.deleteCache("result");
    current.value = (current.value % 4) + 1;
  } else {
    localCache.deleteCache("result");
    alert("验证失败，请检查您的密码");
  }
};

// 修改密码
const changePwd = reactive({
  username: localCache.getCache("username"),
  password: "",
});

const changePwdHandle = async () => {
  await userStore.changePwd(changePwd);
  if (localCache.getCache("result") === "success") {
    localCache.deleteCache("result");
    current.value = (current.value % 4) + 1;
  } else {
    localCache.deleteCache("result");
    alert("修改失败");
  }
};

const handleButtonClick = () => {
  current.value = (current.value % 4) + 1;
};
</script>

<style scoped>
.n-button {
  position: fixed;
  bottom: 10%;
  width: 10%;
  left: 50%;
  transform: translateX(-50%);
}

.pwd-input {
  position: fixed;
  width: 20vw;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
