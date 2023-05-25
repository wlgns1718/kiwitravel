<template>
  <div>
    <h1 class="header">KIWI</h1>
    KIWI는 자신만이 간직한 아름다운 여행지를<br />
    공유하는 커뮤니티 사이트입니다.
    <div class="Regist_Wrap">
      <div class="regist">
        <h2>회원가입</h2>
        <div>
          <div>아이디</div>
          <input
            v-model="user.id"
            type="text"
            maxlength="18"
            class="registInput"
            id="id"
            @blur="idck"
          />
          <div class="message">{{ messageId }}</div>
          <div>비밀번호</div>
          <input
            v-model="user.password"
            type="password"
            class="registInput"
            id="password"
            @blur="pw"
            @focus="emptypwck"
          />
          <div class="message">{{ messagePw }}</div>
          <div>비밀번호 확인</div>
          <input
            v-model="user.passwordck"
            type="password"
            class="registInput"
            id="passwordchk"
            @blur="pwck"
          />
          <div class="message">{{ messagePwck }}</div>
          <div>닉네임</div>
          <input
            v-model="user.nickname"
            type="text"
            class="registInput"
            id="nickname"
            @blur="nickck"
          />
          <div class="message">{{ messageNick }}</div>
          <div>나이</div>
          <input
            v-model="user.age"
            type="number"
            class="registInput"
            id="age"
          />
          <div>이메일</div>
          <input
            v-model="user.email"
            type="email"
            class="registInput"
            id="email"
          />
          <div>성별</div>
          <input
            type="radio"
            v-model="user.gender"
            id="gender"
            value="male"
            name="gender"
            @click="test('male')"
          />남자
          <input
            type="radio"
            v-model="user.gender"
            id="gender"
            value="female"
            name="gender"
            @click="test('female')"
          />여자

          <button class="registBtn" @click="comfirm">회원가입</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "UserRegist",
  components: {},
  data() {
    return {
      check: null,
      messageId: "",
      messagePwck: "",
      messagePw: "",
      messageNick: "",
      user: {
        id: "",
        password: "",
        passwordck: "",
        nickname: "",
        age: 0,
        email: "",
        gender: "",
      },
    };
  },
  created() {},
  methods: {
    idck() {
      if (this.user.id.length >= 4) {
        http.get(`/user/register/${this.user.id}`).then(({ data }) => {
          if (data.isVaild == "false") {
            this.messageId = "중복된 아이디입니다.";
            this.check = false;
          } else {
            this.messageId = "";
            this.check = true;
          }
        });
      } else {
        this.messageId = "4글자이상 입력해주세요.";
        this.check = false;
      }
    },
    pw() {
      if (this.user.password.length < 4) {
        this.messagePw = "비밀번호는 4자 이상 입력해주세요.";
        this.check = false;
      } else {
        this.messagePw = "";
      }
    },
    pwck() {
      if (this.user.password.length >= 4) {
        if (this.user.password == this.user.passwordck) {
          this.messagePwck = "";
          this.check = true;
        } else {
          this.messagePwck = "비밀번호가 다릅니다.";
          this.check = false;
        }
      }
    },
    emptypwck() {
      this.messagePwck = "";
    },
    nickck() {
      http
        .post("/user/register", JSON.stringify(this.user))
        .then(({ data }) => {
          if (data.isVaild == "false") {
            this.messageNick = "중복된 닉네임입니다.";
          } else {
            this.messageNick = "";
            this.check = true;
          }
        });
    },
    comfirm() {
      var reg_email = // eslint-disable-next-line
        /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
      console.log(this.check);
      if (this.user.id == null || this.user.id.length < 4) {
        alert("아이디 제대로 써!");
        return;
      }
      if (this.user.password == null || this.user.password.length < 4) {
        alert("비밀번호 제대로 써!");
        return;
      }
      if (this.user.passwordck == null || this.user.passwordck.length < 4) {
        alert("비밀번호 확인해!");
        return;
      }
      if (this.user.age == null || this.user.age <= 0) {
        alert("나이 제대로 입력해!");
        return;
      }
      if (this.user.nickname == null) {
        alert("닉네임 제대로 써!");
        return;
      }
      if (this.user.email == null || !reg_email.test(this.user.email)) {
        alert("이메일 형식 맞춰!");
        return;
      }
      if (this.user.gender == null || this.user.gender.length == 0) {
        alert("성별 입력해!!");
        return;
      }
      if (!this.check) {
        alert("제대로 써!7");
        return;
      } else {
        //회원가입 성공
        // alert("성공!!");
        let gen = this.user.gender === "male" ? 0 : 1;
        this.user.gender = gen;
        http
          .post(`user/regist`, JSON.stringify(this.user))
          .then(({ data }) => {
            console.log(data);
          })
          .catch((error) => {
            console.log(error);
          });
        alert("회원가입 성공!! 환영합니다 😊");
        this.$router.push({ name: "UserLogin" });
      }
    },
    test(gen) {
      this.user.gender = gen;
    },
  },
};
</script>

<style scoped>
h2 {
  padding: 0 0 40px 0;
}
input[id="LoginCheckbox"] {
  /* display: none; */
}

.Regist_Wrap {
  margin: auto;
  margin-top: 80px;
  width: 300px;
  border: 2px;
  border-style: solid;
  border-radius: 12px 12px 12px 12px;
  background-color: rgb(255, 255, 255);
}
.regist {
  text-align: left;
  padding: 20px;
  position: relative;
}
.registInput {
  border-radius: 6px 6px 6px 6px;
  font-size: 16px;
  font-weight: normal;
  border: 1px solid;
  border-color: #e5e6e9;
  width: 100%;
  box-sizing: border-box;
  line-height: 23px;
  padding: 10px 0 10px 5px;
  margin: 0px 0 10px 0;
}
.registInput:focus {
  border: 1px solid;
  border-color: rgb(105, 228, 128);
  outline: none;
}
h2 {
  margin: 0;
}
.registBtn {
  border: 0;
  margin: 30px 0 20px 0;
  width: 100%;
  border-radius: 6px 6px 6px 6px;
  font-weight: bold;
  padding: 10px 0 10px 0;
  font-size: 18px;
  color: rgb(255, 255, 255);
  background-color: rgb(105, 228, 128);
  cursor: pointer;
}
.message {
  color: red;
  font-size: 10pt;
  padding-bottom: 8px;
}
.header {
  margin-top: 70px;
  color: rgb(105, 228, 128);
}
</style>
