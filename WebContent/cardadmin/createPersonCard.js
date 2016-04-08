/**
 * 制卡
 */
baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "204";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#createCardSubBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		arr[0] = "VILLAGE";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#create_card_village"),"getSysItems.action","1",  eJson);
		 arr[0] = "HOUSE";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#create_card_house"),"getSysItems.action","1",  eJson);
		 
	});

$("#save204").click(function(){
	if($("#create_card_no").val()==""||$("#create_cardusername").val()==""||$("#create_carduserphone").val()==""||$("#create_carduserphone").val()==""
			||$("#create_card_house").val()=="-9"){
		alertMsg.info("请保证非空项有数据");return;
	}
	if(($("#create_carduserphone").val()).length != 11){
		alertMsg.info("持卡人电话应为11位");return;
	}
	 var array = new Array($("#create_card_no").val(),$("#create_card_house").val(),$("#create_cardusername").val(),
			 $("#create_carduserphone").val(),$("#create_carduserno").val());
		createJsonAndAjax(
				'createCardInfo.action',
				array,
				dealCreateResult,'JSON'
	    );
	});
	function dealCreateResult(data){
		alertToUserMsg(data);		
	}