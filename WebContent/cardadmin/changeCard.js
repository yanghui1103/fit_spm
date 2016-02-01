/**
 *  补换卡
 */
baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "209";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#changeCardSubBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		
	});


baidu.dom("#getBtn2").click(function(){
	getPersonInfoByCard2();
});
function getPersonInfoByCard2(){
	var change_code = baidu.dom("#change_code").val();
	if(change_code==""){alertMsg.info("请刷卡或输入卡号");return ;}
	var array = new Array($("#change_code").val() );
	createJsonAndAjax(
			'getPersonInfoByCard.action',
			array,
			viewPersonInfo2,'JSON'
    );
}
function viewPersonInfo2(data){
	if(data.res == "2"){
		var array = data.list ;
		baidu.dom("#change_user").val(array[0].person_name);
		baidu.dom("#change_phone").val(array[0].card_phone);
		baidu.dom("#change_sfz").val(array[0].card_sfz);
	}else{
		alertMsg.error("卡号无效");
	}
}




baidu("#save209").click(function(){
	var value = baidu('#change_code').val();var  result = baidu.type(eval(value)); 

	if($("#change_code").val()=="" ||result!="number" ){
		alertMsg.info("请确保非空项有数据，且为数字");return ;
	}
	if($("#change_card1").val()=="" ||$("#change_card2").val()!=$("#change_card1").val() ){
		alertMsg.info("请确保非空项有数据，且为数字");return ;
	}
	if(!confirm("您确认补换卡？原卡号:"+$("#change_code").val()+" ")){
		return ;
	}
	 var array = new Array($("#change_code").val(),$("#change_card1").val() ); // 单笔录入
		createJsonAndAjax(
				'changePersonCard.action',
				array,
				dealResultMsg,'JSON'
	    );
	});
	function dealResultMsg(data){
		alertToUserMsg(data);		
	}