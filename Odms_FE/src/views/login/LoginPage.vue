<template>
  <div class="login">
    <n-card content-style="padding: 0;" class="login-card">
      <n-tabs
        type="line"
        size="large"
        :tabs-padding="20"
        pane-style="padding: 20px;"
      >
        <n-tab-pane name="login">
          <n-radio-group name="left-size" style="margin-bottom: 12px">
          </n-radio-group>
          <n-form
            :label-width="80"
            :model="userLogin"
            :rules="rules"
            label-placement="top"
          >
            <n-form-item label="username" path="username">
              <n-input v-model:value="userLogin.username" />
            </n-form-item>
            <n-form-item path="password" label="password">
              <n-input
                v-model:value="userLogin.password"
                type="password"
                @keydown.enter.prevent
              />
            </n-form-item>

            <n-form-item>
              <n-button attr-type="button" @click="loginHandle" class="w-1/3">
                登录
              </n-button>
            </n-form-item>
          </n-form>
        </n-tab-pane>
        <n-tab-pane name="register">
          <n-radio-group name="left-size" style="margin-bottom: 12px">
          </n-radio-group>
          <n-form
            :label-width="80"
            :model="userRegister"
            :rules="rules"
            label-placement="top"
          >
            <n-form-item label="username" path="username">
              <n-input v-model:value="userRegister.username" />
            </n-form-item>
            <n-form-item path="password" label="password">
              <n-input
                v-model:value="userRegister.password"
                type="password"
                @keydown.enter.prevent
              />
            </n-form-item>

            <n-form-item>
              <n-button
                attr-type="button"
                @click="registerHandle"
                class="w-1/3"
              >
                注册
              </n-button>
            </n-form-item>
          </n-form>
        </n-tab-pane>
      </n-tabs>
    </n-card>
  </div>
</template>

<script setup>
import rules from "./rules/accountRules";
import { reactive } from "vue";
import { useUserStore } from "@/stores/login";
const userStore = useUserStore();

// 登录
const userLogin = reactive({
  username: "",
  password: "",
});
const loginHandle = () => {
  userStore.loginAction(userLogin);
};

// 注册
const userRegister = reactive({
  username: "",
  password: "",
});
const registerHandle = () => {
  userStore.signUpAction(userRegister);
};
</script>

<style scoped lang="less">
.login {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("http://mingxuan3569.oss-cn-beijing.aliyuncs.com/image/bg.2cfdbb33.svg");
  background-size: cover;
}
.login-card {
  border-radius: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 50%;
  height: 50%;
}

n-form-item {
  width: 100%;
}
</style>
