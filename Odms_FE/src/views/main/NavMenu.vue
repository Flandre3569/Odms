<script setup>
import { h } from "vue";
import { NIcon } from "naive-ui";
import {
  BookmarkOutline,
  CaretDownOutline,
  IdCardOutline,
  ColorPaletteOutline,
  InformationCircleOutline,
  PersonOutline,
  FileTrayFullOutline,
  FolderOpenOutline,
  CodeSlashOutline,
  ShareOutline,
  ChatboxOutline,
} from "@vicons/ionicons5";

const menuOptions = [
  {
    label: "信息总览",
    key: "overview",
  },
  {
    label: "个人账户管理",
    key: "profile",
    children: [
      {
        label: "账号密码管理",
        key: "idCard",
      },
      {
        label: "个人信息管理",
        key: "profileCard",
      },
    ],
  },
  {
    label: "文件管理",
    key: "fileManage",
    children: [
      {
        label: "文件总览",
        key: "filesDisplay",
      },
      {
        label: "文件操作",
        key: "filesOperate",
      },
      {
        label: "文件共享",
        key: "fileDelivery",
      },
    ],
  },
  {
    label: "友链",
    key: "friendship",
  },
];

const props = defineProps(["collapsed"]);

const renderMenuLabel = (option) => {
  if ("href" in option) {
    return h("a", { href: option.href, target: "_blank" }, option.label);
  }
  return option.label;
};

const renderMenuIcon = (option) => {
  if (option.key === "overview") return h(ColorPaletteOutline);
  if (option.key === "profile") return h(IdCardOutline);
  if (option.key === "idCard") return h(InformationCircleOutline);
  if (option.key === "profileCard") return h(PersonOutline);
  if (option.key === "fileManage") return h(FileTrayFullOutline);
  if (option.key === "filesDisplay") return h(FolderOpenOutline);
  if (option.key === "filesOperate") return h(CodeSlashOutline);
  if (option.key === "fileDelivery") return h(ShareOutline);
  if (option.key === "friendship") return h(ChatboxOutline);
  return h(NIcon, null, { default: () => h(BookmarkOutline) });
};

const expandIcon = () => {
  return h(NIcon, null, { default: () => h(CaretDownOutline) });
};
</script>

<template>
  <n-menu
    :collapsed="props.collapsed"
    :collapsed-width="100"
    :collapsed-icon-size="30"
    :options="menuOptions"
    :render-label="renderMenuLabel"
    :render-icon="renderMenuIcon"
    :expand-icon="expandIcon"
  />
</template>

<style scoped></style>
