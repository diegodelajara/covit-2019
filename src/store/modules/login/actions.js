export default {
  async getCondominiums(context, [email, list]) {
    const user = await list.filter(item => item.email === email)[0].condominiums
    return user
  }
}