// vue.config.js
module.exports = {
  lintOnSave: false,
  devServer: {
    'proxy': {
      '/api/*': {
        target: 'https://swapi.co'
      }
    }
  }
}
