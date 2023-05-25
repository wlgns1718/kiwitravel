<template>
  <div class="searchBarWrapper">
    <div class="selectWrapper">
      <div class="select-box">
        <select v-model="sidoCode" @change="gugunList">
          <option v-for="(item, index) in sidos" :key="index" :value="item.value">
            {{ item.text }}
          </option>
        </select>
      </div>
      <div class="select-box">
        <select v-model="gugunCode">
          <option v-for="(item, index) in guguns" :key="index" :value="item.value">
            {{ item.text }}
          </option>
        </select>
      </div>
      <div class="select-box">
        <select v-model="tourType">
          <option value="0" selected>관광지 유형</option>
          <option value="12">관광지</option>
          <option value="14">문화시설</option>
          <option value="15">축제공연행사</option>
          <option value="25">여행코스</option>
          <option value="28">레포츠</option>
          <option value="32">숙박</option>
          <option value="38">쇼핑</option>
          <option value="39">음식점</option>
        </select>
      </div>
    </div>
    <input class="searchBtn" type="button" value="검색" @click="searchTour" />
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "TourSearchBar",
  data() {
    return {
      sidoCode: "",
      gugunCode: "",
      tourType: "0",
    };
  },
  computed: {
    ...mapState("tourStore", ["sidos", "guguns", "tours"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_TOUR_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions("tourStore", ["getSido", "getGugun", "getTourList"]),
    ...mapMutations("tourStore", ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_TOUR_LIST"]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = "";
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchTour() {
      if (this.gugunCode) {
        this.getTourList([this.sidoCode, this.gugunCode, this.tourType]);
        // this.displayMarker();
      }
    },
  },
};
</script>

<style scoped>
.searchBarWrapper {
  display: flex;
  width: 1200px;
  margin-top: 20px;
  margin-bottom: 30px;
}

.selectWrapper {
  display: flex;
}

.select-box {
  position: relative;
  display: inline-block;
  width: 365px;
  margin-right: 10px;
  height: 40px;
  background-color: white;
  border: 1px solid #eeeeee;
  border-radius: 18px;
  overflow: hidden;
}

.select-box select {
  width: 100%;
  height: 100%;
  padding: 8px;
  border: none;
  outline: none;
  background-color: transparent;
  font-size: 14px;
  color: black;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  cursor: pointer;
}

.select-box::after {
  content: "\25BC";
  position: absolute;
  top: 50%;
  right: 12px;
  transform: translateY(-50%);
  font-size: 14px;
  color: #666;
}

.searchBtn {
  background: #eee;
  border: 1px solid #666;
  border-radius: 14px;
  padding: 0 20px;
}
</style>
