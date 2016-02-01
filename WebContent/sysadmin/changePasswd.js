/**
 * 修改密码
 */
	baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "119";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#changePsdSubBar"),
				'getAuthorityBtnsByThisUser.action', eJson);
		
	});
	
	
	baidu.dom('#save119').on('click', function() {
		if($("#password_1").val()==""||$("#password_1").val()!=$("#password_2").val()){
				alertMsg.info("请输入密码，并保证与重复密码一致");
				return;
		}
		var array = new Array($("#password_1").val(),$("#password_2").val());
		createJsonAndAjax(
				'changeUserPasswd.action',
				array,
				dealResult,'JSON'
	    );
	});
	function dealResult(data){
		alertToUserMsg(data);
		
	}