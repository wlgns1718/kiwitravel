const boardStore = {
  namespaced: true,
  state: {
    previousScrollPosition: 0,
  },
  mutations: {
    setPreviousScrollPosition(state, position) {
      state.previousScrollPosition = position;
      // console.log(state.position);
    },
  },
};
export default boardStore;
