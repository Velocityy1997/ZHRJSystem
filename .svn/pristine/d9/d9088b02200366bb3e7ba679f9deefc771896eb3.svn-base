//创建自定义的地图上的按钮控件
L.Control.NavBar = L.Control.extend({
	options:{
		position:  'topright',
		lng: 'en',
	},
	local:{
		cn:{
			road_net:'路网',
			satellite_map:'卫星',
			terminal_manage:'终端管理',
			fence:'电子围栏',
			measure:'测量',
			setting:'设置',
			route:'路线管理',
		},
		en:{
			road_net:'road net',
			satellite_map:'satellite',
			terminal_manage:'manage',
			fence:'fence',
			measure:'measure',
			setting:'setting',
			route:'route manage',
		}
	},
	initialize: function (state, context,options) {
		if(options){
		  L.Util.setOptions( this, options );
		}
	    this._context = context;

	    this.container = L.DomUtil.create("div", '');
		L.DomEvent.disableClickPropagation(this.container);
	    

		/*this.button_Track_state = 0;
		this.button_Track = L.DomUtil.create("button", 'btn btn-light navbar-button text-info', this.container);
		this.button_Track_div = L.DomUtil.create("div", '', this.button_Track);
		this.button_Track_i = L.DomUtil.create("i", 'navbar-icon fa fa-road', this.button_Track_div);
		this.button_Track_i.setAttribute('aria-hidden', 'true');
		this.button_Track_span = L.DomUtil.create("span", '', this.button_Track_div);
		this.button_Track_span.innerHTML = '轨迹';
		L.DomEvent.on(this.button_Track, 'click', function (e) {
		    L.DomEvent.stop(e);
		    if (this.button_Track_state == 0) {
		        this.button_Track_state = 1;
		        this._checked(this.button_Track);
		    } else {
		        this.button_Track_state = 0;
		        this._unchecked(this.button_Track);
		    }
		    state.track(this.button_Track_state);
		}, this);*/
		
		
		this.button_Route_state = 0;
		this.button_Route = L.DomUtil.create("button", 'btn btn-primary navbar-button text-info', this.container);
		this.button_Route_div = L.DomUtil.create("div", '', this.button_Route);
		this.button_Route_i = L.DomUtil.create("i", 'navbar-icon fa fa-road', this.button_Route_div);
		this.button_Route_i.setAttribute('aria-hidden', 'true');
		this.button_Route_span = L.DomUtil.create("span", '', this.button_Route_div);
		this.button_Route_span.innerHTML = this.local[this.options.lng].route;
		L.DomEvent.on(this.button_Route, 'click', function (e) { 
		    L.DomEvent.stop(e);
		    if (this.button_Route_state == 0) {
		        this.button_Route_state = 1;
		        this._checked(this.button_Route);
		    } else {
		        this.button_Route_state = 0;
		        this._unchecked(this.button_Route);
		        
		    }
		    state.route.call(this._context,this.button_Route_state);
		}, this);

		this.button_Tool_state = 0;
		this.button_Tool = L.DomUtil.create("button", 'btn btn-primary navbar-button text-info', this.container);
		this.button_Tool_div = L.DomUtil.create("div", '', this.button_Tool);
		this.button_Tool_i = L.DomUtil.create("i", 'navbar-icon fa fa-archive', this.button_Tool_div);
		this.button_Tool_i.setAttribute('aria-hidden', 'true');
		this.button_Tool_span = L.DomUtil.create("span", '', this.button_Tool_div);
		this.button_Tool_span.innerHTML = this.local[this.options.lng].measure;
		L.DomEvent.on(this.button_Tool, 'click', function (e) {
		    L.DomEvent.stop(e);
		    if (this.button_Tool_state == 0) {
		        this.button_Tool_state = 1;
		        this._checked(this.button_Tool);
		    } else {
		        this.button_Tool_state = 0;
		        this._unchecked(this.button_Tool);
		    }
		    state.tool.call(this._context,this.button_Tool_state);
		}, this);
			
	},
	onAdd:function(map){
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
	_checked: function (btn) {
	    L.DomUtil.removeClass(btn, 'btn-primary');
	    L.DomUtil.addClass(btn, 'btn-success');
	},
	_unchecked: function (btn) {
	    L.DomUtil.addClass(btn, 'btn-primary');
	    L.DomUtil.removeClass(btn, 'btn-success');
	}
});
