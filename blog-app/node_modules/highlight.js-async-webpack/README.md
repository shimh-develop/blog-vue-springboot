# highlight.js-async-webpack
async language request from highlight.js
## Install
```shell
npm install highlight.js-async-webpack
```
## Usage
```javascript
import hljs from 'highlight.js-async-webpack'
hljs.hljsBlock(dom, lang, function(){
// do something...
});

```
### Example
```javascript
function highLightCodex(str, callback) {
    var dom = document.createElement('div');
    dom.innerHTML = str;
    var pre_code = dom.querySelectorAll('pre.hljs > code');
    if (pre_code) {
        var cnt = 0;
        var deal = 0;
        for (var i = 0; i < pre_code.length; i++) {
            if (pre_code[i].className.length > 0) {
                cnt = cnt + 1;
                hljs.hljsBlock(pre_code[i], pre_code[i].className, function() {
                    deal = deal + 1;
                    if (deal == cnt) {
                        callback(dom.innerHTML);
                    }
                });
            }
        }
    }
}
```
## Version
+ **1.0.4** If highlight error return false.
+ **1.0.3** Add async files list(file.lang.hljs.js).
+ **1.0.2** Add callback function
