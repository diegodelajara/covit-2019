import axios from 'axios'

export default {
  async getCondominiums(context, [email, pass]) {
    const url = '/api/v1/user/get'
    const { data } = await axios.post(url, {
      username: email,
      password: pass
    })
    if (data.status === 200 || data.status ===201) {
      // console.log('%c data', 'color:cyan;', data)
      // const user = await list.filter(item => item.email === email)[0].condominiums
      return data.result
    }
  }
}
