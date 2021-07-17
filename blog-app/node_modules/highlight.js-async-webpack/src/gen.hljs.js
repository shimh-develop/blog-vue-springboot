/**
 * @Author: HuaChao Chen <CHC>
 * @Date:   2017-06-17T16:29:50+08:00
 * @Email:  chenhuachaoxyz@gmail.com
 * @Filename: test.js
 * @Last modified by:   CHC
 * @Last modified time: 2017-06-19T17:08:00+08:00
 * @License: MIT
 * @Copyright: 2017
 */

var fs = require('fs');
var path = require('path');
// var file = 'highlight.js'
var hljs = require('highlight.js');
// console.log(hljs.listLanguages());
// console.log(hljs.aliases);
function validFilename(str) {
    var _res = '';
    for (var i = 0; i < str.length; i++) {
        if ((str[i] >= 'a' && str[i] <= 'z') || (str[i] >= 'A' && str[i] <= 'Z') || (str[i] >= '0' && str[i] <= '9')) {
            _res += str[i];
        }
        else {
            _res += '_';
        }
    }
    return _res;
}
// var tmp = require('./node_modules/highlight.js/lib/languages/cpp.js')
// console.log(tmp(hljs));
// console.log(hljs);
fs.readdir(path.resolve(__dirname, '../node_modules/highlight.js/lib/languages'), function (err, files) {
    var _files = [];
    var _str_code = "var hljs = require('highlight.js/lib/highlight.js');\n";
    for (var i = 0; i < files.length; i++) {
        var _lang = files[i].substring(0, files[i].length - 3);
        var __lang = validFilename(_lang);
        _str_code += 'hljs.hljs_' + __lang + ' = function (dom, callback) {\n';
        _str_code += '    require.ensure([], function() {\n';
        _str_code += "        hljs.registerLanguage('" + _lang + "', require('highlight.js/lib/languages/" + files[i] + "'));\n"
        _str_code += '        hljs.highlightBlock(dom);\n';
        _str_code += '        if (callback) callback();\n';
        _str_code += "    }, 'hljs." + __lang + "');\n"
        _str_code += '}\n\n';
        // _files['hljs.' + __lang] = _lang;
        _files.push('hljs.' + __lang)
    }
    _str_code += 'export default hljs';
    fs.writeFile(path.resolve(__dirname, './async.hljs.js'), _str_code, function() {

    })
    var _str_files = 'module.exports = [\n';
    for (var i = 0; i < _files.length; i++) {
        _str_files += "'" + _files[i] + "', ";
    }
    _str_files += ']';
    fs.writeFile(path.resolve(__dirname, './file.lang.hljs.js'), _str_files, function() {

    })
    var _ = {};
    for (var i = 0; i < files.length; i++) {
        var _module = require('highlight.js/lib/languages/' + files[i]);
        var _res = _module(hljs);
        var _lang = files[i].substring(0, files[i].length - 3);
        var __lang = validFilename(_lang);
        _[_lang] = __lang;
        if (_res.aliases) {
            for (var j = 0; j < _res.aliases.length; j++) {
                _[_res.aliases[j]] = __lang;
            }
        }
    }
    var _lang_obj = 'export default {\n';
    for (var i in _) {
        if ( _.hasOwnProperty(i)) {
            // _lang_obj += "    '" + i + "': '" + _[i] + "',\n";
            _lang_obj += "    '" + i + "': '" + _[i] + "',\n";
            // _lang_obj += "    '" + i + "': '" + _[i] + "',\n";
            // console.log(i);
        }
    }
    _lang_obj += '}'
    // console.log(_);
    // var _string = JSON.stringify(_);
    fs.writeFile(path.resolve(__dirname, './lang.hljs.js'), _lang_obj, function(e){
    })
    // console.log(files[0]);
    // var x = require('./node_modules/highlight.js/lib/languages/' + files[0]);
    // console.log(x(hljs));
    // console.log(files.length);
})
