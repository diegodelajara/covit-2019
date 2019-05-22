import BasePerimeter from './BasePerimeter'

export default new BasePerimeter({
  purpose: 'user',
  govern: {
    'can route': function canRoute() {
      return this.isSuperAdmin()
    },
    'can viewParagraph': function canViewParagraph() {
      return this.isSuperAdmin()
    },
  },
})
