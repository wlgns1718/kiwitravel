<template>
  <div>
    <div class="boardlist">
      <div class="container">
        <div class="fixed-section">
          <h3 class="app_title">공지사항</h3>
          <div v-if="this.userInfo != null && this.userInfo.userno == 3">
            <div class="write-article" @click="writeArticle">글작성</div>
          </div>
        </div>
        <div class="scrollable-section">
          <div v-for="(board, index) in boards" :key="index">
            <div class="post">
              <div class="post-header">
                <div class="post-info"></div>
              </div>
              <div class="post-content">
                <h3>{{ board.title }}</h3>
                <p>{{ board.content }}</p>
              </div>
              <div class="post-footer">
                <div>
                  <div class="likes">
                    <svg
                      v-if="board.islike === 0"
                      @click="toggleLikes(board)"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      xmlns="http://www.w3.org/2000/svg"
                      class="pointer mr-6"
                    >
                      <path
                        d="M6.15623 15.5418L6.1561 15.5416L6.156 15.5415L6.15594 15.5415L6.15076 15.5361L6.14515 15.5306C2.9755 12.4253 2.6 9.82916 2.6 8.13045C2.6 7.36994 2.97794 6.22033 3.78099 5.26092C4.56853 4.32004 5.73192 3.6 7.29118 3.6C8.65775 3.6 9.70139 4.19196 10.4186 4.80541C10.7769 5.11188 11.0476 5.41888 11.2276 5.64793C11.3173 5.76209 11.3837 5.85586 11.4265 5.91907C11.4478 5.95064 11.4632 5.97449 11.4726 5.98933L11.4821 6.00463L11.4829 6.00579L11.4831 6.00613L11.4834 6.00662L11.4835 6.00689L11.9984 6.87343L12.5133 6.00689L12.5135 6.00662L12.5135 6.00659L12.5138 6.00613L12.514 6.00579L12.5147 6.00463L12.5243 5.98933C12.5337 5.97449 12.549 5.95064 12.5704 5.91907C12.6131 5.85587 12.6795 5.7621 12.7693 5.64794C12.9493 5.41889 13.2199 5.1119 13.5783 4.80543C14.2956 4.19199 15.3393 3.6 16.7062 3.6C18.2652 3.6 19.4293 4.32008 20.2177 5.26111C21.0215 6.22065 21.4 7.37022 21.4 8.13045C21.4 9.82884 21.0216 12.4272 17.8518 15.5305L17.8462 15.5361L17.8409 15.5415L17.8408 15.5415L17.8408 15.5416L17.8406 15.5418L17.8405 15.5419L17.8378 15.5447L17.8241 15.5586C17.8113 15.5714 17.7913 15.5915 17.7637 15.6186C17.7086 15.6729 17.6235 15.7554 17.5068 15.865C17.2733 16.0842 16.9135 16.4118 16.4146 16.8384C15.4699 17.6461 14.0265 18.8089 11.9984 20.263C9.97032 18.8089 8.52694 17.6461 7.58225 16.8384C7.08337 16.4118 6.72354 16.0842 6.4901 15.865C6.37338 15.7554 6.28825 15.6729 6.23314 15.6186C6.20558 15.5915 6.18552 15.5714 6.17277 15.5586L6.1591 15.5447L6.15632 15.5419L6.15623 15.5418Z"
                        stroke="#16191D"
                        stroke-width="1.2"
                      ></path>
                    </svg>
                    <svg
                      v-else
                      @click="toggleLikes(board)"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      xmlns="http://www.w3.org/2000/svg"
                      class="pointer mr-6"
                    >
                      <path
                        d="M6.15623 15.5418L6.1561 15.5416L6.156 15.5415L6.15594 15.5415L6.15076 15.5361L6.14515 15.5306C2.9755 12.4253 2.6 9.82916 2.6 8.13045C2.6 7.36994 2.97794 6.22033 3.78099 5.26092C4.56853 4.32004 5.73192 3.6 7.29118 3.6C8.65775 3.6 9.70139 4.19196 10.4186 4.80541C10.7769 5.11188 11.0476 5.41888 11.2276 5.64793C11.3173 5.76209 11.3837 5.85586 11.4265 5.91907C11.4478 5.95064 11.4632 5.97449 11.4726 5.98933L11.4821 6.00463L11.4829 6.00579L11.4831 6.00613L11.4834 6.00662L11.4835 6.00689L11.9984 6.87343L12.5133 6.00689L12.5135 6.00662L12.5135 6.00659L12.5138 6.00613L12.514 6.00579L12.5147 6.00463L12.5243 5.98933C12.5337 5.97449 12.549 5.95064 12.5704 5.91907C12.6131 5.85587 12.6795 5.7621 12.7693 5.64794C12.9493 5.41889 13.2199 5.1119 13.5783 4.80543C14.2956 4.19199 15.3393 3.6 16.7062 3.6C18.2652 3.6 19.4293 4.32008 20.2177 5.26111C21.0215 6.22065 21.4 7.37022 21.4 8.13045C21.4 9.82884 21.0216 12.4272 17.8518 15.5305L17.8462 15.5361L17.8409 15.5415L17.8408 15.5415L17.8408 15.5416L17.8406 15.5418L17.8405 15.5419L17.8378 15.5447L17.8241 15.5586C17.8113 15.5714 17.7913 15.5915 17.7637 15.6186C17.7086 15.6729 17.6235 15.7554 17.5068 15.865C17.2733 16.0842 16.9135 16.4118 16.4146 16.8384C15.4699 17.6461 14.0265 18.8089 11.9984 20.263C9.97032 18.8089 8.52694 17.6461 7.58225 16.8384C7.08337 16.4118 6.72354 16.0842 6.4901 15.865C6.37338 15.7554 6.28825 15.6729 6.23314 15.6186C6.20558 15.5915 6.18552 15.5714 6.17277 15.5586L6.1591 15.5447L6.15632 15.5419L6.15623 15.5418Z"
                        stroke="#d1190b"
                        stroke-width="1.2"
                        fill="#d1180b"
                      ></path>
                    </svg>
                    <span class="likes-count">
                      {{ board.cntLike }}
                    </span>
                  </div>
                </div>
                <div v-if="userInfo && board.nickname === userInfo.nickname" class="post-detail">
                  <div @click="moveBoardModify(board.boardno)">수정</div>
                  <div @click="deleteBoard(board.boardno)">삭제</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div v-if="boards.length == 0" class="post">비었습니다..!</div>
      </div>
      <div class="fixed-section right-section"></div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { onlyAuthUser } from "@/api/logincheck";
import { mapState } from "vuex";

export default {
  name: "BoardList",
  components: {},
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      boards: [],
    };
  },
  created() {
    console.log(this.userInfo);
    http
      .get(`/board/notice`)
      .then(({ data }) => {
        console.log(data);
        this.boards = data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    writeArticle() {
      if (this.$route.path !== "/board/write")
        this.$router.push({ name: "boardwrite", params: { isNotice: true } });
    },
    moveBoardModify(boardno) {
      if (this.$route.path !== `/board/modify/${boardno}`)
        this.$router.push({ name: "boardmodify", params: { no: boardno } });
    },
    deleteBoard(boardno) {
      if (window.confirm("정말 삭제하시겠습니까?")) {
        http
          .delete(`/board/${boardno}`)
          .then(({ data }) => {
            if (data === "success") {
              alert("삭제완료");
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    toggleLikes(board) {
      onlyAuthUser();
      if (this.userInfo === null) return;

      let likesInfo = {
        boardno: board.boardno,
        userno: this.userInfo.userno,
      };

      if (board.islike === 0) {
        http
          .post(`/board/addlikes`, JSON.stringify(likesInfo))
          .then(({ data }) => {
            if (data === "success") {
              board.islike = 1;
              board.cntLike += 1;
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        http
          .post(`/board/deletelikes/`, JSON.stringify(likesInfo))
          .then(({ data }) => {
            if (data === "success") {
              board.islike = 0;
              board.cntLike -= 1;
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
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

.post {
  border-radius: 10px;
  border: 1px solid #e5e7eb;
  padding: 20px;
  margin-bottom: 60px;
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

.post-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 25px;
  font-size: 14px;
}

.likes {
  display: flex;
  align-items: center;
  margin-right: 10px;
  width: 50px;
  justify-content: space-around;
}

.likes > svg {
  cursor: pointer;
}

.post-detail {
  display: flex;
}

.post-detail > div {
  color: #888888;
  font-size: 14px;
  margin-left: 10px;
  cursor: pointer;
}
</style>
