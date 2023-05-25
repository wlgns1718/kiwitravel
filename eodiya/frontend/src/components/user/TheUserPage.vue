<template>
  <div class="view-content">
    <h3 class="app_title">{{ user.nickname }}님의 정보</h3>
    <div>
      <div class="MyPageWrap">
        <h3 class="profileHeader">프로필 정보</h3>
        <div class="userInfoWrap">
          <div class="profile">
            <img src="@/assets/yoon.jpg" alt="프로필사진" class="profile" />
          </div>
          <div class="postinfo">
            <div class="User-Post-Info">
              <span>게시글</span>
              <span>팔로워</span>
              <span>팔로잉</span>
            </div>
            <div class="User-Post-Info">
              <a @click="showPost">{{ posts.length }}개</a>
              <a @click="showFollower">{{ followerList.length }}명</a>
              <a @click="showFolloweeing">{{ followeeList.length }}명</a>
            </div>
          </div>
        </div>
        <div>{{ user.nickname }}</div>
        <div>{{ user.email }}</div>
        <div class="FollowWrap" @click="addFollow" :class="{ followed: isFollowed }">
          <div v-if="!isFollowed">팔로우</div>
          <div v-else>언팔로우</div>
        </div>
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
          <div>{{ user.nickname }}님의 게시글을 보고싶다면 팔로우 해보세요!!</div>
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
        <div v-if="followeeList.length == 0" class="nofollwer">팔로잉이 없습니다.</div>
        <div v-else style="width: 550px" class="followees">
          <div v-for="(followee, index) in followeeList" :key="index" class="followee">
            <user-follow :follow="followee" :index="index"></user-follow>
          </div>
        </div>
        <!-- <ul>
          <li v-for="post in posts" :key="post.id">{{ post.title }}</li>
        </ul> -->
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import BoardPostItem from "@/components/board/BoardPostItem.vue";
import UserFollow from "@/components/user/UserFollow.vue";
export default {
  name: "MyPageView",
  components: { BoardPostItem, UserFollow },
  computed: { ...mapState("userStore", ["userInfo"]) },
  watch: {
    isFollowed(newValue) {
      console.log(newValue);
    },
  },
  data() {
    return {
      user: {
        no: 0,
        nickname: "",
        email: "",
        id: "",
      },
      posts: [],
      followerList: [],
      followeeList: [],
      Post: true,
      Follower: false,
      Followeeing: false,
      isFollowed: false,
    };
  },
  methods: {
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
    getUserInfo(no) {
      // console.log(no, "ㅇㅇㅇㅇ");
      http
        .get(`/user/otherinfo/${no}`)
        .then(({ data }) => {
          this.user.nickname = data.userInfo.nickname;
          this.user.email = data.userInfo.email;
          this.user.id = data.userInfo.id;
        })
        .catch((error) => {
          console.log(error);
        });

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
          // console.log(typeof this.followeeList);
          for (const value of Object.values(this.followerList)) {
            console.log(value);
            if (value === this.userInfo.userno) {
              this.isFollowed = true;
              break;
            }
          }
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
      // console.log("teset", this.followerList);
    },

    addFollow() {
      const userno = this.userInfo.userno;
      const followee = this.user.no;

      if (!this.isFollowed) {
        http
          .get(`/user/follow/${userno}/${followee}`)
          .then(({ data }) => {
            if (data == "success") {
              this.isFollowed = true;
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        http
          .delete(`/user/follow/${userno}/${followee}`)
          .then(({ data }) => {
            if (data == "success") this.isFollowed = false;
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
  created() {
    const no = this.$route.params.userno;
    // console.log(no);
    this.user.no = no;
    this.getUserInfo(this.user.no);
    // console.log(this.user.no);
  },
};
</script>

<style scoped>
.MyPageWrap {
  position: relative;
  border: 2px solid;
  width: 450px;
  margin-top: 30px;
  float: left;
  border-radius: 20px 20px 20px 20px;
}
.FollowWrap {
  border: 1px solid;
  width: 95%;
  margin-left: 10px;
  border-radius: 6px 6px 6px 6px;
  cursor: pointer;
}
.FollowWrap:hover {
  background-color: rgb(0, 35, 192);
  color: white;
}
.profile {
  margin: 0;
  width: 150px;
  height: 150px;
  border: 1px solid rgb(255, 255, 255);
  border-radius: 50%;
}
.profileHeader {
  align-content: center;
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
.postinfo {
  display: flex;
  text-align: center;
  justify-content: center;
  flex-direction: column;
  font-weight: bold;
  font-size: 15pt;
  width: 1000%;
}
.userInfoWrap {
  display: flex;
}
.User-Post-Info {
  display: flex;
  justify-content: space-evenly;
}

.followed {
  background-color: skyblue;
}
</style>
