import axios from 'axios'
// import { GET_USERS } from 'src/constants/apis'

export default {
  async getEntriesFromAPI(context) {
    try {
      // console.log('aca')
      const url = 'https://my.api.mockaroo.com/ingresos.json?key=66cff0f0'
      // const url = GET_USERS
      const response = await axios.get(url)

      if (response.status === 200 || response.status === 201) {
        context.commit('setEntries', response.data)
        return true
      }
    } catch (error) {
      return error.response.data
    }
  }
}

