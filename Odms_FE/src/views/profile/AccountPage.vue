<template>
  <n-space vertical>
    <n-steps :current="current" :status="currentStatus">
      <n-step title="验证身份">
        <div class="n-step-description">
          <p>请输入您的密码进行核实</p>
          <n-button
            v-if="current === 1"
            :size="btnSize"
            @click="handleButtonClick"
          >
            {{ btnContent }}
          </n-button>
        </div>
      </n-step>
      <n-step title="修改密码">
        <div class="n-step-description">
          <p>请输入您的新密码</p>
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
            @click="handleButtonClick"
          >
            {{ btnContent }}
          </n-button>
        </div>
      </n-step>
      <n-step title="操作完成">
        <div class="n-step-description">
          <p>您的操作已完成</p>
          <n-button
            v-if="current === 4"
            :size="btnSize"
            @click="handleButtonClick"
          >
            {{ btnContent }}
          </n-button>
        </div>
      </n-step>
    </n-steps>
  </n-space>
</template>

<script setup>
import { ref } from "vue";

const current = ref(1);
const btnSize = ref("medium");
const btnContent = ref("continue");

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
</style>
