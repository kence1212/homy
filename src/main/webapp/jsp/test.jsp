<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>登录-后台管理系统</title>
</head>

<body>

<form action="personUpdate.do" method="post" enctype="multipart/form-data">
	头像：<input type="file" name="img"/><br/>
	
	用户名：<input name="nickname"><br/>
	
	电话：<input name="sex"><br/>
	
	<input type="submit" value="submit">

</form>

</body>

</html>