import localCache from "@/utils/localCache";
import axios from "axios";
import { defineStore } from "pinia";

export const useProfileStore = defineStore({
  id: "profile",
  state: () => ({
    user_id: "",
    name: "",
    avatar: "",
    email: "",
    university: "",
    createAt: "",
    updateAt: ""
  }),
  getters: {},
  actions: {
    async initProfile(user_id) {
      const userProfile = await axios.post("api/profile/findProfile", {
        user_id
      })

      const result = userProfile.data.data;
      const { name, avatar, email, university, createAt, updateAt } = result;
      this.$patch({
        user_id,
        name,
        avatar,
        email,
        university,
        createAt,
        updateAt
      })
      localCache.setCache("avatar", avatar);
      localCache.setCache("name", name)
    }
  }
})
