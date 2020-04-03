function W_Map(){
	this.length = 0;
	this._array = [];
	this.has = function(key){
		var ret = false;
		for(var i = 0, len = this._array.length; i < len; i++){
			if(this._array[i][0] == key){
				ret = true;break;
			}
		}
		return ret;
	};
	this.set = function(key,value){
		var tmp = null;
		for(var i = 0, len = this._array.length; i < len; i++){
			if(this._array[i][0] == key){
				tmp = this._array[i];break;
			}
		}
		if(tmp != null){
			tmp[1] = value;
		}
		else{
			this._array.push([key,value]);
			this.length++;
		}
	};
	this.get = function(key){
		var ret = null;
		for(var i = 0, len = this._array.length; i < len; i++){
			if(this._array[i][0] == key){
				ret = this._array[i][1];break;
			}
		}
		return ret;
	};
	this.getArray = function(){
		return this._array;
	};
	this.remove = function(key){
		this.delete(key);
	};
	this.delete = function(key){
		for(var i = 0, len = this._array.length; i < len; i++){
			if(this._array[i][0] == key){
				this._array.splice(i,1);
				this.length--;
				break;
			}
		}
	};
	this.clear = function(){
		this._array.splice(0,this._array.length);
		this.length = 0;
	}
}
