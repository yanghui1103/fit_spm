<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
$(document).ready(function(){
	var arr = new Array(20);
	 for(var i in arr.length){
		 arr[i] = "-9";		 
	 }arr[0] = "106";
	 var eobj = createParamJson(arr); 
	 var eJson = JSON.stringify(eobj); 
	 renderBtnsToDiv($("#createNewCompSubBar"),'getAuthorityBtnsByThisUser.action',eJson);

});
$("#create106").click(function(){
	 // 新建机构
	if($("#create_company_name").val()=="" || $("#create_uporg_id").val()=="" ){
		alertMsg.error("请填写非空项！");
		return ;
	}
	// over validation
	 var arr = new Array(20);
	 for(var i in arr.length){
		 arr[i] = "-9";		 
	 }arr[0] = $("#create_company_name").val();
	 arr[1] = $("#create_uporg_id").val();
	 arr[2] = $("#createComp_address").val();
	 var eobj = createParamJson(arr); 
	 var eJson = JSON.stringify(eobj); 
	var ttDom = AjaxExchangeBackTextData('createNewSysOrg.action',eJson);   
	alertToUserMsg(ttDom);
});
</script>
</head>
<body>
<div class="pageContent">

<ul id="errorlist"></ul>
	<form method="post" action=""  id=createUserForm class="pageForm required-validate">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>组织编码：</label>
				<input   type="text" size="30" maxlength=10  class=required readonly alt="系统生成"/>
			</p>
			<p>
				<label>组织名称：</label>
				<input id="create_company_name" class="required" type="text"   maxlength=12  size="30"  alt="请输入组织名称"/>
			</p>
			<p>
				<label>上级组织名称：</label>
				<input type="text"  id="create_uporg_name" class="required" readonly name="orgLookup.orgName" value="" suggestFields="orgNum,orgName"   lookupGroup="orgLookup" />
				<a class="btnLook" href="system/getAllOrgs.html" lookupGroup="orgLookup">查找带回</a>		
			</p>
			<p>
				<label>上级组织编码：</label>
				<input type="text"  class="required" readonly="readonly"  id="create_uporg_id" name="orgLookup.id" />
			</p>
			<p>
				<label>地址：</label>
				<input type="text" id=createComp_address size="30" />
			</p>
		</div>
		<div class="formBar" id="createNewCompSubBar">
		</div>
	</form>
</div> 
</body>
</html>