/**
 *  稽核消费记录
 */
	var total = 15; // 共15条
	var actualTotal = 0;
	var pageNo = 0; //当前页数
	var pageTotalNo = 0; //总页数
baidu.dom().ready(function(){	
		// 渲染按钮区域
		var arr = new Array(20);
		for ( var i=0;i<20;i++) {
			arr[i] = "-9";
		}
		arr[0] = "304";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#adminConsumeListBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		baidu.dom("#qry304").click(function(){
			qryAdminConsumeRecords(pageNo);
			pageNo = "0";
		});
	});


function qryAdminConsumeRecords(pageNo){

	var admin_person = $("#admin_person").val()==""?"-9":$("#admin_person").val() ;
	var admin_start = $("#admin_start").val()==""?"-9":$("#admin_start").val() ; 
	var admin_end = $("#admin_end").val()==""?"-9":$("#admin_end").val() ;
	var admin_merchant = $("#admin_merchant").val()==""?"-9":$("#admin_merchant").val() ; 
	var array = new Array(admin_person,admin_start,admin_end,admin_merchant,pageNo,pageNo+ total);

	createJsonAndAjax(
			'qryAdminConsumeRecords.action',
			array,
			dealQryAdminConsumeRecords,'JSON'
    );
}
baidu.dom("#allRecords").click(function(){
	var checked = $(this).prop("checked");
	if(checked){
		baidu.dom("input[name=records]").prop("checked",checked);
	}else {
		baidu.dom("input[name=records]").removeAttr("checked");
	} 
}); 
function dealQryAdminConsumeRecords(data){
	pageTotalNo = data.pageTotalNo ;// 总页数 
	var $tbody = $("#adminConsumeListTbody");
	$tbody.empty();
	if(data.res == "2"){		
		var jsonArr = data.list; 
		for(var i = 0 ;i<jsonArr.length;i++){	
			var $tr = $("<tr>");	
			$tr.append($("<td>").html("<input type=checkbox name=records value='"+(jsonArr[i].fdid)+"'  />"));
			$tr.append($("<td>").text(jsonArr[i].person_name));
			$tr.append($("<td>").text(jsonArr[i].card_code));
			$tr.append($("<td>").text(jsonArr[i].card_phone));
			$tr.append($("<td>").text(jsonArr[i].account_fee));
			$tr.append($("<td>").text(jsonArr[i].company_name));
			$tr.append($("<td>").text(jsonArr[i].area_name));
			$tr.append($("<td>").text(jsonArr[i].state));
			$tr.append($("<td>").text(jsonArr[i].create_time));
			$tr.append($("<td>").text(jsonArr[i].creator));
			$tbody.append($tr)
		}
		
	}
}

baidu.dom("#adminConsumeFirstPage").click(function(){
	qryAdminConsumeRecords("0");
	pageNo ="0";
});
baidu.dom("#adminConsumeEndPage").click(function(){
	qryAdminConsumeRecords((pageTotalNo-1)*15);
	pageNo = pageTotalNo-1;
});
baidu.dom("#adminConsumeBeforePage").click(function(){
	qryAdminConsumeRecords((pageNo-1)*15);
	pageNo = pageNo-1;
});
baidu.dom("#adminConsumeNextPage").click(function(){ 
	qryAdminConsumeRecords((pageNo+1)*15);
	pageNo = pageNo+1;
});