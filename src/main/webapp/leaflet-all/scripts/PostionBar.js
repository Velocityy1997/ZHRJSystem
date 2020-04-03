L.Control.PostionBar = L.Control.extend({
    options: {
        position: 'topleft',
		lng:'en',
		useRoute:true,
		serverIP:'192.168.0.117',
		icoCheckedClass:'text-primary',
		icoUncheckedClass:'text-danger',
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
			typeDesc:'类型：',
			historyTrack:'历史轨迹',
			realtimeTrack:'实时轨迹',
			historyTrackTimeline:'时间范围',
			historyDialogStartTime:'起始时间：',
			historyDialogEndTime:'结束时间：',
			historyDialogStartTimeHolder:'起始时间',
			historyDialogEndTimeHolder:'结束时间',
			dialogOk:'确定',
			dialogCancel:'取消',
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
			typeDesc:'type：',
			realtimeTrack:'real time track',
			historyTrackTimeline:'time range',
			historyTrack:'history track',
			historyDialogStartTime:'start time：',
			historyDialogEndTime:'end&nbsp&nbsp time：',
			historyDialogStartTimeHolder:'start time',
			historyDialogEndTimeHolder:'end&nbsp&nbsp time',
			dialogOk:'&nbsp&nbspOk&nbsp&nbsp',
			dialogCancel:'Cancel',
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
		this._createListHeader();
        this.ul = L.DomUtil.create("ul", 'list-group postionbar-ul-container-ul ', this.container);

		
		this._tracksLayer.addTo(map);
		this._pointsLayer.addTo(map);
		this._myMap = map;
        /*this.updateItem({ id: '000001' ,position: '30.306494, 120.117793',time: '2019-05-14 9:23'});
		this.updateItem({ id: '000001' ,position: '30.307494, 120.117793',time: '2019-05-14 9:28'});
		this.updateItem({ id: '000001' ,position: '30.308494, 120.117793',time: '2019-05-14 9:33'});
		this.updateItem({ id: '000002' ,position: '30.309494, 120.117793',time: '2019-05-14 9:38'});*/
        g_PositionBar = this;
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
    createItemModel: function (obj) {
		var item = null;
        if (!this._items.has(obj.id)) {
            var ui = this._createListItem(obj);
			item = {
				uuid:obj.uuid,
				id: obj.id,
				title:obj.title,
				type:obj.type,
				ui_li: ui.li, 
				ui_track: null,
				ui_history:null,
				ui_cur_mark: null,
				ui_cur_mark_mini_map: null,
				ui_track_layer: L.layerGroup(),
				ui_history_layer:L.layerGroup(),
				track_last_point: null,
				track_visible:false,
				point_visible:false,
				history_visible:false,
				track_visible_ico:ui.track_ico,
				point_visible_ico:ui.point_ico,
				history_visible_ico:ui.history_track_ico,
				history_dlg_st:{},
				track_dlg_st:{}
			};
			if(item.type == 'center'){
			
			}else if(item.type == 'device'){
				item.terminalAeraName = obj.terminalAeraName;
				item.zone = obj.zone;
				item.numInZone = obj.numInZone;
				item.typeDesc = obj.typeDesc;
			}
			this._insertToList(ui.li);
            this._items.set(obj.id, item);
        }else{
			item = this._items.get(obj.id);
		}
		return item;
    },
	deleteItemModel: function (id) {
        var item = this._items.get(id);
        if(item != null){
			this.ul.removeChild(item.ui_li);
			this.eraserPoint(item.id);
			//this.eraserTrack(item.id);
			if(item.history_visible){
				this.closeHistoryDialog(id);
			}
			if(item.track_visible){
				this.closeTrackDialog(id);
			}
            this._items.remove(item.id);
		}
    },
	deleteAllItemModel: function(){
		var items = this._items.getArray();
		for(var i in items){
			var item = items[i][1];
			item.ui_li.parentNode.removeChild(item.ui_li);
			this.eraserPoint(item.id);
			if(item.history_visible){
				this.closeHistoryDialog(item.id);
			}
			if(item.track_visible){
				this.closeTrackDialog(item.id);
			}
			//this.eraserTrack(item.id);
		}
		this._items.clear();
		
	},
	updateHistory: function(id,points,nearest,route){
		var item = this._items.get(id);
		if(item != null && item.history_visible){
			if(points.length > 2){
				if(nearest){
					for(var i in points){
						this._nearest(points[i]);
					}
				}

				item.ui_history = null;
				item.ui_history_layer.clearLayers();

				var addPoints = [];
				addPoints.push(points[0]);
				for(var i = 0, len = points.length - 1;i<len;i++){
					if(route){
						var pts = this._route([points[i],points[i + 1]]);
						for(var i in pts){
							addPoints.push(pts[i]);
						}
					}
					addPoints.push(points[i + 1]);
				}

				if(item.ui_history== null){
					item.ui_history = L.polyline(addPoints,{color: 'red'});
					item.ui_history_layer.addLayer(item.ui_history);
				}else{
					for(var i in addPoints){
						item.ui_history.addLatLng(L.latLng([addPoints[i].lat,addPoints[i].lng]));
					}
				}

				for(var i in points){
					var his_mk = L.circleMarker(L.latLng([points[i].lat,points[i].lng]),{radius: 4,color: 'red',fillColor: 'red',fill: true,fillOpacity: 1}).bindPopup(this._createDetail(points[i])).addTo(item.ui_track_layer);
					item.ui_history_layer.addLayer(his_mk);
				}

				/*if(item != null && !item.track_visible){
					item.track_visible = true;
					this._tracksLayer.addLayer(item.ui_track_layer);
				}*/
			}
		}
	},
	updateTrack: function(id,points,append,nearest,route){
		var item = this._items.get(id);
		if(item != null && item.track_visible){
			if(points.length > 2 || (item.track_last_point != null && append)){
				if(nearest){
					for(var i in points){
						this._nearest(points[i]);
					}
				}

				if(!append){
					item.track_last_point = null;
					item.ui_track = null;
					item.ui_track_layer.clearLayers();
				}

				var addPoints = [];
				var drawLastPoint = false;
				if(item.ui_track_layer.getLayers().length == 0){
					drawLastPoint = true;
				}
				if(append && item.track_last_point != null && route){
					var pts = this._route([item.track_last_point,points[0]]);
					for(var i in pts){
						addPoints.push(pts[i]);
					}
				}
				addPoints.push(points[0]);
				for(var i = 0, len = points.length - 1;i<len;i++){
					if(route){
						var pts = this._route([points[i],points[i + 1]]);
						for(var i in pts){
							addPoints.push(pts[i]);
						}
					}
					addPoints.push(points[i + 1]);
				}

				if(item.ui_track == null){
					item.ui_track = L.polyline(addPoints,{color: 'red'});
					item.ui_track_layer.addLayer(item.ui_track);
				}else{
					for(var i in addPoints){
						item.ui_track.addLatLng(L.latLng([addPoints[i].lat,addPoints[i].lng]));
					}
				}

				if(drawLastPoint){
					var his_mk = L.circleMarker(L.latLng([item.track_last_point.lat,item.track_last_point.lng]),{radius: 4,color: 'red',fillColor: 'red',fill: true,fillOpacity: 1}).bindPopup(this._createDetail(item.track_last_point)).addTo(item.ui_track_layer);
				}
				for(var i in points){
					var his_mk = L.circleMarker(L.latLng([points[i].lat,points[i].lng]),{radius: 4,color: 'red',fillColor: 'red',fill: true,fillOpacity: 1}).bindPopup(this._createDetail(points[i])).addTo(item.ui_track_layer);
					item.ui_track_layer.addLayer(his_mk);
				}
				console.log(item.track_dlg_st);
				item.track_dlg_st.mapHandle.setView(L.latLng([points[points.length - 1].lat,points[points.length - 1].lng]),14);
				/*if(item != null && !item.track_visible){
					item.track_visible = true;
					this._tracksLayer.addLayer(item.ui_track_layer);
				}*/
			}
			item.track_last_point = points[points.length - 1];
			if(nearest){
				this._nearest(item.track_last_point);
			}
		}
	},
	eraserTrack: function(id){
		var item = this._items.get(id);
		if(item != null && item.track_visible){
			item.track_visible = false;
			this._tracksLayer.removeLayer(item.ui_track_layer);
			this._isChecked(item.track_visible_ico, false);
		}
		if(item != null){
			item.ui_track_layer.clearLayers();
			item.ui_track = null;
			item.track_last_point = null;
		}
	},
	hideTrack:function(id){
		var item = this._items.get(id);
		if(item != null && item.track_visible){
			item.track_visible = false;
			this._tracksLayer.removeLayer(item.ui_track_layer);
			this._isChecked(item.track_visible_ico, false);
		}
	},
	showTrack:function(id){
		var item = this._items.get(id);
		if(item != null && !item.track_visible){
			item.track_visible = true;
			this._tracksLayer.addLayer(item.ui_track_layer);
			this._isChecked(item.track_visible_ico, true);
		}
	},
	showAllTracks:function(){
		var items = this._items.getArray();
		for(var i in items){
			var item = items[i][1];
			if(item != null && !item.track_visible){
				item.track_visible = true;
				this._tracksLayer.addLayer(item.ui_track_layer);
				this._isChecked(item.track_visible_ico, true);
			}
		}
	},
	hideAllTracks:function(){
		var items = this._items.getArray();
		for(var i in items){
			var item = items[i][1];
			if(item != null && item.track_visible){
				item.track_visible = false;
				this._tracksLayer.removeLayer(item.ui_track_layer);
				this._isChecked(item.track_visible_ico, false);
			}
		}
	},
	eraserAllTracks:function(){
		var items = this._items.getArray();
		for(var i in items){
			var item = items[i][1];
			if(item != null && item.track_visible){
				item.track_visible = false;
				this._tracksLayer.removeLayer(item.ui_track_layer);
				this._isChecked(item.track_visible_ico, false);
			}
			if(item != null){
				item.ui_track_layer.clearLayers();
				item.ui_track = null;
				item.track_last_point = null;
			}
		}
	},
	updatePoint: function(obj,nearest){
		var item = null;
		if(!this._items.has(obj.id)){
			item = this.createItemModel(obj);
		}
		else{
			item = this._items.get(obj.id);
		}
		if(nearest != undefined && nearest){
			this._nearest(obj);
		}
		if(item.ui_cur_mark == null && item.ui_cur_mark_mini_map == null){
			var ico_name = item.type == 'center' ?  'centerIco' : 'devIco';
			item.ui_cur_mark = L.marker(L.latLng([obj.lat,obj.lng]),{ icon: this._createIcon(ico_name)}).bindPopup(this._createDetail(obj));
			item.ui_cur_mark_mini_map = L.marker(L.latLng([obj.lat,obj.lng]),{ icon: this._createIcon(ico_name)}).bindPopup(this._createDetail(obj));
		}
		else{
			item.ui_cur_mark.setLatLng(L.latLng([obj.lat,obj.lng])).setPopupContent(this._createDetail(obj));
			item.ui_cur_mark_mini_map.setLatLng(L.latLng([obj.lat,obj.lng])).setPopupContent(this._createDetail(obj));
		}

		if(item != null && !item.point_visible){
			item.point_visible = true;
			this._pointsLayer.addLayer(item.ui_cur_mark);
			this._pointsLayerMiniMap.addLayer(item.ui_cur_mark_mini_map);
		}
	},
	eraserPoint:function(id){
		var item = this._items.get(id);
		if(item != null && item.point_visible){
			item.point_visible = false;
			this._pointsLayer.removeLayer(item.ui_cur_mark);
			this._pointsLayerMiniMap.removeLayer(item.ui_cur_mark_mini_map);
			this._isChecked(item.point_visible_ico, false);
		}
		if(item != null){
			item.ui_cur_mark = null;
			item.ui_cur_mark_mini_map = null;
		}
	},
	hidePoint:function(id){
		var item = this._items.get(id);
		if(item != null && item.point_visible){
			item.point_visible = false;
			this._pointsLayer.removeLayer(item.ui_cur_mark);
			this._pointsLayerMiniMap.removeLayer(item.ui_cur_mark_mini_map);
			this._isChecked(item.point_visible_ico, false);
		}
	},
	showPoint:function(id){
		var item = this._items.get(id);
		if(item != null && !item.point_visible){
			item.point_visible = true;
			this._pointsLayer.addLayer(item.ui_cur_mark);
			this._pointsLayerMiniMap.addLayer(item.ui_cur_mark_mini_map);
			this._isChecked(item.point_visible_ico, true);
		}
	},
	showAllPoints:function(){
		var items = this._items.getArray();
		for(var i in items){
			var item = items[i][1];
			if(!item.point_visible){
				item.point_visible = true;
				this._pointsLayer.addLayer(item.ui_cur_mark);
				this._pointsLayerMiniMap.addLayer(item.ui_cur_mark_mini_map);
				this._isChecked(item.point_visible_ico, true);
			}
		}
	},
	hideAllPoints:function(){
		var items = this._items.getArray();
		for(var i in items){
			var item = items[i][1];
			if(item.point_visible){
				item.point_visible = false;
				this._pointsLayer.removeLayer(item.ui_cur_mark);
				this._pointsLayerMiniMap.removeLayer(item.ui_cur_mark_mini_map);
				this._isChecked(item.point_visible_ico, false);
			}
		}
	},
	eraserAllPoints:function(){
		var items = this._items.getArray();
		for(var i in items){
			var item = items[i][1];
			if(item.point_visible){
				item.point_visible = false;
				this._pointsLayer.removeLayer(item.ui_cur_mark);
				this._pointsLayerMiniMap.removeLayer(item.ui_cur_mark_mini_map);
				this._isChecked(item.point_visible_ico, false);
			}
			item.ui_cur_mark = null;
			item.ui_cur_mark_mini_map = null;
		}
	},
	_createListHeader: function(){
		//var ul = L.DomUtil.create('ul', 'list-group', this.container);
		//ul.style.margin = '0px';
		//var li = L.DomUtil.create("li", 'list-group-item list-group-item-primary', ul);
		var div = L.DomUtil.create("div", 'postionbar-li-item list-group-item list-group-item-primary', this.container);
		var ico = L.DomUtil.create("i", 'fa fa-user-circle', div);
		ico.setAttribute('aria-hidden', 'true');

		var lb = L.DomUtil.create('lable', 'postionbar-li-item-lable', div);
        lb.innerHTML = this.local[this.options.lng].terminal_manage;

		ico = L.DomUtil.create("i", 'fa fa-times postionbar-li-item-icon-p', div);
        ico.setAttribute('aria-hidden', 'true');
		this._setTooltip(ico,this.local[this.options.lng].clean);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this.deleteAllItemModel();
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-map-marker postionbar-li-item-icon-p', div);
		$(ico).addClass(this.options.icoCheckedClass);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_sta', 'view');
		this._setTooltip(ico,this.local[this.options.lng].point);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			if(e.srcElement.getAttribute('obj_sta') == 'view'){
				e.srcElement.setAttribute('obj_sta','hide');
				this._isChecked(e.srcElement,false);
				this.hideAllPoints();
			}else{
				e.srcElement.setAttribute('obj_sta','view');
				this._isChecked(e.srcElement,true);
				this.showAllPoints();
			}
			
			//this._onLiBtn(null,e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		/*ico = L.DomUtil.create("i", 'fa fa-vine postionbar-li-item-icon-p', div);
		$(ico).addClass(this.options.icoCheckedClass);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_fun', 'track');
		ico.setAttribute('obj_sta', 'view');
		this._setTooltip(ico,this.local[this.options.lng].track);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			if(e.srcElement.getAttribute('obj_sta') == 'view'){
				e.srcElement.setAttribute('obj_sta','hide');
				this._isChecked(e.srcElement,false);
				this.hideAllTracks();
			}else{
				e.srcElement.setAttribute('obj_sta','view');
				this._isChecked(e.srcElement,true);
				this.showAllTracks();
			}
			//this._onLiBtn(null,e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);*/

		ico = L.DomUtil.create("i", 'fa fa-bars postionbar-li-item-icon-p', div);
		$(ico).addClass(this.options.icoCheckedClass);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_fun', 'devices');
		ico.setAttribute('obj_sta', 'view');
		this._setTooltip(ico,this.local[this.options.lng].expend);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			var htmlElement = e.srcElement;
			var sta = htmlElement.getAttribute('obj_sta');
			if(sta == 'view'){
				htmlElement.setAttribute('obj_sta','hide');
				$(this.ul).hide(100);
				this._isChecked(e.srcElement,false);
			}else{
				htmlElement.setAttribute('obj_sta','view');
				$(this.ul).show(100);
				this._isChecked(e.srcElement,true);
			}
			//this._onLiBtn(null,e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);
	},
	_insertToList: function(li){
		var set = this.ul.getElementsByTagName('li');
		var li_id = li.getAttribute('obj_id');
		var i = 0,len = set.length;
		for(;i < len;i++){
			var id = set[i].getAttribute('obj_id');
			if(li_id < id){
				this.ul.insertBefore(li,set[i]);break;
			}
		}
		if(i == len){
			this.ul.appendChild(li);
		}
	},
    _createListItem: function(obj){
        var li = L.DomUtil.create("li", 'list-group-item list-group-item-success');
		li.setAttribute('obj_id',obj.id);
        var div = L.DomUtil.create("div", 'postionbar-li-item', li);
        var ico = L.DomUtil.create("i", 'fa fa-user', div);
        ico.setAttribute('aria-hidden', 'true');
		div.setAttribute('obj_id', obj.id);
		if(obj.type == 'center'){
			L.DomUtil.addClass(ico,'text-danger');
		}

        var lb = L.DomUtil.create('lable', 'postionbar-li-item-lable', div);
		lb.setAttribute('obj_id', obj.id);
        lb.innerHTML = obj.title + ' ( ' + obj.id + ' )';
		var ret_ui = {li:li};
		L.DomEvent.on(div, 'dblclick', function (e) {
		    L.DomEvent.stop(e);
			var item = this._items.get(e.srcElement.getAttribute('obj_id'));
			this._myMap.setView(item.ui_cur_mark.getLatLng(),14);
			item.ui_cur_mark.openPopup();
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-times postionbar-li-item-icon-p text-dark', div);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_id', obj.id);
		//ico.setAttribute('obj_fun', 'clr');
		this._setTooltip(ico,this.local[this.options.lng].clean);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this.deleteItemModel(e.srcElement.getAttribute('obj_id'));
			//this._onLiBtn(this._items.get(e.srcElement.getAttribute('obj_id')),e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-map-marker postionbar-li-item-icon-p', div);
		//$(ico).addClass(this.options.icoCheckedClass);
		this._isChecked(ico,true);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_id', obj.id);
		//ico.setAttribute('obj_fun', 'point');
		this._setTooltip(ico,this.local[this.options.lng].point);
		ret_ui.point_ico = ico;
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			var item = this._items.get(e.srcElement.getAttribute('obj_id'));
			if(item.point_visible){
				this.hidePoint(item.id);
			}else{
				this.showPoint(item.id);
			}
			//this._onLiBtn(this._items.get(e.srcElement.getAttribute('obj_id')),e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-vine postionbar-li-item-icon-p', div);
		//$(ico).addClass(this.options.icoCheckedClass);
		this._isChecked(ico,false);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_id', obj.id);
		//ico.setAttribute('obj_fun', 'track');
		this._setTooltip(ico,this.local[this.options.lng].track);
		ret_ui.track_ico = ico;
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			var item = this._items.get(e.srcElement.getAttribute('obj_id'));
			if(!item.track_visible){
				this.showTrackDialog(item.id);
			}else{
				this.closeTrackDialog(item.id);
			}
			
		    //this._onLiBtn(this._items.get(e.srcElement.getAttribute('obj_id')),e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-history postionbar-li-item-icon-p', div);
		//$(ico).addClass(this.options.icoCheckedClass);
		this._isChecked(ico,false);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_id', obj.id);
		//ico.setAttribute('obj_fun', 'track');
		this._setTooltip(ico,this.local[this.options.lng].historyTrack);
		ret_ui.history_track_ico = ico;
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			var item = this._items.get(e.srcElement.getAttribute('obj_id'));
			if(!item.history_visible){
				this.showHistoryDialog(item.id);
			}else{
				this.closeHistoryDialog(item.id);
			}
		}, this);
        return ret_ui;
    },
	closeHistoryDialog:function(id){
		var item = this._items.get(id);
		item.history_visible = false;
		item.history_dlg_st.mapHandle.removeLayer(item.ui_history_layer);
		$(item.history_dlg_st.content).dialog('close');
		//document.body.removeChild(item.history_dlg_st.content);
		item.history_dlg_st = {};
		this._isChecked(item.history_visible_ico,false);
	},
	showHistoryDialog:function(id){
		var item = this._items.get(id);
		item.history_visible = true;
		this._isChecked(item.history_visible_ico,true);
		///////////////////////////////////////////////

		var content = L.DomUtil.create('div');
		item.history_dlg_st.content = content;
		$(content).attr('obj_id',id);
		$(content).css({
			'z-index':'10',
			'margin':'0px',
			'padding':'0px',
		});
		document.body.appendChild(content);
		var map_div = L.DomUtil.create('div','',content);
		item.history_dlg_st.mapDiv = map_div;
		$(content).dialog({
			autoOpen:true,
			title: this.local[this.options.lng].historyTrack + '---' + item.title + '(' + item.id + ')',
			minHeight: 200,
			minWidth:300,
			height:400,
			width:500,
			open: function( event, ui ) {
				var item = g_PositionBar._items.get($(event.target).attr('obj_id'));
				$(item.history_dlg_st.mapDiv).css({
					'width':event.target.style.width,
					'height':event.target.style.height,
				});
				var map_handle = L.map(item.history_dlg_st.mapDiv,{
					attributionControl: false,
					//zoomControl: false
				}).setView([34.3468421, 108.9365887],14);
				var vectorMapUri = 'http://{serverIP}/{server}/{z}/{x}/{y}.png'.replace('{server}',g_PositionBar.options.lng == 'en' ? 'eng' : 'hot').replace('{serverIP}',g_Main.options.serverIP);
				L.tileLayer(vectorMapUri, {
					minZoom: 0,
					maxZoom: 17
				}).addTo(map_handle).setZIndex(0);
				item.history_dlg_st.mapHandle = map_handle;

				var btn1 = L.easyButton('fa fa-search',function(){
					g_PositionBar.showHistorySearchPop(this.button,$(this.button).attr('id'));
				},g_PositionBar.local[g_PositionBar.options.lng].historyTrackTimeline,item.id);

				/*var btn2 = L.easyButton('fa fa-location-arrow',function(){
					//g_PositionBar.showHistorySearchPop(this.button,$(this.button).attr('id'));
				},'search',item.id);*/

				L.easyBar([btn1],{position:'topleft'}).addTo(map_handle);

				map_handle.addLayer(item.ui_history_layer);
			},
			resize: function( event, ui ) {
				var item = g_PositionBar._items.get($(event.target).attr('obj_id'));
				$(item.history_dlg_st.mapDiv).css({
					'width':event.target.style.width,
					'height':event.target.style.height,
				})
				item.history_dlg_st.mapHandle.invalidateSize(true);
			},
			close: function(event , ui){
				var item = g_PositionBar._items.get($(event.target).attr('obj_id'));
				item.history_dlg_st.mapHandle.removeLayer(item.ui_history_layer);
				item.history_visible = false;
				item.history_dlg_st = {};
				g_PositionBar._isChecked(item.history_visible_ico,false);
				
			}
		});
	},
	closeTrackDialog:function(id){
		var item = this._items.get(id);
		item.track_visible = false;
		item.track_dlg_st.mapHandle.removeLayer(item.ui_track_layer);
		$(item.track_dlg_st.content).dialog('close');
		//document.body.removeChild(item.history_dlg_st.content);
		item.track_dlg_st = {};
		this._isChecked(item.track_visible_ico,false);
	},
	showTrackDialog:function(id){
		var item = this._items.get(id);
		item.track_visible = true;
		this._isChecked(item.track_visible_ico,true);
		///////////////////////////////////////

		var content = L.DomUtil.create('div');
		item.track_dlg_st.content = content;
		$(content).attr('obj_id',id);
		$(content).css({
			'z-index':'10',
			'margin':'0px',
			'padding':'0px',
		});
		document.body.appendChild(content);
		item.track_dlg_st.body = map_div;
		var map_div = L.DomUtil.create('div','',content);
		item.track_dlg_st.mapDiv = map_div;
		$(content).dialog({
			autoOpen:true,
			title:this.local[this.options.lng].realtimeTrack + '---' + item.title + '(' + item.id + ')',
			minHeight: 200,
			minWidth:300,
			height:400,
			width:500,
			open: function( event, ui ) {
				var item = g_PositionBar._items.get($(event.target).attr('obj_id'));
				$(item.track_dlg_st.mapDiv).css({
					'width':event.target.style.width,
					'height':event.target.style.height,
				});
				var map_handle = L.map(item.track_dlg_st.mapDiv,{
					attributionControl: false,
					//zoomControl: false
				}).setView([34.3468421, 108.9365887],14);
				var vectorMapUri = 'http://{serverIP}/{server}/{z}/{x}/{y}.png'.replace('{server}',g_PositionBar.options.lng == 'en' ? 'eng' : 'hot').replace('{serverIP}',g_Main.options.serverIP);
				L.tileLayer(vectorMapUri, {
					minZoom: 0,
					maxZoom: 17
				}).addTo(map_handle).setZIndex(0);
				item.track_dlg_st.mapHandle = map_handle;

				/*L.easyButton('fa fa-location-arrow',function(){
					//g_PositionBar.showHistorySearchPop(this.button,$(this.button).attr('id'));
				},'search',item.id).addTo(map_handle).setPosition('topleft');*/

				map_handle.addLayer(item.ui_track_layer);
			},
			resize: function( event, ui ) {
				var item = g_PositionBar._items.get($(event.target).attr('obj_id'));
				$(item.track_dlg_st.mapDiv).css({
					'width':event.target.style.width,
					'height':event.target.style.height,
				})
				item.track_dlg_st.mapHandle.invalidateSize(true);
			},
			close: function( event, ui ) {
				var item = g_PositionBar._items.get($(event.target).attr('obj_id'));
				item.track_visible = false;
				item.track_dlg_st.mapHandle.removeLayer(item.ui_track_layer);
				item.track_dlg_st = {};
				g_PositionBar._isChecked(item.track_visible_ico,false);
			}
		});
	},
    showHistorySearchPop:function(parentNode,id){
		var item = this._items.get(id);
		item.history_dlg_st.popRefNode = parentNode;
		var div_title = L.DomUtil.create('div','','');
		//div_title.style.width = '400px';
		var tmp = L.DomUtil.create('span','text-left font-weight-bold',div_title);
		tmp.innerHTML = this.local[this.options.lng].historyTrackTimeline;

		var div_content = L.DomUtil.create('div','','');

		var tmp_div = L.DomUtil.create('div','input-group',div_content);
		tmp_div.style.margin = '2px 0px';
		var span = L.DomUtil.create('span','input-group-addon',tmp_div);
		span.innerHTML = this.local[this.options.lng].historyDialogStartTime;
		var form = L.DomUtil.create('input','form-control',tmp_div);
		form.setAttribute('placeholder',this.local[this.options.lng].historyDialogStartTimeHolder);
		item.history_dlg_st.startTimeCtrl = form;
		/*$(form).datetimepicker({
				language:  'zh-CN',
				weekStart: 1,
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
				showMeridian: 1
		});*/

		tmp_div = L.DomUtil.create('div','input-group',div_content);
		tmp_div.style.margin = '2px 0px';
		span = L.DomUtil.create('span','input-group-addon input-group-addon-large',tmp_div);
		span.innerHTML = this.local[this.options.lng].historyDialogEndTime;
		form = L.DomUtil.create('input','form-control',tmp_div);
		form.setAttribute('placeholder',this.local[this.options.lng].historyDialogEndTimeHolder);
		item.history_dlg_st.endTimeCtrl = form;
		/*$(form).datetimepicker({
				language:  'zh-CN',
				weekStart: 1,
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
				showMeridian: 1
		});*/

		tmp_div = L.DomUtil.create('div','btn-group',div_content);
		tmp_div.style.margin = '2px 0px';
		var btn = L.DomUtil.create('button','btn btn-primary',tmp_div);
		btn.innerHTML = this.local[this.options.lng].dialogOk;
		btn.setAttribute('obj_id',item.id);
		L.DomEvent.on(btn,'click',function(e){
			var item = this._items.get(e.srcElement.getAttribute('obj_id'));
			var stime = item.history_dlg_st.startTimeCtrl.value;
			var etime = item.history_dlg_st.endTimeCtrl.value;
			///////
			if(stime != '' && etime != ''){//console.log(stime);
				g_Main.getHistoryTrack(stime,etime,item);
			}
			///////
			$(item.history_dlg_st.popRefNode).popover('destroy');
		},this);
		btn = L.DomUtil.create('button','btn btn-primary',tmp_div);
		btn.innerHTML = this.local[this.options.lng].dialogCancel;
		btn.setAttribute('obj_id',item.id);
		L.DomEvent.on(btn,'click',function(e){
			var item = this._items.get(e.srcElement.getAttribute('obj_id'));
			$(item.history_dlg_st.popRefNode).popover('destroy');
		},this);
		
		if(item != null){
			$(parentNode).popover({
				placement:'right',
				html:true,
				title:div_title,
				content:div_content,
				trigger:'manual',
				container:item.history_dlg_st.content,
			});
			$(parentNode).popover('show');
		}
	},
	_createDetail: function(obj){
		var div = L.DomUtil.create('div','','');
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
	_createIcon: function(name)
	{
		var icon = null;
		switch(name)
		{
		case "devIco":
			icon = L.icon({
			    iconUrl: '../../js/Map/images/marker-gold.png',
			    iconSize: [21, 25],
			    iconAnchor: [9, 25],
			    popupAnchor: [0, -25],
			    shadowUrl: '../../js/Map/images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [14, 41]
		});
			break;
		case "centerIco":
			icon = L.icon({
			    iconUrl: '../../js/Map/images/wujiaoxing.jpg',
			    iconSize: [48, 48],
			    iconAnchor: [12, 43],
			    popupAnchor: [0, -52],
			    shadowUrl: '../../js/Map/images/marker-shadow.png',
			    shadowSize: [41, 41],
			    shadowAnchor: [0, 51]
		});
			break;
		}
		return icon;
	},
	_nearest:function(obj,async){
		var segs = obj.position.split(',');
		$.ajax({
			async:async === undefined ? false : true,
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
			//this.position = data.waypoints[0].location[1] + ',' + data.waypoints[0].location[0];
			this.lat = data.waypoints[0].location[1];
			this.lng = data.waypoints[0].location[0];
		}
	},
	_route:function(objs,async){
		var len = objs.length;
		var points = '';
		for(var i =0;i<len;i++){
			points += objs[i].lng + ',' + objs[i].lat;
			if(i < len - 1){
				points += ';'
			}
		}
		var proc_points = [];
		$.ajax({
			async:async === undefined ? false : true,
			//cache:false,
			context:{ctx: this,data: proc_points},
			data:{overview: 'false',steps: true},
			dataType:'json',
			type: 'GET',
			url: ('http://{serverIP}:5000/route/v1/driving/').replace('{serverIP}',this.options.serverIP) + points,
			success: this._onRouteRespon,
		});
		return proc_points;
	},
	_onRouteRespon: function(data,status,xhr){
		this.data.splice(0,this.length);
		if(status == 'success' && data.code == 'Ok'){//console.log(data);
			/*var waypoints = data.waypoints;
			var len = waypoints.length - 0;
			for(var i=0;i < len;i++){
				this.push({position:waypoints[i].location[1] + ',' + waypoints[i].location[0]})
			}*/
			var steps = data.routes[0].legs[0].steps;
			for(i in steps){
				for(j in steps[i].intersections){
					this.data.push({lat: steps[i].intersections[j].location[1],lng: steps[i].intersections[j].location[0]});
				}
			}

			//this.ctx._drawTrack({points:this.data});
		}
	},
	_match:function(objs,async){
		var len = objs.length;
		var points = '',radiuses = '';
		for(var i =0;i<len;i++){
			points += objs[i].lng + ',' + objs[i].lat;
			if(i < len - 1){
				points += ';'
			}

			var radius = '49';
			if(objs[i].hasOwnProperty('radius') && objs[i].radius != null){
				radius = objs[i].radius;
			}
			radiuses += radius;
			if(i < len - 1){
				radiuses += ';'
			}
		}
		var proc_points = [];
		$.ajax({
			async:async === undefined ? false : true,
			//cache:false,
			context:{ctx: this,data: proc_points},
			//data:{overview: 'full',steps: true},
			dataType:'json',
			type: 'GET',
			url: ('http://{serverIP}:5000/match/v1/driving/').replace('{serverIP}',this.options.serverIP) + points + '?radiuses=' + radiuses,
			success: this._onMatchRespon,
		});
		return proc_points;
	},
	_onMatchRespon: function(data,status,xhr){
		if(status == 'success' && data.code == 'Ok'){
			var segments = this.data;
			var points = [];
			for(var i = 0,len = data.tracepoints.length;i < len;i++){
				if(data.tracepoints[i] == null){
					if(points.length > 0){
						segments.push(points);
						points = [];
					}
					continue;
				}
				points.push({lng:data.tracepoints[i].location[0],lat:data.tracepoints[i].location[1]});
			}
			if(points.length > 0){
				segments.push(points);
			}
		}
	},
	_isChecked:function(htmlElement,isChecked){
		if(isChecked){
			$(htmlElement).addClass(this.options.icoCheckedClass);
			$(htmlElement).removeClass(this.options.icoUncheckedClass);
		}else{
			$(htmlElement).removeClass(this.options.icoCheckedClass);
			$(htmlElement).addClass(this.options.icoUncheckedClass);
		}
	},
	_setTooltip: function(htmlElement,title){
		$(htmlElement).tooltip({
			html:false,
			placement:'top',
			title:title,
			container:'body'
		});
	}
});
