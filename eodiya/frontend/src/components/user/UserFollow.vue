<template>
  <div class="profileWrap" v-if="index % 2 == 0">
    <div><img src="@/assets/yoon.jpg" alt="프로필 사진" class="profilePic" /></div>
    <div class="main">
      <router-link :to="{ name: 'Userpage', params: { userno: `${this.user.no}` } }" class="name">{{
        user.nickname
      }}</router-link>
      <div class="contentid">{{ user.id }}</div>
      <div class="contentemail">{{ user.email }}</div>
    </div>
  </div>
  <div class="profileWrap1" v-else>
    <div><img src="@/assets/yoon.jpg" alt="프로필 사진" class="profilePic" /></div>
    <div class="main">
      <router-link :to="{ name: 'Userpage', params: { userno: `${this.user.no}` } }" class="name">{{
        user.nickname
      }}</router-link>
      <div class="contentid">{{ user.id }}</div>
      <div class="contentemail">{{ user.email }}</div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
export default {
  props: ["follow", "index"],
  components: {},
  data() {
    return {
      user: {
        id: "",
        nickname: "",
        email: "",
        no: "",
      },
    };
  },
  methods: {},
  created() {
    // console.log(this.follow);
    http
      .get(`/user/otherinfo/${this.follow}`)
      .then(({ data }) => {
        // console.log(data);
        this.user.id = data.userInfo.id;
        this.user.nickname = data.userInfo.nickname;
        this.user.email = data.userInfo.email;
        this.user.no = data.userInfo.userno;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
.profileWrap {
  border: none;
  background-color: white;
  border-radius: 8px 8px 8px 8px;
  width: 240px;
  height: 90px;
  font-weight: bold;
  padding: 10px 10px 5px 5px;
  margin-bottom: 18px;
  display: flex;
}
.profilePic {
  border-radius: 50%;
}
.container {
  display: flex;
}
.name {
  font-size: 15pt;
  /* margin-left: 30px; */
  margin-top: 10px;
  cursor: pointer;
  z-index: 10;
}
.contentid {
  font-size: 9pt;
  /* margin-left: 30px; */
}
.contentemail {
  font-size: 9pt;
  /* margin-left: 30px; */
}
.profileWrap1 {
  border: none;
  background-color: white;
  border-radius: 8px 8px 8px 8px;
  width: 240px;
  height: 90px;
  font-weight: bold;
  padding: 10px 10px 5px 5px;
  margin-left: 1px;
  margin-bottom: 18px;
  display: flex;
}
.main {
  text-align: inherit;
  margin-left: 10px;
}
</style>
