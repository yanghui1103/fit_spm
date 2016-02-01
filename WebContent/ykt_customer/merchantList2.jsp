<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta id="viewport" name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="MobileOptimized" content="320">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath %>/common/js/common.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/common/js/tangram-min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
function init_list_page(){
	var arr = new Array(20);
	for ( var i=0;i<arr.length;i++) {
		arr[i] = "-9";
	}
	arr[0] = "AREA"; 
	 createJsonAndAjax(
				'getSysItems.action',
				arr,
				viewAreaList,'JSON'
	    );
}
function viewAreaList(data){ 
	for(var i=0;i<data.length;i++){
		baidu("#area_list").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
	}
}
function subQry(){ 
	var area = baidu("#area_list").val(); 
	var array = new Array(area);
	 createJsonAndAjax(
				'getCompanyListByArea.action',
				array,
				viewBussinessList,'JSON'
	    );
}
function viewBussinessList(data){ 
	baidu("#result").empty();
	if(data.res =="2"){
		var list = data.list ; 
		for(var i=0;i<list.length;i++){
			var $tr = $("<tr>");
			$tr.append("<td>"+list[i].company_name+"</td>");
			$tr.append("<td>"+list[i].company_type_name+"</td>");
			$tr.append("<td>"+list[i].company_admin_phone+"</td>");
			baidu("#result").append($tr);
		}
	}else{
		alert("暂无数据");
	}
	
}
</script>
</head>
<body onload="init_list_page();">
	<div>
		<table width=100%>
			<tr>
				<td><select id="area_list" /></td>
				<td><input type=button onclick="subQry()" value="查询" /></td>
			</tr>
		</table>
		<table id="result" width=100%>
		</table>

	</div>
</body>
</html>