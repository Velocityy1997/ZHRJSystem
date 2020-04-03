L.Control.LevelControl = L.Control.extend({
    options: {
        position: 'bottomleft',
		lng:'en'
    },
	  local:{
		cn:{
			level:'地图级数：',
			unit:'级'
		},
		en:{
			level:'level：',
			unit:''
		}
	 },
    initialize: function (options) {
        if(options){
			L.Util.setOptions( this, options );
		}
    },
    onAdd: function (map) {
        this._map = map; 
		this._container = L.DomUtil.create('div', 'font-weight-bold leaflet-control-levelcontrol');
		this._container.innerHTML = this.local[this.options.lng].level + this._map.getZoom() + this.local[this.options.lng].unit;
		L.DomEvent.disableClickPropagation(this._container);
        this._map.on('zoomend', function (e) {
            this._container.innerHTML = this.local[this.options.lng].level + this._map.getZoom() + this.local[this.options.lng].unit;
        },this);
        return this._container;
    },
    onRemove: function (map) {

    },
    enable: function () {
        return this;
    },
    disable: function () {
        return this;
    }
});