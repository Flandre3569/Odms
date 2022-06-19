<template>
  <n-upload multiple directory-dnd :action="actionPath" :max="5">
    <n-upload-dragger>
      <div style="margin-bottom: 12px">
        <n-icon size="48" :depth="3">
          <archive-icon />
        </n-icon>
      </div>
      <n-text style="font-size: 16px"> 点击或者拖动文件到该区域来上传 </n-text>
      <n-p depth="3" style="margin: 8px 0 0 0">
        请不要上传敏感数据，比如你的银行卡号和密码，信用卡号有效期和安全码
      </n-p>
    </n-upload-dragger>
  </n-upload>
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
import { NButton } from "naive-ui";
import { ArchiveOutline as ArchiveIcon } from "@vicons/ionicons5";

// 上传
import localCache from "@/utils/localCache";
const actionPath = `api/file/uploadOssFile/${localCache.getCache("user_id")}`;

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
  const getData = await axios.get("/api/file/findAllFile");
  console.log(getData);
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
