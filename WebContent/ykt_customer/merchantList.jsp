<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta charset="utf-8" />
<style type="text/css">
    html, body { color:#222; font-family:Microsoft YaHei, Helvitica, Verdana, Tohoma, Arial, san-serif; margin:0; padding: 0; text-decoration: none; }
    img { border:0; }
    ul { list-style: none outside none; margin:0; padding: 0; }
    body {
        background-color:#eee;
    }
    body .mainmenu:after { clear: both; content: " "; display: block; }
 
    body .mainmenu li{
        float:left;
        margin-left: 2.5%;
        margin-top: 2.5%;
        width: 30%; 
        border-radius:3px;
        overflow:hidden;
    }
 
    body .mainmenu li a{ display:block;  color:#FFF;   text-align:center }
    body .mainmenu li a b{
        display:block; height:80px;
    }
    body .mainmenu li a img{
        margin: 15px auto 15px;
        width: 30px;
        height: 30px;
    }
 
    body .mainmenu li a span{ display:block; height:30px; line-height:30px;background-color:#FFF; color: #999; font-size:14px; }
 
    body .mainmenu li:nth-child(8n+1) {background-color:#36A1DB}
    body .mainmenu li:nth-child(8n+2) {background-color:#678ce1}
    body .mainmenu li:nth-child(8n+3) {background-color:#8c67df}
    body .mainmenu li:nth-child(8n+4) {background-color:#84d018}
    body .mainmenu li:nth-child(8n+5) {background-color:#14c760}
    body .mainmenu li:nth-child(8n+6) {background-color:#f3b613}
    body .mainmenu li:nth-child(8n+7) {background-color:#ff8a4a}
    body .mainmenu li:nth-child(8n+8) {background-color:#fc5366}
</style>
<script type="text/javascript" >
/*
* 智能机浏览器版本信息:
*
*/
  var browser={
    versions:function(){ 
           var u = navigator.userAgent, app = navigator.appVersion; 
           return {//移动终端浏览器版本信息 
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
            };
         }(),
         language:(navigator.browserLanguage || navigator.language).toLowerCase()
} 

function initIndex(){
	if(!browser.versions.mobile){
		alert("您的浏览器版本过低，建议更换使用手机或更高版本浏览器访问！");
		return ;
	}
}
</script>
  </head>
<body onload="initIndex();">
    <ul class="mainmenu">
        <li><a href="http://www.boweikeji.cn/index.php?m=content&c=index&a=show&catid=24&id=25" ><b><img src="../images/tb06.png" /></b><span>一卡通介绍</span></a></li>
        <li><a href="promiseItems.jsp" ><b><img src="../images/tb01.png" /></b><span>承诺与规则</a></li>
        <li><a href="merchantList2.jsp" ><b><img src="../images/tb02.png" /></b><span>联盟商户</span></a></li>
        <li><a href="consumeRecords.jsp" ><b><img src="../images/tb03.png" /></b><span>消费查询</span></a></li>
        <li><a href="#" ><b><img src="../images/tb05.png" /></b><span>提成使用</span></a></li>
        <li><a href="#" ><b><img src="../images/tb04.png" /></b><span>修改密码</span></a></li>
        <li><a href="#" ><b><img src="../images/tb06.png" /></b><span>联系我们</span></a></li>
        <li><a href="http://www.boweikeji.cn/index.php?m=content&c=index&a=lists&catid=3" ><b><img src="../images/tb06.png" /></b><span>关于公司</span></a></li>
    </ul>
</body>
</html>
