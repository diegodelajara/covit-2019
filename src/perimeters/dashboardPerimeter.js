import BasePerimeter from './BasePerimeter'

export default new BasePerimeter({
  purpose: 'dashboardPerimeter',
  govern: {
    'can route': function canRoute() {
      return this.isAdmin() || this.isUser() || this.isSuperAdmin()
    },
    // 'can route': () => (this.isSuperAdmin()),
    'can viewParagraph': function canViewParagraph() {
      return this.isAdmin()
    },
  },
})
