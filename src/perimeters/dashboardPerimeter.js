import BasePerimeter from './BasePerimeter'

export default new BasePerimeter({
  purpose: 'dashboardPerimeter',
  govern: {
    'can route': function canRoute() {
      return this.isAdmin() || this.isUser() || this.isSuperAdmin()
    },
    // 'can viewParagraph': () => (this.isAdmin()),
    'can viewParagraph': function canViewParagraph() {
      return this.isAdmin()
    },
  },
})
