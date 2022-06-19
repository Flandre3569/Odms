import { defineStore } from "pinia";
import router from "@/router";
import localCache from "@/utils/localCache";
import { registerRequest } from "@/service/login/index"
import axios from "axios";

export const useUserStore = defineStore({
  id: "user",
  state: () => ({
    id: "",
    token: "",
    role: ""
  }),
  getters: {},
  actions: {
    async loginAction(payload) {
      const { username, password } = payload
      localCache.setCache("username", username);
      // const loginInfo = await loginRequest(username, password)
      const loginInfo = await axios.post("api/user/login", {
        username,
        password
      })
      const result = loginInfo.data;

      localCache.setCache("token", result.token);
      localCache.setCache("user_id", result.data);
      localCache.setCache("role", "user");

      this.$state.id = result.data;
      this.$state.token = result.token;
      this.$state.role = "user"

      const flag = localCache.getCache("token");
      if (flag) {
        router.push("/main");
      }
    },

    signOutAction() {
      this.$state.id = "";
      this.$state.token = "";
      this.$state.role = "";
      localStorage.clear();
      router.push("/login");
    },

    async signUpAction(payload) {
      const { username, password } = payload;
      const registerInfo = await registerRequest(username, password);
      console.log(registerInfo);
    },

    async judgePwd(payload) {
      const { username, password } = payload;
      const loginInfo = await axios.post("api/user/login", {
        username,
        password
      })
      const result = loginInfo.data;
      result.code === 200 ? localCache.setCache("result", "success") : localCache.setCache("result", "failure");
    },

    async changePwd(payload) {
      const { username, password } = payload;
      const changeInfo = await axios.patch("api/user/updateUser", {
        id: localCache.getCache("user_id"),
        username,
        password
      })
      const result = changeInfo.data;
      console.log(result);
      result.code === 200 ? localCache.setCache("result", "success") : localCache.setCache("result", "failure");
    }
  },
});
