'use strict';

var _require = require('util'),
    inspect = _require.inspect;

var _ = require('lodash');

exports.createAssetsFilter = createAssetsFilter;

function createAssetsFilter(excludePatterns) {
  var excludeFunctions = _(excludePatterns).castArray().compact().map(function (pattern) {
    if (typeof pattern === 'string') {
      pattern = new RegExp(pattern);
    }

    if (_.isRegExp(pattern)) {
      return function (asset) {
        return pattern.test(asset);
      };
    }

    if (!_.isFunction(pattern)) {
      throw new TypeError(`Pattern should be either string, RegExp or a function, but "${inspect(pattern, { depth: 0 })}" got.`);
    }

    return pattern;
  }).value();

  if (excludeFunctions.length) {
    return function (asset) {
      return _.every(excludeFunctions, function (fn) {
        return fn(asset) !== true;
      });
    };
  } else {
    return function () {
      return true;
    };
  }
}