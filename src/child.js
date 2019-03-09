export default store => (store ? store.state.user : null)

// import { getUserToLocalStorage } from 'src/utils/auth'

// export default (store) => {
// 	if (!store.state.user.role) {
// 		console.log(getUserToLocalStorage())
		
// 		return getUserToLocalStorage()
// 	} else {
// 		return store ? store.state.user : null
// 	}
// }
