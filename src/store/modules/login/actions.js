import axios from 'axios'
import { GET_USERS } from 'src/constants/apis'

export default {
  async getCondominiums(context, user) {
    try {
      // console.log('aca')
      const url = 'https://general-api-covit.herokuapp.com' + GET_USERS
      // const url = GET_USERS
      const { data } = await axios.post(url, {
        // withCredentials: true,
        // headers: {
        //   'Access-Control-Allow-Origin': '*',
        //   'Content-Type': 'application/json',
        // },
        username: user.email,
        password: user.password
      })
      return data.result
    } catch (error) {

      return error.response.data
    }
  }
}
