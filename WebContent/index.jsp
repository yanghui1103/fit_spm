<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="ie-comp">
<link rel="icon" href="uploadfile/icon.gif" type="images/gif"/>
<script type="text/javascript" src="common/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script> 
<title>商盟一卡通运营支撑系统</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />

</head>

<body style="position:fixed;width:100%;height:100%" >
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a><img src="themes/default/images/login_logo.jpg" /></a>
			</h1>
			<div class="login_headerContent"> 	 	
				<h2 class="login_title"><img src="themes/default/images/login_title.jpg" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="userLoginStyleStaff.action" method=post>
					<p>
						<label>帐号：</label>
						<input type="text" id= "j_username" name="j_username" size="20"    class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" id="j_password" name="j_password" size="20"  maxlength=6 class="login_input" />
					</p>
					<div class="login_bar">
						<input class="sub" type="submit"  onclick="Save();" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img  src="themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a></a></li>
				</ul>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2016 博为科技公司(业务电话：0477-2233534). All Rights Reserved.
		</div>
	</div>
</body>
</html>