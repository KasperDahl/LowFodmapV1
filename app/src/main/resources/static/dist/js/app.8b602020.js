(()=>{"use strict";var e={261:(e,r,o)=>{var n=o(963),t=o(252);const i=(0,t._)("h1",null,"Low FODMAP Recipes",-1);function l(e,r,o,n,l,s){const c=(0,t.up)("router-view");return(0,t.wg)(),(0,t.iD)("div",null,[i,(0,t._)("button",{class:"btn btn-primary",onClick:r[0]||(r[0]=r=>e.$router.push("/allrecipes"))},"Show all recipes"),(0,t.Wm)(c)])}const s={mounted(){console.log("App component mounted")}};var c=o(744);const a=(0,c.Z)(s,[["render",l]]),p=a;var u=o(201),h=o(577);const f=(0,t._)("h2",null,"All Recipes",-1);function v(e,r,o,n,i,l){return(0,t.wg)(),(0,t.iD)("div",null,[f,(0,t._)("ul",null,[((0,t.wg)(!0),(0,t.iD)(t.HY,null,(0,t.Ko)(i.recipes,(e=>((0,t.wg)(),(0,t.iD)("li",{key:e.id},(0,h.zw)(e.name),1)))),128))])])}const d={data(){return{recipes:[]}},created(){this.fetchAllRecipes()},methods:{fetchAllRecipes(){fetch("/api/recipes").then((e=>{if(!e.ok)throw new Error("HTTP error "+e.status);return e.json()})).then((e=>{this.recipes=e})).catch((e=>{console.error("Error fetching recipes:",e)}))}}},g=(0,c.Z)(d,[["render",v]]),w=g,b=[{path:"/allrecipes",name:"AllRecipes",component:w}];console.log("Router created");const m=(0,u.p7)({history:(0,u.PO)(),routes:b}),O=m;console.log("Creating app");const y=(0,n.ri)(p);console.log("Initializing Vue.js app"),y.use(O),y.mount("#app"),console.log("Vue.js app initialized")}},r={};function o(n){var t=r[n];if(void 0!==t)return t.exports;var i=r[n]={exports:{}};return e[n](i,i.exports,o),i.exports}o.m=e,(()=>{var e=[];o.O=(r,n,t,i)=>{if(!n){var l=1/0;for(p=0;p<e.length;p++){for(var[n,t,i]=e[p],s=!0,c=0;c<n.length;c++)(!1&i||l>=i)&&Object.keys(o.O).every((e=>o.O[e](n[c])))?n.splice(c--,1):(s=!1,i<l&&(l=i));if(s){e.splice(p--,1);var a=t();void 0!==a&&(r=a)}}return r}i=i||0;for(var p=e.length;p>0&&e[p-1][2]>i;p--)e[p]=e[p-1];e[p]=[n,t,i]}})(),(()=>{o.d=(e,r)=>{for(var n in r)o.o(r,n)&&!o.o(e,n)&&Object.defineProperty(e,n,{enumerable:!0,get:r[n]})}})(),(()=>{o.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()})(),(()=>{o.o=(e,r)=>Object.prototype.hasOwnProperty.call(e,r)})(),(()=>{var e={143:0};o.O.j=r=>0===e[r];var r=(r,n)=>{var t,i,[l,s,c]=n,a=0;if(l.some((r=>0!==e[r]))){for(t in s)o.o(s,t)&&(o.m[t]=s[t]);if(c)var p=c(o)}for(r&&r(n);a<l.length;a++)i=l[a],o.o(e,i)&&e[i]&&e[i][0](),e[i]=0;return o.O(p)},n=self["webpackChunklowfodmapv1"]=self["webpackChunklowfodmapv1"]||[];n.forEach(r.bind(null,0)),n.push=r.bind(null,n.push.bind(n))})();var n=o.O(void 0,[998],(()=>o(261)));n=o.O(n)})();
//# sourceMappingURL=app.8b602020.js.map