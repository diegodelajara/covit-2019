export default {
    getAuth (state) {
      return state.user.isLogged
    },
    getUser (state) {
      return state.user
    }
  }