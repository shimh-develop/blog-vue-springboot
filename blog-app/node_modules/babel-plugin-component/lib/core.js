function _typeof(obj) { if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

var _require = require('@babel/helper-module-imports'),
    addSideEffect = _require.addSideEffect,
    addDefault = _require.addDefault;

var resolve = require('path').resolve;

var isExist = require('fs').existsSync;

var cache = {};
var cachePath = {};
var importAll = {};

module.exports = function core(defaultLibraryName) {
  return function (_ref) {
    var types = _ref.types;
    var specified;
    var libraryObjs;
    var selectedMethods;
    var moduleArr;

    function parseName(_str, camel2Dash) {
      if (!camel2Dash) {
        return _str;
      }

      var str = _str[0].toLowerCase() + _str.substr(1);

      return str.replace(/([A-Z])/g, function ($1) {
        return "-".concat($1.toLowerCase());
      });
    }

    function importMethod(methodName, file, opts) {
      if (!selectedMethods[methodName]) {
        var options;
        var path;

        if (Array.isArray(opts)) {
          options = opts.find(function (option) {
            return moduleArr[methodName] === option.libraryName || libraryObjs[methodName] === option.libraryName;
          }); // eslint-disable-line
        }

        options = options || opts;
        var _options = options,
            _options$libDir = _options.libDir,
            libDir = _options$libDir === void 0 ? 'lib' : _options$libDir,
            _options$libraryName = _options.libraryName,
            libraryName = _options$libraryName === void 0 ? defaultLibraryName : _options$libraryName,
            _options$style = _options.style,
            style = _options$style === void 0 ? true : _options$style,
            styleLibrary = _options.styleLibrary,
            _options$root = _options.root,
            root = _options$root === void 0 ? '' : _options$root,
            _options$camel2Dash = _options.camel2Dash,
            camel2Dash = _options$camel2Dash === void 0 ? true : _options$camel2Dash;
        var styleLibraryName = options.styleLibraryName;
        var _root = root;
        var isBaseStyle = true;
        var modulePathTpl;
        var styleRoot;
        var mixin = false;
        var ext = options.ext || '.css';

        if (root) {
          _root = "/".concat(root);
        }

        if (libraryObjs[methodName]) {
          path = "".concat(libraryName, "/").concat(libDir).concat(_root);

          if (!_root) {
            importAll[path] = true;
          }
        } else {
          path = "".concat(libraryName, "/").concat(libDir, "/").concat(parseName(methodName, camel2Dash));
        }

        var _path = path;
        selectedMethods[methodName] = addDefault(file.path, path, {
          nameHint: methodName
        });

        if (styleLibrary && _typeof(styleLibrary) === 'object') {
          styleLibraryName = styleLibrary.name;
          isBaseStyle = styleLibrary.base;
          modulePathTpl = styleLibrary.path;
          mixin = styleLibrary.mixin;
          styleRoot = styleLibrary.root;
        }

        if (styleLibraryName) {
          if (!cachePath[libraryName]) {
            var themeName = styleLibraryName.replace(/^~/, '');
            cachePath[libraryName] = styleLibraryName.indexOf('~') === 0 ? resolve(process.cwd(), themeName) : "".concat(libraryName, "/").concat(libDir, "/").concat(themeName);
          }

          if (libraryObjs[methodName]) {
            /* istanbul ingore next */
            if (cache[libraryName] === 2) {
              throw Error('[babel-plugin-component] If you are using both' + 'on-demand and importing all, make sure to invoke the' + ' importing all first.');
            }

            if (styleRoot) {
              path = "".concat(cachePath[libraryName]).concat(styleRoot).concat(ext);
            } else {
              path = "".concat(cachePath[libraryName]).concat(_root || '/index').concat(ext);
            }

            cache[libraryName] = 1;
          } else {
            if (cache[libraryName] !== 1) {
              /* if set styleLibrary.path(format: [module]/module.css) */
              var parsedMethodName = parseName(methodName, camel2Dash);

              if (modulePathTpl) {
                var modulePath = modulePathTpl.replace(/\[module]/ig, parsedMethodName);
                path = "".concat(cachePath[libraryName], "/").concat(modulePath);
              } else {
                path = "".concat(cachePath[libraryName], "/").concat(parsedMethodName).concat(ext);
              }

              if (mixin && !isExist(path)) {
                path = style === true ? "".concat(_path, "/style").concat(ext) : "".concat(_path, "/").concat(style);
              }

              if (isBaseStyle) {
                addSideEffect(file.path, "".concat(cachePath[libraryName], "/base").concat(ext));
              }

              cache[libraryName] = 2;
            }
          }

          addDefault(file.path, path, {
            nameHint: methodName
          });
        } else {
          if (style === true) {
            addSideEffect(file.path, "".concat(path, "/style").concat(ext));
          } else if (style) {
            addSideEffect(file.path, "".concat(path, "/").concat(style));
          }
        }
      }

      return selectedMethods[methodName];
    }

    function buildExpressionHandler(node, props, path, state) {
      var file = path && path.hub && path.hub.file || state && state.file;
      props.forEach(function (prop) {
        if (!types.isIdentifier(node[prop])) return;

        if (specified[node[prop].name]) {
          node[prop] = importMethod(node[prop].name, file, state.opts); // eslint-disable-line
        }
      });
    }

    function buildDeclaratorHandler(node, prop, path, state) {
      var file = path && path.hub && path.hub.file || state && state.file;
      if (!types.isIdentifier(node[prop])) return;

      if (specified[node[prop].name]) {
        node[prop] = importMethod(node[prop].name, file, state.opts); // eslint-disable-line
      }
    }

    return {
      visitor: {
        Program: function Program() {
          specified = Object.create(null);
          libraryObjs = Object.create(null);
          selectedMethods = Object.create(null);
          moduleArr = Object.create(null);
        },
        ImportDeclaration: function ImportDeclaration(path, _ref2) {
          var opts = _ref2.opts;
          var node = path.node;
          var value = node.source.value;
          var result = {};

          if (Array.isArray(opts)) {
            result = opts.find(function (option) {
              return option.libraryName === value;
            }) || {};
          }

          var libraryName = result.libraryName || opts.libraryName || defaultLibraryName;

          if (value === libraryName) {
            node.specifiers.forEach(function (spec) {
              if (types.isImportSpecifier(spec)) {
                specified[spec.local.name] = spec.imported.name;
                moduleArr[spec.imported.name] = value;
              } else {
                libraryObjs[spec.local.name] = value;
              }
            });

            if (!importAll[value]) {
              path.remove();
            }
          }
        },
        CallExpression: function CallExpression(path, state) {
          var node = path.node;
          var file = path && path.hub && path.hub.file || state && state.file;
          var name = node.callee.name;

          if (types.isIdentifier(node.callee)) {
            if (specified[name]) {
              node.callee = importMethod(specified[name], file, state.opts);
            }
          } else {
            node.arguments = node.arguments.map(function (arg) {
              var argName = arg.name;

              if (specified[argName]) {
                return importMethod(specified[argName], file, state.opts);
              } else if (libraryObjs[argName]) {
                return importMethod(argName, file, state.opts);
              }

              return arg;
            });
          }
        },
        MemberExpression: function MemberExpression(path, state) {
          var node = path.node;
          var file = path && path.hub && path.hub.file || state && state.file;

          if (libraryObjs[node.object.name] || specified[node.object.name]) {
            node.object = importMethod(node.object.name, file, state.opts);
          }
        },
        AssignmentExpression: function AssignmentExpression(path, _ref3) {
          var opts = _ref3.opts;

          if (!path.hub) {
            return;
          }

          var node = path.node;
          var file = path.hub.file;
          if (node.operator !== '=') return;

          if (libraryObjs[node.right.name] || specified[node.right.name]) {
            node.right = importMethod(node.right.name, file, opts);
          }
        },
        ArrayExpression: function ArrayExpression(path, _ref4) {
          var opts = _ref4.opts;

          if (!path.hub) {
            return;
          }

          var elements = path.node.elements;
          var file = path.hub.file;
          elements.forEach(function (item, key) {
            if (item && (libraryObjs[item.name] || specified[item.name])) {
              elements[key] = importMethod(item.name, file, opts);
            }
          });
        },
        Property: function Property(path, state) {
          var node = path.node;
          buildDeclaratorHandler(node, 'value', path, state);
        },
        VariableDeclarator: function VariableDeclarator(path, state) {
          var node = path.node;
          buildDeclaratorHandler(node, 'init', path, state);
        },
        LogicalExpression: function LogicalExpression(path, state) {
          var node = path.node;
          buildExpressionHandler(node, ['left', 'right'], path, state);
        },
        ConditionalExpression: function ConditionalExpression(path, state) {
          var node = path.node;
          buildExpressionHandler(node, ['test', 'consequent', 'alternate'], path, state);
        },
        IfStatement: function IfStatement(path, state) {
          var node = path.node;
          buildExpressionHandler(node, ['test'], path, state);
          buildExpressionHandler(node.test, ['left', 'right'], path, state);
        }
      }
    };
  };
};