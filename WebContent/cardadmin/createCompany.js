/**
 *  创建相关主体
 */
	baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "203";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#createNewCompSubBar"),
				'getAuthorityBtnsByThisUser.action', eJson);
		var arr = new Array(20);
		 for(var i in arr.length){
			 arr[i] = "-9";		 
		 }arr[0] = "COMPTYPE";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#create_type"),"getSysItems.action","1",  eJson);
		 for(var i in arr.length){
			 arr[i] = "-9";		 
		 }arr[0] = "AREA";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#create_areacd"),"getSysItems.action","1",  eJson);

	});
	baidu.dom('#save203').on('click', function() {
		var array = new Array($("#create_company_name").val(),$("#create_type").val(),$("#create_uporg_id").val(),
				$("#createComp_level").val(),$("#create_areacd").val(),$("#createComp_admin").val(),
				$("#createComp_adminphone").val(),$("#createComp_address").val());
		createJsonAndAjax(
				'createCompanyInfo.action',
				array,
				dealResult,'JSON'
	    );
	});
	function dealResult(data){
		alertToUserMsg(data);
		
	}