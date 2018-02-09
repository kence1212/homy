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
				<h2 class="title">编辑权限模块</h2>
			</header>
			<hr>
		</section>
		<form id="saveForm" action="edit.html" class="layui-form" method="POST">
		<input type="hidden" name="id" value="${bean.id }">
		<div class="form-group-col-2">
			<div class="form-label">模块名称：</div>
			<div class="form-cont">
				<input type="text" name="name" value="${bean.name }" lay-verify="required|name" placeholder="模块名称" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">模块编码：</div>
			<div class="form-cont">
				<input type="tel" name="code" value="${bean.code }" lay-verify="required|code" placeholder="模块编码" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">模块样式：</div>
			<div class="form-cont">
				<input type="tel" name="iconClass" value="${bean.iconClass }" lay-verify="required|iconClass" placeholder="模块样式" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">父级模块：</div>
			<div class="form-cont">
			  <select style="width:auto;" name="pid">
                    <option value="">请选择</option>
                	<c:forEach items="${pModels }" var="cate">
                    <option value="${cate.id }" <c:if test="${bean.pid == cate.id }">selected="selected"</c:if>>${cate.name }</option>
                	</c:forEach>
                </select>
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label"></div>
			<div class="form-cont">
				<input type="button" lay-submit lay-filter="*" class="btn btn-primary" value="编辑" />
			</div>
		</div>
		<!--开始::结束-->
		</form>
	</div>
</div>

<div class="mask"></div>
</body>
<script type="text/javascript">
$(function(){

	layui.use('form', function(){
	  var form = layui.form;
	  form.verify({
		  name:function(value,item){
			  if(value.length>60){
				  return '模块名称长度不应超过60个字符';
			  }
		  },
		  code:function(value,item){
			  if(value.length>20){
				  return '模块编码长度不应超过20个字符';
			  }
		  },
		  iconClass:function(value,item){
			  if(value.length>30){
				  return '模块样式长度不应超过30个字符';
			  }
		  }
	  });
	  form.on('submit(*)', function(data){   
		/*   parent.layer.load(3,{shade:[0.5,'#000'],time:0});
		  $('.layui-form').submit(); */
		  parent.layer.confirm("确定编辑吗？",{},function(){
			  $.post("<%=request.getContextPath()%>"+"/ampmodel/edit.html",
					  $("#saveForm").serialize(),
					  function(data){
						  if(data.resultCode == "10000"){
							  parent.layer.closeAll();
							  location.href="index.html";
						  } else{
							  parent.layer.alert(data.errorMsg)
						  }
					  },"json")
			  })
		  
	  });  
	});
});



</script>
</html>