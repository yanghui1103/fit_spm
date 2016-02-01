/**
 * 消费记录A
 */
baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "301";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#singleConsumeSubBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		baidu("#sconsume_code").focus();
	});
baidu("#save301").click(function(){
	var value = baidu('#sconsume_code').val();var  result = baidu.type(eval(value));
	var value2 = baidu('#sconsume_jifen').val();var  result2 = baidu.type(eval(value2)); 

	if($("#sconsume_code").val()==""||$("#sconsume_jifen").val()==""||result!="number"||result2!="number"){
		alertMsg.info("请确保非空项有数据，且为数字");return ;
	}
	if(!confirm("商户，您确认录入本次卡号:"+$("#sconsume_code").val()+"的消费记录吗?")){
		return ;
	}
	 var array = new Array($("#sconsume_code").val(),$("#sconsume_jifen").val(),"SINGLE" ); // 单笔录入
		createJsonAndAjax(
				'createSingleConsumeRecord.action',
				array,
				dealResult,'JSON'
	    );
	});
	function dealResult(data){
		alertToUserMsg(data);		
	}
	baidu.dom("#getBtn").click(function(){
		getPersonInfoByCard();
	});
	function getPersonInfoByCard(){
		var sconsume_code = baidu.dom("#sconsume_code").val();
		if(sconsume_code==""){alertMsg.info("请刷卡或输入卡号");return ;}
		var array = new Array($("#sconsume_code").val() );
		createJsonAndAjax(
				'getPersonInfoByCard.action',
				array,
				viewPersonInfo,'JSON'
	    );
	}
	function viewPersonInfo(data){
		if(data.res == "2"){
			var array = data.list ;
			baidu.dom("#sconsume_user").val(array[0].person_name);
			baidu.dom("#sconsume_phone").val(array[0].card_phone);
		}else{
			alertMsg.error("卡号无效");
		}
	}