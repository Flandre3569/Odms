import router from "@/router";
import localCache from "@/utils/localCache";
import axios from "axios";
import { defineStore } from "pinia";

export const useManagerStore = defineStore({
  id: "manager",
  state: () => ({
    id: "",
    token: "",
    role: ""
  }),
  getters: {},
  actions: {
    async login(payload) {
      const { username, password } = payload;
      const result = await axios.post("api/manager/login", {
        username,
        password
      })
      const { id } = result.data.data;
      const { token } = result.data;
      this.$patch({
        id, token, role: "manager"
      })
      localCache.setCache("token", token);
      localCache.setCache("user_id", id);
      localCache.setCache("role", "manager");

      router.push("/main")
    }
  }
})
