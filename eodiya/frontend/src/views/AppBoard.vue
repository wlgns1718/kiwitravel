<template>
  <div class="view-content">
    <router-view></router-view>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "BoardView",
  components: {},
  data() {
    return {
      message: "",
    };
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
/* .app_title {
  position: fixed;
} */
</style>
