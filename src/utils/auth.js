export const setUserToLocalStorage = user => {
  return window.localStorage.setItem("user", JSON.stringify(user))
}
export const getUserFromLocalStorage = user => {
  return JSON.parse(window.localStorage.getItem("user"))
}
export const unsetUser = user => {
  window.localStorage.removeItem('user')
}