/**
 *  社区列表
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
		arr[0] = "206";
		var eobj = createParamJson(arr);
		var eJson = JSON.stringify(eobj);
		renderBtnsToDiv($("#village_list_Bar"),
				'getAuthorityBtnsByThisUser.action', eJson);	
		arr[0] = "ALLCOMPANY";
		 eobj = createParamJson(arr); 
		 var eJson = JSON.stringify(eobj);  
		 takeTypeDataListV2($("#village_list_company"),"getSysItems.action","1",  eJson);		 
	});
baidu("#qry206").click(function(){
	qryVillageListByPageNo("0");	
});


function qryVillageListByPageNo(pageNo){
	var village_list_company = $("#village_list_company").val();
	var array = new Array(village_list_company,pageNo,total);
	createJsonAndAjax(
			'qryVillageList.action',
			array,
			dealVillageResult,'JSON'
    );
}
function dealVillageResult(data){
	var $tbody = $("#village_listTbody");
	$tbody.empty();
	if(data.res == "2"){		
		var jsonArr = data.list;
		for(var i = 0 ;i<jsonArr.length;i++){
			var $tr = $("<tr>");
			$tr.append($("<td>").html("<input type=checkbox name=compfdid value='"+jsonArr[i].fdid+"'/>"));			
			$tr.append($("<td>").text(jsonArr[i].village_name));
			$tr.append($("<td>").text(jsonArr[i].village_address));
			$tr.append($("<td>").text(jsonArr[i].company_name));
			$tr.append($("<td>").text(jsonArr[i].create_time));
			$tr.append($("<td>").text(jsonArr[i].creator));
			$tbody.append($tr)
		}
	}
}