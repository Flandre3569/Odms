<template>
  <n-modal v-model:show="showModal">
    <n-card
      style="width: 600px"
      title="用户信息"
      :bordered="false"
      size="huge"
      role="dialog"
      aria-modal="true"
    >
      <n-table :bordered="false" :single-line="false">
        <thead>
          <tr>
            <th>id</th>
            <th>name</th>
            <th>email</th>
            <th>university</th>
            <th>createAt</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{ profileData.user_id }}</td>
            <td>{{ profileData.name }}</td>
            <td>{{ profileData.email }}</td>
            <td>{{ profileData.university }}</td>
            <td>{{ profileData.createAt }}</td>
          </tr>
        </tbody>
      </n-table>
    </n-card>
  </n-modal>
  <n-space vertical :size="12">
    <n-data-table
      :columns="columns"
      :data="renderData"
      :pagination="pagination"
    />
  </n-space>
</template>

<script setup>
import { onMounted, reactive, h, ref } from "vue";
import router from "@/router";
import axios from "axios";
import localCache from "@/utils/localCache.js";
import { NButton } from "naive-ui";

let showModal = ref(false);
const deleteFile = (row) => {
  axios.patch(`/api/user/deleteUserById/${row.id}`).then((res) => {
    if (res.data.code === 200) {
      router.go(0);
    }
  });
};

const profileData = reactive({});
const getDetail = async (row) => {
  const getData = await axios.post("api/profile/findProfile", {
    user_id: row.id,
  });
  const data = getData.data.data;
  Object.assign(profileData, data);
  showModal.value = true;
};

// 渲染数据
const renderData = reactive([]);

onMounted(async () => {
  const getData = await axios.get("/api/user/findAllUser");
  const data = getData.data;
  data.forEach((element, index) => {
    let obj = {
      key: index,
      id: element.id,
      username: element.username,
      createAt: element.createAt,
      updateAt: element.updateAt,
    };
    renderData.push(obj);
  });
});

const columns = [
  {
    title: "用户id",
    key: "id",
  },
  {
    title: "用户名",
    key: "username",
  },

  {
    title: "创建时间",
    key: "createAt",
  },
  {
    title: "更新时间",
    key: "updateAt",
  },
  {
    title: "删除",
    key: "delete",
    render(row) {
      return h(
        NButton,
        {
          size: "small",
          onClick: () => deleteFile(row),
        },
        { default: () => "delete" }
      );
    },
  },
  {
    title: "详情",
    key: "detail",
    render(row) {
      return h(
        NButton,
        {
          size: "small",
          onClick: () => getDetail(row),
        },
        { default: () => "detail" }
      );
    },
  },
];

// 分页
const pagination = { pageSize: 10 };
</script>
