const headerStore = {
  namespaced: true,
  state: {
    navBtn: {
      homeBtnOn: false,
      homeBtnOff: true,
      boardBtnOn: false,
      boardBtnOff: true,
      tourBtnOn: false,
      tourBtnOff: true,
    },
  },
  mutations: {
    SET_TOGGLE(state, path) {
      // console.log(path);
      if (path === "/") {
        state.navBtn.homeBtnOn = true;
        state.navBtn.homeBtnOff = false;
        state.navBtn.boardBtnOn = false;
        state.navBtn.boardBtnOff = true;
        state.navBtn.tourBtnOn = false;
        state.navBtn.tourBtnOff = true;
      } else if (path.includes("/board")) {
        state.navBtn.homeBtnOn = false;
        state.navBtn.homeBtnOff = true;
        state.navBtn.boardBtnOn = true;
        state.navBtn.boardBtnOff = false;
        state.navBtn.tourBtnOn = false;
        state.navBtn.tourBtnOff = true;
      } else if (path === "/tour") {
        state.navBtn.homeBtnOn = false;
        state.navBtn.homeBtnOff = true;
        state.navBtn.boardBtnOn = false;
        state.navBtn.boardBtnOff = true;
        state.navBtn.tourBtnOn = true;
        state.navBtn.tourBtnOff = false;
      } else {
        state.navBtn.homeBtnOn = false;
        state.navBtn.homeBtnOff = true;
        state.navBtn.boardBtnOn = false;
        state.navBtn.boardBtnOff = true;
        state.navBtn.tourBtnOn = false;
        state.navBtn.tourBtnOff = true;
      }
    },
  },
  actions: {
    navToggle({ commit }, path) {
      // console.log(path);
      commit("SET_TOGGLE", path);
    },
  },
};
export default headerStore;
