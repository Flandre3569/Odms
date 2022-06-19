<template>
  <n-space vertical>
    <n-avatar round :size="300" :src="formValue.user.avatar" class="mb-5" />
    <hr class="py-1" />
    <n-space class="flex justify-between space-x-20">
      <div class="flex space-x-5">
        <span>Form Item:</span>
        <n-switch v-model:value="formItemShowLabel" />
      </div>
    </n-space>
    <n-form ref="formRef" :model="formValue">
      <n-form-item
        label="姓名"
        path="user.name"
        :show-label="formItemShowLabel"
      >
        <n-input v-model:value="formValue.user.name" placeholder="输入姓名" />
      </n-form-item>
      <n-form-item
        label="邮箱"
        path="user.email"
        :show-label="formItemShowLabel"
      >
        <n-input v-model:value="formValue.user.email" placeholder="输入邮箱" />
      </n-form-item>
      <n-form-item
        label="学校"
        path="user.university"
        :show-label="formItemShowLabel"
      >
        <n-input
          v-model:value="formValue.user.university"
          placeholder="输入您的大学"
        />
      </n-form-item>
      <n-button @click="changeHandle">完成修改</n-button>
    </n-form>
  </n-space>
</template>

<script setup>
import { ref } from "vue";
import localCache from "@/utils/localCache";
import { useProfileStore } from "@/stores/profile";
import axios from "axios";
import router from "@/router";

const formRef = ref(null);

const formItemShowLabel = ref(true);
const profileStore = useProfileStore();

const formValue = ref({
  user: {
    name: profileStore.name,
    email: profileStore.email,
    university: profileStore.university,
    avatar: localCache.getCache("avatar"),
  },
});

// 修改信息
const changeHandle = () => {
  axios
    .patch("api/profile/updateProfile", {
      user_id: localCache.getCache("user_id"),
      name: formValue.value.user.name,
      avatar: formValue.value.user.avatar,
      email: formValue.value.user.email,
      university: formValue.value.user.university,
    })
    .then((res) => {
      if (res.data.code === 200) {
        alert("更新成功");
        router.push("/overview");
      }
    });
};
</script>
