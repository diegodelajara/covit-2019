import BasePerimeter from './BasePerimeter'

export default new BasePerimeter({
  purpose: 'dashboardPerimeter',
  govern: {
    'can route': function canRoute() {
      return this.isAdmin() || this.isUser()
    },
    // 'can viewParagraph': () => (this.isAdmin()),
    'can viewParagraph': function canViewParagraph() {
      return this.isAdmin()
    },
  },
})
