(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3c6ef49c"],{"456d":function(e,t,i){var s=i("4bf8"),a=i("0d58");i("5eda")("keys",(function(){return function(e){return a(s(e))}}))},"5eda":function(e,t,i){var s=i("5ca1"),a=i("8378"),r=i("79e5");e.exports=function(e,t){var i=(a.Object||{})[e]||Object[e],n={};n[e]=t(i),s(s.S+s.F*r((function(){i(1)})),"Object",n)}},8502:function(e,t,i){"use strict";i.r(t);var s=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[e._m(0),i("div",{staticClass:"msgManagement-content"},[i("div",{staticClass:"msgManagement-content-top",staticStyle:{height:"50px"}},[i("div",{staticClass:"clear",staticStyle:{float:"left"}},[i("el-row",{staticClass:"row-toolBox"},[i("el-button",{attrs:{type:"primary",plain:"",size:"medium",icon:"el-icon-refresh"},on:{click:function(t){return e.getList()}}},[e._v("刷新")]),i("el-button",{attrs:{type:"primary",plain:"",size:"medium",icon:"el-icon-circle-plus-outline"},on:{click:function(t){return e.judgeAdd()}}},[e._v("新增")]),i("el-button",{attrs:{type:"primary",plain:"",size:"medium",icon:"el-icon-edit"},on:{click:function(t){return e.judgeEdit()}}},[e._v("修改")]),i("el-button",{directives:[{name:"show",rawName:"v-show",value:1==e.admin,expression:"admin==1"}],attrs:{type:"primary",plain:"",size:"medium",icon:"el-icon-setting"},on:{click:function(t){return e.rePassword()}}},[e._v("重置密码")]),i("el-button",{attrs:{type:"danger",plain:"",size:"medium",icon:"el-icon-delete"},on:{click:function(t){return e.delList()}}},[e._v("删除")])],1)],1),i("div",{staticClass:"search-box-right",staticStyle:{float:"right"}},[i("el-input",{attrs:{placeholder:"请输入用户名",clearable:""},nativeOn:{keydown:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.getList(t)}},model:{value:e.input1,callback:function(t){e.input1=t},expression:"input1"}}),i("el-input",{attrs:{placeholder:"请输入所属区域",clearable:""},nativeOn:{keydown:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.getList(t)}},model:{value:e.input2,callback:function(t){e.input2=t},expression:"input2"}}),i("el-button",{attrs:{type:"primary",icon:"el-icon-search",circle:""},on:{click:function(t){return e.getList()}}})],1)]),i("el-dialog",{staticStyle:{width:"1000px",margin:"0 auto"},attrs:{title:"用户管理操作",visible:e.edituserVisible},on:{"update:visible":function(t){e.edituserVisible=t}}},[i("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules}},[i("el-form-item",{attrs:{label:"用户名称","label-width":e.formLabelWidth,prop:"userName"}},[i("el-input",{staticStyle:{width:"300px"},attrs:{autocomplete:"off",clearable:""},model:{value:e.form.userName,callback:function(t){e.$set(e.form,"userName",t)},expression:"form.userName"}})],1),i("el-form-item",{attrs:{label:"所属区域","label-width":e.formLabelWidth}}),i("el-form-item",{attrs:{label:"省","label-width":e.formLabelWidth,prop:"province"}},[i("el-select",{on:{change:function(t){return e.select2()}},model:{value:e.form.province,callback:function(t){e.$set(e.form,"province",t)},expression:"form.province"}},[i("el-option",{attrs:{label:"请选择",value:""}}),e._l(e.selectProvince,(function(e){return i("el-option",{key:e.areaId,attrs:{label:e.areaName,value:e.areaId}})}))],2)],1),i("el-form-item",{attrs:{label:"市","label-width":e.formLabelWidth,prop:"city"}},[i("el-select",{on:{change:function(t){return e.select3()}},model:{value:e.form.city,callback:function(t){e.$set(e.form,"city",t)},expression:"form.city"}},[i("el-option",{attrs:{label:"请选择",value:""}}),e._l(e.selectCity,(function(e){return i("el-option",{key:e.areaId,attrs:{label:e.areaName,value:e.areaId}})}))],2)],1),i("el-form-item",{attrs:{label:"区/县","label-width":e.formLabelWidth,prop:"county"}},[i("el-select",{model:{value:e.form.county,callback:function(t){e.$set(e.form,"county",t)},expression:"form.county"}},[i("el-option",{attrs:{label:"请选择",value:""}}),e._l(e.selectCounty,(function(e){return i("el-option",{key:e.areaId,attrs:{label:e.areaName,value:e.areaId}})}))],2)],1),i("el-form-item",{attrs:{label:"所属行业","label-width":e.formLabelWidth,prop:"industry"}},[i("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.industry,callback:function(t){e.$set(e.form,"industry",t)},expression:"form.industry"}},e._l(e.selectIndustry,(function(e){return i("el-option",{key:e.industId,attrs:{label:e.industName,value:e.industId}})})),1)],1),i("el-form-item",{attrs:{label:"联系电话","label-width":e.formLabelWidth,prop:"phone"}},[i("el-input",{staticStyle:{width:"300px"},attrs:{autocomplete:"off"},model:{value:e.form.phone,callback:function(t){e.$set(e.form,"phone",t)},expression:"form.phone"}})],1),i("el-form-item",{attrs:{label:"备注(车牌号)","label-width":e.formLabelWidth,prop:"remarks"}},[i("el-input",{staticStyle:{width:"300px"},attrs:{autocomplete:"off"},model:{value:e.form.remarks,callback:function(t){e.$set(e.form,"remarks",t)},expression:"form.remarks"}})],1)],1),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(t){return e.resetForm("form")}}},[e._v("重置")]),i("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.handleOk("form")}}},[e._v("确 定")])],1)],1),i("div",{staticClass:"gridBox-table",staticStyle:{width:"100%",margin:"15px auto"}},[i("el-table",{ref:"multipleTable",staticStyle:{width:"100%","font-size":"16px"},attrs:{data:e.tableData,stripe:""},on:{"selection-change":e.handleSelectionChange}},[i("el-table-column",{attrs:{type:"index",width:"50"}}),i("el-table-column",{attrs:{type:"selection",width:"50"}}),i("el-table-column",{attrs:{prop:"userName",label:"用户名",fit:""}}),i("el-table-column",{attrs:{prop:"userType",label:"用户角色",fit:""}}),i("el-table-column",{attrs:{prop:"areaName",label:"所属区域",fit:""}}),i("el-table-column",{attrs:{prop:"industryName",label:"所属行业",fit:""}}),i("el-table-column",{attrs:{prop:"userPhone",label:"电话",fit:""}}),i("el-table-column",{attrs:{prop:"userRemark",label:"备注",fit:""}})],1)],1),i("div",{staticClass:"block",staticStyle:{"margin-top":"5px","margin-bottom":"5px"}},[i("el-pagination",{staticStyle:{"text-align":"center"},attrs:{"current-page":e.currPage,"page-sizes":[5,8,10,20],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange,"update:currentPage":function(t){e.currPage=t},"update:current-page":function(t){e.currPage=t}}})],1)],1)])},a=[function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"nav",attrs:{id:"nav-bar"}},[i("span",{staticClass:"title"},[e._v("位置：")]),i("span",{staticClass:"name-parent"},[e._v("系统管理")]),i("i",{staticClass:"el-icon-arrow-right"}),i("span",{staticClass:"name-parent"},[e._v("用户管理")])])}],r=(i("456d"),i("ac6a"),i("c8a2")),n={name:"nav-bar",data:function(){return{admin:"",tableData:[],currPage:1,pageSize:10,total:0,input1:"",input2:"",selectList:[],selectId:"",edituserVisible:!1,editpasswordVisible:!1,form:{userName:"",province:"",city:"",remarks:"",county:"",industry:"",phone:""},formLabelWidth:"120px",selectProvince:[],selectCity:[],selectCounty:[],selectIndustry:[],multipleSelection:[],isAdd:!1,isEdit:!1,rules:{userName:[{pattern:/^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/,required:!0,message:"请填写正确用户名称",trigger:"blur"}],province:[{required:!0,message:"请选择省",trigger:"blur"}],phone:[{pattern:/^1[34578]\d{9}$/,required:!0,message:"请输入正确的手机号",trigger:"change,blur"}],industry:[{required:!0,message:"请选择所属行业",trigger:"change,blur"}]}}},created:function(){this.getList()},mounted:function(){this.admin=this.$store.state.userType},methods:{handleSizeChange:function(e){this.pageSize=e,this.getList()},handleCurrentChange:function(e){this.currPage=e,this.getList()},handleSelectionChange:function(e){var t=this;this.selectList=e,this.selectId="",this.selectList.filter((function(e){t.selectId+=e.userID+","})),this.selectId=this.selectId.slice(0,-1)},edit_user:function(){0==this.$refs.multipleTable.selection.length&&this.$message.success("请选择要修改的记录"),this.$refs.multipleTable.selection.length>1&&this.$message.success("只能选择修改一个用户"),1==this.$refs.multipleTable.selection.length&&(this.edituserVisible=!0)},edit_password:function(){0==this.$refs.multipleTable.selection.length&&this.$message.success("请选择要修改的记录"),this.$refs.multipleTable.selection.length>1&&this.$message.success("只能选择修改一个用户"),1==this.$refs.multipleTable.selection.length&&(this.editpasswordVisible=!0)},confirm_edituser:function(){},confirm_editpassword:function(){},getList:function(){var e=this,t={queryName:this.input1,queryAera:this.input2,rows:this.pageSize,page:this.currPage};Object(r["fb"])(t).then((function(t){e.tableData=t.data.rows,e.total=t.data.records,e.input1="",e.input2=""})).catch((function(e){return console.log(e)}))},addList:function(){var e=this,t={name:this.form.userName,province:this.form.province,city:this.form.city,county:this.form.county,industName:this.form.industry,phone:this.form.phone,remark:this.form.remarks};Object(r["cb"])(t).then((function(t){0==t.data.success?e.$message.error(t.data.message):(e.$message.success(t.data.message),e.getList())})).catch((function(e){return console.log(e)}))},editList:function(){var e=this,t={id:this.selectId,name:this.form.userName,province:this.form.province,city:this.form.city,county:this.form.county,industName:this.form.industry,phone:this.form.phone,remark:this.form.remarks};Object(r["eb"])(t).then((function(t){0==t.data.success?e.$message.error(t.data.message):(e.$message.success(t.data.message),e.getList())})).catch((function(e){return console.log(e)}))},delList:function(){var e=this,t={id:this.selectId};Object(r["db"])(t).then((function(t){0==t.data.success?e.$message.error(t.data.message):(e.$message(t.data.message),e.getList())}))},rePassword:function(){var e=this,t={userId:this.selectId};Object(r["gb"])(t).then((function(t){0==t.data.success?e.$message.error(t.data.message):(e.$message(t.data.message),e.getList())}))},judgeAdd:function(){this.isAdd=!0,this.isEdit=!1,this.edituserVisible=!0,this.resetForm("form"),this.select1(),this.select4()},judgeEdit:function(){this.isAdd=!1,this.isEdit=!0,1!=this.selectList.length?this.$message.error("请选择一项进行修改"):(this.form.userName=this.selectList[0].userName,this.form.province=this.selectList[0].provinceId,this.form.city=this.selectList[0].cityId,this.form.county=this.selectList[0].zoneId,this.form.phone=this.selectList[0].userPhone,this.form.industry=this.selectList[0].industryId,this.form.remarks=this.selectList[0].userRemark,this.edituserVisible=!0,this.select1(),this.select4(),this.getCitySelect(),this.getCountySelect())},select1:function(){var e=this,t={};Object(r["f"])(t).then((function(t){e.selectProvince=t.data.data}))},select2:function(){this.form.city="",this.getCitySelect()},getCitySelect:function(){var e=this,t={provinceId:this.form.province};Object(r["g"])(t).then((function(t){e.selectCity=t.data.data}))},select3:function(){this.form.county="",this.getCountySelect()},getCountySelect:function(){var e=this,t={cityId:this.form.city};Object(r["h"])(t).then((function(t){e.selectCounty=t.data.data}))},select4:function(){var e=this,t={};Object(r["i"])(t).then((function(t){0==t.data.success?e.$message.error(t.data.message):e.selectIndustry=t.data}))},handleOk:function(e){var t=this;this.$refs[e].validate((function(e,i){if(!e)return t.$message.error("请完善添加信息"),!1;t.edituserVisible=!1,t.isAdd?t.addList():t.isEdit&&t.editList()}))},resetForm:function(e){var t=this.form;Object.keys(t).forEach((function(e){t[e]=""}))}}},l=n,o=(i("f486"),i("2877")),c=Object(o["a"])(l,s,a,!1,null,"594d7d4e",null);t["default"]=c.exports},ac6a:function(e,t,i){for(var s=i("cadf"),a=i("0d58"),r=i("2aba"),n=i("7726"),l=i("32e9"),o=i("84f2"),c=i("2b4c"),u=c("iterator"),d=c("toStringTag"),m=o.Array,f={CSSRuleList:!0,CSSStyleDeclaration:!1,CSSValueList:!1,ClientRectList:!1,DOMRectList:!1,DOMStringList:!1,DOMTokenList:!0,DataTransferItemList:!1,FileList:!1,HTMLAllCollection:!1,HTMLCollection:!1,HTMLFormElement:!1,HTMLSelectElement:!1,MediaList:!0,MimeTypeArray:!1,NamedNodeMap:!1,NodeList:!0,PaintRequestList:!1,Plugin:!1,PluginArray:!1,SVGLengthList:!1,SVGNumberList:!1,SVGPathSegList:!1,SVGPointList:!1,SVGStringList:!1,SVGTransformList:!1,SourceBufferList:!1,StyleSheetList:!0,TextTrackCueList:!1,TextTrackList:!1,TouchList:!1},h=a(f),p=0;p<h.length;p++){var g,b=h[p],y=f[b],v=n[b],L=v&&v.prototype;if(L&&(L[u]||l(L,u,m),L[d]||l(L,d,b),o[b]=m,y))for(g in s)L[g]||r(L,g,s[g],!0)}},eba8:function(e,t,i){},f486:function(e,t,i){"use strict";var s=i("eba8"),a=i.n(s);a.a}}]);
//# sourceMappingURL=chunk-3c6ef49c.3f171023.js.map