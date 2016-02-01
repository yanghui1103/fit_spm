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
<meta name="renderer" content="ie-comp">
<meta name="MobileOptimized" content="320">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath %>/common/js/common.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/common/js/tangram-min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
function qryConsumeList(){ 
	var card_code = baidu("#list_code").val()==""?"-9":baidu("#list_code").val(); 
	var pwd = baidu("#list_pwd").val()==""?"-9":baidu("#list_pwd").val(); 
	var array = new Array(card_code,pwd,baidu("#list_year").val(),baidu("#list_month").val());
	 createJsonAndAjax(
				'qryConsumeList.action',
				array,
				viewCcList,'JSON'
	    );
}
function viewCcList(data){
	baidu("#result").empty();
	if(data.res =="2"){
		var list = data.list ; 
		for(var i=0;i<list.length;i++){
			var $tr = $("<tr>");
			$tr.append("<td width=40%>"+list[i].company_name+"</td>");
			$tr.append("<td width=30%>"+list[i].create_time+"</td>");
			$tr.append("<td width=20%>"+list[i].account_fee+"</td>");
			$tr.append("<td width=10%>未知</td>");
			baidu("#result").append($tr);
		}
	}else{
		alert("无数据");
	}
}
</script>
</head>
<body>
	<div>
		<table>
		<tr>
				<td><label>卡号：</label> <input type=text id=list_code
				 size=10 /></td>
				<input type=hidden  id=list_pwd
					size=10 />
				<td><label>年份：</label> <select  id=list_year>
						<option value='2015'>2015</option>
						<option value='2016'>2016</option>
						<option value='2017'>2017</option>
						<option value='2018'>2018</option>
						<option value='2019'>2019</option>
						<option value='2020'>2020</option>
						<option value='2021'>2021</option>
						<option value='2022'>2022</option>
						<option value='2023'>2023</option>
						<option value='2024'>2024</option>
				</select></td>
				<td><label>月份：</label> <select  id=list_month>
						<option value='01'>1</option>
						<option value='02'>2</option>
						<option value='03'>3</option>
						<option value='04'>4</option>
						<option value='05'>5</option>
						<option value='06'>6</option>
						<option value='07'>7</option>
						<option value='08'>8</option>
						<option value='09'>9</option>
						<option value='10'>10</option>
						<option value='11'>11</option>
						<option value='12'>12</option>
				</select></td>			
			</tr></table>
			<div><input type=button onclick = "qryConsumeList()"  value="查询"/></div>
		</div>
	</div>
		<div>
			<table  width="100%" >
				<thead>
					<tr>
						<th width="40%">消费商户</th>
						<th width="30%">录入时间</th>
						<th width="20%">消费金额/积分</th>
						<th width="10%" >状态</th>
					</tr>
				</thead>				
			</table>
			<table id=result  width="100%" ></table>
		</div>
</body>
</html>