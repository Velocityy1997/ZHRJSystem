L.Control.PostionBar = L.Control.extend({
    options: {
        position: 'topleft',
		lng:'en',
		useRoute:true,
		serverIP:'192.168.0.117'
    },
	local:{
		cn:{
			terminal_manage:'终端管理',
			serial:'编号：',
			latitude:'纬度：',
			longitude:'经度：',
			time:'时间：',
			track:'轨迹',
			point:'坐标',
			clean:'清除',
			expend:'设备列表',
			detail:"详细：",
			title:'名称：',
			typeDesc:'类型：'
			
		},
		en:{
			terminal_manage:'manage',
			serial:'serial：',
			latitude:'latitude：',
			longitude:'longitude：',
			time:'time：',
			track:'track',
			point:'coordinate',
			clean:'clean',
			expend:'device list',
			detail:"detail：",
			title:'name：',
			typeDesc:'type：'
		}
	},
    initialize: function (map,options) {
		if(options){
			L.Util.setOptions( this, options );
		}
        this._items = new W_Map();
		this._tracksLayer = L.layerGroup();
		this._pointsLayer = L.markerClusterGroup();
		this._pointsLayerMiniMap = L.markerClusterGroup();

        this.container = L.DomUtil.create("div", 'postionbar-container');
		L.DomEvent.disableClickPropagation(this.container);
		L.DomEvent.disableScrollPropagation(this.container);
		this._createheader();
        this.ul = L.DomUtil.create("ul", 'list-group postionbar-ul-container-ul ', this.container);

		
		this._tracksLayer.addTo(map);
		this._pointsLayer.addTo(map);
		this._myMap = map;
        /*this.updateItem({ id: '000001' ,position: '30.306494, 120.117793',time: '2019-05-14 9:23'});
		this.updateItem({ id: '000001' ,position: '30.307494, 120.117793',time: '2019-05-14 9:28'});
		this.updateItem({ id: '000001' ,position: '30.308494, 120.117793',time: '2019-05-14 9:33'});
		this.updateItem({ id: '000002' ,position: '30.309494, 120.117793',time: '2019-05-14 9:38'});*/
        
    },
	setMiniMap: function(miniMap,groupLayer){
		this._miniMap = miniMap;
		this._miniMapGroupLayer = groupLayer;
		this._pointsLayerMiniMap.addTo(this._miniMapGroupLayer);
	},
    onAdd: function (map) {		
        return this.container;
    },
    onRemove: function (map) {

    },
    enable: function () {
        return this;
    },
    disable: function () {
        return this;
    },
    updateItem: function (obj) {
        if (!this._items.has(obj.id)) {
            var ui = this._createItem(obj);
            this._items.set(obj.id, {
				id: obj.id,
				ui_li: ui.li, 
				points: [obj],
				ui_track: null,
				ui_cur_mark: null,
				ui_cur_mark_mini_map: null,
				ui_his_mark: null,
				track_layer: L.layerGroup(),
				track_visible:true,
				point_visible:true,
				track_visible_ico:ui.track_ico,
				point_visible_ico:ui.point_ico
			});
        }else{
			var item = this._items.get(obj.id);
			if(item.points == null){
				item.points = [obj];
			}else{
				item.points.push(obj);
			}
		}

		var item = this._items.get(obj.id);
		if(item.points.length == 1){
			var segs = item.points[0].position.split(',');
			var ico_name = obj.type == 'centre' ?  'centerIco' : 'devIco';
			item.ui_cur_mark = L.marker([segs[0],segs[1]],{ icon: this._createIcon(ico_name)}).bindPopup(this._createDetail(item.points[0])).addTo(this._pointsLayer);
			item.ui_cur_mark_mini_map = L.marker([segs[0],segs[1]],{ icon: this._createIcon(ico_name)}).bindPopup(this._createDetail(item.points[0])).addTo(this._pointsLayerMiniMap);
		}else{
			var psegs = item.points[item.points.length - 2].position.split(',');
			var segs = item.points[item.points.length - 1].position.split(',');
			item.ui_cur_mark.setLatLng([segs[0],segs[1]]).setPopupContent(this._createDetail(item.points[item.points.length - 1]));
			item.ui_cur_mark_mini_map.setLatLng([segs[0],segs[1]]).setPopupContent(this._createDetail(item.points[item.points.length - 1]));

			////
			var additionPoints = this.options.useRoute ? this._route([item.points[item.points.length - 2],item.points[item.points.length - 1]]) : [];
			////

			if(item.ui_track == null){
				var pa = [[psegs[0],psegs[1]]];
				for(var i = 0,len = additionPoints.length;i<len;i++){
					var segs = additionPoints[i].position.split(',');//console.log(p);
					pa.push([segs[0],segs[1]]);
				}
				pa.push([segs[0],segs[1]]);//console.log(pa);
				item.ui_track = L.polyline(pa,{color: 'red'});
				item.ui_track.addTo(item.track_layer);
				if(item.track_visible){
					item.track_layer.addTo(this._tracksLayer);
				}
			}else{
				for(var i = 0,len = additionPoints.length;i<len;i++){
					var segs = additionPoints[i].position.split(',');//console.log(p);
					item.ui_track.addLatLng([segs[0],segs[1]]);
				}
				item.ui_track.addLatLng([segs[0],segs[1]]);
			}

			var his_mk = L.circleMarker([psegs[0],psegs[1]],{radius: 4,color: 'red',fillColor: 'red',fill: true,fillOpacity: 1}).bindPopup(this._createDetail(item.points[item.points.length - 2])).addTo(item.track_layer);
			if(item.ui_his_mark == null){
				item.ui_his_mark = [his_mk];
			}else{
				item.ui_his_mark.push(his_mk);
			}
		}
    },
	_createheader: function(){
		var ul = L.DomUtil.create('ul', 'list-group', this.container);
		ul.style.margin = '0px';
		var li = L.DomUtil.create("li", 'list-group-item list-group-item-primary', ul);
		var div = L.DomUtil.create("div", 'postionbar-li-item', li);
		var ico = L.DomUtil.create("i", 'fa fa-user-circle', div);
		ico.setAttribute('aria-hidden', 'true');

		var lb = L.DomUtil.create('lable', 'postionbar-li-item-lable', div);
        lb.innerHTML = this.local[this.options.lng].terminal_manage;

		ico = L.DomUtil.create("i", 'fa fa-times postionbar-li-item-icon-p', div);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_fun', 'clr');
		this._setTooltip(ico,this.local[this.options.lng].clean);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this._onLiBtn(null,e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-map-marker postionbar-li-item-icon-p text-primary', div);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_fun', 'point');
		ico.setAttribute('obj_sta', 'view');
		this._setTooltip(ico,this.local[this.options.lng].point);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this._onLiBtn(null,e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-road postionbar-li-item-icon-p text-primary', div);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_fun', 'track');
		ico.setAttribute('obj_sta', 'view');
		this._setTooltip(ico,this.local[this.options.lng].track);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this._onLiBtn(null,e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-bars postionbar-li-item-icon-p text-primary', div);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_fun', 'devices');
		ico.setAttribute('obj_sta', 'view');
		this._setTooltip(ico,this.local[this.options.lng].expend);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this._onLiBtn(null,e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);
	},
    _createItem: function(obj){
        var li = L.DomUtil.create("li", 'list-group-item list-group-item-success', this.ul);
        var div = L.DomUtil.create("div", 'postionbar-li-item', li);
        var ico = L.DomUtil.create("i", 'fa fa-user', div);
        ico.setAttribute('aria-hidden', 'true');
		div.setAttribute('obj_id', obj.id);
		if(obj.type == 'centre'){
			L.DomUtil.addClass(ico,'text-danger');
		}
        var lb = L.DomUtil.create('lable', 'postionbar-li-item-lable', div);
	lb.setAttribute('obj_id', obj.id);
        lb.innerHTML = obj.title + ' ( ' + obj.id + ' )';
		var ret_ui = {li:li};
		L.DomEvent.on(div, 'dblclick', function (e) {
		    L.DomEvent.stop(e);
			var item = this._items.get(e.srcElement.getAttribute('obj_id'));
			this._myMap.panTo(item.ui_cur_mark.getLatLng());
			item.ui_cur_mark.openPopup();
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-times postionbar-li-item-icon-p text-dark', div);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_id', obj.id);
		ico.setAttribute('obj_fun', 'clr');
		this._setTooltip(ico,this.local[this.options.lng].clean);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this._onLiBtn(this._items.get(e.srcElement.getAttribute('obj_id')),e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-map-marker postionbar-li-item-icon-p text-primary', div);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_id', obj.id);
		ico.setAttribute('obj_fun', 'point');
		this._setTooltip(ico,this.local[this.options.lng].point);
		ret_ui.point_ico = ico;
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this._onLiBtn(this._items.get(e.srcElement.getAttribute('obj_id')),e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-road postionbar-li-item-icon-p text-primary', div);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_id', obj.id);
		ico.setAttribute('obj_fun', 'track');
		this._setTooltip(ico,this.local[this.options.lng].track);
		ret_ui.track_ico = ico;
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
		    this._onLiBtn(this._items.get(e.srcElement.getAttribute('obj_id')),e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);
        return ret_ui;
    },
    deleteItem: function (id) {
        if (this._items.has(obj.id)) {
            this.ul.removeChild(this.items[id].ui);
            this._items.remove(obj.id);
        }
    },
	_createDetail: function(obj){
		var div = L.DomUtil.create('div','container','');
		var p = L.DomUtil.create('p','text-left font-weight-bold',div);
		var content = "";
		if(obj.hasOwnProperty('id'))content += this.local[this.options.lng].serial + obj.id + "<br/>";
		if(obj.hasOwnProperty('title'))content += this.local[this.options.lng].title + obj.title + "<br/>";
		if(obj.hasOwnProperty('typeDesc'))content += this.local[this.options.lng].typeDesc + obj.typeDesc + "<br/>";
		if(obj.hasOwnProperty('position'))content += this.local[this.options.lng].longitude + obj.position.split(',')[1] + "°<br/>" + this.local[this.options.lng].latitude + obj.position.split(',')[0] + "°<br/>";
		if(obj.hasOwnProperty('time'))content += this.local[this.options.lng].time + obj.time + "<br/>";
		if(obj.hasOwnProperty('detail'))content += this.local[this.options.lng].detail + obj.detail + "<br/>";
		p.innerHTML = content;
		return div;
	},
	_onLiBtn: function(obj,fun,htmlElement){
		if(fun == 'clr'){
			if(obj == null){
				for (var i = 0, len = this._items.length;i < len; i++){ // 遍历Map
					obj = this._items.getArray()[i][1];
					obj.track_layer.clearLayers();
					obj.ui_track = null;
					obj.ui_his_mark = null;
					if(obj.ui_cur_mark != null){
						this._pointsLayer.removeLayer(obj.ui_cur_mark);
						obj.ui_cur_mark = null;
					}
					if(obj.ui_cur_mark_mini_map != null){
						this._pointsLayer.removeLayer(obj.ui_cur_mark_mini_map);
						obj.ui_cur_mark_mini_map = null;
					}
					obj.points = null;
				}
			}else{
				obj.track_layer.clearLayers();
				obj.ui_track = null;
				obj.ui_his_mark = null;
				if(obj.ui_cur_mark != null){
					this._pointsLayer.removeLayer(obj.ui_cur_mark);
					obj.ui_cur_mark = null;
				}
				if(obj.ui_cur_mark_mini_map != null){
					this._pointsLayer.removeLayer(obj.ui_cur_mark_mini_map);
					obj.ui_cur_mark_mini_map = null;
				}
				obj.points = null;
			}
		}else if(fun == 'point'){
			if(obj == null){
				var sta = htmlElement.getAttribute('obj_sta');
				if(sta == 'view'){
					htmlElement.setAttribute('obj_sta','hide');
					for (var i = 0, len = this._items.length;i < len; i++){// 遍历Map
						obj = this._items.getArray()[i][1];
						obj.point_visible = false;
						if(obj.ui_cur_mark != null){
							this._pointsLayer.removeLayer(obj.ui_cur_mark);
						}
						if(obj.ui_cur_mark_mini_map != null){
							this._pointsLayerMiniMap.removeLayer(obj.ui_cur_mark_mini_map);
						}
						L.DomUtil.removeClass(obj.point_visible_ico,'text-primary');
					}
					
					L.DomUtil.removeClass(htmlElement,'text-primary');
				}else{
					htmlElement.setAttribute('obj_sta','view');
					for (var i = 0, len = this._items.length;i < len; i++){// 遍历Map
						obj = this._items.getArray()[i][1];
						obj.point_visible = true;
						if(obj.ui_cur_mark != null){
							this._pointsLayer.addLayer(obj.ui_cur_mark);
						}
						if(obj.ui_cur_mark_mini_map != null){
							this._pointsLayerMiniMap.addLayer(obj.ui_cur_mark_mini_map);
						}
						L.DomUtil.addClass(obj.point_visible_ico,'text-primary');
					}
					
					L.DomUtil.addClass(htmlElement,'text-primary');
				}
			}else{
				if(obj.point_visible){
					obj.point_visible = false;
					if(obj.ui_cur_mark != null){
						this._pointsLayer.removeLayer(obj.ui_cur_mark);
					}
					if(obj.ui_cur_mark_mini_map != null){
						this._pointsLayerMiniMap.removeLayer(obj.ui_cur_mark_mini_map);
					}
					L.DomUtil.removeClass(htmlElement,'text-primary');
				}else{
					obj.point_visible = true;
					if(obj.ui_cur_mark != null){
						this._pointsLayer.addLayer(obj.ui_cur_mark);
					}
					if(obj.ui_cur_mark_mini_map != null){
						this._pointsLayerMiniMap.addLayer(obj.ui_cur_mark_mini_map);
					}
					L.DomUtil.addClass(htmlElement,'text-primary');
				}
			}
			
		}else if(fun == 'track'){
			if(obj == null){
				var sta = htmlElement.getAttribute('obj_sta');
				if(sta == 'view'){
					htmlElement.setAttribute('obj_sta','hide');
					for (var i = 0, len = this._items.length;i < len; i++){// 遍历Map
						obj = this.items.getArray()[i][1];
						obj.track_visible = false;
						if(obj.track_layer != null){
							obj.track_layer.remove();
						}
						L.DomUtil.removeClass(obj.track_visible_ico,'text-primary');
					}
					
					L.DomUtil.removeClass(htmlElement,'text-primary');
				}else{
					htmlElement.setAttribute('obj_sta','view');
					for (var i = 0, len = this._items.length;i < len; i++){// 遍历Map
						obj = this._items.getArray()[i][1];
						obj.track_visible = true;
						if(obj.track_layer != null){
							obj.track_layer.addTo(this._tracksLayer);
						}
						L.DomUtil.addClass(obj.track_visible_ico,'text-primary');
					}
					
					L.DomUtil.addClass(htmlElement,'text-primary');
				}
			}else{
				if(obj.track_visible){
					obj.track_visible = false;
					if(obj.track_layer != null){
						obj.track_layer.remove();
					}
					L.DomUtil.removeClass(htmlElement,'text-primary');
				}else{
					obj.track_visible = true;
					if(obj.track_layer != null){
						obj.track_layer.addTo(this._tracksLayer);
					}
					L.DomUtil.addClass(htmlElement,'text-primary');
				}
			}
		}else if(fun == 'devices'){
			var sta = htmlElement.getAttribute('obj_sta');
				if(sta == 'view'){
					htmlElement.setAttribute('obj_sta','hide');
					$(this.ul).hide(100);
					L.DomUtil.removeClass(htmlElement,'text-primary');
				}else{
					htmlElement.setAttribute('obj_sta','view');
					$(this.ul).show(100);
					L.DomUtil.addClass(htmlElement,'text-primary');
				}
		}
	},
	_createIcon: function(name)
	{
		var icon = null;
		switch(name)
		{
		case "RedPoint":
			icon = L.icon({
			    iconUrl: 'images/gps_red_map_48px.png',
			    iconSize: [48, 48],
			    iconAnchor: [24, 48],
			    popupAnchor: [0, -52],
			    shadowUrl: 'images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [14, 41]
			});
			break;
		case "BluePoint":
			icon = L.icon({
			    iconUrl: 'images/gps_blue_map_48px.png',
			    iconSize: [48, 48],
			    iconAnchor: [24, 48],
			    popupAnchor: [0, -52],
			    shadowUrl: 'images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [14, 41]
		});
			break;
		case "BlueCentrePoint":
			icon = L.icon({
			    iconUrl: 'images/gps_center_blue_map_48px.png',
			    iconSize: [48, 48],
			    iconAnchor: [24, 48],
			    popupAnchor: [0, -52],
			    shadowUrl: 'images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [14, 41]
		});
			break;
		case "RedPhone":
			icon = L.icon({
			    iconUrl: 'images/gps_red_phone_48px.png',
			    iconSize: [48, 48],
			    iconAnchor: [10, 48],
			    popupAnchor: [0, -52],
			    shadowUrl: 'images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [16, 41]
		});
			break;
		case "BluePhone":
			icon = L.icon({
			    iconUrl: 'images/gps_blue_phone_48px.png',
			    iconSize: [48, 48],
			    iconAnchor: [10, 48],
			    popupAnchor: [0, -52],
			    shadowUrl: 'images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [16, 41]
		});	
			break;
		case "RedCar":
			icon = L.icon({
			    iconUrl: 'images/gps_red_car_48px.png',
			    iconSize: [48, 48],
			    iconAnchor: [6, 48],
			    popupAnchor: [0, -52],
			    shadowUrl: 'images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [12, 41]
		});
			break;
		case "BlueCar":
			icon = L.icon({
			    iconUrl: 'images/gps_blue_car_48px.png',
			    iconSize: [48, 48],
			    iconAnchor: [10, 48],
			    popupAnchor: [0, -52],
			    shadowUrl: 'images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [16, 41]
		});
			break;
		case "devIco":
			icon = L.icon({
			    iconUrl: 'images/marker-gold.png',
			    iconSize: [21, 25],
			    iconAnchor: [9, 25],
			    popupAnchor: [0, -25],
			    shadowUrl: 'images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [14, 41]
		});
			break;
		case "centerIco":
			icon = L.icon({
			    iconUrl: 'images/wujiaoxing.jpg',
			    iconSize: [48, 48],
			    iconAnchor: [12, 43],
			    popupAnchor: [0, -52],
			    shadowUrl: 'images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [0, 51]
		});
			break;
		}
		return icon;
	},
	_nearest:function(obj){
		var segs = obj.position.split(',');
		$.ajax({
			async:false,
			//cache:false,
			context:obj,
			data:{number: 1},
			dataType:'json',
			type: 'GET',
			url: ('http://{serverIP}:5000/nearest/v1/driving/').replace('{serverIP}',this.options.serverIP) + segs[1] + ',' + segs[0] + '.json',
			success: this._onNearestRespon,
		});
		return obj;
	},
	_onNearestRespon: function(data,status,xhr){
		if(status == 'success'){
			this.position = data.waypoints[0].location[1] + ',' + data.waypoints[0].location[0];
		}
	},
	_route:function(objs){
		var len = objs.length;
		var points = '';
		for(var i =0;i<len;i++){
			var segs = objs[i].position.split(',');
			points += segs[1] + ',' + segs[0];
			if(i < len - 1){
				points += ';'
			}
		}
		$.ajax({
			async:false,
			//cache:false,
			context:objs,
			data:{overview: 'false',steps: true},
			dataType:'json',
			type: 'GET',
			url: ('http://{serverIP}:5000/route/v1/driving/').replace('{serverIP}',this.options.serverIP) + points,
			success: this._onRouteRespon,
		});
		return objs;
	},
	_onRouteRespon: function(data,status,xhr){
		this.pop();
		this.pop();
		if(status == 'success' && data.code == 'Ok'){//console.log(data);
			/*var waypoints = data.waypoints;
			var len = waypoints.length - 0;
			for(var i=0;i < len;i++){
				this.push({position:waypoints[i].location[1] + ',' + waypoints[i].location[0]})
			}*/
			var steps = data.routes[0].legs[0].steps;
			for(i in steps){
				for(j in steps[i].intersections){
					this.push({position:steps[i].intersections[j].location[1] + ',' + steps[i].intersections[j].location[0]});
				}
			}
		}
	},
	_setTooltip:function(htmlElement,title){
		htmlElement.setAttribute('data-toggle','tooltip');
		htmlElement.setAttribute('data-placement','bottom');
		htmlElement.setAttribute('title',title);
	}
});
