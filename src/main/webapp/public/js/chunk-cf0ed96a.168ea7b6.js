(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-cf0ed96a"],{"2f0d":function(t,e,a){},"456d":function(t,e,a){var i=a("4bf8"),r=a("0d58");a("5eda")("keys",(function(){return function(t){return r(i(t))}}))},"5eda":function(t,e,a){var i=a("5ca1"),r=a("8378"),s=a("79e5");t.exports=function(t,e){var a=(r.Object||{})[t]||Object[t],n={};n[t]=e(a),i(i.S+i.F*s((function(){a(1)})),"Object",n)}},"8f8a":function(t,e,a){"use strict";var i=a("2f0d"),r=a.n(i);r.a},ac6a:function(t,e,a){for(var i=a("cadf"),r=a("0d58"),s=a("2aba"),n=a("7726"),o=a("32e9"),l=a("84f2"),c=a("2b4c"),m=c("iterator"),u=c("toStringTag"),d=l.Array,f={CSSRuleList:!0,CSSStyleDeclaration:!1,CSSValueList:!1,ClientRectList:!1,DOMRectList:!1,DOMStringList:!1,DOMTokenList:!0,DataTransferItemList:!1,FileList:!1,HTMLAllCollection:!1,HTMLCollection:!1,HTMLFormElement:!1,HTMLSelectElement:!1,MediaList:!0,MimeTypeArray:!1,NamedNodeMap:!1,NodeList:!0,PaintRequestList:!1,Plugin:!1,PluginArray:!1,SVGLengthList:!1,SVGNumberList:!1,SVGPathSegList:!1,SVGPointList:!1,SVGStringList:!1,SVGTransformList:!1,SourceBufferList:!1,StyleSheetList:!0,TextTrackCueList:!1,TextTrackList:!1,TouchList:!1},h=r(f),p=0;p<h.length;p++){var g,b=h[p],v=f[b],y=n[b],L=y&&y.prototype;if(L&&(L[m]||o(L,m,d),L[u]||o(L,u,b),l[b]=d,v))for(g in i)L[g]||s(L,g,i[g],!0)}},d1ce:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[t._m(0),a("div",{staticClass:"msgManagement-content"},[a("div",{staticClass:"msgManagement-content-top",staticStyle:{height:"50px"}},[a("div",{staticClass:"clear",staticStyle:{float:"left"}},[a("el-row",{staticClass:"row-toolBox"},[a("el-button",{attrs:{type:"primary",plain:"",icon:"el-icon-refresh",size:"medium"},on:{click:function(e){return t.getList()}}},[t._v("刷新")]),a("el-button",{attrs:{type:"primary",plain:"",icon:"el-icon-circle-plus-outline",size:"medium"},on:{click:function(e){return t.judgeAdd()}}},[t._v("新增")]),a("el-button",{attrs:{type:"primary",plain:"",icon:"el-icon-edit",size:"medium"},on:{click:function(e){return t.judgeedit()}}},[t._v("修改")]),a("el-button",{attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"medium"},on:{click:function(e){return t.delList()}}},[t._v("删除")])],1)],1),a("div",{staticClass:"search-box-right",staticStyle:{float:"right"}},[a("el-input",{attrs:{placeholder:"请输入所属区域",clearable:""},nativeOn:{keydown:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.getList(e)}},model:{value:t.input,callback:function(e){t.input=e},expression:"input"}}),a("el-button",{attrs:{type:"primary",icon:"el-icon-search",circle:""},on:{click:function(e){return t.getList()}}})],1)]),a("div",{staticClass:"gridBox-table",staticStyle:{width:"100%",margin:"15px auto"}},[a("el-table",{ref:"multipleTable",staticStyle:{width:"100%","font-size":"16px"},attrs:{data:t.tableData,stripe:""},on:{"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"index",width:"50"}}),a("el-table-column",{attrs:{type:"selection",width:"50"}}),a("el-table-column",{attrs:{prop:"areaName",label:"区域名称",fit:""}}),a("el-table-column",{attrs:{prop:"parentName",label:"上级区域",fit:""}}),a("el-table-column",{attrs:{prop:"remark",label:"备注",fit:""}})],1)],1),a("div",{staticClass:"block",staticStyle:{"margin-top":"5px","margin-bottom":"5px"}},[a("div",{staticClass:"block",staticStyle:{"margin-top":"5px","margin-bottom":"5px"}},[a("el-pagination",{staticStyle:{"text-align":"center"},attrs:{"current-page":t.currPage,"page-sizes":[5,8,10,20],"page-size":t.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:t.total},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange,"update:currentPage":function(e){t.currPage=e},"update:current-page":function(e){t.currPage=e}}})],1)])]),a("el-dialog",{staticStyle:{width:"1000px",margin:"0 auto"},attrs:{title:"区域管理操作",visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"form",attrs:{model:t.form,rules:t.rules}},[a("el-form-item",{attrs:{label:"区域名称","label-width":t.formLabelWidth,prop:"areaName"}},[a("el-input",{staticStyle:{width:"300px"},attrs:{autocomplete:"off",clearable:""},model:{value:t.form.areaName,callback:function(e){t.$set(t.form,"areaName",e)},expression:"form.areaName"}})],1),a("el-form-item",{attrs:{label:"上级区域","label-width":t.formLabelWidth}}),a("el-form-item",{attrs:{label:"省","label-width":t.formLabelWidth,prop:"province"}},[a("el-select",{on:{change:function(e){return t.select2()}},model:{value:t.form.province,callback:function(e){t.$set(t.form,"province",e)},expression:"form.province"}},[a("el-option",{attrs:{label:"请选择",value:""}}),t._l(t.selectProvince,(function(t){return a("el-option",{key:t.areaId,attrs:{label:t.areaName,value:t.areaId}})}))],2)],1),a("el-form-item",{attrs:{label:"市","label-width":t.formLabelWidth,prop:"city"}},[a("el-select",{model:{value:t.form.city,callback:function(e){t.$set(t.form,"city",e)},expression:"form.city"}},[a("el-option",{attrs:{label:"请选择",value:""}}),t._l(t.selectCity,(function(t){return a("el-option",{key:t.areaId,attrs:{label:t.areaName,value:t.areaId}})}))],2)],1),a("el-form-item",{attrs:{label:"备注","label-width":t.formLabelWidth,prop:"remarks"}},[a("el-input",{staticStyle:{width:"300px"},attrs:{autocomplete:"off"},model:{value:t.form.remarks,callback:function(e){t.$set(t.form,"remarks",e)},expression:"form.remarks"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){return t.resetForm("form")}}},[t._v("重置")]),a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.handleOk("form")}}},[t._v("确 定")])],1)],1)],1)},r=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"nav",attrs:{id:"nav-bar"}},[a("span",{staticClass:"title"},[t._v("位置：")]),a("span",{staticClass:"name-parent"},[t._v("系统管理")]),a("i",{staticClass:"el-icon-arrow-right"}),a("span",{staticClass:"name-parent"},[t._v("区域管理")])])}],s=(a("456d"),a("ac6a"),a("c8a2")),n={name:"nav-bar",data:function(){return{currPage:1,pageSize:10,input:"",total:0,tableData:[{Name:"鄠邑县",Superior:"西安市",Remarks:"石油大学新校区"},{Name:"长安县",Superior:"西安市",Remarks:"电子科大新校区"}],selectList:[],selectId:"",dialogTableVisible:!1,dialogFormVisible:!1,form:{areaName:"",province:"",city:"",remarks:""},formLabelWidth:"120px",rules:{areaName:[{required:!1,message:"请填写所属区域",trigger:"blur"}],province:[{required:!1,message:"请选择省",trigger:"blur"}],remarks:[{}]},isEdit:!1,selectProvince:[],selectCity:[]}},created:function(){this.getList()},methods:{handleSizeChange:function(t){this.pageSize=t,this.getList()},handleCurrentChange:function(t){this.currPage=t,this.getList()},getList:function(){var t=this,e={queryArea:this.input,rows:this.pageSize,page:this.currPage};Object(s["o"])(e).then((function(e){t.tableData=e.data.rows,t.total=e.data.records,t.input=""})).catch((function(t){return console.log(t)}))},handleSelectionChange:function(t){var e=this;this.selectList=t,this.selectId="",this.selectList.filter((function(t){e.selectId+=t.areaId+","})),this.selectId=this.selectId.slice(0,-1)},delList:function(){var t=this,e={id:this.selectId};Object(s["q"])(e).then((function(e){0==e.data.success?t.$message.error(e.data.message):(t.$message.success(e.data.message),t.getList())}))},addList:function(){var t=this;if(""==this.form.province){var e={areaName:this.form.areaName,provinceId:"0",cityId:this.form.city,remark:this.form.remarks};Object(s["p"])(e).then((function(e){0==e.data.success?t.$message.error(e.data.message):(t.$message.success(e.data.message),t.getList())}))}else{var a={areaName:this.form.areaName,provinceId:this.form.province,cityId:this.form.city,remark:this.form.remarks};Object(s["p"])(a).then((function(e){0==e.data.success?t.$message.error(e.data.message):(t.$message.success(e.data.message),t.getList())}))}},editList:function(){var t=this,e={areaId:this.selectId,areaName:this.form.areaName,provinceId:this.form.province,cityId:this.form.city,remark:this.form.remarks};Object(s["r"])(e).then((function(e){0==e.data.success?t.$message.error(e.data.message):(t.$message.success(e.data.message),t.getList())}))},judgeAdd:function(){this.isEdit=!1,this.dialogFormVisible=!0,this.resetForm("form"),this.select1()},judgeedit:function(){this.isEdit=!0,1==this.isEdit&&(1!=this.selectList.length?this.$message.error("请选择一项进行修改"):(this.form.areaName=this.selectList[0].areaName,this.form.province=this.selectList[0].provinceId,this.form.city=this.selectList[0].cityId,this.form.remarks=this.selectList[0].remark,this.dialogFormVisible=!0,this.select1(),this.getCitySelect()))},select1:function(){var t=this,e={};Object(s["s"])(e).then((function(e){t.selectProvince=e.data.data}))},select2:function(){this.form.city="",this.getCitySelect()},getCitySelect:function(){var t=this,e={provinceId:this.form.province};Object(s["t"])(e).then((function(e){t.selectCity=e.data.data}))},handleOk:function(t){var e=this;this.$refs[t].validate((function(t,a){if(!t)return e.$message.error("请完善添加信息"),e.dialogFormVisible=!0,!1;e.dialogFormVisible=!1,e.isEdit?e.editList():e.addList(),e.isEdit=!1}))},resetForm:function(t){var e=this.form;Object.keys(e).forEach((function(t){e[t]=""}))}}},o=n,l=(a("8f8a"),a("2877")),c=Object(l["a"])(o,i,r,!1,null,"9cbb651a",null);e["default"]=c.exports}}]);
//# sourceMappingURL=chunk-cf0ed96a.168ea7b6.js.map