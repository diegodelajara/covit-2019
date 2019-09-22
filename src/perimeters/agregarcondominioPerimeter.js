import BasePerimeter from './BasePerimeter'

export default new BasePerimeter({
  purpose: 'agregarcondominioPerimeter',
  govern: {
    'can route': function canRoute() {
      return this.isSuperAdmin()
    },
    'can viewParagraph': function canViewParagraph() {
      return this.isSuperAdmin()
    },
  },
})
