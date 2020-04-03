L.Control.FenceBar = L.Control.extend({
	options:{
		position:  'topleft',
		lng:'en',
	},
	local:{
		cn:{
			fence:'电子围栏',
			range:'范围不能小于10米',
			tip_list:'列表',
			tip_layer:'显示围栏',
			tip_edit:'工具',
			tip_pos:'移动到',
			tip_del:'删除',
			tip_books:'有效设备',
			newFenceTitle:'新建电子围栏',
			inputFenceTitle:'请输入电子围栏名字',
			modifyFenceTitle:'请输入新的电子围栏名字',
			pop_title:'名称：',
			pop_circle_center:'圆心：',
			pop_circle_radius:'半径：',
			pop_points:'坐标：',
			dialogOk:'保存',
			dialogCancel:'取消',
			dialogExplain:'在下面输入卡号，按回车键录入',
			dialogTitle:'有效卡号编辑',
		},
		en:{
			fence:'fence',
			range:'The range cannot be less than 10 meters',
			tip_list:'list',
			tip_layer:'show fence',
			tip_edit:'edit tool',
			tip_pos:'move to',
			tip_del:'delete',
			tip_books:'valid devices',
			newFenceTitle:'new fence',
			inputFenceTitle:'please enter fence name',
			modifyFenceTitle:'please enter fence new name',
			pop_title:'name：',
			pop_circle_center:'center point：',
			pop_circle_radius:'radius：',
			pop_points:'coordinates：',
			dialogOk:'&nbspsave&nbsp',
			dialogCancel:'Cancel',
			dialogExplain:"enter card number and than press 'enter' key in keyboard to input",
			dialogTitle:'valid card editting',
		}
	},
	initialize:function(map,options){
		if(options){
			L.Util.setOptions( this, options );
		}
		this.EARTH_RADIUS = 6378138.0;
	    this._layer = L.featureGroup();
		this.Units = new W_Map();
		//
		this._myMap = map;
	    this._layer.addTo(this._myMap);
		this._container = L.DomUtil.create('div','fencebar-container');
		L.DomEvent.disableClickPropagation(this._container);
		L.DomEvent.disableScrollPropagation(this._container);
		this._header = L.DomUtil.create('div','list-group-item list-group-item-primary',this._container);
		this._ul = L.DomUtil.create('ul','fencebar-container-ul list-group',this._container);
		var ico = L.DomUtil.create("i", 'fa fa-object-group', this._header);
		ico.setAttribute('aria-hidden', 'true');

		var lb = L.DomUtil.create('lable', 'postionbar-li-item-lable', this._header);
        lb.innerHTML = this.local[this.options.lng].fence;

		ico = L.DomUtil.create("i", 'fa fa-pencil postionbar-li-item-icon-p', this._header);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_fun', 'edit');
		ico.setAttribute('obj_sta', 'hide');
		//ico.setAttribute('title', this.local[this.options.lng].tip_edit);
		this._setTooltip(ico,this.local[this.options.lng].tip_edit);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this._onLiBtn(e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);
		this._edit_btn_ico = ico;

		ico = L.DomUtil.create("i", 'fa fa-eye postionbar-li-item-icon-p text-primary', this._header);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_fun', 'show');
		ico.setAttribute('obj_sta', 'view');
		//ico.setAttribute('title', this.local[this.options.lng].tip_layer);
		this._setTooltip(ico,this.local[this.options.lng].tip_layer);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this._onLiBtn(e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-bars postionbar-li-item-icon-p text-primary', this._header);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('obj_fun', 'fences');
		ico.setAttribute('obj_sta', 'view');
		//ico.setAttribute('title', this.local[this.options.lng].tip_list);
		this._setTooltip(ico,this.local[this.options.lng].tip_list);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			this._onLiBtn(e.srcElement.getAttribute('obj_fun'),e.srcElement);
		}, this);

	    ////////////////////////////
		
	    //fence
	    this._fence = new L.Control.Draw({
	        position: 'topright',
	        draw: {
	            polyline: false,
	            polygon: {
	                allowIntersection: false, // Restricts shapes to simple polygons
	                drawError: {
	                    color: '#e1e100', // Color the shape will turn when intersects
	                    message: '<strong>Oh snap!<strong> you can\'t draw that!' // Message that will show when intersect
	                },
	                shapeOptions: {
	                    color: '#ff0000'
	                }
	            },
	            circle: {
	                allowIntersection: false, // Restricts shapes to simple polygons
	                drawError: {
	                    color: '#e1e100', // Color the shape will turn when intersects
	                    message: '<strong>Oh snap!<strong> you can\'t draw that!' // Message that will show when intersect
	                },
	                shapeOptions: {
	                    color: '#ff0000'
	                }
	            },
	            rectangle: {
	                shapeOptions: {
	                    clickable: true,
	                    color: '#ff0000'
	                }
	            },
	            marker: false,
	            circlemarker: false
	        },
	        edit: {
	            featureGroup: this._layer, //REQUIRED!!
	            remove: true,
	            // edit: true
	        },
			lng: this.options.lng,
	    });
	},
	createEventHandler:function(e){
		 var type = e.layerType,
                	layer = e.layer;
		if(type != 'circle' && type != 'polygon' && type != 'rectangle')return;
			
		var title = this.getFenceTitle();
		var num = 200;
		while(num-- > 0){
			title = prompt(this.local[this.options.lng].inputFenceTitle,title);
			if(title == null)return;
			if(this.checkFenceTitle(title))break;
		}
		if (type == 'polygon') {
		    var latlngs = layer.getLatLngs()[0];
		    var path = "";
		    var len = latlngs.length;
		    for(var i = 0; i < len ;i++){
		        path += latlngs[i].lng.toFixed(6) + ',' + latlngs[i].lat.toFixed(6);
		        if(i < len - 1){
		            path += ';';
		        }
		    }
			var unit = {
					id: this._getid(6),
					path: path,
					latlngs: latlngs,
					title: title,
					shape: type,
					ui_mark: null,
				};
			this.addFenceUnit(unit);
		}else if(type == 'circle'){
		    var latlon = layer.getLatLng();
		    var radius = layer.getRadius();
		    var path = latlon.lng.toFixed(6) + ',' + latlon.lat.toFixed(6) + ',' + radius.toFixed(3);
			if(radius < 10){alert(this.local[this.options.lng].range); return;}
			var unit = {
					id: this._getid(36),
					path: path,
					latlng: latlon,
					radius: radius,
					title: title,
					shape: type,
					ui_mark: null,
				};
			this.addFenceUnit(unit);
		       
		}else if(type == 'rectangle'){
		    var latlngs = layer.getLatLngs()[0];
		    var path = "";
		    var len = latlngs.length;
			if(latlngs[0].distanceTo(latlngs[1]) < 10 || latlngs[0].distanceTo(latlngs[3]) < 10){alert(this.local[this.options.lng].range); return;}
		    for(var i = 0; i < len ;i++){
		        path += latlngs[i].lng.toFixed(6) + ',' + latlngs[i].lat.toFixed(6);
		        if(i < len - 1){
		            path += ';';
		        }
		    }
        	var unit = {
					id: this._getid(6),
					path: path,
					latlngs: latlngs,
					title: title,
					shape: type,
					ui_mark: null,
				};
			this.addFenceUnit(unit);
		}
		this.saveFence();
	},
	editEventHandler:function(e){
		var layers = e.layers.getLayers();
		for(var i in layers){
			var layer = layers[i];
			if(layer.application != 'fencebar')return;
		    var unit = this.getFenceUnitByUIMark(layer);
		    var path = "";
		    if (unit.shape == 'circle') {
		        var latlon = layer.getLatLng();
		        var radius = layer.getRadius();
		        path = latlon.lng.toFixed(6) + ',' + latlon.lat.toFixed(6) + ',' + radius.toFixed(3);
				unit.path = path;
				unit.latlng = latlon;
				unit.radius = radius;
		    } else if (unit.shape == 'rectangle') {
		        var latlngs = layer.getLatLngs()[0];
		        var len = latlngs.length;
		        for (var i = 0; i < len ; i++) {
		            path += latlngs[i].lng.toFixed(6) + ',' + latlngs[i].lat.toFixed(6);
		            if (i < len - 1) {
		                path += ';';
		            }
		        }
				unit.path = path;
				unit.latlngs = latlngs;
		    } else if (unit.shape == 'polygon') {
		        var latlngs = layer.getLatLngs()[0];
		        var len = latlngs.length;
		        for (var i = 0; i < len ; i++) {
		            path += latlngs[i].lng.toFixed(6) + ',' + latlngs[i].lat.toFixed(6);
		            if (i < len - 1) {
		                path += ';';
		            }
		        }
				unit.path = path;
				unit.latlngs = latlngs;
		    }
		}
		this.saveFence();
	},
	deleteEventHandler:function(e){
		var layers = e.layers.getLayers();
		for(var i in layers){
			var layer = layers[i];
			if(layer.application != 'fencebar')return;
		    var unit = this.getFenceUnitByUIMark(layer);
			this._ul.removeChild(unit.list_li);
			this.Units.delete(unit.id);
			this._layer.removeLayer(unit.ui_mark);
		}
		this.saveFence();
	},
	createMapHandler:function(map){
		map.on(L.Draw.Event.EDITED, this.editEventHandler,this);
		map.on(L.Draw.Event.DELETED, this.deleteEventHandler,this);
		map.on(L.Draw.Event.CREATED, this.createEventHandler,this);
	},
	removeMapHandler:function(map){
		map.off(L.Draw.Event.EDITED, this.editEventHandler,this);
		map.off(L.Draw.Event.DELETED, this.deleteEventHandler,this);
		map.off(L.Draw.Event.CREATED, this.createEventHandler,this);
	},
	checkFenceTitle:function(title){
		for(var i in this.Units.getArray()){
			if(this.Units.getArray()[i][1].title == title)return false;
		}
		return true;
	},
	getFenceTitle:function(){
		var num = 0;
		while(num++ < 200){
			var t = this.local[this.options.lng].newFenceTitle + num.toString();
			var c = 0;
			for(var i in this.Units.getArray()){
				if(this.Units.getArray()[i][1].title == t)c = 1;
			}
			if(c == 0)return t;
		}
		return this.local[this.options.lng].newFenceTitle;
	},
	deserializeFenceFromArray:function(units){
		//var units = JSON.parse(json);
		for(var i = 0, len = units.length;i < len;i++){
			var unit = units[i];
			unit.ui_mark = null;
			unit.cards = unit.cards;
			unit.title = unit.title;
			if(unit.shape == 'circle'){
				var segs = unit.path.split(',');
				unit.latlng = L.latLng(segs[1],segs[0]);
				unit.radius = parseFloat(segs[2]);
			}else if(unit.shape == 'rectangle'){
				unit.latlngs = [];
				var points = unit.path.split(';');
				for(var i = 0,len = points.length;i < len; i++){
					var point = points[i];
					var _p = point.split(',');
					unit.latlngs.push(L.latLng(_p[1],_p[0]));
				}
			}else if(unit.shape == 'polygon'){
				unit.latlngs = [];
				var points = unit.path.split(';');
				for(var i = 0,len = points.length;i < len; i++){
					var point = points[i];
					var _p = point.split(',');
					unit.latlngs.push(L.latLng(_p[1],_p[0]));	
				}
			}
			this.addFenceUnit(unit);
		}
		
	},
	saveFence:function(){
		if(this.hasOwnProperty('_eventHandler')){
			this._eventHandler.call(this._eventHandlerCtx,'saveFence');
		}	
	},
	serializeFenceToArray:function(){
		var arr = [];
		for(var i = 0,len = this.Units.length; i < len; i++){
			var unit = this.Units.getArray()[i];
			arr.push({
				id:unit[1].id,
				title:unit[1].title,
				shape:unit[1].shape,
				path:unit[1].path,
				cards:unit[1].cards
				});
		}

		//var json = JSON.stringify(arr);
		return arr;
	},
	setEventHanlder: function(fun,ctx){
		this._eventHandler = fun;
		this._eventHandlerCtx = ctx;
	},
	_latlngsTolatlngsString: function(latlngs){
		var len = latlngs.length;
		var path = "";
		for (var i = 0; i < len ; i++) {
		    path += latlngs[i].lng.toFixed(6) + ',' + latlngs[i].lat.toFixed(6);
		    if (i < len - 1) {
		        path += ';';
		    }
		}
		return path;
	},
	_getid: function(len){
		return Math.random().toString(36).substr(3,len) + Date.now();
	},
	_addListItem: function(unit){
		var li = L.DomUtil.create('li','list-group-item list-group-item-success',this._ul);
		var ico_name = '';
		if(unit.shape == 'circle'){
			ico_name = 'circle';
		}else if(unit.shape == 'rectangle'){
			ico_name = 'square';
		}else if(unit.shape == 'polygon'){
			ico_name = 'star';
		}
		var ico = L.DomUtil.create("i", 'fa fa-' + ico_name, li);
		ico.setAttribute('aria-hidden', 'true');

		var lb = L.DomUtil.create('lable', 'postionbar-li-item-lable', li);
		lb.setAttribute('item-id',unit.id);
		L.DomEvent.on(lb,'dblclick',function(e){
	 		L.DomEvent.stop(e);
			var unit = this.Units.get(e.srcElement.getAttribute('item-id'));
			/////
			var title = unit.title;
			var num = 200;
			while(num-- > 0){
				title = prompt(this.local[this.options.lng].inputFenceTitle,this.getFenceTitle(),title);
				if(title == null){title = unit.title;continue;}
				if(this.checkFenceTitle(title))break;
			}
			/////
			unit.title = title;
			e.srcElement.innerHTML = unit.title;
		},this);
        lb.innerHTML = unit.title;

		ico = L.DomUtil.create("i", 'fa fa-times postionbar-li-item-icon-p', li);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('item-id', unit.id);
		//ico.setAttribute('title', this.local[this.options.lng].tip_del);
		this._setTooltip(ico,this.local[this.options.lng].tip_del);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			var unit = this.Units.get(e.srcElement.getAttribute('item-id'));
			this._ul.removeChild(unit.list_li);
			this.Units.delete(unit.id);
			this._layer.removeLayer(unit.ui_mark);
			this.saveFence();
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-map-marker postionbar-li-item-icon-p', li);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('item-id', unit.id);
		this._setTooltip(ico,this.local[this.options.lng].tip_pos);
		//ico.setAttribute('title', this.local[this.options.lng].tip_pos);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			var unit = this.Units.get(e.srcElement.getAttribute('item-id'));
			if(unit.shape == 'circle'){
				this._myMap.panTo(unit.latlng);console.log(unit.latlng);
			}else if(unit.shape == 'rectangle'){
				this._myMap.panTo(unit.latlngs[0]);
			}else if(unit.shape == 'polygon'){
				this._myMap.panTo(unit.latlngs[0]);
			}
		}, this);

		ico = L.DomUtil.create("i", 'fa fa-address-book postionbar-li-item-icon-p', li);
        ico.setAttribute('aria-hidden', 'true');
		ico.setAttribute('item-id', unit.id);
		unit.filter_dlg_st.ico = ico;
		this._setTooltip(ico,this.local[this.options.lng].tip_books);
		//ico.setAttribute('title', this.local[this.options.lng].tip_pos);
		L.DomEvent.on(ico, 'click', function (e) {
		    L.DomEvent.stop(e);
			var unit = this.Units.get(e.srcElement.getAttribute('item-id'));
			this.showFilterDialog(e.srcElement,unit.id);
		}, this);
		return li;
	},
	showFilterDialog:function(parentNode,id){
		var item = this.Units.get(id);
		var div_title = L.DomUtil.create('div','','');
		div_title.style.width = '500px';
		var tmp = L.DomUtil.create('span','text-left font-weight-bold',div_title);
		tmp.innerHTML = this.local[this.options.lng].dialogTitle + '---' + item.title;

		var div_content = L.DomUtil.create('div','','');

		var div_span = L.DomUtil.create('div','',div_content);
		div_span.style.height = '55px';
		div_span.innerHTML = this.local[this.options.lng].dialogExplain;
	
		var div_labs = L.DomUtil.create('input','',div_content);

		item.filter_dlg_st.unit_container = div_labs;
		$(div_labs).tagsinput({
		});

		for(var i in item.cards){
			$(div_labs).tagsinput('add', item.cards[i]);
		}

		item.filter_dlg_st.input = div_labs;
	
		tmp_div = L.DomUtil.create('div','btn-group',div_content);
		tmp_div.style.margin = '2px 0px';
		var btn = L.DomUtil.create('button','btn btn-primary',tmp_div);
		btn.innerHTML = this.local[this.options.lng].dialogOk;
		btn.setAttribute('obj_id',item.id);
		L.DomEvent.on(btn,'click',function(e){
			var item = this.Units.get(e.srcElement.getAttribute('obj_id'));
			var cards = $(item.filter_dlg_st.input).val().split(',');
			item.cards = cards;
			this.saveFence();
			///////
			///////
			$(item.filter_dlg_st.ico).popover('destroy');
		},this);
		btn = L.DomUtil.create('button','btn btn-primary',tmp_div);
		btn.innerHTML = this.local[this.options.lng].dialogCancel;
		btn.setAttribute('obj_id',item.id);
		L.DomEvent.on(btn,'click',function(e){
			var item = this.Units.get(e.srcElement.getAttribute('obj_id'));
			$(item.filter_dlg_st.ico).popover('destroy');
		},this);
		
		if(item != null){
			$(parentNode).popover({
				placement:'right',
				html:true,
				title:div_title,
				content:div_content,
				trigger:'manual',
				container:'body'
			});
			$(parentNode).popover('show');
		}
	},
	onAdd:function(map){
		return this._container;
	},
	getFenceUnitByUIMark: function(ui_mark){
		var unit = null;
			for(var i = 0,len = this.Units.length;i < len;i++){
				if(this.Units.getArray()[i][1].ui_mark == ui_mark){
					  unit = this.Units.getArray()[i][1];
					  break;
				  }
			}
		return unit;
	},
	//title,id,shape,path,ui_mark
	addFenceUnit: function(unit){
		if(!this.Units.has(unit.id)){
			unit.filter_dlg_st = {};
			unit.list_li = this._addListItem(unit);
			this.Units.set(unit.id,unit);
			if(unit.shape == 'circle'){
				var segments = unit.path.split(',');
				var latlon = [segments[1],segments[0]];
				var radius = segments[2];
				unit.ui_mark = L.circle(latlon, {radius: radius,color: '#ff0000',fill: true,fillOpacity: 0.1}).bindPopup(this._createDetail(unit)).addTo(this._layer);
				unit.ui_mark.shape = 'circle';
				unit.ui_mark.application = 'fencebar';
			}else if(unit.shape == 'rectangle'){
				var points = unit.path.split(';');
				var segments = points[0].split(',');
				var latlon1 = [segments[1],segments[0]];
				segments = points[2].split(',');
				var latlon2 = [segments[1],segments[0]];
				var bounds = L.latLngBounds(L.latLng(latlon1), L.latLng(latlon2));
				unit.ui_mark = L.rectangle(bounds, {color: '#ff0000',fill: true,fillOpacity: 0.1}).bindPopup(this._createDetail(unit)).addTo(this._layer);
				unit.ui_mark.shape = 'rectangle';
				unit.ui_mark.application = 'fencebar';
			}else if(unit.shape == 'polygon'){
				var points = unit.path.split(';');
				var latlngs = [];
				for(var i = 0,len = points.length; i < len;i++){
					var point = points[i];
					var segments = point.split(',');
					latlngs.push(L.latLng([segments[1],segments[0]]));
				}
				unit.ui_mark = L.polygon(latlngs, {color: '#ff0000',fill: true,fillOpacity: 0.1}).bindPopup(this._createDetail(unit)).addTo(this._layer);
				unit.ui_mark.shape = 'polygon';
				unit.ui_mark.application = 'fencebar';
			}
		}
		return unit;
	},
	_createDetail: function(obj){
		var div = L.DomUtil.create('div','','');
		var p = L.DomUtil.create('p','text-left font-weight-bold',div);
		var content = "";
		if(obj.hasOwnProperty('title'))content += this.local[this.options.lng].pop_title + obj.title + "<br/>";
		if(obj.hasOwnProperty('radius'))content += this.local[this.options.lng].pop_circle_radius + (obj.radius > 1000 ? ((obj.radius / 1000.0).toFixed(2) + 'km') : (obj.radius.toFixed(2) + 'm')) + "<br/>";
		if(obj.hasOwnProperty('latlng'))content += this.local[this.options.lng].pop_circle_center + L.Util.formatNum(obj.latlng.lng) + '° , ' + L.Util.formatNum(obj.latlng.lat) +  '°' + "<br/>";
		if(obj.hasOwnProperty('latlngs')){
			content += this.local[this.options.lng].pop_points +  L.Util.formatNum(obj.latlngs[0].lng) + '° , ' + L.Util.formatNum(obj.latlngs[0].lat) +  '°' + "<br/>";
			for(var i = 1,len = obj.latlngs.length; i < len;i++){
				content += "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +  L.Util.formatNum(obj.latlngs[i].lng) + '° , ' + L.Util.formatNum(obj.latlngs[i].lat) +  '°' + "<br/>";
			}
		}
		p.innerHTML = content;
		return div;
	},
	onRemove:function(map){
		var htmlElement = this._edit_btn_ico;
		var sta = htmlElement.getAttribute('obj_sta');
	        if (sta == 'view') {
	            htmlElement.setAttribute('obj_sta', 'hide');
	            L.DomUtil.removeClass(htmlElement, 'text-primary');
	            this._fence.remove();
				this.removeMapHandler(this._myMap);
	        }
	},
	enable:function(){
		return this;
	},
	disable:function(){
		return this;
	},
	_onLiBtn:function(fun,htmlElement){
	    if (fun == 'edit') {
	        var sta = htmlElement.getAttribute('obj_sta');
	        if (sta == 'view') {
	            htmlElement.setAttribute('obj_sta', 'hide');
	            L.DomUtil.removeClass(htmlElement, 'text-primary');
	            this._fence.remove();
				this.removeMapHandler(this._myMap);
	        } else {
	            htmlElement.setAttribute('obj_sta', 'view');
	            L.DomUtil.addClass(htmlElement, 'text-primary');
	            this._fence.addTo(this._myMap);
				this.createMapHandler(this._myMap);
	        }
	    } else if(fun == 'show') {
	        var sta = htmlElement.getAttribute('obj_sta');
	        if (sta == 'view') {
	            htmlElement.setAttribute('obj_sta', 'hide');
	            L.DomUtil.removeClass(htmlElement, 'text-primary');
	            L.DomUtil.removeClass(htmlElement,'fa-eye');
	            L.DomUtil.addClass(htmlElement, 'fa-eye-slash');
	            this._layer.remove();
	        } else {
	            htmlElement.setAttribute('obj_sta', 'view');
	            L.DomUtil.addClass(htmlElement, 'text-primary');
	            L.DomUtil.removeClass(htmlElement, 'fa-eye-slash');
	            L.DomUtil.addClass(htmlElement, 'fa-eye');
	            this._layer.addTo(this._myMap);
	        }
	    }else if(fun == 'fences'){
			var sta = htmlElement.getAttribute('obj_sta');
	        if (sta == 'view') {
	            htmlElement.setAttribute('obj_sta', 'hide');
	            L.DomUtil.removeClass(htmlElement, 'text-primary');
				$(this._ul).hide(200);
	        } else {
	            htmlElement.setAttribute('obj_sta', 'view');
	            L.DomUtil.addClass(htmlElement, 'text-primary');
				$(this._ul).show(200);
	        }
		}
	},
		_rad: function(d){
		return d * Math.PI / 180.0;
	},
	isInFences: function(obj){
		// latLng = L.latLng([obj.position.split(',')[0],obj.position.split(',')[1]]);
		latLng = L.latLng([obj.lat,obj.lng]);
		if(this.Units.length == 0){return true;}
		for(var i = 0,len = this.Units.length; i < len; i++){
			var unit = this.Units.getArray()[i][1];
			//if(unit.cards.indexOf(obj.id) == -1)continue;
			var ret = false;
			if(unit.shape == 'circle'){
				ret = this._isInCircle(unit.radius,unit.latlng.lat,unit.latlng.lng,latLng.lat,latLng.lng);
			}else if(unit.shape == 'rectangle'){
				ret = this._isInRectangleArea(
				latLng.lat,
				latLng.lng,
				Math.min(unit.latlngs[0].lat,unit.latlngs[2].lat),
				Math.max(unit.latlngs[0].lat,unit.latlngs[2].lat),
				Math.min(unit.latlngs[0].lng,unit.latlngs[2].lng),
				Math.max(unit.latlngs[0].lng,unit.latlngs[2].lng));
			}else if(unit.shape == 'polygon'){
				ret = this._isInPolygon(latLng,unit.latlngs);
			}
			if(ret)return true;
		}
		return false;
	},
	checkFences: function(obj){
		latLng = L.latLng([obj.lat,obj.lng]);
		var g_ret = {outFences:[]};
		if(this.Units.length == 0){return g_ret;}
		for(var i = 0,len = this.Units.length; i < len; i++){
			var unit = this.Units.getArray()[i][1];
			if(unit.cards.indexOf(obj.id) == -1)continue;
			var ret = false;
			if(unit.shape == 'circle'){
				ret = this._isInCircle(unit.radius,unit.latlng.lat,unit.latlng.lng,latLng.lat,latLng.lng);
			}else if(unit.shape == 'rectangle'){
				ret = this._isInRectangleArea(
				latLng.lat,
				latLng.lng,
				Math.min(unit.latlngs[0].lat,unit.latlngs[2].lat),
				Math.max(unit.latlngs[0].lat,unit.latlngs[2].lat),
				Math.min(unit.latlngs[0].lng,unit.latlngs[2].lng),
				Math.max(unit.latlngs[0].lng,unit.latlngs[2].lng));
			}else if(unit.shape == 'polygon'){
				ret = this._isInPolygon(latLng,unit.latlngs);
			}
			if(!ret)g_ret.outFences.push(unit.title);
		}
		return g_ret;
	},
	_setTooltip: function(htmlElement,title){
		$(htmlElement).tooltip({
			html:false,
			placement:'top',
			title:title,
			container:'body'
		});
	},
	//radius:半径
	_isInCircle: function(radius, lat1, lng1, lat2, lng2){
		var radLat1 = this._rad(lat1);    
		var radLat2 = this._rad(lat2);    
		var a = radLat1 - radLat2;    
		var b = this._rad(lng1) - this._rad(lng2);    
		var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +      
				Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		s = s * this.EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		if(s > radius) {//不在圆上
			return false;
		}else {
			return true;
		}
	},
	//lat:测试点纬度，lng：测试点经度，minLat:最小纬度，maxLat：最大纬度，minLng:最小经度，maxLng：最大经度
	_isInRectangleArea: function(lat, lng, minLat, maxLat, minLng, maxLng){  
        if(this._isInRange(lat, minLat, maxLat)){//如果在纬度的范围内  
            if(minLng*maxLng>0){
                if(this._isInRange(lng, minLng, maxLng)){  
                    return true;  
                }else {  
                    return false;  
                }  
            }else {
                if(Math.abs(minLng)+Math.abs(maxLng)<180){
                    if(this._isInRange(lng, minLng, maxLng)){  
                        return true;  
                    }else {  
                        return false;  
                    }  
                }else{
                    var left = Math.max(minLng, maxLng);
                    var right = Math.min(minLng, maxLng);  
                    if(this._isInRange(lng, left, 180)||this._isInRange(lng, right,-180)){  
                        return true;  
                    }else {  
                        return false;  
                    }  
                }  
            }  
        }else{  
            return false;  
        }  
    },
	_isInRange: function(point, left, right){  
        if(point>=Math.min(left, right)&&point<=Math.max(left, right)){  
            return true;  
        }else {  
            return false;  
        }  
    },
	//point:测试点,pts:多边形点
	_isInPolygon: function(point, pts){  
        var N = pts.length;  
        var boundOrVertex = true; 
        var intersectCount = 0;//交叉点数量  
        var precision = 2e-10; //浮点类型计算时候与0比较时候的容差  
        var p1, p2;//临近顶点  
        var p = point; //当前点  
          
        p1 = pts[0];      
        for(var i = 1; i <= N; ++i){      
            if(p.equals(p1)){  
                return boundOrVertex;
            }  
              
            p2 = pts[i % N];           
            if(p.lng < Math.min(p1.lng, p2.lng) || p.lng > Math.max(p1.lng, p2.lng)){                  
                p1 = p2;   
                continue;
            }  
            
            //射线穿过算法
            if(p.lng > Math.min(p1.lng, p2.lng) && p.lng < Math.max(p1.lng, p2.lng)){
                if(p.lat <= Math.max(p1.lat, p2.lat)){               
                    if(p1.lng == p2.lng && p.lat >= Math.min(p1.lat, p2.lat)){  
                        return boundOrVertex;  
                    }  
                      
                    if(p1.lat == p2.lat){                   
                        if(p1.lat == p.lat){
                            return boundOrVertex;  
                        }else{
                            ++intersectCount;  
                        }   
                    }else{                     
                        var xinters = (p.lng - p1.lng) * (p2.lat - p1.lat) / (p2.lng - p1.lng) + p1.lat;                       
                        if(Math.abs(p.lat - xinters) < precision){ 
                            return boundOrVertex;  
                        }  
                          
                        if(p.lat < xinters){
                            ++intersectCount;  
                        }   
                    }  
                }  
            }else{            
                if(p.lng == p2.lng && p.lat <= p2.lat){                 
                    var p3 = pts[(i+1) % N];                 
                    if(p.lng >= Math.min(p1.lng, p3.lng) && p.lng <= Math.max(p1.lng, p3.lng)){
                        ++intersectCount;  
                    }else{  
                        intersectCount += 2;  
                    }  
                }  
            }              
            p1 = p2;
        }  
        if(intersectCount % 2 == 0){//偶数在多边形外  
            return false;  
        } else { //奇数在多边形内  
            return true;
        } 
    }
});
