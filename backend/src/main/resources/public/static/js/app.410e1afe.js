(function(e){function t(t){for(var r,l,u=t[0],a=t[1],i=t[2],f=0,b=[];f<u.length;f++)l=u[f],Object.prototype.hasOwnProperty.call(o,l)&&o[l]&&b.push(o[l][0]),o[l]=0;for(r in a)Object.prototype.hasOwnProperty.call(a,r)&&(e[r]=a[r]);s&&s(t);while(b.length)b.shift()();return c.push.apply(c,i||[]),n()}function n(){for(var e,t=0;t<c.length;t++){for(var n=c[t],r=!0,l=1;l<n.length;l++){var a=n[l];0!==o[a]&&(r=!1)}r&&(c.splice(t--,1),e=u(u.s=n[0]))}return e}var r={},o={app:0},c=[];function l(e){return u.p+"static/js/"+({about:"about"}[e]||e)+"."+{about:"b877f97f"}[e]+".js"}function u(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,u),n.l=!0,n.exports}u.e=function(e){var t=[],n=o[e];if(0!==n)if(n)t.push(n[2]);else{var r=new Promise((function(t,r){n=o[e]=[t,r]}));t.push(n[2]=r);var c,a=document.createElement("script");a.charset="utf-8",a.timeout=120,u.nc&&a.setAttribute("nonce",u.nc),a.src=l(e);var i=new Error;c=function(t){a.onerror=a.onload=null,clearTimeout(f);var n=o[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),c=t&&t.target&&t.target.src;i.message="Loading chunk "+e+" failed.\n("+r+": "+c+")",i.name="ChunkLoadError",i.type=r,i.request=c,n[1](i)}o[e]=void 0}};var f=setTimeout((function(){c({type:"timeout",target:a})}),12e4);a.onerror=a.onload=c,document.head.appendChild(a)}return Promise.all(t)},u.m=e,u.c=r,u.d=function(e,t,n){u.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(e,t){if(1&t&&(e=u(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)u.d(n,r,function(t){return e[t]}.bind(null,r));return n},u.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(t,"a",t),t},u.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},u.p="/",u.oe=function(e){throw console.error(e),e};var a=window["webpackJsonp"]=window["webpackJsonp"]||[],i=a.push.bind(a);a.push=t,a=a.slice();for(var f=0;f<a.length;f++)t(a[f]);var s=i;c.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},3785:function(e,t,n){"use strict";n("4570")},"3af4":function(e,t,n){},4570:function(e,t,n){},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var r=n("7a23"),o={id:"nav"},c=Object(r["e"])("Home"),l=Object(r["e"])(" | "),u=Object(r["e"])("About");function a(e,t){var n=Object(r["v"])("router-link"),a=Object(r["v"])("router-view");return Object(r["o"])(),Object(r["d"])(r["a"],null,[Object(r["f"])("div",o,[Object(r["f"])(n,{to:"/"},{default:Object(r["B"])((function(){return[c]})),_:1}),l,Object(r["f"])(n,{to:"/about"},{default:Object(r["B"])((function(){return[u]})),_:1})]),Object(r["f"])(a)],64)}n("3785");const i={};i.render=a;var f=i,s=(n("d3b7"),n("3ca3"),n("ddb0"),n("6c02")),b=n("cf05"),d=n.n(b),p={class:"home"},j=Object(r["f"])("img",{alt:"Vue logo",src:d.a},null,-1);function O(e,t,n,o,c,l){var u=Object(r["v"])("HelloWorld");return Object(r["o"])(),Object(r["d"])("div",p,[j,Object(r["f"])(u,{msg:"Welcome to Your Vue.js App"})])}var m=Object(r["D"])("data-v-218ad0bf");Object(r["r"])("data-v-218ad0bf");var v={class:"hello"},h=Object(r["f"])("br",null,null,-1),g=Object(r["f"])("br",null,null,-1),y=Object(r["f"])("br",null,null,-1),w=Object(r["f"])("br",null,null,-1),S=Object(r["f"])("br",null,null,-1),k=Object(r["f"])("br",null,null,-1),C={class:"container"},_={class:"large-12 medium-12 small-12 cell"},x=Object(r["e"])(" Upload a file "),I=Object(r["f"])("br",null,null,-1),A=Object(r["f"])("br",null,null,-1),P=Object(r["f"])("br",null,null,-1),R=Object(r["f"])("br",null,null,-1),U={class:"errase"},E=Object(r["f"])("br",null,null,-1),F=Object(r["f"])("br",null,null,-1),T=Object(r["f"])("img",{id:"currentImage",src:""},null,-1);Object(r["p"])();var H=m((function(e,t,n,o,c,l){return Object(r["o"])(),Object(r["d"])("div",v,[Object(r["f"])("h1",null,Object(r["x"])(n.msg),1),Object(r["f"])("button",{class:"Search__button",onClick:t[1]||(t[1]=function(e){return l.callRestService()})},"Call Spring Boot REST backend"),h,g,Object(r["C"])(Object(r["f"])("select",{"onUpdate:modelValue":t[2]||(t[2]=function(e){return c.selected=e})},[(Object(r["o"])(!0),Object(r["d"])(r["a"],null,Object(r["u"])(c.response,(function(e,t){return Object(r["o"])(),Object(r["d"])("option",{key:t,onClick:function(e){return l.updateImage(t)}},Object(r["x"])(e),9,["onClick"])})),128))],512),[[r["z"],c.selected]]),y,w,Object(r["f"])("span",null,"Selected: "+Object(r["x"])(c.selected),1),S,k,Object(r["f"])("div",C,[Object(r["f"])("div",_,[Object(r["f"])("label",null,[x,I,A,Object(r["f"])("input",{type:"file",id:"file",ref:"file",onChange:t[3]||(t[3]=function(e){return l.handleFileUpload()})},null,544)]),Object(r["f"])("button",{onClick:t[4]||(t[4]=function(e){return l.submitFile()})},"Submit")])]),P,R,Object(r["f"])("div",U,[Object(r["C"])(Object(r["f"])("select",{"onUpdate:modelValue":t[5]||(t[5]=function(t){return e.errased=t})},[(Object(r["o"])(!0),Object(r["d"])(r["a"],null,Object(r["u"])(c.response,(function(e,t){return Object(r["o"])(),Object(r["d"])("option",{key:t,onClick:function(e){return l.erraseImage(t)}},Object(r["x"])(e),9,["onClick"])})),128))],512),[[r["z"],e.errased]])]),E,F,T])})),B=n("bc3a"),L=n.n(B),D={name:"HelloWorld",props:{msg:String},data:function(){return{response:"[]",errors:[],selected:"",file:""}},mounted:function(){this.callRestService()},methods:{callRestService:function(){var e=this;L.a.get("images").then((function(t){e.response=t.data})).catch((function(t){e.errors.push(t)}))},updateImage:function(e){var t="/images/"+e,n=document.getElementById("currentImage");L.a.get(t,{responseType:"blob"}).then((function(e){var t=new window.FileReader;t.readAsDataURL(e.data),t.onload=function(){var e=t.result;n.setAttribute("src",e)}}))},submitFile:function(){var e=new FormData;e.append("file",this.file),L.a.post("/images/",e,{headers:{"Content-Type":"multipart/form-data"}}).then((function(){console.log("SUCCESS!!")})).catch((function(){console.log("FAILURE!!")}))},handleFileUpload:function(){this.file=this.$refs.file.files[0]},erraseImage:function(e){var t="/images/"+e,n=document.getElementById("currentImage");L.a.delete(t,{responseType:"blob"}).then((function(e){var t=new window.FileReader;t.readAsDataURL(e.data),t.onload=function(){var e=t.result;n.setAttribute("src",e)}}))}}};n("b00c");D.render=H,D.__scopeId="data-v-218ad0bf";var M=D,V={name:"Home",components:{HelloWorld:M}};V.render=O;var W=V,z=[{path:"/",name:"Home",component:W},{path:"/about",name:"About",component:function(){return n.e("about").then(n.bind(null,"f820"))}}],J=Object(s["a"])({history:Object(s["b"])("/"),routes:z}),q=J;Object(r["c"])(f).use(q).mount("#app")},b00c:function(e,t,n){"use strict";n("3af4")},cf05:function(e,t,n){e.exports=n.p+"static/img/logo.82b9c7a5.png"}});
//# sourceMappingURL=app.410e1afe.js.map