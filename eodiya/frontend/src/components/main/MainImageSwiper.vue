<template>
  <div class="slider-wrap">
    <swiper
      class="swiper gallery-thumbs"
      :options="swiperOption"
      ref="swiperThumbs"
    >
      <swiper-slide class="slide-1"
        ><img src="@/assets/img01.jpg"
      /></swiper-slide>
      <swiper-slide class="slide-2"
        ><img src="@/assets/img02.jpg"
      /></swiper-slide>
      <div class="swiper-pagination" slot="pagination"></div>
    </swiper>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import "swiper/css/swiper.css";

export default {
  name: "MainImageSwiper",
  components: {
    Swiper,
    SwiperSlide,
  },
  props: {},
  data() {
    return {
      swiperOption: {
        slidesPerView: 1,
        spaceBetween: 30,
        loop: true,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      },
    };
  },
  computed: {},
  methods: {
    init() {
      this.startProgress = true;
    },
    stopAutoplay() {
      try {
        this.swiper.autoplay.stop();
        this.startProgress = false;
      } catch (err) {
        //console.error(err)
      }
    },
    startAutoplay() {
      try {
        this.swiper.autoplay.start();
        this.startProgress = true;
      } catch (err) {
        console.error(err);
      }
    },
  },
  watch: {},
  // mounted() {
  //     this.$nextTick(() => {
  //         const swiperTop = this.$refs.swiperTop.$swiper
  //         const swiperThumbs = this.$refs.swiperThumbs.$swiper
  //         swiperTop.controller.control = swiperThumbs
  //         swiperThumbs.controller.control = swiperTop
  //     })
  // }
};
</script>
<style lang="scss">
.slider-content {
  padding-bottom: 170px;
}

.slider-wrap {
  & + .slider-wrap {
    margin-top: 40px;
  }

  &.thumb-example {
    height: 480px;

    .swiper-slide {
      display: flex;
      justify-content: center;
      align-items: center;
      text-align: center;
      font-weight: bold;
      background-size: cover;
      background-position: center;
    }

    .gallery-top {
      height: 80%;
      width: 100%;
    }

    .gallery-thumbs {
      height: 20%;
      margin-top: 20px;
    }

    .gallery-thumbs .swiper-slide {
      width: 25%;
      height: 100%;
      opacity: 0.4;
    }

    .gallery-thumbs .swiper-slide-active {
      opacity: 1;
    }
  }
}

.swiper {
  width: 100%;
  height: 300px;
  border-radius: 8px;

  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    font-weight: bold;
    background: #ffffff;
    opacity: 0.7;

    img {
      width: 100%;
    }

    &.swiper-slide-active {
      opacity: 1;
    }
  }

  .swiper-pagination {
    bottom: 20px;

    &.num {
      display: inline-block;
      width: auto;
      left: 20px;
      padding: 7px 15px;
      background: #fff;
      border-radius: 20px;
      color: #666;

      .swiper-pagination-current {
        font-weight: 900;
        color: #ff7600;
      }

      .swiper-pagination-total {
        color: #666;
      }
    }
  }

  .swiper-progress {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    z-index: 1;
    background: #333;

    .bar {
      width: 100%;
      height: 4px;
      transform: translateX(-100%);
      background: #ff7600;
    }

    &.start {
      .bar {
        animation-duration: 5s;
        animation-iteration-count: infinite;
        animation-name: progress;
      }
    }
  }
}

@keyframes progress {
  from {
    transform: translateX(-100%);
  }

  to {
    transform: translateX(0);
  }
}
</style>
