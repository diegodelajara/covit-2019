// vue.config.js
module.exports = {
  lintOnSave: false,
  // devServer: {
  //   proxy: {
  //     '/api/v1': {
  //       target: 'https://general-api-covit.herokuapp.com',
  //       ws: true,
  //       changeOrigin: true,
  //       pathRewrite: {
  //         '^/api': ''
  //       }
  //     }
  //   }
  // }
  pwa: {
    // name: "Covit asadasda",
    themeColor: "#00b0f2",
    msTileColor: "#de8528",
    appleMobileWebAppCapable: "yes",
    appleMobileWebAppStatusBarStyle: "black",

    // configure the workbox plugin
    workboxPluginMode: "InjectManifest",
    workboxOptions: {
      // swSrc is required in InjectManifest mode.
      swSrc: "./sw.js"
      // ...other Workbox options...
    },
    manifestPath: './public/manifest.json'
  }
};
