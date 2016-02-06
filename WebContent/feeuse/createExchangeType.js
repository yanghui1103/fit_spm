/**
 * 创建兑换类型
 */
baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "400";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#createFeeUseSubBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		baidu.dom("#save400").click(function(){
			var createType_name = baidu("#createType_name").val();
			var createType_formula = baidu("#createType_formula").val();
			var createUse_org_id = baidu("#createUse_org_id").val();
			var array = new Array(createType_name,createType_formula,createUse_org_id);
			createJsonAndAjax(
					'createCompanyFeeUse.action',
					array,
					dealCreateFeeUse,'JSON'
		    );
		});
	});


function dealCreateFeeUse(data){
	alertToUserMsg(data);		
}