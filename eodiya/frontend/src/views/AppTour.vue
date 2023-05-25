<template>
  <div class="view-content">
    <h3 class="app_title">관광지 명소</h3>
    <div class="tour-wrap">
      <div>
        <tour-search-bar></tour-search-bar>
      </div>
      <div class="tour-map-result-wrap">
        <div class="left">
          <tour-map :location="location"></tour-map>
        </div>
        <div class="right">
          <tour-result @moveCenter="moveCenterHandler"></tour-result>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TourMap from "@/components/tour/TourMap.vue";
import TourSearchBar from "@/components/tour/TourSearchBar.vue";
import TourResult from "@/components/tour/TourResult.vue";
import { mapActions } from "vuex";

export default {
  name: "AppTour",
  components: { TourMap, TourSearchBar, TourResult },
  data() {
    return {
      message: "",
      location: {},
    };
  },
  created() {
    this.navToggle(this.$route.path);
  },
  methods: {
    moveCenterHandler(location) {
      this.location = location;
    },
    ...mapActions("headerStore", ["navToggle"]),
  },
};
</script>

<style scoped>
.tour-wrap {
  height: 100%;
}

.tour-map-result-wrap {
  display: flex;
  justify-content: space-between;
}
</style>
