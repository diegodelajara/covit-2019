export default {
  getters: {
    getAuth(state) {
      return state.user.isLogged
    }
  }
}