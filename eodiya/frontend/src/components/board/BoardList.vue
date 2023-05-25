<template>
  <div>
    <div class="boardlist">
      <div class="container">
        <div class="fixed-section">
          <h3 class="app_title">커뮤니티</h3>
          <div v-if="this.userInfo">
            <label class="radio-button" :class="{ active: viewMode === 'all' }">
              <input
                type="radio"
                name="viewMode"
                value="all"
                v-model="viewMode"
                @change="changeViewMode"
              />
              <span>전체 보기</span>
            </label>
            <label class="radio-button" :class="{ active: viewMode === 'follow' }">
              <input
                type="radio"
                name="viewMode"
                value="follow"
                v-model="viewMode"
                @change="changeViewMode"
              />
              <span>팔로우만 보기</span>
            </label>
            <div class="write-article" @click="writeArticle">글작성</div>
          </div>
        </div>
        <div class="scrollable-section">
          <div v-for="(board, index) in boards" :key="index">
            <board-post-item :board="board"></board-post-item>
          </div>
          <div v-if="boards.length == 0" class="post">비었습니다..!</div>
        </div>
        <div class="fixed-section right-section">
          <div class="notice-title">
            <h3>공지사항</h3>
            <div @click="moveNotice">더보기</div>
          </div>
          <board-small-item :boards="noticeBoards" :isNotice="true"></board-small-item>
          <h3>추천 게시물</h3>
          <board-small-item :boards="bestBoards" :isNotice="false"></board-small-item>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import BoardPostItem from "./BoardPostItem.vue";
import BoardSmallItem from "./BoardSmallItem.vue";

export default {
  name: "BoardList",
  components: { BoardPostItem, BoardSmallItem },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      viewMode: "all",
      isFollowSelect: 0,
      boards: [],
      bestBoards: [],
      noticeBoards: [],
    };
  },
  created() {
    // 게시글 불러오기
    this.getBoard();
    // 추천 게시물 불러오기
    http
      .get(`/board/best`)
      .then(({ data }) => {
        this.bestBoards = data;
      })
      .catch((error) => {
        console.log(error);
      });
    // 공지사항 불러오기
    http
      .get(`/board/notice`)
      .then(({ data }) => {
        data.splice(1);
        this.noticeBoards = data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    getBoard() {
      this.boards = [];
      const boardInfo = {
        isFollowSelect: this.isFollowSelect,
        userno: this.userInfo ? this.userInfo.userno : 0,
      };
      http
        .post(`/board`, JSON.stringify(boardInfo))
        .then(({ data }) => {
          // console.log(data);
          this.boards = data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    changeViewMode() {
      this.isFollowSelect = this.isFollowSelect == 0 ? 1 : 0;
      this.getBoard();
    },

    writeArticle() {
      if (this.$route.path !== "/board/write") this.$router.push({ name: "boardwrite" });
    },
    moveNotice() {
      if (this.$route.path !== "/notice") this.$router.push({ name: "noticelist" });
    },
  },
  mounted() {
    // 이전 스크롤 위치를 가져옴
    const previousScrollPosition = this.$store.state.previousScrollPosition;

    // 이전 스크롤 위치가 존재하면 스크롤을 복원
    if (previousScrollPosition) {
      window.scrollTo(0, previousScrollPosition);
    }
  },
  beforeRouteLeave(to, from, next) {
    // 현재 스크롤 위치를 저장
    const previousScrollPosition = window.scrollY;
    // console.log(previousScrollPosition);
    // 다음 라우터로 이동하기 전에 스크롤 위치를 저장하기 위해 이전 스크롤 위치를 저장
    this.$store.commit("boardStore/setPreviousScrollPosition", previousScrollPosition);

    // 다음 라우터로 이동
    next();
  },
};
</script>

<style scoped>
.container {
  display: flex;
  height: calc(100vh - 108px);
}

.fixed-section {
  position: fixed;
  /* flex-shrink: 0; */
  width: 240px;
  height: calc(100vh - 70px);
}

.scrollable-section {
  width: 680px;
  height: 100%;
  margin: 0 240px;
  padding: 0 100px;
  padding-top: 40px;
  overflow: auto;
}

.scrollable-section::-webkit-scrollbar {
  width: 0.5rem;
}

.scrollable-section::-webkit-scrollbar-track {
  background-color: transparent;
}

.scrollable-section::-webkit-scrollbar-thumb {
  background-color: transparent;
}

.right-section {
  padding-top: 20px;
  margin-left: calc(240px + 680px);
}

.right-section h3 {
  margin-left: 5px;
}

.post {
  border-radius: 10px;
  border: 1px solid #e5e7eb;
  padding: 20px;
  margin-bottom: 60px;
}

.radio-button {
  display: inline-flex;
  align-items: center;
  border-radius: 9999px;
  padding: 10px 16px;
  background-color: #fff;
  color: #555;
  cursor: pointer;
  border: 1px solid #ccc;
  margin-right: 10px;
  /* transition: border-color 0.3s ease; */
  /* width: 100px;
  justify-content: center; */
}

.radio-button.active {
  border-color: #2979ff;
}

.radio-button input[type="radio"] {
  display: none;
}

.radio-button input[type="radio"]:checked + span {
  color: #2979ff;
}

.radio-button input[type="radio"]:checked + span:before {
  background-color: #2979ff;
}

.radio-button input[type="radio"]:focus + span {
  outline: none;
}

.radio-button input[type="radio"]:focus + span:before {
  box-shadow: 0 0 3px rgb(0, 96, 255);
}

.write-article {
  width: 190px;
  border-radius: 9999px;
  padding: 10px 16px;
  background-color: #fff;
  color: #555;
  cursor: pointer;
  border: 1px solid #ccc;
  margin-top: 20px;
  text-align: center;
}

.write-article:hover {
  border: 1px solid rgb(0, 96, 255);
  color: rgb(0, 96, 255);
}

.notice-title {
  display: flex;
  justify-content: space-between;
}

.notice-title > div {
  display: flex;
  font-size: 14px;
  align-items: center;
  cursor: pointer;
}
</style>
