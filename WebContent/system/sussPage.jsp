<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">       
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript">
    function clos(){
      //var parentWin = window.opener ;
      // parentWin.reshParent();
      //parentWin.close();      
      // alert(parentWin.document.getElementById('username').value );
      window.close(); 

    }
  </script>
  </head>
   

    上传成功!
    <table>
       <tr>
         <td>
            <input type= button   value = "关闭页面"/>
         </td>
       </tr>
    </table>
  </body>
</html>
