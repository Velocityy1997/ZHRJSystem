(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-41a5a053"],{"5b83":function(t,s,a){},c0dc:function(t,s,a){"use strict";var e=a("5b83"),n=a.n(e);n.a},dc3f:function(t,s,a){"use strict";a.r(s);var e=function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{attrs:{id:"login"}},[a("div",{staticClass:"main"},[a("div",{staticClass:"bg"}),a("div",{staticClass:"fly"}),a("div",{staticClass:"right-login"},[a("div",{staticClass:"logo"}),a("div",{staticClass:"title"}),t._m(0),a("div",{staticClass:"input-box"},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.v1,expression:"v1"}],staticClass:"user input",attrs:{type:"text",placeholder:"用户名"},domProps:{value:t.v1},on:{input:function(s){s.target.composing||(t.v1=s.target.value)}}}),a("input",{directives:[{name:"model",rawName:"v-model",value:t.v2,expression:"v2"}],staticClass:"password input",attrs:{type:"password",placeholder:"密码"},domProps:{value:t.v2},on:{keydown:function(s){return!s.type.indexOf("key")&&t._k(s.keyCode,"enter",13,s.key,"Enter")?null:(s.stopPropagation(),t.login(s))},input:function(s){s.target.composing||(t.v2=s.target.value)}}}),a("span",{staticClass:"icon icon-user"}),a("span",{staticClass:"icon icon-password"})]),a("button",{staticClass:"btn input",attrs:{type:"button"},on:{click:t.login}},[t._v("登录")]),a("div",{staticClass:"bottom-text"},[t._v("版权所有 2019 西安航光卫星测控技术有限公司")])])])])},n=[function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"text"},[t._v("\n        用户登录\n        "),a("span",{staticClass:"small-text"},[t._v("Login")])])}],i=a("8137"),o={name:"login",data:function(){return{v1:"",v2:""}},methods:{login:function(){var t=this,s=this;Object(i["a"])({url:"/user/checkUser",params:{userName:this.v1,password:this.v2},method:"post"}).then((function(a){a.data.success?t.$store.dispatch("getUserType").then((function(t){s.$router.push({path:"main"})})):t.$alert("错误的用户名或密码","提示",{confirmButtonText:"确定"})})).catch((function(t){return console.log(t)}))}}},c=o,r=(a("c0dc"),a("2877")),l=Object(r["a"])(c,e,n,!1,null,"53fa54a9",null);s["default"]=l.exports}}]);
//# sourceMappingURL=chunk-41a5a053.1deb2eca.js.map