<template>
  <div class="footerWrap">
    <div>CopyRight @ 2023. YunWooHyuck, ShinJiHoon All rights reserved</div>
    <div>Contact With (wlgns1718@naver.com / squareyun@naver.com)</div>
  </div>
</template>

<script>
export default {
  name: "TheFooterView",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.footerWrap {
  min-height: 50px;
  width: 100%;
  position: absolute;
  bottom: 0;
  background-color: white;
  color: black;
  font-weight: bold;
  font-size: 10pt;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  border-top: 1px solid var(--colorBg1);
}
</style>
