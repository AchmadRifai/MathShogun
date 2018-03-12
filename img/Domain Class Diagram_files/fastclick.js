/**
*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
*EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
*MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
*NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS 
*BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
*ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
*CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
*SOFTWARE.
*@licence MIT License (http://www.opensource.org/licenses/mit-license.php)
*@copyright (c) 2011 Assanka Limited
*@author Rowan Beentje <rowan@assanka.net>, Matt Caruana Galizia <matt@assanka.net>
*/
var FastClick=(function(){var b="ontouchstart" in window;return function(n){if(!(n instanceof HTMLElement)){throw new TypeError("Layer must be instance of HTMLElement")}if(b){n.addEventListener("touchstart",l,true);n.addEventListener("touchmove",m,true);n.addEventListener("touchend",j,true);n.addEventListener("touchcancel",a,true)}n.addEventListener("click",k,true);if(n.onclick instanceof Function){n.addEventListener("click",n.onclick,false);n.onclick=""}var o={x:0,y:0,scroll:0},p=false;function l(c){p=true;o.x=c.targetTouches[0].clientX;o.y=c.targetTouches[0].clientY;o.scroll=window.pageYOffset;return true}function m(c){if(p){if(Math.abs(c.targetTouches[0].clientX-o.x)>10||Math.abs(c.targetTouches[0].clientY-o.y)>10){p=false}}return true}function j(c){var d,e;if(!p||Math.abs(window.pageYOffset-o.scroll)>5){return true}d=document.elementFromPoint(o.x,o.y);if(d.nodeType===Node.TEXT_NODE){d=d.parentNode}if(!(d.className.indexOf("clickevent")!==-1&&d.className.indexOf("touchandclickevent")===-1)){e=document.createEvent("MouseEvents");e.initMouseEvent("click",true,true,window,1,0,0,o.x,o.y,false,false,false,false,0,null);e.forwardedTouchEvent=true;d.dispatchEvent(e)}if(!(d instanceof HTMLSelectElement)&&d.className.indexOf("clickevent")===-1){c.preventDefault()}else{return false}}function a(c){p=false}function k(d){if(!window.event){return true}var c=true;var e;var f=window.event.forwardedTouchEvent;if(b){e=document.elementFromPoint(o.x,o.y);if(!e||(!f&&e.className.indexOf("clickevent")==-1)){c=false}}if(c){return true}d.stopPropagation();d.preventDefault();d.stopImmediatePropagation();return false}}})();