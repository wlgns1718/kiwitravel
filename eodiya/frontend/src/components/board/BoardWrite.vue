<template>
  <div class="container">
    <div class="fixed-section"><h3 class="app_title">글작성</h3></div>
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
      <div
        class="board-form textarea-form"
        :class="{ 'border-highlither': isTextareaFocused }"
      >
        <textarea
          v-model="board.content"
          placeholder="본문을 입력하세요."
          @focus="isTextareaFocused = true"
          @blur="isTextareaFocused = false"
        ></textarea>
      </div>
      <input-view :board="board" @setFile="setFile"></input-view>
      <div v-show="!isNotice" class="board-form scope-buttons">
        <div
          class="scope-button"
          :class="{ selected: board.scope === 0 }"
          @click="setScope(0)"
        >
          전체공개
        </div>
        <div
          class="scope-button"
          :class="{ selected: board.scope === 1 }"
          @click="setScope(1)"
        >
          팔로우공개
        </div>
        <div
          class="scope-button"
          :class="{ selected: board.scope === 2 }"
          @click="setScope(2)"
        >
          비공개
        </div>
      </div>
      <div class="button-group">
        <button class="cancel-button" @click="goBack">취소</button>
        <button class="publish-button" @click="publish">발행하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import InputView from "@/components/TheInput.vue";
export default {
  name: "BoardDetail",
  components: {
    InputView,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      board: {
        title: "",
        content: "",
        scope: 0,
        userno: 0,
      },
      files: "",
      isTitleFocused: false,
      isTextareaFocused: false,
      images: "",
      isNotice: false,
    };
  },
  created() {
    this.board.userno = this.userInfo.userno;

    if (this.$route.params.isNotice) {
      this.isNotice = true;
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    setScope(scope) {
      this.board.scope = scope;
    },
    publish() {
      var total_size = 0;
      for (let i = 0; i < this.files.length; i++) {
        if (this.files[i].size > 10 * 1024 * 1024) {
          alert("하나의 사진은 10MB를 넘을 수 없습니다!!");
          return;
        }
        total_size += this.files[i].size;
      }
      if (total_size > 30 * 1024 * 1024) {
        alert("전체 사진 크기는 30MB를 넘을 수 없습니다!!");
        return;
      }
      //확장자 제한 해주기
      for (let i = 0; i < this.files.length; i++) {
        let temp_ext = this.files[i].file.name;
        let ext = temp_ext.substring(temp_ext.lastIndexOf(".") + 1);
        if (!ext === "jpg" || !ext === "png" || !ext === "jpeg") {
          console.log(ext);
          alert(
            "jpeg, jpg, png 파일만 선택해 주세요.\n\n현재 파일 : " +
              ext.substring(ext.lastIndexOf(".") + 1)
          );
          return;
        }
      }

      // console.log(this.board);
      if (this.isNotice) {
        this.board.scope = 3;
      }
      const frm = new FormData();
      var images = this.files;
      // console.log("아래 확인");
      for (let i = 0; i < images.length; i++) {
        frm.append("files", this.files[i].file);
        // console.log(this.files[i]);
      }
      frm.append("title", this.board.title);
      frm.append("content", this.board.content);
      frm.append("scope", this.board.scope);
      frm.append("userno", this.board.userno);
      // frm.append("boardDto", JSON.stringify(this.board));
      // for (let val of frm.values()) {
      //   console.log(val);
      // }
      // console.log(frm.get("files"));
      // console.log(frm.get("files"));
      http
        .post(`/board/write`, frm, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(({ data }) => {
          if (data.message == "success") {
            alert("등록완료 !");
            if (this.isNotice) {
              this.$router.push({ name: "noticelist" });
            } else {
              this.$router.push({ name: "boardlist" });
            }
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setFile(files) {
      this.files = files;
      // console.log(this.files, "이게 실행되야 한다");
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
#files {
  width: 230px;
}
</style>
