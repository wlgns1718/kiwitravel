<template>
  <div style="comment-wrap">
    <div class="comment-form">
      <input type="text" v-model="replyMsg" placeholder="댓글을 입력하세요" />
      <button @click="sendReplyMsg">
        <div class="send-icon">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="feather feather-send"
          >
            <line x1="22" y1="2" x2="11" y2="13"></line>
            <polygon points="22 2 15 22 11 13 2 9 22 2"></polygon>
          </svg>
        </div>
      </button>
    </div>
    <div class="comment" v-if="replys.length != 0">
      <div class="comment-each" v-for="(reply, index) in replys" :key="index">
        <div class="header">
          <div>
            <span class="nickname" style="padding-right: 10px">{{ reply.nickname }}</span>
            <span
              v-if="reply.nickname == userInfo.nickname"
              class="date cursor"
              style="padding-right: 10px"
              @click="modifyReply(index)"
              >{{ editIndex === index ? "저장" : "수정" }}</span
            >
            <span
              v-if="reply.nickname == userInfo.nickname"
              class="date cursor"
              @click="deleteReply(reply, index)"
              >삭제</span
            >
          </div>
          <div class="date">
            <span>{{ formattedDate(reply.createdate) }}</span>
            <span class="cursor">신고</span>
          </div>
        </div>
        <div class="content">
          <div v-if="editIndex === index" class="comment-form">
            <p>
              <input type="text" v-model="reply.content" />
            </p>
          </div>
          <div v-else>
            <p>
              {{ reply.content }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

export default {
  name: "BoardReplyItem",
  components: {},
  props: ["boardno"],
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      replyMsg: "",
      replys: [],
      replyInfo: {},
      editIndex: -1, // 현재 편집 중인 댓글 인덱스 (-1은 편집 중이 아님을 의미)
    };
  },
  created() {
    this.replyInfo = {
      boardno: this.$route.params.no,
      userno: this.userInfo.userno,
    };
    this.getReplys();
  },
  methods: {
    getReplys() {
      http.get(`/reply/${this.replyInfo.boardno}`).then(({ data }) => {
        // console.log(data);
        this.replys = data;
        this.$emit("update-reply-count", this.replys.length);
      });
    },
    sendReplyMsg() {
      const writeInfo = { ...this.replyInfo, content: this.replyMsg };
      http.post(`/reply/write`, JSON.stringify(writeInfo)).then(({ data }) => {
        if (data === "success") {
          this.getReplys();
        }
      });
      this.replyMsg = "";
    },
    deleteReply(reply) {
      if (window.confirm("정말 삭제하시겠습니까?")) {
        http.delete(`/reply/${reply.replyno}`).then(({ data }) => {
          if (data === "success") this.getReplys();
        });
        this.editIndex = -1;
      }
    },
    modifyReply(index) {
      if (this.editIndex === index) {
        // 현재 편집 중인 댓글을 저장
        const modifiedReply = this.replys[index];
        // console.log(modifiedReply);
        http.put(`/reply/update`, JSON.stringify(modifiedReply)).then(({ data }) => {
          if (data === "success") {
            this.editIndex = -1; // 편집 모드 종료
          }
        });
      } else {
        this.editIndex = index; // 편집 모드로 진입
      }
    },
    formattedDate(createDate) {
      const now = new Date();
      const diffInMilliseconds = now - new Date(createDate);
      const diffInSeconds = Math.floor(diffInMilliseconds / 1000);
      const diffInMinutes = Math.floor(diffInSeconds / 60);
      const diffInHours = Math.floor(diffInMinutes / 60);
      const diffInDays = Math.floor(diffInHours / 24);
      const diffInMonths = Math.floor(diffInDays / 30);

      if (diffInMinutes == 0) {
        return `방금 전`;
      } else if (diffInMinutes < 60) {
        return `${diffInMinutes}분 전`;
      } else if (diffInHours < 24) {
        return `${diffInHours}시간 전`;
      } else if (diffInDays < 30) {
        return `${diffInDays}일 전`;
      } else if (diffInMonths < 12) {
        return `${diffInMonths}달 전`;
      } else {
        const date = new Date(this.createDate);
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${year}년 ${month}월 ${day}일`;
      }
    },
  },
};
</script>

<style scoped>
.comment {
  display: flex;
  flex-direction: column;
  border: 1px solid #e5e7eb;
  border-radius: 10px;
  padding: 20px;
}

.comment-form {
  display: flex;
  align-items: center;
  border: 1px solid #e5e7eb;
  border-radius: 10px;
  margin-bottom: 10px;
}

.comment-form input[type="text"] {
  flex: 1;
  padding: 8px;
  border: 1px solid transparent;
  border-radius: 4px;
}

.comment-form input[type="text"]:focus {
  outline: none;
}

.comment-form button {
  padding: 8px;
  background-color: transparent;
  border: none;
  cursor: pointer;
}

.comment-form .send-icon {
  margin-top: 2px;
}

.comment-form svg {
  width: 20px;
  height: 20px;
  stroke: #cccccc;
  fill: none;
}

.comment-each {
  margin-bottom: 30px;
}

.comment-each:last-child {
  margin-bottom: 0;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 5px;
}

.nickname {
  font-weight: bold;
}

.date {
  font-size: 12px;
  color: gray;
}

.date span {
  padding-left: 10px;
}

.content {
  flex: 1;
}

p {
  margin: 0;
}

.cursor {
  cursor: pointer;
}
</style>
