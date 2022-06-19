<script setup>
import localCache from "@/utils/localCache";
import axios from "axios";
import { reactive, ref, onMounted } from "vue";

// 拿到用户基本信息
const profile = reactive({});

axios
  .post("/api/profile/findProfile", {
    user_id: localCache.getCache("user_id"),
  })
  .then((res) => {
    Object.assign(profile, res.data.data);
  });

// 数值动画
const numberAnimationInstRef = ref(null);

const handleClick = () => {
  numberAnimationInstRef.value?.play();
};

let getData = reactive({
  number: localCache.getCache("fileNumber"),
});
onMounted(async () => {
  const user_id = localCache.getCache("user_id");
  const fileNumber = await axios.get(`api/file/fileCount/${user_id}`);
  localCache.setCache("fileNumber", fileNumber.data.data);
  await handleClick();
});
</script>

<template>
  <div class="overview flex space-x-10">
    <n-card :title="profile.name">
      <template #cover>
        <img :src="profile.avatar" />
      </template>
      <div class="space-y-3">
        <p>{{ profile.university }}</p>
        <p>{{ profile.email }}</p>
      </div>
    </n-card>
    <n-card title="文档数" size="huge">
      <n-statistic label="File Number" tabular-nums>
        <n-number-animation
          ref="numberAnimationInstRef"
          :from="0.0"
          :to="getData.number"
          :active="false"
          :precision="2"
        />
      </n-statistic>
    </n-card>
    <n-card title="文档分类" size="huge"></n-card>
  </div>
</template>

<style scoped>
.n-card {
  max-width: 300px;
}
</style>
