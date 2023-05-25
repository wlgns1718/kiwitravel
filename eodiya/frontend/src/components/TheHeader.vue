<template>
  <header class="header">
    <div class="wrap-header">
      <nav class="header-left">
        <router-link to="/" @click.native="navBtnToggle">
          <img src="@/assets/KIWI3.png" alt="" />
        </router-link>
      </nav>
      <div class="header-center" :class="{ 'header-center-fix': userInfo }">
        <ul>
          <li v-if="navBtn.homeBtnOff">
            <router-link to="/">
              <svg
                width="36"
                height="36"
                viewBox="0 0 28 28"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M3 14L14 3L25 14"
                  stroke="#16191D"
                  stroke-width="1.2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                ></path>
                <path
                  d="M5 24V12L14 3L23 12V24C23 24.5523 22.5523 25 22 25H17.25C16.6977 25 16.25 24.5523 16.25 24V19.4C16.25 18.8477 15.8023 18.4 15.25 18.4H12.75C12.1977 18.4 11.75 18.8477 11.75 19.4V24C11.75 24.5523 11.3023 25 10.75 25H6C5.44772 25 5 24.5523 5 24Z"
                  stroke="#16191D"
                  stroke-width="1.2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                ></path>
              </svg>
            </router-link>
          </li>
          <li v-if="navBtn.homeBtnOn">
            <router-link to="/"
              ><svg
                width="36"
                height="36"
                viewBox="0 0 28 28"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M3 14L14 3L25 14"
                  stroke="#0060FF"
                  stroke-width="1.2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                ></path>
                <path
                  d="M5 24V12L14 3L23 12V24C23 24.5523 22.5523 25 22 25H17.25C16.6977 25 16.25 24.5523 16.25 24V19.4C16.25 18.8477 15.8023 18.4 15.25 18.4H12.75C12.1977 18.4 11.75 18.8477 11.75 19.4V24C11.75 24.5523 11.3023 25 10.75 25H6C5.44772 25 5 24.5523 5 24Z"
                  fill="#0060FF"
                  stroke="#0060FF"
                  stroke-width="1.2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                ></path>
              </svg>
            </router-link>
          </li>
          <li v-if="navBtn.boardBtnOff">
            <router-link to="/board/list">
              <svg
                width="36"
                height="36"
                viewBox="0 0 28 28"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M4.80417 19.4896L5.36126 19.7124L5.48401 19.4055L5.29022 19.1378L4.80417 19.4896ZM3.16015 23.5996L2.60307 23.3768H2.60307L3.16015 23.5996ZM3.40909 23.8636L3.59883 24.4328H3.59883L3.40909 23.8636ZM7.94681 22.3511L8.2556 21.8366L8.01894 21.6946L7.75707 21.7819L7.94681 22.3511ZM24.4 14C24.4 19.1389 19.7989 23.4 14 23.4V24.6C20.3514 24.6 25.6 19.9068 25.6 14H24.4ZM14 4.6C19.7989 4.6 24.4 8.86106 24.4 14H25.6C25.6 8.09325 20.3514 3.4 14 3.4V4.6ZM3.6 14C3.6 8.86106 8.20114 4.6 14 4.6V3.4C7.64859 3.4 2.4 8.09325 2.4 14H3.6ZM5.29022 19.1378C4.21894 17.6577 3.6 15.893 3.6 14H2.4C2.4 16.1616 3.10831 18.1699 4.31812 19.8414L5.29022 19.1378ZM3.71724 23.8225L5.36126 19.7124L4.24708 19.2667L2.60307 23.3768L3.71724 23.8225ZM3.21936 23.2944C3.54242 23.1867 3.84371 23.5063 3.71724 23.8225L2.60307 23.3768C2.35012 24.0092 2.95269 24.6482 3.59883 24.4328L3.21936 23.2944ZM7.75707 21.7819L3.21936 23.2944L3.59883 24.4328L8.13655 22.9203L7.75707 21.7819ZM14 23.4C11.8726 23.4 9.89882 22.823 8.2556 21.8366L7.63802 22.8655C9.46746 23.9636 11.6547 24.6 14 24.6V23.4Z"
                  fill="#16191D"
                ></path>
                <circle cx="18" cy="14" r="1" fill="#16191D"></circle>
                <circle cx="10" cy="14" r="1" fill="#16191D"></circle>
                <circle cx="14" cy="14" r="1" fill="#16191D"></circle>
              </svg>
            </router-link>
          </li>
          <li v-if="navBtn.boardBtnOn">
            <router-link to="/board/list">
              <svg
                width="36"
                height="36"
                viewBox="0 0 29 28"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                  clip-rule="evenodd"
                  d="M14.5 24C20.5751 24 25.5 19.5228 25.5 14C25.5 8.47715 20.5751 4 14.5 4C8.42487 4 3.5 8.47715 3.5 14C3.5 16.0273 4.16363 17.9138 5.30417 19.4896L3.66015 23.5996C3.59691 23.7577 3.74756 23.9175 3.90909 23.8636L8.44681 22.3511C10.1831 23.3933 12.2636 24 14.5 24Z"
                  fill="#0060FF"
                ></path>
                <path
                  d="M5.30417 19.4896L5.76841 19.6753L5.8707 19.4195L5.70921 19.1964L5.30417 19.4896ZM3.66015 23.5996L3.19591 23.4139L3.19591 23.4139L3.66015 23.5996ZM3.90909 23.8636L4.06721 24.338L4.06721 24.338L3.90909 23.8636ZM8.44681 22.3511L8.70414 21.9224L8.50692 21.804L8.2887 21.8767L8.44681 22.3511ZM25 14C25 19.2029 20.3449 23.5 14.5 23.5V24.5C20.8054 24.5 26 19.8428 26 14H25ZM14.5 4.5C20.3449 4.5 25 8.79707 25 14H26C26 8.15723 20.8054 3.5 14.5 3.5V4.5ZM4 14C4 8.79707 8.6551 4.5 14.5 4.5V3.5C8.19464 3.5 3 8.15723 3 14H4ZM5.70921 19.1964C4.62639 17.7004 4 15.9154 4 14H3C3 16.1393 3.70086 18.1272 4.89913 19.7827L5.70921 19.1964ZM4.12439 23.7853L5.76841 19.6753L4.83993 19.3039L3.19591 23.4139L4.12439 23.7853ZM3.75098 23.3893C3.99328 23.3085 4.21925 23.5482 4.12439 23.7853L3.19591 23.4139C2.97458 23.9673 3.50184 24.5264 4.06721 24.338L3.75098 23.3893ZM8.2887 21.8767L3.75098 23.3893L4.06721 24.338L8.60492 22.8254L8.2887 21.8767ZM14.5 23.5C12.3544 23.5 10.3629 22.918 8.70414 21.9224L8.18948 22.7798C10.0034 23.8686 12.1728 24.5 14.5 24.5V23.5Z"
                  fill="#0060FF"
                ></path>
                <circle cx="18.5" cy="14" r="1" fill="white"></circle>
                <circle cx="10.5" cy="14" r="1" fill="white"></circle>
                <circle cx="14.5" cy="14" r="1" fill="white"></circle>
              </svg>
            </router-link>
          </li>
          <li style="padding-top: 5px" v-if="navBtn.tourBtnOff">
            <router-link to="/tour">
              <svg
                width="30"
                height="30"
                viewBox="0 0 100 100"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                aria-hidden="true"
                role="img"
                class="iconify iconify--gis"
                preserveAspectRatio="xMidYMid meet"
                fill="#000000"
                stroke="#000000"
                stroke-width="0.0005"
              >
                <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                <g
                  id="SVGRepo_tracerCarrier"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                ></g>
                <g id="SVGRepo_iconCarrier">
                  <path
                    d="M86.49.088a2.386 2.386 0 0 0-.882.463L11.34 62.374a2.386 2.386 0 0 0 1.62 4.218l37.957-1.478l17.7 33.612a2.386 2.386 0 0 0 4.462-.707l16.406-95.23a2.386 2.386 0 0 0-2.994-2.7zm-2.808 8.277L69.567 90.29L54.439 61.558a2.386 2.386 0 0 0-2.203-1.272L19.79 61.551z"
                    fill="#000000"
                    fill-rule="evenodd"
                  ></path>
                </g>
              </svg>
            </router-link>
          </li>
          <li style="padding-top: 5px" v-if="navBtn.tourBtnOn">
            <router-link to="/tour">
              <svg
                width="30"
                height="30"
                viewBox="0 0 100 100"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                aria-hidden="true"
                role="img"
                class="iconify iconify--gis"
                preserveAspectRatio="xMidYMid meet"
                fill="#0060FF"
              >
                <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                <g
                  id="SVGRepo_tracerCarrier"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                ></g>
                <g id="SVGRepo_iconCarrier">
                  <path
                    d="M87.13 0a2.386 2.386 0 0 0-.64.088a2.386 2.386 0 0 0-.883.463L11.34 62.373a2.386 2.386 0 0 0 1.619 4.219l37.959-1.479l17.697 33.614a2.386 2.386 0 0 0 4.465-.707L89.486 2.79A2.386 2.386 0 0 0 87.131 0z"
                    fill="#0060FF"
                    fill-rule="evenodd"
                  ></path>
                </g>
              </svg>
            </router-link>
          </li>
        </ul>
      </div>
      <div class="header-right">
        <router-link v-if="!userInfo" to="/user/login">로그인</router-link>
        <div v-else>
          <a href="/user/mypage" id="mypage">마이페이지</a>
          <a @click.prevent="onClickLogout">로그아웃</a>
          <div style="font-size: 14px">
            {{ userInfo.nickname }} 님 안녕하세요.
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

export default {
  name: "TheHeader",
  computed: {
    ...mapState("userStore", ["isLogin", "userInfo"]),
    ...mapState("headerStore", ["navBtn"]),
    ...mapGetters("userStore", ["checkUserInfo"]),
  },
  data() {
    return {};
  },
  methods: {
    ...mapActions("userStore", ["userLogout"]),
    ...mapActions("headerStore", ["navToggle"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      // console.log(this.userInfo.id);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.id);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scope>
img {
  width: 80px;
}

ul,
li {
  list-style: none;
  margin: 0 0 0 0;
  padding: 0 0 0 0;
  border: 0;
}

li {
  width: 120px;
  float: left;
}

header {
  /* display: flex; */
  /* justify-content: space-between; */
  /* padding: 30px; */
  position: fixed;
  width: 100%;
  border-bottom: 1px solid var(--colorBg1);
  height: 50px;
  top: 25px;
  text-align: center;
  margin-top: -25px;
  padding-top: 10px;
  background-color: white;
  /* z-index: 2000; */
}

.wrap-header {
  max-width: 1200px;
  margin: 0 auto;
}

.header-left {
  float: left;
  /* padding-top: 8px; */
}

.header-center {
  /* position: absolute; */
  display: inline-block;
  /* padding-top: 10px; */
}

.header-center-fix {
  margin-left: 106.44px;
}

.header-right {
  float: right;
  font-size: 20px;
  padding-top: 10px;
  padding-right: 40px;
}

a {
  font-weight: bold;
  color: var(--baseForeground);
}

a:hover {
  color: var(--colorMain);
}
#mypage {
  padding-right: 10px;
}
.username {
  margin-right: 10px;
  font-weight: bold;
}
</style>
