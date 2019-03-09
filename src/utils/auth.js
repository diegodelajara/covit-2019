export const setUserToLocalStorage = user => {
  return window.localStorage.setItem("user", JSON.stringify(user))
}
export const getUserToLocalStorage = user => {
  return window.localStorage.getItem("user")
}
