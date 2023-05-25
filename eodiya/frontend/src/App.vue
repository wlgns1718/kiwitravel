<template>
  <div class="main">
    <div id="app">
      <the-header></the-header>
      <router-view />
      <the-footer></the-footer>
    </div>
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader";
// import AppMain from "@/views/AppMain";
// import AppBoard from "@/views/AppBoard";
import TheFooter from "./components/TheFooter.vue";
export default {
  components: {
    TheHeader,
    TheFooter,

    // AppMain,
    // AppBoard,
  },
};
</script>

<style>
@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.6/dist/web/static/pretendard.css");

.main {
  position: relative;
  height: 100%;
}
#app {
  padding-top: 60px;
  min-height: 100vh;
}
*,
#app,
body,
input,
select {
  font-family: pretendard, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

a {
  text-decoration: none;
}

.app_title {
  font-size: 36px;

  /* padding-left: 20px; */
}

.view-content {
  width: 1200px;
  margin: 0 auto;
  margin-top: 0px;
}

:root {
  --baseBackground: #fff;
  --baseForeground: #000;
  --colorMain: #0060ff;
  --colorBg1: #eee;
  --colorBg2: #eee;
  --colorBg3: #fff;
  --colorBg4: #111;
  --colorBg5: #eee;
  --colorBg6: #f4f4f4;
  --colorBg7: #f7f7f7;
  --colorBg8: #fcfcfc;
  --colorBg9: #333;
  --colorBg10: #fff;
  --colorBg11: #1a1a1a;
  --colorBg12: #444;
  --colorBg13: #dfdfdf;
  --colorBg14: #333;
  --colorBg15: #000;
  --colorBg16: #000;
  --colorBg17: #000;
  --colorBg18: #f7f7f7;
  --colorFg1: #333;
  --colorFg2: #111;
  --colorFg3: #000;
  --colorFg4: #000;
  --colorFg5: #666;
  --colorFg6: #666;
  --colorFg7: #333;
  --colorFg8: #ededed;
  --colorFg9: #333;
  --colorFg10: #333;
  --colorFg11: #666;
  --colorFg12: #666;
  --colorFg13: #888;
  --colorFg14: #888;
  --colorFg15: #bbb;
  --colorFg16: #e6e6e6;
  --colorFg17: #ebebeb;
  --colorFg18: #666;
  --colorFg19: #ededed;
  --colorFg20: #d8d8d8;
  --colorFg21: #fff;
  --colorFg22: #d8d8d8;
  --colorFg23: #888;
  --colorFg24: #666;
  --colorFg25: #666;
  --colorFg26: #333;
  --colorFg27: #666;
}
/* http://meyerweb.com/eric/tools/css/reset/ 
   v2.0 | 20110126
   License: none (public domain)
*/

html,
body {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}
/* HTML5 display-role reset for older browsers */
</style>
