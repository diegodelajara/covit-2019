export default {
    mutations: {
        setUser(state, user) {
          state.user.email = user.authUser.email
          state.user.isLogged = user.authUser
        },
        setFormData(state) {
          state.formData
        }
      }
}