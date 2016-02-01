/**
 * 商户查询消费记录
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
		arr[0] = "303";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#consumeQryBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		baidu.dom("#qry303").click(function(){
			qryCardConsumeRecords2(pageNo);
			pageNo = "0";
		});
	});


function qryCardConsumeRecords2(pageNo){
	var qry_person = $("#qry_person").val()==""?"-9":$("#qry_person").val() ;
	var qry_phone = $("#qry_phone").val()==""?"-9":$("#qry_phone").val() ;
	var qry_date = $("#qry_date").val()==""?"-9":$("#qry_date").val() ; 
	var array = new Array(qry_person,qry_phone,qry_date,pageNo,pageNo+ total);
	createJsonAndAjax(
			'qryCardConsumeRecords2.action',
			array,
			dealqryCardConsumeRecords2,'JSON'
    );
}
function dealqryCardConsumeRecords2(data){
	pageTotalNo = data.pageTotalNo ;// 总页数 
	var $tbody = $("#consumeTbody");
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
	qryCardConsumeRecords2("0");
	pageNo ="0";
});
baidu.dom("#consumeEndPage").click(function(){
	qryCardConsumeRecords2((pageTotalNo-1)*15);
	pageNo = pageTotalNo-1;
});
baidu.dom("#consumeBeforePage").click(function(){
	qryCardConsumeRecords2((pageNo-1)*15);
	pageNo = pageNo-1;
});
baidu.dom("#consumeNextPage").click(function(){ 
	qryCardConsumeRecords2((pageNo+1)*15);
	pageNo = pageNo+1;
});