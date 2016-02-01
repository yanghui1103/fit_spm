/**
 *  持卡人查看商户列表
 */

baidu.dom().ready(function(){	
	// 渲染按钮区域
	var arr = new Array(20);
	for ( var i=0;i<arr.length;i++) {
		arr[i] = "-9";
	}
	arr[0] = "AREA";
	alert(arr);
	 eobj = createParamJson(arr); 
	 var eJson = JSON.stringify(eobj);  
	 alert(eJson);
	 takeTypeDataListV2($("#area_list"),"getSysItems.action","1",  eJson);		 
	});