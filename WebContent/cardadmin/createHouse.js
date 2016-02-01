/**
 * 创建家庭资料
 */
baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "202";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#createHouseSubBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		arr[0] = "VILLAGE";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#create_house_comp"),"getSysItems.action","1",  eJson);
		 
	});

 $("#save202").click(function(){
	 var array = new Array($("#create_housename").val(),$("#create_house_comp").val() );
		createJsonAndAjax(
				'createHouseInfo.action',
				array,
				dealResult,'JSON'
	    );
	});
	function dealResult(data){
		alertToUserMsg(data);		
	}