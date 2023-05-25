<template>
  <div class="main-content">
    <div class="weather" v-if="this.weather !== null">
      {{ weather.name }}
      <img :src="weatherImgSrc" style="width: 40px" />
      {{ parseInt(weather.main.temp - 273.15) }} ℃
    </div>
    <main-image-swiper></main-image-swiper>
  </div>
</template>

<script>
import MainImageSwiper from "@/components/main/MainImageSwiper.vue";
import axios from "axios";
import { mapActions } from "vuex";

export default {
  name: "AppMain",
  components: {
    MainImageSwiper,
  },
  created() {
    this.navToggle(this.$route.path);
    this.loadWeather();
  },
  methods: {
    ...mapActions("headerStore", ["navToggle"]),
    loadWeather() {
      // 위치 정보 가져오기
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const latitude = position.coords.latitude;
          const longitude = position.coords.longitude;

          // 날씨 API 호출
          axios
            .get(
              `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=5efbc6e88a0abdec1b22df8318e29b34`
            )
            .then(({ data }) => {
              console.log(data);
              this.weather = data;
              this.weatherImgSrc = `http://openweathermap.org/img/w/${data.weather[0].icon}.png`;
              // this.weather = response.main.weather[0].description;
            })
            .catch((error) => {
              console.error(error);
            });
        },
        (error) => {
          console.error(error);
        }
      );
    },
  },
  data() {
    return {
      weather: null,
      weatherImgSrc: "",
    };
  },
};
</script>

<style scope>
.main-content {
  width: 1200px;
  margin: 0 auto;
  margin-top: 50px;
}

.weather {
  display: flex;
  align-items: center;
}
</style>
