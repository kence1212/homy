<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>登录-后台管理系统</title>
<meta name="keywords"  content="设置关键词..." />
<meta name="description" content="设置描述..." />
<meta name="author" content="DeathGhost" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name='apple-touch-fullscreen' content='yes'>
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="address=no">
<jsp:include page="common/_cssjs.jsp"></jsp:include>
<script>
if(!(window.top==window.self))   
    window.parent.window.location.reload();
</script>
</head>
<body class="login-page">
	<section class="login-contain">
		<header>
			<h1>后台管理系统</h1>
			<p>management system</p>
		</header>
		<div class="form-content">
		<form action="<%=contextPath%>/qem/logins.html" method="post">
			<ul>
				<li>
					<div class="form-group">
						<label class="control-label">管理员账号：</label>
						<input type="text" name="username" placeholder="管理员账号..." class="form-control form-underlined" id="adminName"/>
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="control-label">管理员密码：</label>
						<input type="password" name="password"  placeholder="管理员密码..." class="form-control form-underlined" id="adminPwd"/>
					</div>
				</li>
				<!-- <li>
					<label class="check-box">
						<input type="checkbox" name="remember"/>
						<span>记住账号密码</span>
					</label>
				</li> -->
				<li>
					<button type="submit" class="btn btn-lg btn-block" id="entry">立即登录</button>
				</li>
				<li>
					<p class="btm-info">©Copyright 2006-2010 <a href="#" target="_blank" title="DeathGhost">homy.cn</a></p>
					<address class="btm-info">广东省深圳市龙岗区</address>
				</li>
			</ul>
		</form>
		</div>
	</section>
<div class="mask"></div>
<div class="dialog">
	<div class="dialog-hd">
		<strong class="lt-title">标题</strong>
		<a class="rt-operate icon-remove JclosePanel" title="关闭"></a>
	</div>
	<div class="dialog-bd">
		<!--start::-->
		<p>这里是基础弹窗,可以定义文本信息，HTML信息这里是基础弹窗,可以定义文本信息，HTML信息。</p>
		<!--end::-->
	</div>
	<div class="dialog-ft">
		<button class="btn btn-info JyesBtn">确认</button>
		<button class="btn btn-secondary JnoBtn">关闭</button>
	</div>
</div>
</body>
</html>