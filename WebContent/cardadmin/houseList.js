/**
 *  住户列表
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
		arr[0] = "207";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#houseListBar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		arr[0] = "COMPANY";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#houselist_company"),"getSysItems.action","1",  eJson);		 
		 arr[0] = "VILLAGE";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#houselist_village"),"getSysItems.action","1",  eJson);		 
	});

baidu("#qry207").click(function(){
	qryHouseInfoListByPageNo("0");
	
});

function qryHouseInfoListByPageNo(pageNo){
	var houselist_name = $("#houselist_name").val()==""?"-9":$("#houselist_name").val() ;
	var array = new Array(houselist_name,$("#houselist_company").val(),$("#houselist_village").val(),pageNo,total);
	createJsonAndAjax(
			'qryHouseInfoList.action',
			array,
			dealHouseListResult,'JSON'
    );
}
function dealHouseListResult(data){
	var $tbody = $("#houseListTbody");
	$tbody.empty();
	if(data.res == "2"){		
		var jsonArr = data.list;
		for(var i = 0 ;i<jsonArr.length;i++){
			var $tr = $("<tr>");
			$tr.append($("<td>").html("<input type=checkbox name=cardfdid value='"+jsonArr[i].fdid+"'/>"));			
			$tr.append($("<td>").text(jsonArr[i].house_name));
			$tr.append($("<td>").text(jsonArr[i].village_name));
			$tr.append($("<td>").text(jsonArr[i].company_name));
			$tr.append($("<td>").text(jsonArr[i].area_name));
			$tr.append($("<td>").text(jsonArr[i].create_time));
			$tr.append($("<td>").text(jsonArr[i].creator)); 
			$tbody.append($tr);
		}
	}
}