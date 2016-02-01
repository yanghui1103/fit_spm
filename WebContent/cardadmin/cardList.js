/**
 * 卡查询
 */
	var total = "15"; // 共15条
	var actualTotal = 0;
	var pageNo = 1; //当前页数
	var pageTotalNo = 1; //总页数
baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "208";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#cardistBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		arr[0] = "COMPANY";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#list_company"),"getSysItems.action","1",  eJson);
		 
	});

baidu("#delete208").click(function(){
	var temp = "";
	for(var i=0;i<$("input[name='cardfdid']:checked").length ;i++){
		temp = temp+ $("input[name='cardfdid']:checked").eq(i).val() + ",";
	}
	if(temp==""){alertMsg.info("请选择记录");return;}
	var array = new Array(temp);
	createJsonAndAjax(
			'deleteCardInfoList.action',
			array,
			dealMsg,'JSON'
    );
	} );
function dealMsg(data){
	alertToUserMsg(data);		
}
baidu("#qry208").click(function(){
	qryCardInfoListByPageNo("0");
	
});
function qryCardInfoListByPageNo(pageNo){
	var list_person_name = $("#list_person_name").val()==""?"-9":$("#list_person_name").val() ;
	var list_person_phone = $("#list_person_phone").val()==""?"-9":$("#list_person_phone").val() ;
	var list_person_card = $("#list_person_card").val()==""?"-9":$("#list_person_card").val() ;
	var list_company = $("#list_company").val();
	var array = new Array(list_person_name,list_company,pageNo,total,list_person_phone,list_person_card);
	createJsonAndAjax(
			'qryCardInfoList.action',
			array,
			dealResult,'JSON'
    );
}
function dealResult(data){
	var $tbody = $("#cardListTbody");
	$tbody.empty();
	if(data.res == "2"){		
		var jsonArr = data.list;
		for(var i = 0 ;i<jsonArr.length;i++){
			var $tr = $("<tr>");
			$tr.append($("<td>").html("<input type=checkbox name=cardfdid value='"+jsonArr[i].fdid+"'/>"));			
			$tr.append($("<td>").text(jsonArr[i].person_name));
			$tr.append($("<td>").text(jsonArr[i].card_code));
			$tr.append($("<td>").text(jsonArr[i].phone));
			$tr.append($("<td>").text(jsonArr[i].village_name));
			$tr.append($("<td>").text(jsonArr[i].company_name));
			$tr.append($("<td>").text(jsonArr[i].area_name));
			$tr.append($("<td>").text(jsonArr[i].create_time));
			$tr.append($("<td>").text(jsonArr[i].staff_name));
			$tbody.append($tr);
		}
	}
}