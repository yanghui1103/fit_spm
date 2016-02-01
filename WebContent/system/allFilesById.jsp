<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

	var foregin_id = <%=request.getParameter("foregin_id")%>;
	$(function(){  
		initUserInfoList($("#"+foregin_id+"Div").find("table").eq(2),"1",foregin_id);
		$("#"+foregin_id+"Div").find("button").eq(1).click(function(){		
			ajaxFileUpload(foregin_id);
		});
		$("#"+foregin_id+"Div").find("button").eq(0).click(function(){		
			alert("删除");
		});
		$("#"+foregin_id+"Div").find("button").eq(2).click(function(){		
			alert("下载");
		});
	});
	function initUserInfoList(obj,page_num,foregin_id){ 
		obj.find("tr").remove();
		//把本级机构及以下机构的人员查询出来
		var arr = new Array(20);
		 for(var i in arr.length){
			 arr[i] = "-9";		 
		 }arr[0] = foregin_id ; 
		 var eobj = createParamJson(arr); 	 
		 var eJson = JSON.stringify(eobj);   
		var ttDom = AjaxExchangeBackXMLDataByJson('getFileListByForeginId.action',eJson);    
	    var s = $.parseXML(ttDom.xml );
		   	$(s).find('file').each(function() {  
			 	 var table = obj; 
			 	 var xml = $(this);
			 	 var tr = "<tr class = list-blue target=sid_user><td width=110><input  class="+foregin_id+"CheckboxCtrl type=checkbox value="+xml.children("fileId").text()+
			 	 "></input><td width=160>"+xml.children("beforeFileName").text()+
			 	 "</td><td width=170>"+xml.children("createDate").text()+
			 	 "</td><td width=175>"+xml.children("creator").text()+
			 	 "</td><td width=160>"+xml.children("afterFileName").text()+
			 	 "</td></tr>";
			 	table.append(tr); 
		   }); 
	}	

</script> 
</head>
<body>
<div id="<%=request.getParameter("foregin_id")%>Div" class="pageContent">
		<div class="formBar"  >
    	 	<img src="system/loading.gif" id="loading" style="display: none;">
			<input type="file" id="file" name="file" /> 
		</div>
	 <table width="40%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						<div class="button"><div class="buttonContent deleteFileCss"><button>删除</button></div></div>
						
						<div class="button"><div class="buttonContent upFileCss"  ><button>上传</button></div></div>
						
						<div class="button"><div class="buttonContent downFileCss"><button>下载</button></div></div>
						</td>
					</tr>
	</table>
<div class="pageContent">	
	<table   class="table"  layoutH="30"  heigth="120" width="100%">
		<thead>
			<tr>			
				<th width="80">选择</th>
				<th width="120">文件名称</th>
				<th width="120">上传时间</th>
				<th width="120">上传者</th>
				<th width="120">说明</th>
			</tr>
		</thead>			
	</table>
	<table></table>
	</div>
</div>

</body>
</html>