<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<script language="javascript" type="text/javascript" >
$(document).ready(function(){ 
	var arr = new Array(20);
	 for(var i in arr.length){
		 arr[i] = "-9";		 
	 }arr[0] = "ROLE";
	 var eobj = createParamJson(arr); 
	 var eJson = JSON.stringify(eobj); 
	 takeTypeDataListV2($("#giveRole_role_cd"),'getSysItems.action','0',eJson);	
}); 

$('#treeAuth').click(function(){	
		$('#dog').attr("href","system/pwrSet.jsp");		
	
});
    function getZtree(){    
    	window.showModalDialog("system/pwrSet.jsp",document.getElementById('giveRole_role_cd').value,"height:300, width:580, minH:40, minW:50, total:20, max:false, mask:false, resizable:true, drawable:true, maxable:true,minable:true,fresh:true");
    }
</script>
  </head>
  
  <body>
<div class="pageFormContent" id="roleBox" >
			<p>
				<label>待选角色：</label>
				<select name="giveRole_role_cd" id="giveRole_role_cd" class="required combox"  >
				</select>
			</p>
			<p>
				<input type="hidden" id="org_id" readonly  />
				<input type="hidden"  id="org_name"   readonly  />
				<a class="btnLook" href="system/pwrSet.jsp" lookupGroup="orgLookup">查找带回</a>		
			</p>
			<!-- div>
					<a class="button" id="dog"     target="dialog" rel="dlg_page2"  ><span id="treeAuth">权限树</span></a>
			
			
				<input 
					type="button"  value="获取权限树" class="button-long"
					onClick="getZtree();"  /></div-->
		</div>		
  </body>
</html>
