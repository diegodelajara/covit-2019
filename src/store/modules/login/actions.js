import axios from 'axios'
import {
  GET_TOKEN_API,
  GET_USERS_API,
  LOGIN_API
} from 'src/constants/apis'

export default {
  async getCondominiums(context, user) {
    try {
      const url = GET_USERS_API
      const { data } = await axios.post(url, {
        username: user.email,
        password: user.password
      })
      return data.result
    } catch (error) {

      return error.response.data
    }
  },
  async getToken(context, params) {
    const url = params.condominium + GET_TOKEN_API,
      username = params.access.email,
      pass = params.access.pass

    try {
      const { data } = await axios.post(url, {
        username: username,
        password: pass
      })
      return data.status === 200 || data.status === 201
        ? data.token
        : false

    } catch (error) {

    }
  },
  async getUserInfo(context, params) {
    const url = params.condominium + LOGIN_API,
      username = params.access.email,
      pass = params.access.pass,
      token = params.authorization

    try {
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
      const {data} = await axios.post(url, {
        username: username,
        password: pass
      })
      return data.status === 200 || data.status === 201
        ? data
        : false

    } catch (error) {
      console.log('%c error', 'color:yellow;', error)

    }
  }
}
