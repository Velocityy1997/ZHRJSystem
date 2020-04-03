L.Control.SearchBar = L.Control.extend({
	options:{
		position:  'topleft',
		lng: 'en',
		serverIP: '192.168.0.117'
	},
	local:{
		en:{
			search:'search',
			search_position:'search keyword',
			clean:'clean'
		},
		cn:{
			search:'搜索',
			search_position:'搜索位置',
			clean:'清空'
		}
	},
	initialize:function(options){
		if(options){
		  L.Util.setOptions( this, options );
		}
		/*this.container = L.DomUtil.create("div", 'input-group mb-3');
		this.input = L.DomUtil.create("input",'form-control',this.container);
		this.input.setAttribute('placeholder',this.local[this.options.lng].search_position);
		this.input.setAttribute('type','text');
		this.button = L.DomUtil.create("button",'input-group-append btn btn-primary',this.container);
		this.button.setAttribute('data-toggle','tooltip');
		this.button.setAttribute('data-placement','bottom');
		this.button.setAttribute('title',this.local[this.options.lng].search);
		this.button_icon = L.DomUtil.create("i",'fa fa-search',this.button);
		this.button_icon.setAttribute('aria-hidden','true');
		L.DomEvent.on(this.button, 'click', function (e) {
		    L.DomEvent.stop(e);
			this.onSearch();
		}, this);*/
		this._items = null;
		this._lastRequestSerial = "";

		this.container = L.DomUtil.create("div", '');
		var div = L.DomUtil.create("div", '',this.container);
		var div1 = L.DomUtil.create("div", '',div);
		this.ul = L.DomUtil.create("ul", 'list-group searchbar-container-ul',div);
		this.input = L.DomUtil.create("input", '',div1);
		this.input.style.width = '200px';
		this.input.style.height = '34px';
		this.input.style.fontSize = '16px';
		this.input.style.borderRadius = '5px';
		this.input.style.margin = '1px 1px 0px 0px';
		this.input.setAttribute('type','text');
		this.input.setAttribute('placeholder',this.local[this.options.lng].search_position);
		L.DomEvent.disableClickPropagation(div1);
		L.DomEvent.on(this.input, 'input propertychange', function (e) {
		    L.DomEvent.stop(e);
			this.onSearch();
		}, this);
		//$(this.input).on('input propertychange',function(){console.log("123");});

		var btn = L.DomUtil.create("button", 'btn btn-primary searchbar-container-btn',div1);
		btn.style.height = '35px';
		//btn.setAttribute('data-toggle','tooltip');
		//btn.setAttribute('data-placement','bottom');
		//btn.setAttribute('title',this.local[this.options.lng].search);
		this._setTooltip(btn,this.local[this.options.lng].search);
		L.DomEvent.on(btn, 'click', function (e) {
		    L.DomEvent.stop(e);
			this.onSearch();
		}, this);
		var btn_ico = L.DomUtil.create("i",'fa fa-search',btn);	
		////////////////////
		btn = L.DomUtil.create("button", 'btn btn-primary searchbar-container-btn',div1);
		btn.style.height = '35px';
		//btn.setAttribute('data-toggle','tooltip');
		//btn.setAttribute('data-placement','bottom');
		//btn.setAttribute('title',this.local[this.options.lng].clean);
		this._setTooltip(btn,this.local[this.options.lng].clean);
		L.DomEvent.on(btn, 'click', function (e) {
		    L.DomEvent.stop(e);
			this.clearItem();
			this.input.value = '';
		}, this);
		btn_ico = L.DomUtil.create("i",'fa fa-times',btn);	
		
	},
	addItem: function(obj){
		var li = L.DomUtil.create('li','list-group-item list-group-item-light',this.ul);
		li.innerHTML = obj.display_name;
		L.DomEvent.on(li, 'click', function (e) {
		    L.DomEvent.stop(e);
			var ele = e.srcElement;
			var mod = this._items.get(ele);
			map.panTo([mod.lat,mod.lon]);
		}, this);
		L.DomEvent.on(li, 'mouseover', function (e) {
		    L.DomEvent.stop(e);
			var ele = e.srcElement;
			L.DomUtil.addClass(ele,'text-primary');
		}, this);
		L.DomEvent.on(li, 'mouseout', function (e) {
		    L.DomEvent.stop(e);
			var ele = e.srcElement;
			L.DomUtil.removeClass(ele,'text-primary');
		}, this);
		this._items.set(li,obj);

		L.marker([obj.lat,obj.lon]).bindPopup(this._createDetail(obj)).addTo(this._layer);
	},
	clearItem: function(){
		this._items = new W_Map();
		this.ul.innerHTML = null;
		this._layer.clearLayers();
	},
	onSearch: function(){
		if(this.input.value == ''){
			this._lastRequestSerial = ""
			this.clearItem();
			return;
		}
		
		var params = {
			q: this.input.value,
			format: 'json',
			//limit: 1,
			//addressdetails: 1,

		};
		this._search(params);
		/*this.clearItem();
		var data = this._search(params);
		for(obj of data){
			this.addItem(obj);
		}*/
		//var url = "http://192.168.0.117:8088/search.php" + L.Util.getParamString(params);
		/*var url = "https://nominatim.openstreetmap.org/search.php" + L.Util.getParamString(params);
		$.get(url, function(results,status){
			if(status == 'success'){
				if (results.length == 0) {
				console.log("ERROR: didn't find a result");
				return;
			}
			console.log(results);
			var bbox = results[0].boundingbox,
				first = new L.LatLng(bbox[0], bbox[2]),
				second = new L.LatLng(bbox[1], bbox[3]),
				bounds = new L.LatLngBounds([first, second]);
			    map.fitBounds(bounds);
			}
		});*/
	},
	_search:function(params){
		//var ret = {ret:null};
		this._lastRequestSerial = this._getid(36);
		$.ajax({
			async:true,
			//cache:false,
			context:{ctx:this,serial:this._lastRequestSerial},
			data:params,
			dataType:'json',
			type: 'GET',
			//url: "https://nominatim.openstreetmap.org/search.php",
			url: ("http://{serverIP}:8088/search.php").replace('{serverIP}',this.options.serverIP),
			success: this._onSearchRespon,
		});
		//return ret.ret;
	},
	_onSearchRespon: function(data,status,xhr){
		if(status == 'success' && this.ctx._lastRequestSerial == this.serial){
			//this.ret = data;
			//this.position = data.waypoints[0].location[1] + ',' + data.waypoints[0].location[0];

			this.ctx.clearItem();
			for(var i = 0,len = data.length;i<len;i++){
				this.ctx.addItem(data[i]);
			}
		}
	},
	_getid: function(len){
		return Math.random().toString(36).substr(3,len) + Date.now();
	},
	onAdd:function(map){
		this._layer = L.layerGroup().addTo(map);
		return this.container;
	},
	onRemove:function(map){
		
	},
	enable:function(){
		return this;
	},
	disable:function(){
		return this;
	},
	_createDetail: function(obj){
		var div = L.DomUtil.create('div','container','');
		var p = L.DomUtil.create('p','text-left font-weight-bold',div);
		p.innerHTML = obj.display_name;
		return div;
	},
	_setTooltip:function(htmlElement,title){
		$(htmlElement).tooltip({
			html:false,
			placement:'bottom',
			title:title,
			container:'body'
		});
	}
});