/**
 * 管理修改JS
 * @auther 
 */
	var ${editJSName} = {
		url : '${simpleclassName?uncap_first}/edit',
		listUrl : '${parentPackageName}/jsp/list_${simpleclassName?uncap_first}.jsp',
		// 提交按钮事件
		submitForm : function(){
			// 设置查询URL
			button_util.listUrl = this.listUrl;
			// 调用分页插件初始化列表
			button_util.submitRest(this.url,this.listUrl,'PUT');
		},
		// 返回按钮事件
		goback : function(){
			button_util.winToUrl(this.listUrl);
		}
	};
