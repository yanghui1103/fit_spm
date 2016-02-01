/**
 * 商业主体列表
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
		arr[0] = "205";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#companylistBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		arr[0] = "AREA";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#areaCd"),"getSysItems.action","1",  eJson);
		 
	});

baidu("#qry205").click(function(){
	qryCompanyListByPageNo("0");
	
});
function qryCompanyListByPageNo(pageNo){
	var area = $("#areaCd").val();
	var array = new Array(area,pageNo,total);
	createJsonAndAjax(
			'qryCompanyList.action',
			array,
			dealResult,'JSON'
    );
}
function dealResult(data){
	var $tbody = $("#companyListTbody");
	$tbody.empty();
	if(data.res == "2"){		
		var jsonArr = data.list;
		for(var i = 0 ;i<jsonArr.length;i++){
			var $tr = $("<tr>");
			$tr.append($("<td>").html("<input type=checkbox name=compfdid value='"+jsonArr[i].fdid+"'/>"));			
			$tr.append($("<td>").text(jsonArr[i].company_name));
			$tr.append($("<td>").text(jsonArr[i].area_name));
			$tr.append($("<td>").text(jsonArr[i].address));
			$tr.append($("<td>").text(jsonArr[i].state));
			$tr.append($("<td>").text(jsonArr[i].create_time));
			$tr.append($("<td>").text(jsonArr[i].staff_name));
			$tbody.append($tr)
		}
	}
}