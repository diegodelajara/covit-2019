import BasePerimeter from './BasePerimeter'

export default new BasePerimeter({
  purpose: 'dashboard',
  govern: {
    'can route': function canRoute() {
      return this.isAdmin()
    },
    // 'can viewParagraph': () => (this.isAdmin()),
    'can viewParagraph': function canViewParagraph() {
      return this.isAdmin()
    },
  },
})
