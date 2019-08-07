// vue.config.js
module.exports = {
  lintOnSave: false,
  devServer: {
    proxy: {
      "/api/v1/*": {
        target: "https://general-api-covit.herokuapp.com"
      }
    }
  },
  // pwa: {
  //   name: "Covit 2019",
  //   themeColor: "#2b2e33",
  //   msTileColor: "#de8528",
  //   appleMobileWebAppCapable: "yes",
  //   appleMobileWebAppStatusBarStyle: "black",

  //   // configure the workbox plugin
  //   workboxPluginMode: "InjectManifest",
  //   workboxOptions: {
  //     // swSrc is required in InjectManifest mode.
  //     swSrc: "./sw.js"
  //     // ...other Workbox options...
  //   },
  //   iconPaths: {
  //     favicon32: "img/icons/favicon-32x32.png",
  //     favicon16: "img/icons/favicon-16x16.png",
  //     appleTouchIcon: "img/icons/apple-touch-icon-152x152.png",
  //     maskIcon: "img/icons/safari-pinned-tab.svg",
  //     msTileImage: "img/icons/msapplication-icon-144x144.png"
  //   }
  // }
};
