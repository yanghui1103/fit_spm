/**
 * 新建社区资料
 */
baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "201";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#createVillageSubBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		var arr = new Array(20);
		 for(var i in arr.length){
			 arr[i] = "-9";		 
		 }arr[0] = "ALLCOMPANY";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#create_village_comp"),"getSysItems.action","1",  eJson);
	});
baidu.dom('#save201').on('click', function() {
	if($("#create_village_comp").val()=="-9"){
		alertMsg.info("请选择主体");return ;
	}
	if($("#create_villagename").val()==""){
		alertMsg.info("请填写住区名称");return ;
	}
	var array = new Array($("#create_villagename").val(),$("#create_villageAddress").val(),$("#create_village_comp").val());
	createJsonAndAjax(
			'createVillageInfo.action',
			array,
			dealResult,'JSON'
    );
});
function dealResult(data){
	alertToUserMsg(data);	
}