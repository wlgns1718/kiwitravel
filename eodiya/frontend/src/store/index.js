import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import userStore from "@/store/modules/userStore";
import tourStore from "@/store/modules/tourStore";
import headerStore from "@/store/modules/headerStore";
import boardStore from "@/store/modules/boardStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: { userStore, tourStore, headerStore, boardStore },

  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});
