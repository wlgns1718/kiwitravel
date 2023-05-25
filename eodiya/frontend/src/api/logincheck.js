import store from "@/store";
import router from "@/router/index";

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
export const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  //   console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    // console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    // next({ name: "login" });
    router.push({ name: "UserLogin" });
  } else {
    // console.log("로그인 했다!!!!!!!!!!!!!.");
    if (next) next();
  }
};
