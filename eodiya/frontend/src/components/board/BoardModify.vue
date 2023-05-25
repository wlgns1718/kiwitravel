<template>
  <div class="container">
    <div class="fixed-section"><h3 class="app_title">글수정</h3></div>
    <div class="board-detail-wrap">
      <div class="board-form" :class="{ 'border-highlither': isTitleFocused }">
        <input
          type="text"
          v-model="board.title"
          placeholder="제목을 입력하세요."
          @focus="isTitleFocused = true"
          @blur="isTitleFocused = false"
        />
      </div>
      <div class="board-form textarea-form" :class="{ 'border-highlither': isTextareaFocused }">
        <textarea
          v-model="board.content"
          placeholder="본문을 입력하세요."
          @focus="isTextareaFocused = true"
          @blur="isTextareaFocused = false"
        ></textarea>
      </div>
      <div class="board-form scope-buttons">
        <div class="scope-button" :class="{ selected: board.scope === 0 }" @click="setScope(0)">
          전체공개
        </div>
        <div class="scope-button" :class="{ selected: board.scope === 1 }" @click="setScope(1)">
          팔로우공개
        </div>
        <div class="scope-button" :class="{ selected: board.scope === 2 }" @click="setScope(2)">
          비공개
        </div>
      </div>
      <div class="button-group">
        <button class="cancel-button" @click="goBack">취소</button>
        <button class="publish-button" @click="publish">수정완료</button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

export default {
  name: "BoardModify",
  components: {},
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      board: {},
      isTitleFocused: false,
      isTextareaFocused: false,
    };
  },
  created() {
    const boardInfo = {
      boardno: this.$route.params.no,
      userno: this.userInfo.userno,
    };

    http
      .post(`/board/detail`, JSON.stringify(boardInfo))
      .then(({ data }) => {
        this.board = data;
        console.log(this.board);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    setScope(scope) {
      this.board.scope = scope;
    },
    publish() {
      // console.log(this.board);
      http
        .put(`/board`, JSON.stringify(this.board))
        .then(({ data }) => {
          if (data == "success") {
            alert("수정완료 !");
            this.$router.push({
              name: "boarddetail",
              params: { no: this.board.boardno },
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
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

.board-form {
  display: flex;
  align-items: center;
  border: 1px solid #e5e7eb;
  border-radius: 10px;
  margin-bottom: 10px;
  padding: 10px;
}

.board-form input[type="text"],
.board-form textarea {
  flex: 1;
  padding: 8px;
  border: 1px solid transparent;
  border-radius: 4px;
  font-size: 16px;
}

.board-form input[type="text"]:focus,
.board-form textarea:focus {
  outline: none;
}
.textarea-form textarea {
  min-height: 200px;
  resize: none;
}

.border-highlither {
  border-color: rgb(0, 96, 255);
}

.scope-buttons {
  display: flex;
  justify-content: space-between;
  font-size: 16px;
}

.scope-button {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 40px;
  margin-right: 10px;
  border-radius: 50%;
  /* background-color: #e5e7eb; */
  cursor: pointer;
}

.scope-button.selected {
  color: rgb(0, 96, 255);
}

.button-group {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

.publish-button,
.cancel-button {
  /* flex: 1; */
  padding: 10px;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
}

.publish-button {
  background-color: #0060ff;
  color: #fff;
  width: 30%;
}

.cancel-button {
  background-color: #ffffff;
  margin-right: 10px;
  color: #000;
  width: 10%;
  font-size: 14px;
}
</style>
