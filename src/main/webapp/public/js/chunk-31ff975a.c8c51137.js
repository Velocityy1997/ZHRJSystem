(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-31ff975a"],{"206c":function(t,e,a){"use strict";(function(t){a("6b54"),a("a481"),a("28a5");var n=a("bd86"),i=(a("7f7f"),a("c8a2"));e["a"]={name:"map-box",data:function(){return{map:null,mapUrl:"http://www.google.cn/maps/vt?lyrs=m@189&gl=cn&x={x}&y={y}&z={z}",_navbar:null,_searchbar:null,_postionbar:null,_vectorBaseMapLayer:null,_satBaseMapLayerGroup:null,_streetMapLayerGroup:null,_gpsLayerGroup:null,_trackLayerGroup:null,_fenceLayerGroup:null,_miniMapBaseVectorMapLayer:null,_miniMapCtrl:null,_miniMapLayerGroup:null,index:0,_measureArea:null,_measureRuler:null,_btnCentre:null,_levelCtrl:null,_zoomCtrl:null,_fenceBar:null,_routeBar:null,dataSavedInServer:null,options:{lng:"cn",serverIP:"192.168.0.177",mapDefaultLevel:14,domain:""},local:{cn:{position:"坐标：",my_position:"本机位置",zoom_in:"放大",zoom_out:"缩小",distance:"距离:",bearing:"角度:",language_sel_lab:"语言",language_sel_item_cn:"中文",language_sel_item_en:"英文",setting_dlg_btn_save:"保存",setting_dlg_btn_cancel:"取消",setting_dlg_title:"系统设置",outFence:"跃出电子围栏"},en:{position:"coordinate：",my_position:"The native place",zoom_in:"zoom in",zoom_out:"zoom out",distance:"distance:",bearing:"bearing:",language_sel_lab:"language",language_sel_item_cn:"chinese",language_sel_item_en:"english",setting_dlg_btn_save:"save",setting_dlg_btn_cancel:"cancel",setting_dlg_title:"system setting",outFence:"out of fence"}}}},mounted:function(){this.init(),this.getTerminaltraces()},methods:{_createDetail2:function(t){var e=L.DomUtil.create("div","",""),a=L.DomUtil.create("p","text-left font-weight-bold",e),n="";return t.hasOwnProperty("lat")&&(n+="经纬度:"+t.lng+"° , "+t.lat+"°<br/>"),t.hasOwnProperty("name")&&(n+="指挥机名称："+t.name+"<br/>"),t.hasOwnProperty("cardNum")&&(n+="指挥机卡号："+t.cardNum+"<br/>"),t.hasOwnProperty("longitude")&&(n+="经纬度:"+t.longitude+"° , "+t.latitude+"°<br/>"),a.innerHTML=n,e},_createDetail1:function(t){var e=L.DomUtil.create("div","",""),a=L.DomUtil.create("p","text-left font-weight-bold",e),n="";return t.hasOwnProperty("name")&&(n+="终端名称："+t.name+"<br/>"),t.hasOwnProperty("cardNum")&&(n+="终端卡号："+t.cardNum+"<br/>"),t.hasOwnProperty("longitude")&&(n+="经纬度："+t.longitude+"° , "+t.latitude+"°<br/>"),t.hasOwnProperty("locateTime")&&(n+="定位时间："+t.locateTime+"<br/>"),a.innerHTML=n,e},init:function(){var e,a=this,i=0;document.documentElement&&document.documentElement.clientHeight&&document.documentElement.clientWidth&&(i=document.documentElement.clientHeight,document.documentElement.clientWidth),null!=this.dataSavedInServer&&(this.options.lng=this.dataSavedInServer.lng),t("#mymap").height(i),t(window).resize((function(){document.documentElement&&document.documentElement.clientHeight&&document.documentElement.clientWidth&&(i=document.documentElement.clientHeight,document.documentElement.clientWidth),t("#mymap").height(i)})),this.map=L.map("mymap",{attributionControl:!1,zoomControl:!1}).setView([34.3468421,108.9365887],this.options.mapDefaultLevel),L.control.mousePosition({separator:",",lngFirst:!0,numDigits:6,prefix:this.local[this.options.lng].position}).addTo(a.map),this._levelCtrl=new L.Control.LevelControl({lng:this.options.lng}).addTo(a.map),L.control.scale({maxWidth:200,imperial:!1}).addTo(a.map),this._vectorBaseMapLayer=L.tileLayer(a.mapUrl,{minZoom:3,maxZoom:17,Subdomains:[]}).addTo(a.map).setZIndex(0),this._satBaseMapLayerGroup=L.layerGroup(),L.tileLayer(a.mapUrl,{minZoom:0,maxZoom:8}).addTo(this._satBaseMapLayerGroup),L.tileLayer(a.mapUrl,{minZoom:3,maxZoom:17,Subdomains:[]}).addTo(this._satBaseMapLayerGroup),this._streetMapLayerGroup=L.layerGroup(),L.tileLayer(a.mapUrl,{minZoom:0,maxZoom:8,Subdomains:[]}).addTo(this._streetMapLayerGroup),this._miniMapBaseVectorMapLayer=L.tileLayer(a.mapUrl,{minZoom:0,maxZoom:13,Subdomains:[]}),this._miniMapLayerGroup=L.layerGroup([this._miniMapBaseVectorMapLayer]),this._miniMapCtrl=new L.Control.MiniMap(this._miniMapLayerGroup,{toggleDisplay:!0}).addTo(a.map),this._zoomCtrl=L.control.zoom({position:"bottomright",zoomInTitle:this.local[this.options.lng].zoom_in,zoomOutTitle:this.local[this.options.lng].zoom_out}).addTo(a.map),this._postionbar=new L.Control.PostionBar(a.map,{lng:this.options.lng,serverIP:this.options.serverIP}),this._postionbar.setMiniMap(this._miniMapCtrl,this._miniMapLayerGroup),this._measureArea=new L.Control.Measure((e={primaryLengthUnit:"meters",secondaryLengthUnit:"kilometers",primaryAreaUnit:"sqmeters"},Object(n["a"])(e,"secondaryLengthUnit","hectares"),Object(n["a"])(e,"activeColor","#ff0000"),Object(n["a"])(e,"completedColor","#0000ff"),Object(n["a"])(e,"localization",this.options.lng),e)),this._measureRuler=new L.Control.Ruler({position:"topright",circleMarker:{color:"red",radius:2},lineStyle:{color:"red",dashArray:"1,6"},lengthUnit:{display:"km",decimal:2,factor:null,label:this.local[this.options.lng].distance},angleUnit:{display:"&deg;",decimal:2,factor:null,label:this.local[this.options.lng].bearing}}),this._fenceBar=new L.Control.FenceBar(a.map,{lng:this.options.lng}),this._fenceBar.setEventHanlder(this._fenceEventHandler,this),null!=this.dataSavedInServer&&null!=this.dataSavedInServer.fence&&this._fenceBar.deserializeFenceFromArray(this.dataSavedInServer.fence),this._routeBar=new L.Control.RouteBar({lng:this.options.lng}),this._routeBar.setEventHanlder(this._routeEventHandler,this),null!=this.dataSavedInServer&&null!=this.dataSavedInServer.route&&this._routeBar.deserializeRouteFromArray(this.dataSavedInServer.route)},getTerminaltraces:function(t){var e=this,a=this,n=location.hash.split("?");n[1]=n[1].replace(/%20/g," "),n[1]=n[1].replace(/%3A/g,":");var o=n[1].split("&"),l=o[0].indexOf("="),r=(o[0].substring(0,l),o[0].substring(l+1));l=o[1].indexOf("="),o[1].substring(0,l);var s=o[1].substring(l+1);l=o[2].indexOf("="),o[2].substring(0,l);var d=o[2].substring(l+1);Object(i["G"])({card:r,startTime:s,endTime:d}).then((function(t){var n=t.data;if(""!==n.data){a.map.setView(L.latLng([n.data[0].latitude,n.data[0].longitude]),a.options.mapDefaultLevel),a.terminalLayer=L.featureGroup(),a.lineLayer=L.layerGroup();var i=[];if(n.data.length>0){var o=L.marker(L.latLng([n.data[0].latitude,n.data[0].longitude]),{icon:a._routeBar._createIcon("qidian")}).bindPopup(a._createDetail1(n.data[0]));o.dataModel=n.data[0],o.shape="marker",o.application="routebar",o.addTo(a.terminalLayer)}for(var l=2;l<n.data.length-2;l+=5){var r=L.marker(L.latLng([n.data[l].latitude,n.data[l].longitude]),{icon:a._routeBar._createIcon("tujing")}).bindPopup(a._createDetail1(n.data[l]));r.dataModel=n.data[l],r.shape="marker",r.application="routebar",r.addTo(a.terminalLayer)}if(n.data.length>0){var s=L.marker(L.latLng([n.data[n.data.length-1].latitude,n.data[n.data.length-1].longitude]),{icon:a._routeBar._createIcon("zhongdian")}).bindPopup(a._createDetail1(n.data[n.data.length-1]));s.dataModel=n.data[n.data.length-1],s.shape="marker",s.application="routebar",s.addTo(a.terminalLayer)}for(var d=0;d<n.data.length;d++){i.push(L.latLng([n.data[d].latitude,n.data[d].longitude]));L.polyline(i,{color:"red"}).addTo(a.lineLayer)}a.terminalLayer.addTo(a.map),a.lineLayer.addTo(a.map),L.polylineDecorator(i,{patterns:[{offset:25,repeat:50,symbol:L.Symbol.arrowHead({pixelSize:15,pathOptions:{fillOpacity:1,weight:0}})}]}).addTo(a.map),n.data.length>0?a.map.setView(L.latLng([n.data[0].latitude,n.data[0].longitude]),a.options.mapDefaultLevel):e.$alert("该终端没有历史位置信息！","系统提示",{confirmButtonText:"确定"})}else a.$alert("".concat(n.message),"系统提示",{confirmButtonText:"确定"})})).catch((function(t){return console.log(t)}))},_fenceEventHandler:function(t){this._saveDataToServer()},updateDevice:function(t){var e=this,a=t,n={uuid:a.uuid,title:a.name,id:a.cardNum,position:a.latitude.toString()+","+a.longitude.toString(),lat:a.latitude,lng:a.longitude,time:a.gpstime,type:"device",detail:"",terminalAeraName:a.terminalAeraName,zone:a.zone,numInZone:a.numInZone,typeDesc:a.typeDesc},i=this._fenceBar.checkFences(n);i.outFences.length>0&&this._notify(n.title+"("+n.id+")"+this.local[this.options.lng].outFence+": "+i.outFences.join()),e.map.setView(L.latLng(n.lat,n.lng),this.options.mapDefaultLevel),this._postionbar.updatePoint(n,!0),this._postionbar.updateTrack(n.id,[n],!0,!0,!0)},updateCenter:function(t){var e=this,a=t,n={uuid:a.uuid,title:null==a.name?"指挥机":a.name,id:null==a.number?"000000":a.number,position:a.latitude.toString()+","+a.longitude.toString(),lat:a.latitude,lng:a.longitude,time:a.gpstime,type:"center",detail:""};e.map.setView(L.latLng([n.lat,n.lng]),this.options.mapDefaultLevel);var i=this._fenceBar.checkFences(n);i.outFences.length>0&&(this._notify(n.title+"("+n.id+")"+this.local[this.options.lng].outFence),i.outFences.join()),this._postionbar.updatePoint(n,!1),this._postionbar.updateTrack(n.id,[n],!0,!0,!0)},_loadAllDevices:function(){var t=this,e=this.options.domain+"/BAISTMonitor/getBdLocateList.action";Object(i["x"])(e).then((function(e){for(var a=0;a<data.data.length;a++){var n=data.data[a];""!=n.gpstime&&t.updateDevice(n)}})).catch((function(t){return console.log("err")}))},_onNavbarRoadnet:function(t){1==t?this._streetMapLayerGroup.addTo(this.map).setZIndex(1):this._streetMapLayerGroup.remove()},_onNavbarSatmap:function(t){1==t?(this._vectorBaseMapLayer.remove(),this._satBaseMapLayerGroup.addTo(this.map).setZIndex(0)):(this._satBaseMapLayerGroup.remove(),this._vectorBaseMapLayer.addTo(this.map).setZIndex(0),this.map.hasLayer(this._streetMapLayerGroup)&&this._streetMapLayerGroup.remove())},_onNavbarDevMan:function(t){1==t?(this._loadAllDevices(),this._postionbar.addTo(this.map)):this._postionbar.remove()},_onNavbarFence:function(t){1==t?this._fenceBar.addTo(this.map):this._fenceBar.remove()},_onNavbarTool:function(t){1==t?(this._measureRuler.addTo(this.map),this._measureArea.addTo(this.map)):(this._measureArea.remove(),this._measureRuler.remove())},_onNavbarSetting:function(){this.showSettingDialog()},_onNavbarTest:function(){for(var t='{"data":[{"altitude":"0.0","cardNum":"142342","gpstime":"2019-04-02 16:19:55","latitude":"34.1595","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142342","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"朝鲜","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"91623a81-5d61-44bd-a69b-86c496989556","zone":"---"},{"altitude":"0.0","cardNum":"142339","gpstime":"2019-05-29 11:23:09","latitude":"34.1595","latitudefloat":34.1595,"longitude":"108.97707","longitudefloat":108.97707,"name":"142339","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"实时","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"94b9dd71-09b8-4be2-940d-a346ddc3ce0e","zone":"---"},{"altitude":"0.0","cardNum":"142338","gpstime":"2019-05-29 16:54:54","latitude":"34.159527","latitudefloat":34.159527,"longitude":"108.97713","longitudefloat":108.97713,"name":"142338","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"陕西省","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"d5321496-85a9-486e-9070-a459ee4f3c4d","zone":"---"},{"altitude":"0.0","cardNum":"142346","gpstime":"2019-04-01 14:22:43","latitude":"34.1595","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142346","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"平壤大使馆","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"f3639dbd-36a0-4959-8b99-dff890d3d87f","zone":"---"}],"message":null,"status":0}',e='{"data":{"altitude":"0","gpstime":"2019-05-29 17:28:57.0","latitude":"34.1595","longitude":"108.977104","name":null,"number":null,"uuid":"b2d72703-c4e6-4457-ad65-e4c4c7b66142"},"message":"定位成功","status":1}',a=JSON.parse(t).data,n=JSON.parse(e).data,i=0,o=a.length;i<o;i++){var l=a[i];this.updateDevice(l)}this.updateCenter(n);for(t='{"data":[{"altitude":"0.0","cardNum":"142342","gpstime":"2019-04-02 16:19:55","latitude":"34.1605","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142342","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"朝鲜","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"91623a81-5d61-44bd-a69b-86c496989556","zone":"---"},{"altitude":"0.0","cardNum":"142339","gpstime":"2019-05-29 11:23:09","latitude":"34.1605","latitudefloat":34.1595,"longitude":"108.97707","longitudefloat":108.97707,"name":"142339","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"实时","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"94b9dd71-09b8-4be2-940d-a346ddc3ce0e","zone":"---"},{"altitude":"0.0","cardNum":"142338","gpstime":"2019-05-29 16:54:54","latitude":"34.160527","latitudefloat":34.159527,"longitude":"108.97713","longitudefloat":108.97713,"name":"142338","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"陕西省","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"d5321496-85a9-486e-9070-a459ee4f3c4d","zone":"---"},{"altitude":"0.0","cardNum":"142346","gpstime":"2019-04-01 14:22:43","latitude":"34.1605","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142346","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"平壤大使馆","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"f3639dbd-36a0-4959-8b99-dff890d3d87f","zone":"---"}],"message":null,"status":0}',e='{"data":{"altitude":"0","gpstime":"2019-05-29 17:28:57.0","latitude":"34.1605","longitude":"108.977104","name":null,"number":null,"uuid":"b2d72703-c4e6-4457-ad65-e4c4c7b66142"},"message":"定位成功","status":1}',a=JSON.parse(t).data,n=JSON.parse(e).data,i=0,o=a.length;i<o;i++){l=a[i];this.updateDevice(l)}},_notify:function(e){t.notify({icon:"fa fa-exclamation-triangle",message:e},{type:"danger",offset:{x:70,y:60},newest_on_top:!0})},_tranPosition:function(t){for(var e in t){var a=t[e].position.split(",");t[e].lat=a[0],t[e].lng=a[1]}return t},showSettingDialog:function(){var e=L.DomUtil.create("div","");t(e).css("z-index","100");var a=L.DomUtil.create("div","input-group",e),n=L.DomUtil.create("span","input-group-addon",a);n.innerHTML=this.local[this.options.lng].language_sel_lab;var i=L.DomUtil.create("select","form-control",a);t(i).attr({id:"map_setting_dialog_lng_input"}),t(i).append("<option>"+this.local[this.options.lng].language_sel_item_cn+"</option>"),t(i).append("<option>"+this.local[this.options.lng].language_sel_item_en+"</option>"),t(e).dialog({autoOpen:!0,title:this.local[this.options.lng].setting_dlg_title,modal:!0,resizable:!1,buttons:[{text:this.local[this.options.lng].setting_dlg_btn_save,click:function(){var e=t("#map_setting_dialog_lng_input").val();e==g_Main.local[g_Main.options.lng].language_sel_item_cn?g_Main.options.lng="cn":e==g_Main.local[g_Main.options.lng].language_sel_item_en&&(g_Main.options.lng="en"),g_Main._saveDataToServer(),t(this).dialog("destroy")}},{text:this.local[this.options.lng].setting_dlg_btn_cancel,click:function(){t(this).dialog("destroy")}}]})}}}}).call(this,a("debc"))},"2bda":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement;t._self._c;return t._m(0)},i=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"map",attrs:{id:"map-box"}},[a("div",{staticClass:"my-map",attrs:{id:"mymap"}})])}],o=a("206c"),l=o["a"],r=(a("5395"),a("2877")),s=Object(r["a"])(l,n,i,!1,null,"1746c2e0",null);e["default"]=s.exports},5395:function(t,e,a){"use strict";var n=a("5672"),i=a.n(n);i.a},5672:function(t,e,a){}}]);
//# sourceMappingURL=chunk-31ff975a.c8c51137.js.map