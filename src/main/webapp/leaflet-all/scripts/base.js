//GetElementById
function $id(sId){
	return document.getElementById(sId);
}

//过滤帐号
function fTrim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "").replace(/(^　*)|(　*$)/g, "");
}
/*
 * 过滤掉字符串中间的空格键
 */
function ignoreSpace(username_add1) {
	var i=0;
	var temp="";
	var username_add=username_add1.split(" ");
	for (i=0;i<username_add.length;i++) {
		temp+=username_add[i];
	}
	return temp;
}
//Html5标签支持
function fHtml5Tag(){
	var aTag = ["aside","figcaption","figure","footer","header","hgroup","nav","section"],i = 0;
	for(i in aTag){document.createElement(aTag[i]);}
}

//绑定事件监听
function fEventListen(oElement, sName, fObserver, bUseCapture) {
	bUseCapture = !!bUseCapture;

	if (oElement.addEventListener){
		oElement.addEventListener(sName, fObserver, bUseCapture);
	}else if(oElement.attachEvent){
		oElement.attachEvent('on' + sName, fObserver);
	}
}

//删除事件监听
function fEventUnlisten(oElement, sName, fObserver, bUseCapture){
	bUseCapture = !!bUseCapture;
	if(oElement.removeEventListener){
		oElement.removeEventListener(sName, fObserver, bUseCapture);
	}else if(oElement.detachEvent){
		oElement.detachEvent('on' + sName, fObserver);
	}
}

//限定范围随机数
function fRandom(nLength){
	return Math.floor(nLength * Math.random());
}

//设置button样式
function setbuttonstyle(){	
	$(":button").addClass("ui-button").addClass("ui-widget").addClass("ui-state-default").addClass("ui-corner-all");
	$(":button").mouseover(function(){
		$(this).addClass("ui-state-hover");		
	}).mouseout(function()
	{
		$(this).removeClass("ui-state-hover");
	})
	;	
}
//提示信息
function showTipDialog(div,title, message,fadeout) {

	var mydiv = arguments[0];

	$(mydiv).html("<center></br>" + message + "</left>");

	$(mydiv).dialog( {
		height : 150,
		width : 240,
		modal : false,
		title : title,
		onClose : function(event, ui) {
			$(mydiv).dialog("close");
		},
		resizable : false
	});
	setTimeout(function() {
		$(mydiv).dialog("close");
	}, fadeout);
}

//提示信息
function showConfigDialog(div, message,title,ok_fun) {
	var mydiv = arguments[0];
	var msg = arguments[1];
	
	$(mydiv).html("</br>" + message + "</left>");
	$(mydiv).dialog( {
		title:title,
		height : 150,
		width : 240,
		modal : true,
		resizable : false,
		onClose : function() {
			$(mydiv).dialog("close");
		},
		buttons:[{
			text:'确定',
			iconCls:'icon-ok',
			handler:function(){
				ok_fun();
			}
		},{
			id:'config_dialog_cancle',
			text:'取消',
			iconCls:'icon-no',
			focus:true,
			handler:function(){
				$(mydiv).dialog("close");
			}
		}]
	});
	$('#config_dialog_cancle').focus();
}


//自定义对话框
function showDialog(div,title,height,width,url,ok_fun) {
	var mydiv = arguments[0];
	var title = arguments[1];	
	var height = arguments[2];
	var width = arguments[3];
	//var fadeout = arguments[4];
	var okfun = arguments[5];

	$(mydiv).dialog( {
		modal : true,
		height : height,
		width : width,
		title : title,
		
		resizable : true,
		onClose : function() {
			//alert(mydiv);
			$(mydiv).dialog("close");
		},
		//content:"<iframe frameborder='0' style='width=100%;height=100%' src="+url+"></iframe>",
		buttons:[{
			text:'确定',
			iconCls:'icon-ok',
			handler:function(){
				alert("I'm ok");
			}
		},{
			id:'dialog_cancle',
			text:'取消',
			iconCls:'icon-no',
			handler:function(){
			alert(mydiv);
				$(div).dialog("close");
			}
		}]
	});
	$('#dialog_cancle').focus();

};

var no_select_update = "请选中一条进行修改！";
var more_select_update = "只能选中一条记录进行修改！";
var no_select_delete = "请选择一条进行删除！";