﻿document.write("<script language=javascript src='scripts/W.Map.js'></script>");
//fontawesome
//document.write('<link href="https://maxcdn.bootstrapcdn.com/font-awesome/5.8.1/css/font-awesome.min.css" rel="stylesheet">');
//document.write('<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">');
//document.write('<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"/>');
//document.write("<link rel='stylesheet' href='css/fontawesome-all.css' />");


$(document).ready(function(){
    g_Main = new Main();
    g_Main.init();
    this.zhiMarker = null;
    //g_Main._getDataToServer();//获取路线
    g_Main.getZhjLocation();//指挥机位置定位到中心
    g_Main.getTerminalLocations();//获取所有终端的位置
});

function getZhjLocation() { 
    g_Main.getZhjLocation();//指挥机位置定位到中心
}

function getTerminalLocation(card) { //某个终端的位置
   
	  $.ajax({
		async:true,
		//cache:false,
		data:"card="+card,
	    dataType : "json",
		context:this,
		type: 'GET',
		url: '/ZHRJSystem/terminalTask/getLocation',
		success: function(data){	
		    if(data.status ==2) {
		        showTipDialog('#tip_dlg',"提示", "该终端没有位置信息！",3000);
		    }
		    else {		
			     map.setView(L.latLng([data.data.latitude,data.data.longitude]),14);		    									    		    		   
		}
		}
	})
};
function _createDetail2(obj){
	var div = L.DomUtil.create('div','','');
	var p = L.DomUtil.create('p','text-left font-weight-bold',div);
	var content = "";
	
	if(obj.hasOwnProperty('lat'))content += "经纬度:" + obj.lng + '° , ' + obj.lat +  '°' + "<br/>";
	if(obj.hasOwnProperty('name'))content += "指挥机名称：" + obj.name + "<br/>";
	if(obj.hasOwnProperty('cardNum'))content += "指挥机卡号：" + obj.cardNum  + "<br/>";		
	if(obj.hasOwnProperty('longitude'))content += "经纬度:" + obj.longitude + '° , ' + obj.latitude +  '°' + "<br/>";
	
	p.innerHTML = content;

	return div;
}

function _createDetail1(obj){
	var div = L.DomUtil.create('div','','');
	var p = L.DomUtil.create('p','text-left font-weight-bold',div);
	var content = "";
	
	if(obj.hasOwnProperty('name'))content += "终端名称：" + obj.name + "<br/>";
	if(obj.hasOwnProperty('cardNum'))content += "终端卡号：" + obj.cardNum  + "<br/>";		
	if(obj.hasOwnProperty('longitude'))content += "经纬度：" + obj.longitude + '° , ' + obj.latitude +  '°' + "<br/>";
	if(obj.hasOwnProperty('locateTime'))content += "定位时间：" + obj.locateTime + "<br/>";
	p.innerHTML = content;		
	return div;
}

function Main() {
	var mapUrl = 'http://www.google.cn/maps/vt?lyrs=m@189&gl=cn&x={x}&y={y}&z={z}';
	
	this._navbar = null;
	this._searchbar = null;
	this._postionbar = null;

	this._vectorBaseMapLayer = null;
	this._satBaseMapLayerGroup = null;
	this._streetMapLayerGroup = null;
	this._gpsLayerGroup = null;
	this._trackLayerGroup = null;
	this._fenceLayerGroup = null;

	this._miniMapBaseVectorMapLayer = null;
	this._miniMapCtrl = null;
	this._miniMapLayerGroup = null;
	this.index = 0;
	//tool
	this._measureArea = null;
	this._measureRuler = null;
	//
	this._btnCentre = null;
	//level ctrl
	this._levelCtrl = null;
	//zoom ctrl
	this._zoomCtrl = null;
	//fence ctrl bar
	this._fenceBar = null;
	//route ctrl bar
	this._routeBar = null;
	this.dataSavedInServer = null;
	this.options = {
		lng:'cn',
		//serverIP:'127.0.0.1',
		serverIP:'192.168.0.177',
		mapDefaultLevel: 14,
		//domain: 'http://192.168.0.88'
			domain: ''
	};
	this.local = {
		cn:{
			position:'坐标：',
			my_position:'本机位置',
			zoom_in:'放大',
			zoom_out:'缩小',
			distance:'距离:',
			bearing:'角度:',
			language_sel_lab:'语言',
			language_sel_item_cn:'中文',
			language_sel_item_en:'英文',
			setting_dlg_btn_save:'保存',
			setting_dlg_btn_cancel:'取消',
			setting_dlg_title:'系统设置',
			outFence:'跃出电子围栏',
		},
		en:{
			position:'coordinate：',
			my_position:'The native place',
			zoom_in:'zoom in',
			zoom_out:'zoom out',
			distance:'distance:',
			bearing:'bearing:',
			language_sel_lab:'language',
			language_sel_item_cn:'chinese',
			language_sel_item_en:'english',
			setting_dlg_btn_save:'save',
			setting_dlg_btn_cancel:'cancel',
			setting_dlg_title:'system setting',
			outFence:'out of fence',
		}
	};
    	this.init = function () {
        var winWidth = 0;
        var winHeight = 0;

        if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
            winHeight = document.documentElement.clientHeight;
            winWidth = document.documentElement.clientWidth;
        }

	this._getSavedDataFromServer();
	if(this.dataSavedInServer != null){this.options.lng = this.dataSavedInServer.lng;}
	

    $("#mymap").height(winHeight);
	$(window).resize(function(){
		if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
			winHeight = document.documentElement.clientHeight;
			winWidth = document.documentElement.clientWidth;
		}
		$("#mymap").height(winHeight);
	});

    map = L.map('mymap', {
            attributionControl: false,
            zoomControl: false
    }).setView([34.3468421, 108.9365887], this.options.mapDefaultLevel);

    //鼠标位置控件
    L.control.mousePosition({ separator: ",", lngFirst: true, numDigits: 6, prefix: this.local[this.options.lng].position}).addTo(map);
	//地图级数控件
	this._levelCtrl = new L.Control.LevelControl({lng:this.options.lng}).addTo(map);
    //比例尺控件
    L.control.scale({ maxWidth: 200 ,imperial: false}).addTo(map);
    //鹰眼控件
	///var vectorMapUri = mapUrl.replace('{server}',this.options.lng == 'en' ? 'eng' : 'hot').replace('{serverIP}',this.options.serverIP);
        
	this._vectorBaseMapLayer = L.tileLayer(mapUrl, {
            minZoom: 3,
            maxZoom: 17,
            Subdomains: []
        }).addTo(map).setZIndex(0);
      
	this._satBaseMapLayerGroup = L.layerGroup();
	L.tileLayer(mapUrl, {
            minZoom: 0,
            maxZoom: 8
	}).addTo(this._satBaseMapLayerGroup);
	L.tileLayer(mapUrl, {
	    minZoom: 3,
	    maxZoom: 17,
        Subdomains: []
	}).addTo(this._satBaseMapLayerGroup);
       
	this._streetMapLayerGroup = L.layerGroup();
	L.tileLayer(mapUrl, {
            minZoom: 0,
            maxZoom: 8,
            Subdomains: []
        }).addTo(this._streetMapLayerGroup);
		
        this._miniMapBaseVectorMapLayer = L.tileLayer(mapUrl, {
            minZoom: 0,
            maxZoom: 13,
            Subdomains: []
        });

        this._miniMapLayerGroup = L.layerGroup([this._miniMapBaseVectorMapLayer])
        this._miniMapCtrl = new L.Control.MiniMap(this._miniMapLayerGroup, { toggleDisplay: true }).addTo(map);
		//zoom ctrl
		this._zoomCtrl = L.control.zoom({
			position:'bottomright',
			zoomInTitle:this.local[this.options.lng].zoom_in,
			zoomOutTitle:this.local[this.options.lng].zoom_out
		}).addTo(map);

		 this._narbar = new L.Control.NavBar({
	           
			    route: this._onNavbarRoute,
		            tool: this._onNavbarTool,
			    test:this._onNavbarTest,
			    serverIP:this.options.serverIP
		        },this,{lng:this.options.lng}).addTo(map);

		        this._postionbar = new L.Control.PostionBar(map,{lng:this.options.lng,serverIP:this.options.serverIP});
				this._postionbar.setMiniMap(this._miniMapCtrl,this._miniMapLayerGroup);

				this._measureArea = new L.Control.Measure({
					primaryLengthUnit:'meters',
					secondaryLengthUnit:'kilometers',
					primaryAreaUnit: 'sqmeters',
					secondaryLengthUnit: 'hectares',
					activeColor: '#ff0000',
					completedColor: '#0000ff',
					localization: this.options.lng
				});

				this._measureRuler = new L.Control.Ruler({
					position: 'topright',         // Leaflet control position option
					circleMarker: {               // Leaflet circle marker options for points used in this plugin
					color: 'red',
					radius: 2
					},
					lineStyle: {                  // Leaflet polyline options for lines used in this plugin
					color: 'red',
					dashArray: '1,6'
					},
					lengthUnit: {                 // You can use custom length units. Default unit is kilometers.
					display: 'km',              // This is the display value will be shown on the screen. Example: 'meters'
					decimal: 2,                 // Distance result will be fixed to this value. 
					factor: null,               // This value will be used to convert from kilometers. Example: 1000 (from kilometers to meters)  
					label: this.local[this.options.lng].distance           
					},
					angleUnit: {
					display: '&deg;',           // This is the display value will be shown on the screen. Example: 'Gradian'
					decimal: 2,                 // Bearing result will be fixed to this value.
					factor: null,                // This option is required to customize angle unit. Specify solid angle value for angle unit. Example: 400 (for gradian).
					label: this.local[this.options.lng].bearing
					}
				});

				this._btnCentre = L.easyButton('fa fa-location-arrow',this.getZhjLocation,this.local[this.options.lng].my_position,null).addTo( map );
				this._btnCentre.setPosition('bottomright');

				this._fenceBar = new L.Control.FenceBar(map,{lng:this.options.lng});
				this._fenceBar.setEventHanlder(this._fenceEventHandler,this);
				if(this.dataSavedInServer != null && this.dataSavedInServer.fence != null){this._fenceBar.deserializeFenceFromArray(this.dataSavedInServer.fence);}

				this._routeBar = new L.Control.RouteBar({lng:this.options.lng});
				this._routeBar.setEventHanlder(this._routeEventHandler,this);
				if(this.dataSavedInServer != null && this.dataSavedInServer.route != null){this._routeBar.deserializeRouteFromArray(this.dataSavedInServer.route);}
				//var comp = new L.Control.Compass({position:'bottomright',autoActive: false, showDigit:false});

				//map.addControl(comp);
				//this._loadAllDevices();
				this._loadcenter();
		    };
		    
			this.getZhjLocation = function () { 
			     
			    $.ajax({
				async:true,
				//cache:false,
				context:this,
				type: 'POST',
				url: '/ZHRJSystem/zhj/getPosition',
				success: function(data,status,xhr){	
				    var icon =  L.icon({
					    iconUrl: 'images/wujiaoxing.jpg',
					    iconSize: [48, 48],
					    iconAnchor: [12, 43],
					    popupAnchor: [0, -52],
					    shadowUrl: 'images/marker-shadow.png',
					    shadowSize: [41, 41],
					    shadowAnchor: [0, 51]
				});
				    if(data.data != null) {
				    					
						//this.zhjLayer = L.featureGroup();
						//this.zhjLayer.clearLayers();
						//map.removeLayer(this.zhjLayer);
						if(this.zhiMarker != null) {
						    //alert("1");
						    map.removeLayer(this.zhiMarker);
						}	
						
						this.zhiMarker = L.marker(L.latLng([data.data.latitude,data.data.longitude]),{icon: icon})
						.bindPopup(_createDetail2(data.data));
						this.zhiMarker.dataModel = data.data;
						this.zhiMarker.shape = 'marker';
						this.zhiMarker.application = 'routebar';
						//mk.addTo(this.zhjLayer);	
						//this.zhjLayer.addTo(map);	
						map.addLayer(this.zhiMarker);
						map.setView(L.latLng([data.data.latitude,data.data.longitude]),this.options.mapDefaultLevel);	
				    }
				    	
				}
			});
			};
		    
		    
		    this._getDataToServer = function(){
				
				//return;
				$.ajax({
					async:true,
					//cache:false,
					context:this,
					type: 'POST',
					url: this.options.domain + 'getMapData.action',
					success: function(data,status,xhr){
					    
						mapData = data.rows;
						//console.log(mapData);
						
						for(var i=0; i<mapData.length; i++) {
						    this._routeBar.createRoute({title:mapData[i].rote});//添加路线					    	
						    var json = JSON.parse(mapData[i].content);		
						    console.log(json);				    
						    for(var j=0; j<json.wayPoints.length; j++) {				    
						    this._routeBar.createPoint({lng:json.wayPoints[j].lng,lat:json.wayPoints[j].lat,
						    title:json.rote,type:json.wayPoints[j].type,pos:json.wayPoints[j].index});			        
						}	
						for(var j=0; j<json.awayPoints.length; j++) {				    
						    this._routeBar.createPoint({lng:json.awayPoints[j].lng,lat:json.awayPoints[j].lat,
						    title:json.rote,type:json.awayPoints[j].type,pos:json.awayPoints[j].index});			        
						}
						for(var j=0; j<json.purpose.length; j++) {				    
						    this._routeBar.createPoint({lng:json.purpose[j].lng,lat:json.purpose[j].lat,
						    title:json.rote,type:json.purpose[j].type,pos:json.purpose[j].index});			        
						}				    		        
						}
					}
				});
			};
		    
			this._saveDataToServer = function(){
				var dat = {
					lng:this.options.lng,
					fence:this._fenceBar.serializeFenceToArray(),
					route:this._routeBar.serializeRouteToArrayAddOrUpdate(),		
				};
				var json = JSON.stringify(dat);	
				
				//return;
				$.ajax({
					async:true,
					//cache:false,
					context:this,
					data:{mapData:json},
					dataType:'json',
					type: 'POST',
					url: this.options.domain + 'updateMapData.action',
					success: function(data,status,xhr){
						showTipDialog('#tip_dlg',"提示", data.message,2000);
					}
				});
			};
			this._saveRouteDataToServer = function(){
				var dat = {
					route:this._routeBar.serializeRouteToArray(),		
				};
				var json = JSON.stringify(dat);console.log(json);		
				//return;
				/*$.ajax({
					async:true,
					//cache:false,
					context:this,
					data:dat,
					dataType:'json',
					type: 'POST',
					url: this.options.domain + '/BAISTMonitor/updateMapData.action',
					success: function(data,status,xhr){
						console.log('save data to server success!')
					}
				});*/
			};
			this._getSavedDataFromServer = function(){
				/*$.ajax({
					async:false,
					//cache:false,
					context:this,
					//data:dat,
					dataType:'json',
					type: 'GET',
					url: this.options.domain + '/BAISTMonitor/getMapData.action',
					success: function(data,status,xhr){
						var obj = JSON.parse(data.data);
						this.dataSavedInServer = obj;console.log(data.data);
						console.log('get data to server success!')
					}
				});*/
				/*var json = '{"lng":"en","fence":[{"id":"q14g3m1559190972047","shape":"rectangle","path":"108.904982,34.340885;108.904982,34.358671;108.940516,34.358671;108.940516,34.340885"},{"id":"k7iuqk40qe1559190977350","shape":"circle","path":"108.961029,34.368094,1129.746"}]}';
				var obj = JSON.parse(json);
				this.dataSavedInServer = obj;*/
			};
			this._getSavedRouteDataFromServer = function(){
				/*$.ajax({
					async:true,
					//cache:false,
					context:this,
					//data:dat,
					dataType:'json',
					type: 'GET',
					url: this.options.domain + '/BAISTMonitor/﻿getMapData.action',
					success: function(data,status,xhr){
						var obj = JSON.parse(data.data);
						this.routeDataSavedInServer = obj;
						if(this.routeDataSavedInServer != null && this.routeDataSavedInServer.route != null){this._routeBar.deserializeRouteFromArray(this.routeDataSavedInServer.route);}
						console.log('get data to server success!')
					}
				});*/
				/*var json = '{"lng":"en","fence":[{"id":"q14g3m1559190972047","shape":"rectangle","path":"108.904982,34.340885;108.904982,34.358671;108.940516,34.358671;108.940516,34.340885"},{"id":"k7iuqk40qe1559190977350","shape":"circle","path":"108.961029,34.368094,1129.746"}]}';
				var obj = JSON.parse(json);
				this.dataSavedInServer = obj;*/
			};
			
		
				
				this.getTerminalLocations = function () { 
					     
					    icon = L.icon({
					    iconUrl: 'images/mapImg/marker-green.png',
					    iconSize: [21, 25],
					    iconAnchor: [9, 25],
					    popupAnchor: [0, -25],
					    shadowUrl: 'images/marker-shadow.png',
					    shadowSize: [41, 41],
					    shadowAnchor: [14, 41]
				}); 
					    $.ajax({
						async:true,
						//cache:false,
						context:this,
						type: 'GET',
						url:  '/ZHRJSystem/terminal/getAllPosion',
						success: function(data,status,xhr){					    
						    console.log(data.data);	
						    this.terminalLayer = L.featureGroup();	
						    for(var i=0; i<data.data.length; i++) {				    				        
								var mk = L.marker(L.latLng([data.data[i].latitude,data.data[i].longitude]),{icon: icon/* this._routeBar._createIcon('devIco') */})
								.bindPopup(_createDetail1(data.data[i]));;
								mk.dataModel = data.data[i];
								mk.shape = 'marker';
								mk.application = 'routebar';
								mk.addTo(this.terminalLayer);										
						    }
						    this.terminalLayer.addTo(map);
						}
					});
					};
					
					
			this.getHistoryTrack = function(startTime,endTime,item){
				//console.log(uuid);
				$.ajax({
					async:true,
					//cache:false,
					context:this,
					data:{
						starttime:startTime,
						endtime:endTime,
						uuid:item.uuid,
					},
					dataType:'json',
					type: 'GET',
					url: this.options.domain + '/BAISTMonitor/getTerminalHisPositions.action',
					success: function(data,status,xhr){
						for(var i in data.data){
							var pt = data.data[i];
							pt.lat = pt.latitude;
							pt.lng = pt.longitude;
						}
						this._postionbar.updateHistory(item.id,data.data,true,true);
						console.log('getHistoryTrack success!')
					}
				});
			};
			this._fenceEventHandler = function(msg){
				this._saveDataToServer();
			};
			this._routeEventHandler = function(msg){
				this._saveDataToServer();
			};
			this._onNavToCentre = function(btn, _map){
				//map.setView([34.3468421, 108.9365887], 16);
				/*$.ajax({
					async:true,
					//cache:false,
					context:g_Main,
					//data:{overview: 'false',steps: true},
					dataType:'json',
					type: 'GET',
					url: g_Main.options.domain + '/BAISTMonitor/osmpostionZHJDataBase.action',
					success: function(data,status,xhr){
						if(data.status == 1){
							this.updateCenter(data.data);
							//map.setView(L.latLng([data.data.latitude, data.data.longitude]), this.options.mapDefaultLevel);				
						}
					}
				});*/
			};
			this.updateDevice = function(extObj){
				var obj = extObj;
				var pt = {
					uuid: obj.uuid,
					title: obj.name,
					id: obj.cardNum,
					position: obj.latitude.toString() + ',' + obj.longitude.toString(),
					lat: obj.latitude,
					lng: obj.longitude,
					time: obj.gpstime,
					type : 'device',
					detail:'',
					terminalAeraName: obj.terminalAeraName,
					zone: obj.zone,
					numInZone: obj.numInZone,
					typeDesc: obj.typeDesc
				};
				//var processed_pt = this._postionbar._nearest(pt);
				/*if(!this._fenceBar.isInFences(pt)){
					this._notify(pt.title + '(' + pt.id + ')' + this.local[this.options.lng].outFence);
				}*/

				var f_ret = this._fenceBar.checkFences(pt);
				if(f_ret.outFences.length > 0){
					this._notify(pt.title + '(' + pt.id + ')' + this.local[this.options.lng].outFence + ': ' + f_ret.outFences.join());
				}
				map.setView(L.latLng(pt.lat,pt.lng),this.options.mapDefaultLevel);
				this._postionbar.updatePoint(pt,true);
				this._postionbar.updateTrack(pt.id,[pt],true,true,true);
			};
			this.updateCenter = function(extObj){
				var obj = extObj;
				var pt = {
					uuid: obj.uuid,
					title: obj.name == null ? '指挥机' : obj.name,
					id: obj.number == null ? '000000': obj.number,
					position: obj.latitude.toString() + ',' + obj.longitude.toString(),
					lat: obj.latitude,
					lng: obj.longitude,
					time: obj.gpstime,
					type : 'center',
					detail:''
				};
				//var processed_pt = this._postionbar._nearest(pt);
				map.setView(L.latLng([pt.lat,pt.lng]),this.options.mapDefaultLevel);
				/*if(!this._fenceBar.isInFences(pt)){
					this._notify(pt.title + '(' + pt.id + ')' + this.local[this.options.lng].outFence);
				}*/
				var f_ret = this._fenceBar.checkFences(pt);
				if(f_ret.outFences.length > 0){
					this._notify(pt.title + '(' + pt.id + ')' + this.local[this.options.lng].outFence) + ': ' + f_ret.outFences.join();
				}
				this._postionbar.updatePoint(pt,false);
				this._postionbar.updateTrack(pt.id,[pt],true,true,true);
			};
			this._loadcenter = function(){
				/*$.ajax({
					async:true,
					//cache:false,
					context:this,
					//data:{overview: 'false',steps: true},
					dataType:'json',
					type: 'GET',
					url: this.options.domain + '/BAISTMonitor/osmpostionZHJDataBase.action',
					success: function(data,status,xhr){
						if(data.status == 1){
							this.updateCenter(data.data);
							map.setView(L.latLng([data.data.latitude, data.data.longitude]), this.options.mapDefaultLevel);				
						}
					}
				});*/
			}
			this._loadAllDevices = function(){
				$.ajax({
					async:true,
					//cache:false,
					context:this,
					//data:{overview: 'false',steps: true},
					dataType:'json',
					type: 'GET',
					url: this.options.domain + '/BAISTMonitor/getBdLocateList.action',
					success: function(data,status,xhr){
						if(true || status == 1){
							for(var i = 0; i < data.data.length;i++){
								var obj = data.data[i];
								if(obj.gpstime != ''){
									this.updateDevice(obj);
								}
							}				
						}
					}
				});
			};
		    
		    this._onNavbarRoadnet = function (state) {
		        if (state == 1) {
		            this._streetMapLayerGroup.addTo(map).setZIndex(1);
		        } else {
		            this._streetMapLayerGroup.remove();
		        }
		    };

		    this._onNavbarSatmap = function (state) {
		        if (state == 1) {
		            this._vectorBaseMapLayer.remove();
		            this._satBaseMapLayerGroup.addTo(map).setZIndex(0);
		        } else {
		            this._satBaseMapLayerGroup.remove();
		            this._vectorBaseMapLayer.addTo(map).setZIndex(0);
		            if (map.hasLayer(this._streetMapLayerGroup)) {
		                this._streetMapLayerGroup.remove();
		            }
		        }
		    };

		    this._onNavbarDevMan = function (state) {
				if(state == 1){
					this._loadAllDevices();
					this._postionbar.addTo(map);
				}else{
					this._postionbar.remove();
				}
		    };
		    this._onNavbarRoute = function (state) {
				if(state == 1){
				    this._routeBar.addTo(map);
				}else{
				    this._routeBar.remove();
				   
				}
		    };
		    this._onNavbarFence = function (state) {
				if(state == 1){
				    this._fenceBar.addTo(map);
				}else{
				    this._fenceBar.remove();
				}
		    };
		    this._onNavbarTool = function (state) {
				if(state == 1){
					this._measureRuler.addTo(map);
					this._measureArea.addTo(map);
				}else{
					this._measureArea.remove();
					this._measureRuler.remove();
				}
		    };
		    this._onNavbarSetting = function(){
		    	this.showSettingDialog();
		    };
		    this._onNavbarTest = function () {
				var lat = 34.3468421,lon = 108.9365887;
				/*for(var i = 0;i < 10;i++){
					var pt = { id: '1234' ,position: (lat + this.index).toString() + ',108.9365887',time: '2019-05-14 9:23',type : i == 0 ? 'centre' : 'device',detail:'12245675'};
					var p = this._postionbar._nearest(pt);
					//console.log(this._fenceBar.isInFences(pt));
					this._postionbar.updateItem(p);
					this.index += 0.001;
					//this._postionbar._route([{position:'34.348786,108.933649'},{position:'34.359344,108.925323'}]);
				}*/
				var devs_json = '{"data":[{"altitude":"0.0","cardNum":"142342","gpstime":"2019-04-02 16:19:55","latitude":"34.1595","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142342","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"朝鲜","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"91623a81-5d61-44bd-a69b-86c496989556","zone":"---"},{"altitude":"0.0","cardNum":"142339","gpstime":"2019-05-29 11:23:09","latitude":"34.1595","latitudefloat":34.1595,"longitude":"108.97707","longitudefloat":108.97707,"name":"142339","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"实时","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"94b9dd71-09b8-4be2-940d-a346ddc3ce0e","zone":"---"},{"altitude":"0.0","cardNum":"142338","gpstime":"2019-05-29 16:54:54","latitude":"34.159527","latitudefloat":34.159527,"longitude":"108.97713","longitudefloat":108.97713,"name":"142338","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"陕西省","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"d5321496-85a9-486e-9070-a459ee4f3c4d","zone":"---"},{"altitude":"0.0","cardNum":"142346","gpstime":"2019-04-01 14:22:43","latitude":"34.1595","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142346","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"平壤大使馆","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"f3639dbd-36a0-4959-8b99-dff890d3d87f","zone":"---"}],"message":null,"status":0}';
				var centre_json = '{"data":{"altitude":"0","gpstime":"2019-05-29 17:28:57.0","latitude":"34.1595","longitude":"108.977104","name":null,"number":null,"uuid":"b2d72703-c4e6-4457-ad65-e4c4c7b66142"},"message":"定位成功","status":1}';
				
				var devs = JSON.parse(devs_json).data;
				var center = JSON.parse(centre_json).data;
				for(var i = 0,len = devs.length;i < len;i++){var dev = devs[i];this.updateDevice(dev);}
				this.updateCenter(center);


				var devs_json = '{"data":[{"altitude":"0.0","cardNum":"142342","gpstime":"2019-04-02 16:19:55","latitude":"34.1605","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142342","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"朝鲜","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"91623a81-5d61-44bd-a69b-86c496989556","zone":"---"},{"altitude":"0.0","cardNum":"142339","gpstime":"2019-05-29 11:23:09","latitude":"34.1605","latitudefloat":34.1595,"longitude":"108.97707","longitudefloat":108.97707,"name":"142339","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"实时","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"94b9dd71-09b8-4be2-940d-a346ddc3ce0e","zone":"---"},{"altitude":"0.0","cardNum":"142338","gpstime":"2019-05-29 16:54:54","latitude":"34.160527","latitudefloat":34.159527,"longitude":"108.97713","longitudefloat":108.97713,"name":"142338","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"陕西省","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"d5321496-85a9-486e-9070-a459ee4f3c4d","zone":"---"},{"altitude":"0.0","cardNum":"142346","gpstime":"2019-04-01 14:22:43","latitude":"34.1605","latitudefloat":34.1595,"longitude":"108.97702","longitudefloat":108.97702,"name":"142346","numInZone":"---","remark":"","sjState":0,"sjStatedesc":"未安装，未引导","status":0,"terminalAeraId":null,"terminalAeraName":"平壤大使馆","txLevel":null,"type":1,"typeDesc":"区内终端","tzState":0,"tzStatedesc":"出厂状态，未安装任何数据","uuid":"f3639dbd-36a0-4959-8b99-dff890d3d87f","zone":"---"}],"message":null,"status":0}';
				var centre_json = '{"data":{"altitude":"0","gpstime":"2019-05-29 17:28:57.0","latitude":"34.1605","longitude":"108.977104","name":null,"number":null,"uuid":"b2d72703-c4e6-4457-ad65-e4c4c7b66142"},"message":"定位成功","status":1}';
				
				var devs = JSON.parse(devs_json).data;
				var center = JSON.parse(centre_json).data;
				for(var i = 0,len = devs.length;i < len;i++){var dev = devs[i];this.updateDevice(dev);}
				//this.updateCenter(center);
				
				/*var points = [];
				for(var i = 0; i< 100;i++){
					points.push({lat:lat + 0.001 * i,lng:lon,position:('{0},{1}').replace('{0}',lat + 0.001 * i).replace('{1}',lon)});
				}
				var ret = this._postionbar._routeMatch(points);*/
				
				/*var div = L.DomUtil.create('div','');
				$(div).css('z-index','1');
				$('body').append(div);

				$(div).dialog({ autoOpen: true ,title: 'test',modal:true});*/
				
		    };
			this._notify = function(msg){
				$.notify({
					//title: 'hahha',
					icon: 'fa fa-exclamation-triangle',
					message: msg,
				}, {
					type:'danger',
					/*animate: {
						enter: 'animated rollIn',
						exit: 'animated rollOut'
					},*/
					offset: {
						x: 70,
						y: 60
					},
					//delay:1000,
					newest_on_top: true
				});
			};
			this._tranPosition = function(objs){
				for(var i in objs){
					var segs = objs[i].position.split(',');
					objs[i].lat = segs[0];
					objs[i].lng = segs[1];
				}
				return objs;
			};
			this.showSettingDialog = function(){
				var content = L.DomUtil.create('div','');
				$(content).css('z-index','100');

				var div = L.DomUtil.create('div','input-group',content);
				var span = L.DomUtil.create('span','input-group-addon',div);
				span.innerHTML = this.local[this.options.lng].language_sel_lab;
				var input = L.DomUtil.create('select','form-control',div);
				$(input).attr({'id':'map_setting_dialog_lng_input'})
				$(input).append("<option>" +this.local[this.options.lng].language_sel_item_cn+ "</option>")
				$(input).append("<option>" +this.local[this.options.lng].language_sel_item_en+ "</option>")
				//$(input).val();

				$(content).dialog({ 
					autoOpen: true ,
					title: this.local[this.options.lng].setting_dlg_title,
					modal:true,
					resizable:false,
					buttons:[{
						text:this.local[this.options.lng].setting_dlg_btn_save,
						click:function(){
							var lng = $('#map_setting_dialog_lng_input').val();
							if(lng == g_Main.local[g_Main.options.lng].language_sel_item_cn){
								g_Main.options.lng = 'cn';
							}else if(lng == g_Main.local[g_Main.options.lng].language_sel_item_en){
								g_Main.options.lng = 'en';
							}

							g_Main._saveDataToServer();
							$(this).dialog('destroy');
						}
					},{
						text:this.local[this.options.lng].setting_dlg_btn_cancel,
						click:function(){
							$(this).dialog('destroy');
						}
					}]
				});
			};
		}


