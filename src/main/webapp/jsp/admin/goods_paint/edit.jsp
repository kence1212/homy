<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台管理系统-HTML5后台管理系统</title>
<meta name="keywords"  content="设置关键词..." />
<meta name="description" content="设置描述..." />
<meta name="author" content="DeathGhost" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<jsp:include page="../common/_cssjs.jsp"></jsp:include>
<jsp:include page="../common/_validformcssjs.jsp"></jsp:include>
</head>
<body>
<div class="main-wrap">
	<div class="page-wrap">
		<div class="clear mb-10">
			<div class="fl">
				<a href="index.html"><button class="btn btn-secondary" ><i class="icon-chevron-left"></i>返回</button></a>
			</div>
		</div>
		<!--开始::内容-->
		<section class="page-hd">
			<header>
				<h2 class="title">编辑油漆</h2>
			</header>
			<hr>
		</section>
		<form action="edit.html" class="layui-form" method="POST">
		<input type="hidden" name="id" value="${bean.id }">
		<div class="form-group-col-2">
			<div class="form-label">油漆名称：</div>
			<div class="form-cont">
				<input type="text" name="paintName" value="${bean.paintName }" lay-verify="required|paintName" placeholder="油漆名称" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">油漆代码：</div>
			<div class="form-cont">
				<input type="text" name="paintCode" value="${bean.paintCode }"  lay-verify="required|paintCode" placeholder="油漆代码" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">油漆描述：</div>
			<div class="form-cont">
				<input type="text" name="paintDesc" value="${bean.paintDesc }" placeholder="油漆描述" class="form-control form-boxed">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label"></div>
			<div class="form-cont">
				<input type="submit" lay-submit lay-filter="*" class="btn btn-primary" value="修改" />
			</div>
		</div>
		<!--开始::结束-->
		</form>
	</div>
</div>

<div class="mask"></div>
<script>
$(function(){
	layui.use('form', function(){
	  var form = layui.form;
	  form.verify({
		  paintName:function(value,item){
			  if(value.length>20){
				  return '油漆名称长度不能超过20个字符';
			  }
		  },
		  paintCode:function(value,item){
			  if(value.length>20){
				  return '油漆代码长度不能超过20个字符';
			  }
		  }
	  });
	  form.on('submit(*)', function(data){
		  parent.layer.load(3,{shade:[0.5,'#000'],time:0});
		  $('.layui-form').submit();
	  });  
	});
})
</script>

</body>
</html>