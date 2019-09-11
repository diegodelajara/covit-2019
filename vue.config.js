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
    name: "Covit asadasda", // meta apple-mobile-web-app-title
    themeColor: "#e38e69", // meta theme-color
    msTileColor: "#de8528", // meta msapplication-TileColor
    appleMobileWebAppCapable: "yes", // meta apple-mobile-web-app-capable
    appleMobileWebAppStatusBarStyle: "black", // meta apple-mobile-web-app-status-bar-style

    // configure the workbox plugin
    workboxPluginMode: "InjectManifest",
    workboxOptions: {
      // swSrc is required in InjectManifest mode.
      swSrc: "./sw.js"
      // ...other Workbox options...
    },
    manifestPath: 'manifest.json?id=' + Math.random() +'"'
  }
};
