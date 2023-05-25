import Vue from "vue";
import VueRouter from "vue-router";
import { onlyAuthUser } from "@/api/logincheck";
// import store from "@/store/index";

//views
import AppMain from "@/views/AppMain.vue";
import AppTour from "@/views/AppTour.vue";
import AppBoard from "@/views/AppBoard.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/tour",
    name: "tour",
    component: AppTour,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/AppUser"),
    redirect: "/user/login",
    children: [
      {
        path: "login",
        name: "UserLogin",
        component: () => import("@/components/user/TheLogin"),
      },
      {
        path: "regist",
        name: "UserRegist",
        component: () => import("@/components/user/TheRegist"),
      },
      {
        path: "mypage",
        name: "UserMypage",
        component: () => import("@/components/user/TheMypage"),
      },
      {
        path: "userpage/:userno",
        name: "Userpage",
        component: () => import("@/components/user/TheUserPage"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: AppBoard,
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "detail/:no",
        name: "boarddetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDetail"),
      },
      {
        path: "modify/:no",
        name: "boardmodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "write",
        name: "boardwrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "notice",
        name: "noticelist",
        component: () => import("@/components/board/BoardNotice"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

// router.beforeEach(() => {
//   store.dispatch("headerStore/navToggle");
// });

export default router;
