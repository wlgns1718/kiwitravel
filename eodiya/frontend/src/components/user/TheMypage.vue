<template>
  <div class="view-content">
    <h3 class="app_title">마이 페이지</h3>
    <div class="wrap">
      <div class="MyPageWrap">
        <h3 class="profileHeader">프로필 정보</h3>
        <div class="profile">
          <div class="profile-image">
            <img src="@/assets/yoon.jpg" alt="프로필사진" class="profile" />
            <div class="FollowWrap">
              <span>게시글</span>
              <span>팔로워</span>
              <span>팔로잉</span>
            </div>
            <div class="FollowWrap1">
              <a @click="showPost">{{ posts.length }}개</a>
              <a @click="showFollower">{{ followerList.length }}명</a>
              <a @click="showFolloweeing">{{ followeeList.length }}명</a>
            </div>
          </div>
        </div>
        <div class="userinfoname" v-once>{{ user.nickname }}</div>
        <div class="userinfoemail">{{ user.email }}</div>
        <a class="modify" @click="openmodal">프로필 편집</a>
      </div>
      <div class="right-container" v-if="Post">
        <h3 class="boardTitle">게시글 목록</h3>
        <div v-if="posts.length == 0">작성한 게시글이 없습니다.</div>
        <div v-else>
          <div v-for="(post, index) in posts" :key="index">
            <div>
              <board-post-item :board="post"></board-post-item>
            </div>
          </div>
        </div>
      </div>
      <div class="right-container" v-else-if="Follower">
        <h3 class="boardTitle">팔로워 목록</h3>
        <div v-if="followerList.length == 0" class="nofollwer">
          팔로워가 없습니다.
          <div>여행 포스트를 추가해서 팔로워를 모아보세요!! 😊</div>
        </div>
        <div v-else>
          <div v-for="(follower, index) in followerList" :key="index">
            <div style="height: 100px">{{ follower }}</div>
          </div>
        </div>
        <!-- <ul>
          <li v-for="post in posts" :key="post.id">{{ post.title }}</li>
        </ul> -->
      </div>
      <div class="right-container" v-else-if="Followeeing">
        <h3 class="boardTitle">팔로잉 목록</h3>
        <div v-if="followeeList.length == 0" class="nofollwer">
          팔로잉이 없습니다.
          <div class="nofollwer">
            다른 사람을 팔로워해 그 사람의 게시글을 확인해보세요!! 🙂
          </div>
        </div>
        <div v-else style="width: 550px" class="followees">
          <div
            v-for="(followee, index) in followeeList"
            :key="index"
            class="followee"
          >
            <user-follow :follow="followee" :index="index"></user-follow>
          </div>
        </div>
        <!-- <ul>
          <li v-for="post in posts" :key="post.id">{{ post.title }}</li>
        </ul> -->
      </div>
    </div>
    <div id="modalbackground" @click="closemodal">
      <div>
        <div id="modal">
          <div class="modal-content" @click="preventClose">
            <h2>프로필 편집</h2>
            <!-- <div class="modaltitle">닉네임</div> -->
            <div>
              <label>아이디</label>
              <input
                class="modalinput"
                type="text"
                v-model="user.id"
                disabled
              />
            </div>
            <div>
              <label>닉네임</label>
              <input
                id="nick"
                class="modalinput"
                type="text"
                v-model="user.nickname"
                @blur="nickck"
              />
            </div>
            <div class="message">{{ messageNick }}</div>
            <div>
              <label>이메일</label>
              <input
                class="modalinput"
                type="text"
                v-model="user.email"
                disabled
              />
            </div>
            <div class="idnickname">
              <label>나 이</label>
              <input class="modalinput" type="text" v-model="user.age" />
            </div>
            <div class="idnickname">
              <label>성 별</label>
              <input
                class="modalinput"
                type="text"
                v-model="temp_gender"
                disabled
              />
            </div>
            <button class="registBtn" @click="modify">수정</button>
            <button id="close-modal" @click="closemodal">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState, mapActions } from "vuex";
import BoardPostItem from "@/components/board/BoardPostItem.vue";
import UserFollow from "@/components/user/UserFollow.vue";
export default {
  name: "MyPageView",
  components: {
    BoardPostItem,
    UserFollow,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      user: {
        age: 0,
        nickname: "",
        email: "",
        id: "",
        gender: 0,
        password: "",
        passwordck: "",
      },
      triger: true,
      temp_gender: "",
      Post: true,
      Follower: false,
      Followeeing: false,
      posts: [],
      followerList: [],
      followeeList: [],
      // 모달창 추가
      isModalOpen: false,
      messageNick: "",
    };
  },
  methods: {
    ...mapActions("userStore", ["modifyUserInfo"]),
    showPost() {
      this.Post = true;
      this.Follower = false;
      this.Followeeing = false;
      console.log(this.Post);
    },
    showFollower() {
      this.Post = false;
      this.Follower = true;
      this.Followeeing = false;
      console.log(this.Post);
    },
    showFolloweeing() {
      this.Post = false;
      this.Follower = false;
      this.Followeeing = true;
      console.log(this.Post);
    },
    getUserInfo() {
      const no = this.userInfo ? this.userInfo.userno : 0;
      //   console.log(this.user.no);
      http
        .get(`/board/mypage/${no}`)
        .then(({ data }) => {
          // console.log(data, "성공");
          this.posts = data;
        })
        .catch((error) => {
          console.log(error);
        });

      http
        .get(`/user/getfollowee/${no}`)
        .then(({ data }) => {
          this.followeeList = data.followeeList;
          //   console.log(this.followee, "안녕");
          //   console.log(data);
        })
        .catch((error) => {
          console.log(error);
        });

      http
        .get(`/user/getfollower/${no}`)
        .then(({ data }) => {
          //   console.log(this.user.no);
          //   console.log(data, "asdsjj");
          this.followerList = data.followerList;
          //   console.log(this.followerList, "asd");
          //   console.log(this.followerList.length, "asd");
        })
        .catch((error) => {
          console.log(error);
        });
      //   console.log("teset", this.followerList);
    },
    openmodal() {
      this.user.nickname = this.userInfo.nickname;
      this.user.age = this.userInfo.age;
      console.log(this.userInfo.nickname);
      const modal = document.getElementById("modal");
      modal.style.display = "block";
      document.body.style.overflow = "hidden";
      console.log(modal.style.display);
      this.isModalOpen = true;
      this.messageNick = "";
      this.triger = true;
    },
    closemodal() {
      const modal = document.getElementById("modal");
      modal.style.display = "none";
      document.body.style.overflow = "auto";
      this.isModalOpen = false;
    },
    preventClose(event) {
      event.stopPropagation();
    },
    nickck() {
      if (this.userInfo.nickname === this.user.nickname) {
        this.messageNick = "";
        return;
      }
      // let nick = document.getElementsByClassName("modalinput").value;
      // console.log(name, this.user.nickname);
      http
        .post("/user/register", JSON.stringify(this.user))
        .then(({ data }) => {
          if (data.isVaild == "false") {
            this.messageNick = "중복된 닉네임입니다.";
            this.triger = false;
          } else {
            this.messageNick = "";
            this.triger = true;
          }
        });
    },
    modify() {
      this.nickck();
      // console.log(this.triger);
      if (!this.triger) {
        alert("정보를 다시 확인하세요!!");
        return;
      }
      //수정해주기
      console.log(this.user);
      http
        .post(`/user/modifyinfo`, JSON.stringify(this.user))
        .then(({ data }) => {
          this.modifyUserInfo(data.userInfo);
          this.$router.go(0);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    this.user.age = this.userInfo.age;
    this.user.nickname = this.userInfo.nickname;
    this.user.email = this.userInfo.email;
    this.temp_gender = this.userInfo.gender === 0 ? "남성" : "여성";
    this.user.id = this.userInfo.id;
    this.getUserInfo();
  },
};
</script>

<style scoped>
.wrap {
  display: flex;
}

.right-container {
  background-color: rgb(233, 231, 231);
  width: 540px;
  height: 700px;
  overflow: auto;
  margin: 30px 0px 0px 100px;
  text-align: left;
  padding: 0px 10px 0px 10px;
  border-radius: 8px 8px 8px 8px;
  overflow-x: hidden;
}

.MyPageWrap {
  position: relative;
  border: 2px solid;
  width: 450px;
  margin-top: 30px;
  border-radius: 20px 20px 20px 20px;
  height: 280px;
}
.FollowWrap {
  border: 1px solid;
  width: 265px;
  margin-left: 10px;
  border-radius: 6px 6px 6px 6px;
  display: flex;
  justify-content: space-between;
  font-size: 15pt;
  font-weight: bold;
  border-color: rgb(255, 255, 255);
}
.FollowWrap1 {
  position: absolute;
  top: 40px;
  border: 1px solid;
  width: 258px;
  margin: 120px 0px 0px 160px;
  border-radius: 6px 6px 6px 6px;
  display: flex;
  justify-content: space-between;
  font-size: 15pt;
  font-weight: bold;
  border-color: rgb(255, 255, 255);
}
.profile {
  margin: 0;
  width: 150px;
  height: 150px;
  border: 1px solid rgb(255, 255, 255);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.profile-image {
  display: flex;
  align-items: center; /* 수직 가운데 정렬 */
  justify-content: space-between; /* 가로로 분산 정렬 */
}
.profileHeader {
  align-content: center;
}
.userinfoname {
  position: absolute;
  margin-top: 15px;
  margin-left: 20px;
  font-weight: bold;
}
.userinfoemail {
  position: absolute;
  margin-top: 35px;
  margin-left: 20px;
  font-weight: bold;
}
.modify {
  width: 270px;
  margin: 30px 0px 0px 155px;
  float: left;
  border-radius: 10px 10px 10px 10px;
}

.board-lists {
  background-color: red;
}
a {
  cursor: pointer;
}
.boardTitle {
  text-align: center;
}
.nofollwer {
  font-size: 15pt;
  font-weight: bold;
}

#modal {
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
  display: none;
}
.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px 0 30px 0;
  border: 1px solid #888;
  width: 20%;
  border-radius: 10px 10px 10px 10px;
}
.close {
  color: rgb(81, 185, 11);
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover,
.close:focus {
  color: rgb(37, 197, 5);
  text-decoration: none;
  cursor: pointer;
}
#close-modal {
  background-color: var(--colorMain);
  border: none;
  color: white;
  padding: 8px 8px;
  border-radius: 8px 8px 8px 8px;
  cursor: pointer;
  font-weight: bold;
  position: relative;
}
#close-modal:hover {
  background-color: rgb(0, 35, 192);
}

.modalinput {
  border-radius: 6px 6px 6px 6px;
  font-size: 16px;
  font-weight: normal;
  border: 1px solid;
  border-color: #e5e6e9;
  width: 200px;
  box-sizing: border-box;
  line-height: 23px;
  text-align: center;
  margin: 0px 0 10px 20px;
  height: 40px;
}
.modalinput:focus {
  border: 1px solid;
  border-color: rgb(105, 228, 128);
  outline: none;
}
.modaltitle {
  padding-right: 265px;
}
.gender {
  text-align: center;
}
.gendertitle {
  margin-right: 255px;
}
.registBtn {
  background-color: var(--colorMain);
  border: none;
  color: white;
  padding: 8px 8px;
  border-radius: 8px 8px 8px 8px;
  cursor: pointer;
  font-weight: bold;
  margin-left: 20px;
  margin-right: 5px;
}
.registBtn:hover {
  background-color: rgb(0, 35, 192);
}
.message {
  color: red;
  font-size: 10pt;
  padding-bottom: 8px;
  padding-right: 18px;
}
.idnickname {
  padding-left: 8px;
}
.followees {
  display: flex;
  flex-wrap: wrap;
}

.followee {
  width: 50%;
  /* width: 50%; */
  /* box-sizing: border-box; */
}
.index {
  margin-left: 100px;
}
</style>
