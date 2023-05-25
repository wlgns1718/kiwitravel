<template>
  <div>
    <!-- <h1 class="header">KIWI에 오신것을 환영합니다!!</h1> -->
    <router-view></router-view>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "UserView",
  components: {},
  data() {
    return {};
  },
  created() {
    this.navToggle(this.$route.path);
  },
  methods: {
    ...mapActions("headerStore", ["navToggle"]),
  },
};
</script>

<style scoped>
.header {
  margin-top: 100px;
  color: rgb(105, 228, 128);
}
div {
  text-align: center;
}
</style>
