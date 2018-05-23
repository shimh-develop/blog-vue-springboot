const webpack = require('webpack')
const merge = require('webpack-merge')
const base = require('./webpack.base.config')
const SWPrecachePlugin = require('sw-precache-webpack-plugin')
const VueSSRClientPlugin = require('vue-server-renderer/client-plugin')
const utils = require('./utils')

const config = merge(base, {
  entry: {
    app: './src/entry-client.js'
  },
  resolve: {
    alias: {
      'create-api': './create-api-client.js'
    }
  },
  plugins: [
    // strip dev-only code in Vue source
    new webpack.DefinePlugin({
      'process.env.NODE_ENV': JSON.stringify(process.env.NODE_ENV || 'development'),
      'process.env.VUE_ENV': '"client"'
    }),
    // extract vendor chunks for better caching
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      minChunks: function (module) {
        // a module is extracted into the vendor chunk if...
        return (
          // it's inside node_modules
          /node_modules/.test(module.context) &&
          // and not a CSS file (due to extract-text-webpack-plugin limitation)
          !/\.css$/.test(module.request)
        )
      }
    }),
    // extract webpack runtime & manifest to avoid vendor chunk hash changing
    // on every build.
    new webpack.optimize.CommonsChunkPlugin({
      name: 'manifest'
    }),
    new VueSSRClientPlugin()
  ]
})

if (process.env.NODE_ENV === 'production') {
  // const categories = Category.map(category => category.title).join('|');
  // const categoryUrlPattern = new RegExp('^/(' + categories + ')');
  // config.plugins.push(
  //   // auto generate service worker
  //   new SWPrecachePlugin({
  //     cacheId: 'vue-hn',
  //     filename: 'service-worker.js',
  //     minify: false,
  //     dontCacheBustUrlsMatching: /./,
  //     staticFileGlobsIgnorePatterns: [/\.map$/, /\.json$/],
  //     runtimeCaching: [
  //       {
  //         urlPattern: '/',
  //         handler: 'networkFirst'
  //       },
  //       {
  //         urlPattern: categoryUrlPattern,
  //         handler: 'networkFirst'
  //       }
  //     ]
  //   })
  // )
}

module.exports = config
