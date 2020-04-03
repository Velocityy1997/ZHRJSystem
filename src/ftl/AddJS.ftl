/**
 * 管理新增JS
 * @auther 
 */
	var ${addJSName} = {
		url : '${simpleclassName?uncap_first}/add',
		listUrl : '${parentPackageName}/jsp/list_${simpleclassName?uncap_first}.jsp',
		// 提交按钮事件
		submitForm : function(){
			// 设置查询URL
			button_util.listUrl = this.listUrl; 
			// 调用分页插件初始化列表
			button_util.submitRest(this.url,this.listUrl,'POST');
		},
		// 返回按钮事件
		goback : function(){
			button_util.winToUrl(this.listUrl);
		}
	};
