'use strict';

var fs = require('fs');
var path = require('path');

var _ = require('lodash');
var gzipSize = require('gzip-size');

var Logger = require('./Logger');
var Folder = require('./tree/Folder').default;

var _require = require('./parseUtils'),
    parseBundle = _require.parseBundle;

var _require2 = require('./utils'),
    createAssetsFilter = _require2.createAssetsFilter;

var FILENAME_QUERY_REGEXP = /\?.*$/;

module.exports = {
  getViewerData,
  readStatsFromFile
};

function getViewerData(bundleStats, bundleDir, opts) {
  var _ref = opts || {},
      _ref$logger = _ref.logger,
      logger = _ref$logger === undefined ? new Logger() : _ref$logger,
      _ref$excludeAssets = _ref.excludeAssets,
      excludeAssets = _ref$excludeAssets === undefined ? null : _ref$excludeAssets;

  var isAssetIncluded = createAssetsFilter(excludeAssets);

  // Sometimes all the information is located in `children` array (e.g. problem in #10)
  if (_.isEmpty(bundleStats.assets) && !_.isEmpty(bundleStats.children)) {
    bundleStats = bundleStats.children[0];
  }

  // Picking only `*.js` assets from bundle that has non-empty `chunks` array
  bundleStats.assets = _.filter(bundleStats.assets, function (asset) {
    // Removing query part from filename (yes, somebody uses it for some reason and Webpack supports it)
    // See #22
    asset.name = asset.name.replace(FILENAME_QUERY_REGEXP, '');

    return _.endsWith(asset.name, '.js') && !_.isEmpty(asset.chunks) && isAssetIncluded(asset.name);
  });

  // Trying to parse bundle assets and get real module sizes if `bundleDir` is provided
  var bundlesSources = null;
  var parsedModules = null;

  if (bundleDir) {
    bundlesSources = {};
    parsedModules = {};

    var _iteratorNormalCompletion = true;
    var _didIteratorError = false;
    var _iteratorError = undefined;

    try {
      for (var _iterator = bundleStats.assets[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
        var statAsset = _step.value;

        var assetFile = path.join(bundleDir, statAsset.name);
        var bundleInfo = void 0;

        try {
          bundleInfo = parseBundle(assetFile);
        } catch (err) {
          var msg = err.code === 'ENOENT' ? 'no such file' : err.message;
          logger.warn(`Error parsing bundle asset "${assetFile}": ${msg}`);
          continue;
        }

        bundlesSources[statAsset.name] = bundleInfo.src;
        _.assign(parsedModules, bundleInfo.modules);
      }
    } catch (err) {
      _didIteratorError = true;
      _iteratorError = err;
    } finally {
      try {
        if (!_iteratorNormalCompletion && _iterator.return) {
          _iterator.return();
        }
      } finally {
        if (_didIteratorError) {
          throw _iteratorError;
        }
      }
    }

    if (_.isEmpty(bundlesSources)) {
      bundlesSources = null;
      parsedModules = null;
      logger.warn('\nNo bundles were parsed. Analyzer will show only original module sizes from stats file.\n');
    }
  }

  var modules = getBundleModules(bundleStats);
  var assets = _.transform(bundleStats.assets, function (result, statAsset) {
    var asset = result[statAsset.name] = _.pick(statAsset, 'size');

    if (bundlesSources && _.has(bundlesSources, statAsset.name)) {
      asset.parsedSize = bundlesSources[statAsset.name].length;
      asset.gzipSize = gzipSize.sync(bundlesSources[statAsset.name]);
    }

    // Picking modules from current bundle script
    asset.modules = _(modules).filter(function (statModule) {
      return assetHasModule(statAsset, statModule);
    }).each(function (statModule) {
      if (parsedModules) {
        statModule.parsedSrc = parsedModules[statModule.id];
      }
    });

    asset.tree = createModulesTree(asset.modules);
  }, {});

  return _.transform(assets, function (result, asset, filename) {
    result.push({
      label: filename,
      // Not using `asset.size` here provided by Webpack because it can be very confusing when `UglifyJsPlugin` is used.
      // In this case all module sizes from stats file will represent unminified module sizes, but `asset.size` will
      // be the size of minified bundle.
      // Using `asset.size` only if current asset doesn't contain any modules (resulting size equals 0)
      statSize: asset.tree.size || asset.size,
      parsedSize: asset.parsedSize,
      gzipSize: asset.gzipSize,
      groups: _.invokeMap(asset.tree.children, 'toChartData')
    });
  }, []);
}

function readStatsFromFile(filename) {
  return JSON.parse(fs.readFileSync(filename, 'utf8'));
}

function getBundleModules(bundleStats) {
  return _(bundleStats.chunks).map('modules').concat(bundleStats.modules).compact().flatten().uniqBy('id').value();
}

function assetHasModule(statAsset, statModule) {
  // Checking if this module is the part of asset chunks
  return _.some(statModule.chunks, function (moduleChunk) {
    return _.includes(statAsset.chunks, moduleChunk);
  });
}

function createModulesTree(modules) {
  var root = new Folder('.');

  _.each(modules, function (module) {
    return root.addModule(module);
  });

  return root;
}