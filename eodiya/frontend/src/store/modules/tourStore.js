import http from "@/api/http";

const tourStore = {
  namespaced: true,
  state: {
    sidos: [{ value: "", text: "선택하세요" }],
    guguns: [{ value: "", text: "선택하세요" }],
    tourtype: "",
    tours: [],
    tour: null,
  },
  getters: {},
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.code, text: sido.name });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.code, text: gugun.name });
      });
    },
    SET_TOUR_TYPE(state, tourtype) {
      state.tourtype = tourtype;
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: "", text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: "", text: "선택하세요" }];
    },
    CLEAR_TOUR_LIST(state) {
      state.tours = [];
      state.tour = null;
    },
    SET_TOUR_LIST(state, tours) {
      state.tours = tours;
    },
    SET_DETAIL_TOUR(state, tour) {
      state.tour = tour;
    },
  },
  actions: {
    getSido({ commit }) {
      http
        .get(`/tour/sidoList`)
        .then(({ data }) => {
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      http
        .get(`/tour/sigunList/${sidoCode}`)
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getTourList({ commit }, params) {
      http
        .get(`/tour/tourList/${params[0]}/${params[1]}/${params[2]}`)
        .then(({ data }) => {
          data.forEach((d) => {
            d["expanded"] = false;
          });
          // console.log(data);
          commit("SET_TOUR_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default tourStore;
