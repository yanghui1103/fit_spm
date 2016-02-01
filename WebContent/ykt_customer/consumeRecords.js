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
		for ( var i in arr.length) {
			arr[i] = "-9";
		}
		arr[0] = "302";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#consumeListBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		baidu.dom("#qry302").click(function(){
			qryCardConsumeRecords(pageNo);
			pageNo = "0";
		});
	});


function qryCardConsumeRecords(pageNo){
	var audit_person = $("#audit_person").val()==""?"-9":$("#audit_person").val() ;
	var audit_date = $("#audit_date").val()==""?"-9":$("#audit_date").val() ;
	var audit_merchant = $("#audit_merchant").val()==""?"-9":$("#audit_merchant").val() ; 
	var array = new Array(audit_person,audit_date,audit_merchant,pageNo,pageNo+ total);
	createJsonAndAjax(
			'qryCardConsumeRecords.action',
			array,
			dealQryCardConsumeRecords,'JSON'
    );
}
function dealQryCardConsumeRecords(data){
	pageTotalNo = data.pageTotalNo ;// 总页数 
	var $tbody = $("#consumeListTbody");
	$tbody.empty();
	if(data.res == "2"){		
		var jsonArr = data.list; 
		for(var i = 0 ;i<jsonArr.length;i++){	
			var $tr = $("<tr>");	
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

baidu.dom("#consumeFirstPage").click(function(){
	qryCardConsumeRecords("0");
	pageNo ="0";
});
baidu.dom("#consumeEndPage").click(function(){
	qryCardConsumeRecords((pageTotalNo-1)*15);
	pageNo = pageTotalNo-1;
});
baidu.dom("#consumeBeforePage").click(function(){
	qryCardConsumeRecords((pageNo-1)*15);
	pageNo = pageNo-1;
});
baidu.dom("#consumeNextPage").click(function(){ 
	qryCardConsumeRecords((pageNo+1)*15);
	pageNo = pageNo+1;
});