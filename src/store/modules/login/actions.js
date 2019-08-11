import axios from 'axios'
import { GET_USERS } from 'src/constants/apis'

export default {
  async getCondominiums(context, [email, pass]) {
    try {
      const url = GET_USERS
      const { data } = await axios.post(url, {
        username: email,
        password: pass
      })

      return data.result
    } catch (error) {
      return error.response.data
    }
  }
}
