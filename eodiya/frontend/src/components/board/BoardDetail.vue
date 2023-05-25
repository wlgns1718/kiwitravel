<template>
  <div class="container">
    <div class="fixed-section">
      <div class="back-arrow" @click="goBack">
        <svg
          height="60"
          viewBox="0 0 21 21"
          width="60"
          xmlns="http://www.w3.org/2000/svg"
        >
          <g
            fill="none"
            fill-rule="evenodd"
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            transform="translate(3 6)"
          >
            <path d="m4.499.497-3.999 4.002 4 4.001" />
            <path d="m13.5 4.5h-13" />
          </g>
        </svg>
      </div>
    </div>
    <div class="board-detail-wrap">
      <board-post-item :board="board" :boardno="boardno"></board-post-item>
      <!-- <router-link :to="`/board/modify/${board.boardno}`">수정</router-link>
      <a @click="deleteBoard">삭제</a> -->
      <board-reply-item
        @update-reply-count="updateReplyCount"
      ></board-reply-item>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import BoardPostItem from "./BoardPostItem.vue";
import BoardReplyItem from "./BoardReplyItem.vue";
import { mapState } from "vuex";

export default {
  name: "BoardDetail",
  components: { BoardPostItem, BoardReplyItem },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      board: {},
      images: [],
      boardno: 0,
    };
  },
  created() {
    const boardInfo = {
      boardno: this.$route.params.no,
      userno: this.userInfo.userno,
    };
    this.boardno = this.$route.params.no;
    console.log(boardInfo, this.boardno);
    http
      .post(`/board/detail`, JSON.stringify(boardInfo))
      .then(({ data }) => {
        this.board = data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    deleteBoard() {
      // console.log(this.board.boardno);
      http
        .delete(`/board/${this.board.boardno}`)
        .then(({ data }) => {
          alert(data);
          this.$router.replace("/board/list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    goBack() {
      this.$router.replace("/board/list");
    },
    updateReplyCount(count) {
      this.board.cntReply = count;
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  overflow: hidden;
}

.fixed-section {
  position: fixed;
  width: 240px;
  height: calc(100vh - 70px);
}

.board-detail-wrap {
  width: 680px;
  height: calc(100vh - 117px);
  margin: 0 240px;
  padding: 0 100px;
  padding-top: 40px;
  overflow: auto;
}

.board-detail-wrap::-webkit-scrollbar {
  width: 0.5rem;
}

.board-detail-wrap::-webkit-scrollbar-track {
  background-color: transparent;
}

.board-detail-wrap::-webkit-scrollbar-thumb {
  background-color: transparent;
}

.back-arrow {
  display: flex;
  justify-content: flex-end;
  padding-top: 25px;
  cursor: pointer;
}
</style>
