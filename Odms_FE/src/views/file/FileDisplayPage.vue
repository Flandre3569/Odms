<template>
  <n-space vertical :size="12">
    <n-data-table
      :columns="columns"
      :data="renderData"
      :pagination="pagination"
    />
  </n-space>
</template>

<script setup>
import { onMounted, reactive, h } from "vue";
import router from "@/router";
import axios from "axios";
import localCache from "@/utils/localCache.js";
import { NButton } from "naive-ui";

const deleteFile = (row) => {
  axios.delete(`/api/file/deleteFile/${row.id}`).then((res) => {
    if (res.data.code === 200) {
      router.go(0);
    }
  });
};

const downloadFile = (row) => {
  axios.get(`/api/file/findFileById/${row.id}`).then((res) => {
    window.open(res.data.data.address);
  });
};

// 渲染数据
const renderData = reactive([]);

onMounted(async () => {
  const user_id = localCache.getCache("user_id");
  const getData = await axios.post(`/api/file/findFileList/${user_id}`);
  const data = getData.data.data;
  data.forEach((element, index) => {
    let obj = {
      key: index,
      id: element.id,
      name: element.name,
      type: element.name.split(".")[1],
      createAt: element.createAt,
      updateAt: element.updateAt,
    };
    renderData.push(obj);
  });
});

const columns = [
  {
    title: "文件ID",
    key: "id",
  },
  {
    title: "文件名",
    key: "name",
  },
  {
    title: "文件类型",
    key: "type",
  },
  {
    title: "上传时间",
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
    title: "下载",
    key: "download",
    render(row) {
      return h(
        NButton,
        {
          size: "small",
          onClick: () => downloadFile(row),
        },
        { default: () => "download" }
      );
    },
  },
];

// 分页
const pagination = { pageSize: 10 };
</script>
