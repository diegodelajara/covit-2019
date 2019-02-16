import BasePerimeter from './BasePerimeter'

export default new BasePerimeter({
  purpose: 'dashboard',
  govern: {
    'can route': function () {
      return this.isAdmin()
    },
  },
})