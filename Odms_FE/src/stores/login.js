import { defineStore } from "pinia";
import router from "@/router";
import localCache from "@/utils/localCache";
import { loginRequest, registerRequest } from "@/service/login/index"
import axios from "axios";

export const useUserStore = defineStore({
  id: "user",
  state: () => ({
    id: "",
    token: "",
  }),
  getters: {},
  actions: {
    async loginAction(payload) {
      const { username, password } = payload
      // const loginInfo = await loginRequest(username, password)
      const loginInfo = await axios.post("api/user/login", {
        username,
        password
      })
      const result = loginInfo.data;

      localCache.setCache("token", result.token);
      localCache.setCache("user_id", result.data);

      this.$state.id = result.data;
      this.$state.token = result.token;

      const flag = localCache.getCache("token");
      if (flag) {
        router.push("/main");
      }
    },

    signOutAction() {
      this.$state.id = "";
      this.$state.token = "";
    },

    async signUpAction(payload) {
      const { username, password } = payload;
      const registerInfo = await registerRequest(username, password);
      console.log(registerInfo);
    },
  },
});
