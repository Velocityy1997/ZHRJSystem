(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-8fa549d6"],{"073a":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"map",attrs:{id:"map-box"}},[a("div",{staticStyle:{background:"rgb(215, 225, 255)",height:"31px","line-height":"31px",width:"100%"},attrs:{align:"right"}},[a("a",{staticStyle:{"padding-right":"20px"},attrs:{id:"top_status"}},[a("img",{staticStyle:{"padding-right":"10px","margin-bottom":"6px","vertical-align":"middle"},attrs:{src:t.imgSrc,title:"系统提示"},on:{click:function(e){return t.initServiceState()}}}),a("span",{staticStyle:{"text-align":"left","font-weight":"bold"},attrs:{type:"text",title:"电量"}},[t._v(t._s(t.batteryStatus))]),a("span",{staticStyle:{"text-align":"left","font-weight":"bold","padding-left":"15px"},attrs:{title:"信号",type:"text"}},[t._v(t._s(t.baudsInfo))])])]),a("el-dialog",{attrs:{title:"系统状态",visible:t.dialogVisible,width:"30%"},on:{"update:visible":function(e){t.dialogVisible=e}}},[a("div",{staticClass:"itemStatus"},[t._v("IC状态：  "+t._s(t.ICStatus))]),a("div",{staticClass:"itemStatus"},[t._v("硬件状态：  "+t._s(t.hardwareStatus))]),a("div",{staticClass:"itemStatus"},[t._v("入站状态：  "+t._s(t.stationStatus))]),a("div",{staticClass:"itemStatus"},[t._v("通信服务：  "+t._s(t.commuStatus))]),a("div",{staticClass:"itemStatus"},[t._v("云服务：  "+t._s(t.cloudService))]),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogVisible=!1}}},[t._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogVisible=!1}}},[t._v("确 定")])],1)]),a("div",{staticClass:"map",attrs:{id:"map"}}),a("div",{attrs:{id:"tip_dlg"}}),a("div",{staticClass:"gridBox-msgtable",staticStyle:{width:"100%",height:"20%","margin-top":"0px"}},[a("el-tabs",{staticStyle:{"font-size":"16px"},attrs:{type:"card"},on:{"tab-click":t.handleClick},model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[a("el-tab-pane",{staticStyle:{padding:"5px 15px 5px"},attrs:{label:"最新信息",name:"first"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.newInfo}},[a("el-table-column",{attrs:{prop:"startTime",label:"时间",width:"200"}}),a("el-table-column",{attrs:{prop:"taskSender",label:"发送方",width:"100"}}),a("el-table-column",{attrs:{prop:"taskReceiver",label:"接受方",width:"100"}}),a("el-table-column",{attrs:{prop:"taskType",label:"类型",width:"100"}}),a("el-table-column",{attrs:{prop:"taskDescription",label:"状态",width:"250"}}),a("el-table-column",{attrs:{prop:"taskContent",label:"内容"}})],1),a("div",{staticClass:"block",staticStyle:{"margin-top":"5px","margin-bottom":"5px"}},[a("el-pagination",{staticStyle:{"text-align":"center"},attrs:{layout:"total, sizes, prev, pager, next, jumper","current-page":t.currPage,"page-size":t.pageSize,"page-sizes":[3],total:t.total},on:{"update:currentPage":function(e){t.currPage=e},"update:current-page":function(e){t.currPage=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1),a("el-tab-pane",{attrs:{label:"待执行任务",name:"second"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.ExecuteTask}},[a("el-table-column",{attrs:{prop:"startTime",label:"时间",width:"200"}}),a("el-table-column",{attrs:{prop:"sender",label:"发送方",width:"150"}}),a("el-table-column",{attrs:{prop:"receiver",label:"接受方",width:"150"}}),a("el-table-column",{attrs:{prop:"type",label:"类型",width:"150"}}),a("el-table-column",{attrs:{prop:"content",label:"内容"}})],1),a("div",{staticClass:"block",staticStyle:{"margin-top":"5px","margin-bottom":"5px"}},[a("el-pagination",{staticStyle:{"text-align":"center"},attrs:{layout:"total, sizes, prev, pager, next, jumper","current-page":t.ExecurrPage,"page-size":t.ExepageSize,"page-sizes":[3],total:t.total2},on:{"update:currentPage":function(e){t.ExecurrPage=e},"update:current-page":function(e){t.ExecurrPage=e},"size-change":t.handSizeChange,"current-change":t.handCurrentChange}})],1)],1)],1)],1)],1)},i=[],o=a("f5c8"),r=o["a"],s=(a("9246"),a("2877")),l=Object(s["a"])(r,n,i,!1,null,null,null);e["default"]=l.exports},"2c9d":function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABUAAAAZCAYAAADe1WXtAAAABHNCSVQICAgIfAhkiAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAKDSURBVEjHY/j//z8DIQwEekwsTPOBtCBR6gkpYGJmymblZP2qaqf6i42L7TXQYBuyDQUCAXYe9q1iKmKfq09X/5/wbsL/1OWp/zn4OL6xcbK1AuWZSTIUCCyArnphk2zzvedFD9hAGG681vhf0VzxKwcvx1mgOlmChgIBIws7SxVQw9eUZSkohiHjvjd9/71rvH8Dg+UzUE8QTkOBQAxo2GFZQ9nPDVcacBqIjAt2FfznE+f7CgymBUD9nCiGAoEz0Lvv3Ercfva97iPKQBjueNDxX89X7xvQ4PtAc3TA5jGzMleDAj9nSw5OjaWHSv8HtgWCwxOXmsgpkf9YOVi/A83LALkyTVRZ9Ev/236sirM3Zv8Hhh0Ys3GzgV2Gy2Bg0H0DGloLjhx2XvbLkZMj/2FTCEwB/xkZGUFh9B+o7n/S4iSsBibMTwAlt9ugpAYLUzNOfs6vHQ87yHJp5+PO/9zC3F+B5liixD4woFc55jj+JCdMHbIdfgJduRxbkpICBvTXmnM1JMV+xbGK/8CU8wmoXwRr4mdhY6nTdNH8Soqh8sbyX4CRk4UvR3EAbX2ZtSGLKANjZ8b+A2aY60B9THjzPhAECysIfyGUCUARBopcoHpTogoUoO2nQ7pD/uIzFJjUfgCT2EJSSil9YGr41na/DWcOAyYxUOQIkVSesnOzLwS5Bt1AUM6T1pX+wsjEmEJOIS0KTGJfKk9UouZxYM4DBs9lUE4kqzoBJpVSZWvlLzAD2+61/QeVtUADDcmuo4CAFZg1n6StSAMbahFj8R3In0NxxQcE3gLSAl/yd+SDIucDqO6i2FBoEjvCI8Lzg4GRIYEqVTQsiQHDdym+yEHGABQiG/wFXbQjAAAAAElFTkSuQmCC"},"556d":function(t,e,a){},"8fcb":function(t,e,a){"use strict";a.d(e,"m",(function(){return i})),a.d(e,"g",(function(){return o})),a.d(e,"d",(function(){return r})),a.d(e,"l",(function(){return s})),a.d(e,"e",(function(){return l})),a.d(e,"c",(function(){return u})),a.d(e,"q",(function(){return c})),a.d(e,"b",(function(){return d})),a.d(e,"j",(function(){return p})),a.d(e,"h",(function(){return m})),a.d(e,"p",(function(){return g})),a.d(e,"a",(function(){return h})),a.d(e,"f",(function(){return f})),a.d(e,"i",(function(){return v})),a.d(e,"o",(function(){return b})),a.d(e,"n",(function(){return S})),a.d(e,"k",(function(){return _})),a.d(e,"r",(function(){return A}));var n=a("8137");function i(t){var e="/message/list",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function o(t){var e="/message/del",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function r(t){var e="/message/clearMes",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function s(t){var e="/historyTask/list",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function l(t){var e="/historyTask/del",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function u(t){var e="/historyTask/clearTask",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function c(t){var e="/preMessage/list",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function d(t){var e="/preMessage/add",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function p(t){var e="/preMessage/edit",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function m(t){var e="/preMessage/del",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function g(t){var e="/blackList/list",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function h(t){var e="/blackList/add",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function f(t){var e="/blackList/del",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function v(t){var e="/blackList/edit",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function b(t){var e="/baudInfo/signal",a="get",i=t;return Object(n["a"])({url:e,method:a,params:i})}function S(t){var e="/historyTask/newTasklist",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function _(t){var e="/taskList/list",a="post",i=t;return Object(n["a"])({url:e,method:a,params:i})}function A(t){var e="/baudInfo/getIsRelogin",a="get",i=t;return Object(n["a"])({url:e,method:a,params:i})}},9246:function(t,e,a){"use strict";var n=a("556d"),i=a.n(n);i.a},c9f4:function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACkAAAApCAQAAAACach9AAACMUlEQVR4Ae3ShY7jQBAE0Aoz/f9/HTMzhg1zrdKUrJbdx+Kd2nD8VNudfsL/Th///dyQN2TH6f3y/BGpC379rV+S+qqetBOxImNQXL8JCAr2V4iMQXHGNJxeCfZXhSRBcQMfvkOWUdtfzlLgAENmZDcmo2TVmt8OSM2eXxBp3DjHSMFutqS7SbmemzBiR+xpKCNUIRkdkkYxhAkyGoBvyQFEJEefwSmmvBfJuJ6aKqKWnAkvGZOaZXTUgFqYULWNSHUckZuR1HIIimUExutRxwzOLROIG4vKmCKQt364mIlhSyzAf1m9lHZHJZrlAOMMztRRiKimp/rpdJDc9Awry5xTZCte7FHtuS8wJgeYGrex28xNTd086Dik7vUMscQOa8y4DoGtCCSkAKlNwpgNtphjrC6MIHUkR6YWxxs6Sc5xqn222mmCRFzIt8lEdKx+ikCtg91qS2WpwVfBelJCiQJwvzixfI9cxZQWgiSJelKnwBElKYtDOb2MFbhmUigbReQBV0Cg4+qMXSxXSyGUn4UbF8l+7qdSGnTC0XLCmahIgUHLhLOhpVCtw4CzYXvLQWQbJNmxoCsOKAxSgBJno75avolkRw8iIAFcsdc02e9iyCd8tHwmeSSoKTowIgvscSGZUOA7PuCN5b2BX9mQM7S0wYhMNU74zgsPBj3HU7wguAfnxxjFQGBE6pwN+GjME9zHY7zGp8wVxMShYX9NXvEWD3HbwJf4giO4CFIQxXScH1/TM+04kkBiAAAAAElFTkSuQmCC"},de2e:function(t,e){t.exports="data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAG4AAABmCAYAAAAqCKSWAAAEC0lEQVR4nO2cO1LkMBCG54Lcg0NwBE7ABcjJiUlJCSck4wZatxfGaqlbD3tstaz/q+qqZWoBoccnqS35cgEAAAAAAAAAAABYg/v6chStywEqca+vjqJ1OUAl7uHBUbQuB6jAfX66qcX+B3TZD+7lZWk46LIfbo1GAV32AdMkdNkPTJPQZT9EjQZd2kfUJHRpH/f8zEbZHNClbdzPTzSvzQFd2sZ9fERqnAO6tI17eloa6PGRKxO6tEmkybc3rkzo0iaRJr+/uTKhS5swTfr/hi7twkYXRTj6oEubsPkssfmGLo3BVpBSnhK6tEekyVTKC7q0A9OkP6KgS9swTfojCrq0S6TJ6zXfcNBle1gj+CMPurQN017JlgC6bE80csJcJXRpE1b5UooLurQJ052U4oIu7RGNmDUBXR4Pq/RUigu65MwLAaowOpjTInzNpVJcNbqk7USrv+f9/bhOMzde7aLg3pFLcdXoslUc2WisAWv2T/eOrRV/j7lyS6e7Xts02q3xqALC3k/qCVNSI8Y0oqLPJj02bTAfVZ1rlupnCKoP/3Cu15Ct20pEVOfalV+vIRnIghpzzFn6UdUpdVxLaixBVMVZ1alNFVbVmEOcnM+mTtpL9qjGHKdWp7Ql6U2NOU6lTup0Z1JjjlOokzrbGdWYo2t1Sp1s+qx1nR5KV+qkTiWda5nK27oem9CFOqUy/hqidf01xbQ6JSuMpsYcptQpdaaR1ZjDhDqlzgI15pl7u78QKD3seq8IN9VQYzlsc3v0w9rwASv0WEZ0uLXFIsWf36ZO1LpOuoDNMUdrUtPl1Jla14t5mmpSG+XQZRoTmpRGO3SZxoQmtdEOXeowTUp7OujSHpEma65OSZVOnWDLSWfosgymyS2npMPMx5aDs9BlHtZYa3OUWnpsbdIausyzSZPa87Iw1syb0KUOm4tqNaklhSnnKT1xkD6r+fnQ5QJTXI0mC44SiKOMUlqllz7C0Q9dLlRrUnpeRl9PI1f8+ZpKSzMzwWsVj64fk1RrUhpB9H0Fz8vEFebv90KXlVRpUpqfplFT9fu0Sxmp3w1dxhRpUlPjyrvb6ln/1NN26HKhSJM7Xg5UT3BJ7wWDLheSmjzoBkx0VELrMNDlgloxievJu5VF0iTNqX65/P8zqi7ZPir38PSgAzvqlam/soZJ6xF1yZbmpElJjYm92W7l0m7iUHmhy0v8Pq6wpxfuzXYrnzTyqUwj5y6ZJqUTw5V7s71QTzSPqkv1GZnRe2bJ/d1IuhR7sfEruGpmZRRdiln5TnqtunAZQZfRS7A7vEwRLVw66XibuGmy8zezsiTB2XV5+2MP3pvtye2JxZl1+bfRbl2OezPnNUfQJQAAAAAAAAAAAAAAAAj8A4m+RwAmrulsAAAAAElFTkSuQmCC"},f5c8:function(t,e,a){"use strict";(function(t){a("28a5"),a("6b54"),a("7f7f");var n,i=a("bd86"),o=(a("a481"),a("c8a2")),r=a("8fcb");e["a"]={name:"map-box",mounted:function(){this.init(),this._getDataToServer(),this.getZhjLocation(),this.getTerminalLocations(),this.stateBarRefresh(),this.getOnceLocation()},data:function(){return{baudsInfo:"",batteryStatus:"",imgSrc:"",currPage:1,pageSize:3,ExecurrPage:1,ExepageSize:3,activeName:"first",newInfo:[],ExecuteTask:[],total:0,total2:0,dialogVisible:!1,ICStatus:"",hardwareStatus:"",stationStatus:"",commuStatus:"",cloudService:"",map:null,mapUrl:"http://www.google.cn/maps/vt?lyrs=m@189&gl=cn&x={x}&y={y}&z={z}",options:{lng:"cn",serverIP:"192.168.0.177",mapDefaultLevel:14,domain:""},local:{cn:{position:"坐标：",my_position:"本机位置",zoom_in:"放大",zoom_out:"缩小",distance:"距离:",bearing:"角度:",language_sel_lab:"语言",language_sel_item_cn:"中文",language_sel_item_en:"英文",setting_dlg_btn_save:"保存",setting_dlg_btn_cancel:"取消",setting_dlg_title:"系统设置",outFence:"跃出电子围栏"},en:{position:"coordinate：",my_position:"The native place",zoom_in:"zoom in",zoom_out:"zoom out",distance:"distance:",bearing:"bearing:",language_sel_lab:"language",language_sel_item_cn:"chinese",language_sel_item_en:"english",setting_dlg_btn_save:"save",setting_dlg_btn_cancel:"cancel",setting_dlg_title:"system setting",outFence:"out of fence"}},_zoomCtrl:null,_btnCentre:null,_levelCtrl:null,_satBaseMapLayerGroup:null,_streetMapLayerGroup:null,_miniMapBaseVectorMapLayer:null,_miniMapLayerGroup:null,_miniMapCtrl:null,zhiMarker:null,terminalLayer:null,_fenceBar:null,_routeBar:null,dataSavedInServer:null}},created:function(){var t=this;this.getNewMsg(),this.getExecuteTask(),window.setInterval((function(){setTimeout(t.getNewMsg(),0)}),1e3),window.setInterval((function(){setTimeout(t.getExecuteTask(),0)}),1e3),window.setInterval((function(){t.getFinishTask()}),1e3)},methods:(n={handleClick:function(t,e){},handleSizeChange:function(t){this.pageSize=t,this.getNewMsg()},handleCurrentChange:function(t){this.currPage=t,this.getNewMsg()},handSizeChange:function(t){this.ExepageSize=t,this.getExecuteTask()},handCurrentChange:function(t){this.ExecurrPage=t,this.getExecuteTask()},getNewMsg:function(){var t=this,e={rows:this.pageSize,page:this.currPage};Object(r["n"])(e).then((function(e){t.newInfo=e.data.rows,t.total=e.data.records}))},getExecuteTask:function(){var t=this,e={rows:this.ExepageSize,page:this.ExecurrPage};Object(r["k"])(e).then((function(e){t.ExecuteTask=e.data.rows,t.total2=e.data.records}))},init:function(){var t,e=this;e.map=L.map("map",{attributionControl:!1,zoomControl:!1}).setView([34.3468421,108.9365887],14),L.tileLayer(this.mapUrl,{minZoom:3,maxZoom:17,Subdomains:[]}).addTo(e.map),L.control.mousePosition({separator:",",lngFirst:!0,numDigits:6,prefix:this.local[this.options.lng].position}).addTo(e.map),this._levelCtrl=new L.Control.LevelControl({lng:this.options.lng}).addTo(e.map),L.control.scale({maxWidth:200,imperial:!1}).addTo(e.map);var a=this.mapUrl.replace("{server}","en"==this.options.lng?"eng":"hot").replace("{serverIP}",this.options.serverIP);this._vectorBaseMapLayer=L.tileLayer(a,{minZoom:8,maxZoom:17}).addTo(e.map).setZIndex(0),this._vectorBaseMapLayer=L.tileLayer(this.mapUrl,{minZoom:3,maxZoom:17,Subdomains:[]}).addTo(e.map).setZIndex(0),this._satBaseMapLayerGroup=L.layerGroup(),L.tileLayer(this.mapUrl,{minZoom:0,maxZoom:8}).addTo(this._satBaseMapLayerGroup),L.tileLayer(this.mapUrl,{minZoom:3,maxZoom:17,Subdomains:[]}).addTo(this._satBaseMapLayerGroup),this._streetMapLayerGroup=L.layerGroup(),L.tileLayer(this.mapUrl,{minZoom:0,maxZoom:8,Subdomains:[]}).addTo(this._streetMapLayerGroup),this._miniMapBaseVectorMapLayer=L.tileLayer(a,{minZoom:0,maxZoom:13,Subdomains:[]}),this._miniMapLayerGroup=L.layerGroup([this._miniMapBaseVectorMapLayer]),this._miniMapCtrl=new L.Control.MiniMap(this._miniMapLayerGroup,{toggleDisplay:!0}).addTo(e.map),this._zoomCtrl=L.control.zoom({position:"bottomright",zoomInTitle:this.local[this.options.lng].zoom_in,zoomOutTitle:this.local[this.options.lng].zoom_out}).addTo(e.map),this._narbar=new L.Control.NavBar({route:e._onNavbarRoute,tool:e._onNavbarTool,test:e._onNavbarTest,serverIP:e.options.serverIP},e,{lng:this.options.lng}).addTo(e.map),this._postionbar=new L.Control.PostionBar(e.map,{lng:this.options.lng,serverIP:this.options.serverIP}),this._postionbar.setMiniMap(this._miniMapCtrl,this._miniMapLayerGroup),this._measureArea=new L.Control.Measure((t={primaryLengthUnit:"meters",secondaryLengthUnit:"kilometers",primaryAreaUnit:"sqmeters"},Object(i["a"])(t,"secondaryLengthUnit","hectares"),Object(i["a"])(t,"activeColor","#ff0000"),Object(i["a"])(t,"completedColor","#0000ff"),Object(i["a"])(t,"localization",this.options.lng),t)),this._measureRuler=new L.Control.Ruler({position:"topright",circleMarker:{color:"red",radius:2},lineStyle:{color:"red",dashArray:"1,6"},lengthUnit:{display:"km",decimal:2,factor:null,label:this.local[this.options.lng].distance},angleUnit:{display:"&deg;",decimal:2,factor:null,label:this.local[this.options.lng].bearing}}),this._btnCentre=L.easyButton("fa fa-location-arrow",this.getZhjLocation,this.local[this.options.lng].my_position,null).addTo(e.map),this._btnCentre.setPosition("bottomright"),this._fenceBar=new L.Control.FenceBar(e.map,{lng:this.options.lng}),this._fenceBar.setEventHanlder(e._fenceEventHandler,e),null!=this.dataSavedInServer&&null!=this.dataSavedInServer.fence&&this._fenceBar.deserializeFenceFromArray(this.dataSavedInServer.fence),this._routeBar=new L.Control.RouteBar({lng:this.options.lng},e.map),this._routeBar.setEventHanlder(this._routeEventHandler,this),null!=this.dataSavedInServer&&null!=this.dataSavedInServer.route&&this._routeBar.deserializeRouteFromArray(this.dataSavedInServer.route)},_routeEventHandler:function(t){this._saveDataToServer()},getZhjLocation:function(){var t=this;Object(o["D"])().then((function(e){var n=e.data,i=L.icon({iconUrl:a("de2e"),iconSize:[48,48],iconAnchor:[12,43],popupAnchor:[0,-52],shadowUrl:a("c9f4"),shadowSize:[41,41],shadowAnchor:[0,51]});null!=n.data&&(null!=t.zhiMarker&&t.map.removeLayer(t.zhiMarker),t.zhiMarker=L.marker(L.latLng([n.data.latitude,n.data.longitude]),{icon:i}).bindPopup(t._createDetail2(n.data)),t.zhiMarker.dataModel=n.data,t.zhiMarker.shape="marker",t.zhiMarker.application="routebar",t.map.addLayer(t.zhiMarker),t.map.setView(L.latLng([n.data.latitude,n.data.longitude]),t.options.mapDefaultLevel))})).catch((function(t){return console.log("err")}))},_createDetail2:function(t){var e=L.DomUtil.create("div","",""),a=L.DomUtil.create("p","text-left font-weight-bold",e),n="";return t.hasOwnProperty("lat")&&(n+="经纬度:"+t.lng+"° , "+t.lat+"°<br/>"),t.hasOwnProperty("name")&&(n+="指挥机名称："+t.name+"<br/>"),t.hasOwnProperty("cardNum")&&(n+="指挥机卡号："+t.cardNum+"<br/>"),t.hasOwnProperty("longitude")&&(n+="经纬度:"+t.longitude+"° , "+t.latitude+"°<br/>"),a.innerHTML=n,e},_createDetail1:function(t){var e=L.DomUtil.create("div","",""),a=L.DomUtil.create("p","text-left font-weight-bold",e),n="";return t.hasOwnProperty("name")&&(n+="终端名称："+t.name+"<br/>"),t.hasOwnProperty("cardNum")&&(n+="终端卡号："+t.cardNum+"<br/>"),t.hasOwnProperty("longitude")&&(n+="经纬度："+t.longitude+"° , "+t.latitude+"°<br/>"),t.hasOwnProperty("locateTime")&&(n+="定位时间："+t.locateTime+"<br/>"),a.innerHTML=n,e},getTerminalLocations:function(){var t=this,e=L.icon({iconUrl:a("2c9d"),iconSize:[21,25],iconAnchor:[9,25],popupAnchor:[0,-25],shadowUrl:a("c9f4"),shadowSize:[41,41],shadowAnchor:[14,41]});Object(o["w"])().then((function(a){var n=a.data.data,i=t;i.terminalLayer=L.featureGroup();for(var o=0;o<n.length;o++){var r=L.marker(L.latLng([n[o].latitude,n[o].longitude]),{icon:e}).bindPopup(i._createDetail1(n[o]));r.dataModel=n[o],r.shape="marker",r.application="routebar",r.addTo(i.terminalLayer)}i.terminalLayer.addTo(i.map)})).catch((function(t){return console.log(t)}))},_saveRouteDataToServer:function(){var t={route:this._routeBar.serializeRouteToArray()};JSON.stringify(t)},_fenceEventHandler:function(t){this._saveDataToServer()},_saveDataToServer:function(){var t={lng:this.options.lng,fence:this._fenceBar.serializeFenceToArray(),route:this._routeBar.serializeRouteToArrayAddOrUpdate()},e=JSON.stringify(t);Object(o["H"])("/route/handleRoute",{mapData:e}).then((function(t){var e=t.data;showTipDialog("#tip_dlg","提示",e.message,2e3)})).catch((function(t){return console.log("提示信息获取错误")}))},_onNavbarTool:function(t){1==t?(this._measureRuler.addTo(this.map),this._measureArea.addTo(this.map)):(this._measureArea.remove(),this._measureRuler.remove())},_onNavbarRoute:function(t){1==t?this._routeBar.addTo(this.map):this._routeBar.remove()},_onNavbarTest:function(){for(var t='{"data":[{"altitude":"0.0","cardNum":"142342","gpstime":"2019-04-02 16:19:55","latitude":"34.1605","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142342","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"朝鲜","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"91623a81-5d61-44bd-a69b-86c496989556","zone":"---"},{"altitude":"0.0","cardNum":"142339","gpstime":"2019-05-29 11:23:09","latitude":"34.1605","latitudefloat":34.1595,"longitude":"108.97707","longitudefloat":108.97707,"name":"142339","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"实时","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"94b9dd71-09b8-4be2-940d-a346ddc3ce0e","zone":"---"},{"altitude":"0.0","cardNum":"142338","gpstime":"2019-05-29 16:54:54","latitude":"34.160527","latitudefloat":34.159527,"longitude":"108.97713","longitudefloat":108.97713,"name":"142338","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"陕西省","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"d5321496-85a9-486e-9070-a459ee4f3c4d","zone":"---"},{"altitude":"0.0","cardNum":"142346","gpstime":"2019-04-01 14:22:43","latitude":"34.1605","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142346","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"平壤大使馆","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"f3639dbd-36a0-4959-8b99-dff890d3d87f","zone":"---"}],"message":null,"status":0}',e='{"data":{"altitude":"0","gpstime":"2019-05-29 17:28:57.0","latitude":"34.1605","longitude":"108.977104","name":null,"number":null,"uuid":"b2d72703-c4e6-4457-ad65-e4c4c7b66142"},"message":"定位成功","status":1}',a=JSON.parse(t).data,n=(JSON.parse(e).data,0),i=a.length;n<i;n++){var o=a[n];this.updateDevice(o)}},updateCenter:function(t){var e=t,a={uuid:e.uuid,title:null==e.name?"指挥机":e.name,id:null==e.number?"000000":e.number,position:e.latitude.toString()+","+e.longitude.toString(),lat:e.latitude,lng:e.longitude,time:e.gpstime,type:"center",detail:""};this.map.setView(L.latLng([a.lat,a.lng]),this.options.mapDefaultLevel);var n=this._fenceBar.checkFences(a);n.outFences.length>0&&(this._notify(a.title+"("+a.id+")"+this.local[this.options.lng].outFence),n.outFences.join()),this._postionbar.updatePoint(a,!1),this._postionbar.updateTrack(a.id,[a],!0,!0,!0)},_getDataToServer:function(){var t=this;Object(o["E"])("/route/getAll").then((function(e){for(var a=e.data.data,n=0;n<a.length;n++){t._routeBar.createRoute({title:a[n].routeName});for(var i=JSON.parse(a[n].content),o=0;o<i.wayPoints.length;o++)t._routeBar.createPoint2({lng:i.wayPoints[o].lng,lat:i.wayPoints[o].lat,title:i.rote,type:i.wayPoints[o].type,pos:i.wayPoints[o].index});for(var r=0;r<i.awayPoints.length;r++)t._routeBar.createPoint2({lng:i.awayPoints[r].lng,lat:i.awayPoints[r].lat,title:i.rote,type:i.awayPoints[r].type,pos:i.awayPoints[r].index});for(var s=0;s<i.purpose.length;s++)t._routeBar.createPoint2({lng:i.purpose[s].lng,lat:i.purpose[s].lat,title:i.rote,type:i.purpose[s].type,pos:i.purpose[s].index})}})).catch((function(t){return console.log("获取路线数据err")}))},getHistoryTrack:function(t,e,a){var n=this,i={starttime:t,endtime:e,uuid:a.uuid},r="/terminalTask/getHistoryLocation";Object(o["F"])(r,i).then((function(t){var e=t.data;for(var i in e.data){var o=e.data[i];o.lat=o.latitude,o.lng=o.longitude}n._postionbar.updateHistory(a.id,e.data,!0,!0)})).catch((function(t){return console.log("err")}))},updateDevice:function(t){var e=t,a={uuid:e.uuid,title:e.name,id:e.cardNum,position:e.latitude.toString()+","+e.longitude.toString(),lat:e.latitude,lng:e.longitude,time:e.gpstime,type:"device",detail:"",terminalAeraName:e.terminalAeraName,zone:e.zone,numInZone:e.numInZone,typeDesc:e.typeDesc},n=this._fenceBar.checkFences(a);n.outFences.length>0&&this._notify(a.title+"("+a.id+")"+this.local[this.options.lng].outFence+": "+n.outFences.join()),this.map.setView(L.latLng(a.lat,a.lng),this.options.mapDefaultLevel),this._postionbar.updatePoint(a,!0),this._postionbar.updateTrack(a.id,[a],!0,!0,!0)},_onNavbarDevMan:function(t){var e=this;1==t?(this._loadAllDevices(),this._postionbar.addTo(e.map)):this._postionbar.remove()},_loadAllDevices:function(){var t=this,e=this.options.domain+"/BAISTMonitor/getBdLocateList.action";getBdLocateList(e).then((function(e){for(var a=0;a<data.data.length;a++){var n=data.data[a];""!=n.gpstime&&t.updateDevice(n)}})).catch((function(t){return console.log("err")}))},_onNavbarRoadnet:function(t){var e=this;1==t?this._streetMapLayerGroup.addTo(e.map).setZIndex(1):this._streetMapLayerGroup.remove()},_onNavbarSatmap:function(t){var e=this;1==t?(this._vectorBaseMapLayer.remove(),this._satBaseMapLayerGroup.addTo(e.map).setZIndex(0)):(this._satBaseMapLayerGroup.remove(),this._vectorBaseMapLayer.addTo(e.map).setZIndex(0),e.map.hasLayer(this._streetMapLayerGroup)&&this._streetMapLayerGroup.remove())},_onNavbarFence:function(t){var e=this;1==t?this._fenceBar.addTo(e.map):this._fenceBar.remove()}},Object(i["a"])(n,"_onNavbarTool",(function(t){var e=this;1==t?(this._measureRuler.addTo(e.map),this._measureArea.addTo(e.map)):(this._measureArea.remove(),this._measureRuler.remove())})),Object(i["a"])(n,"_onNavbarSetting",(function(){this.showSettingDialog()})),Object(i["a"])(n,"showSettingDialog",(function(){var e=this,a=L.DomUtil.create("div","");t(a).css("z-index","100");var n=L.DomUtil.create("div","input-group",a),i=L.DomUtil.create("span","input-group-addon",n);i.innerHTML=this.local[this.options.lng].language_sel_lab;var o=L.DomUtil.create("select","form-control",n);t(o).attr({id:"map_setting_dialog_lng_input"}),t(o).append("<option>"+e.local[e.options.lng].language_sel_item_cn+"</option>"),t(o).append("<option>"+e.local[e.options.lng].language_sel_item_en+"</option>"),t(a).dialog({autoOpen:!0,title:e.local[e.options.lng].setting_dlg_title,modal:!0,resizable:!1,buttons:[{text:e.local[e.options.lng].setting_dlg_btn_save,click:function(){var a=t("#map_setting_dialog_lng_input").val();a==e.local[e.options.lng].language_sel_item_cn?e.options.lng="cn":a==e.local[e.options.lng].language_sel_item_en&&(e.options.lng="en"),e._saveDataToServer(),t(this).dialog("destroy")}},{text:e.local[e.options.lng].setting_dlg_btn_cancel,click:function(){t(this).dialog("destroy")}}]})})),Object(i["a"])(n,"_notify",(function(e){t.notify({icon:"fa fa-exclamation-triangle",message:e},{type:"danger",offset:{x:70,y:60},newest_on_top:!0})})),Object(i["a"])(n,"_tranPosition",(function(t){for(var e in t){var a=t[e].position.split(",");t[e].lat=a[0],t[e].lng=a[1]}return t})),Object(i["a"])(n,"stateBarRefresh",(function(){var t=this;this.$Bus.$on("systemState",(function(e){t.batteryStatus=e.batteryStatus,t.baudsInfo=e.baudsInfo,"1"==e.status?t.imgSrc="./img/stateStop.png":t.imgSrc="./img/stateRun.png"}))})),Object(i["a"])(n,"initServiceState",(function(){var t=this;this.dialogVisible=!0;var e={};Object(r["o"])(e).then((function(e){t.ICStatus=e.data.icStatus,t.hardwareStatus=e.data.hardwardStatus,t.stationStatus=e.data.instationStatus,0==e.data.serviceState?t.commuStatus="正常":1==e.data.serviceState&&(t.commuStatus="异常"),0==e.data.rhptState?t.cloudService="正常":1==e.data.rhptState&&(t.cloudService="异常")}))})),Object(i["a"])(n,"getOnceLocation",(function(){var t=this,e=this;this.$Bus.$on("getLocation",(function(a){Object(o["A"])({card:a}).then((function(a){var n=a.data;2==n.status?t.$alert("该终端没有位置信息！","系统提示",{confirmButtonText:"确定"}):e.map.setView(L.latLng([n.data.latitude,n.data.longitude]))})).catch((function(t){return console.log(t)}))}))})),Object(i["a"])(n,"getFinishTask",(function(){var t=this;Object(o["y"])().then((function(e){var a=e.data;"1"===a.status&&t.getZhjLocation()})).catch((function(t){return console.log(t)}))})),n)}}).call(this,a("debc"))}}]);
//# sourceMappingURL=chunk-8fa549d6.e4cd556f.js.map