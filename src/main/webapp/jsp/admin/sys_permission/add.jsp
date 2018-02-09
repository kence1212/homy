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
				<a href="index.html?modelId=${modelId}"><button class="btn btn-secondary" ><i class="icon-chevron-left"></i>返回</button></a>
			</div>
		</div>
		<!--开始::内容-->
		<section class="page-hd">
			<header>
				<h2 class="title">新增权限</h2>
			</header>
			<hr>
		</section>
		<form  id="saveForm" class="layui-form" method="POST">
		
		<div  class="form-group-col-2">
		<input id="permissionModelId" type="hidden" name="permissionModelId" value="${modelId }">
		<%-- <div class="form-label">顶级模块：</div>
			<div class="form-cont">
			  <select lay-filter='modelId' style="width:auto;" >
                    <option value="0">请选择</option>
                	<c:forEach items="${models }" var="cate">
                    <option value="${cate.id }">${cate.name }</option>
                	</c:forEach>
                </select>
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">父级模块：</div>
			<div class="form-cont">
			  <select lay-filter='secondModelId' id="secondModelId" style="width:auto;" >
                    <option value="0">请选择</option>
                </select>
			</div>
		</div> --%>
		
		<div class="form-group-col-2">
			<div class="form-label">权限名称：</div>
			<div class="form-cont">
				<input type="text" name="permissionName" lay-verify="permissionName"  placeholder="权限名称" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div  class="form-group-col-2">
			<div class="form-label">权限url：</div>
			<div class="form-cont">
				<input type="tel"  name="permissionUri" lay-verify="permissionUri" placeholder="权限url" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label"></div>
			<div class="form-cont">
				<input type="button" lay-submit lay-filter="*" class="btn btn-primary" value="新建" />
			</div>
		</div>
		<!--开始::结束-->
		</form>
	</div>
</div>

<div class="mask"></div>
</body>
<script type="text/javascript">

//选择顶级模块时渲父级模块
<%-- layui.use(['form','jquery'],function(){
	var form = layui.form,$=layui.jquery;
	form.on('select(modelId)',function(data){
		var v = data.value;
		$("#permissionModelId").val(v);
		if(v==0){
			$("#secondModelId").html('<option value="0">请选择模块</option>');
			form.render('select');
			return ;
		}
		 $.post("<%=request.getContextPath()%>/adpermission/getSecondModel.html",
			 {pid: v},
			function(data){
				$("#secondModelId").html('');
				var option = '<option value="0">请选择模块</option>';
				for(var index in data){
					option += '<option value='+data[index].id+'>'+data[index].name+'</option>';
				}
				$("#secondModelId").html(option);
				form.render('select');
			},"json" 
		) 
		
	});
	form.on('select(secondModelId)',function(data){
		var v = data.value;
		$("#permissionModelId").val(v);
	});
}); --%>




$(function(){
	
	layui.use('form', function(){
	  var form = layui.form;
	  form.verify({
		  permissionName:function(value,item){
			  if(value.length>60){
				  return '权限名称长度不应超过60个字符';
			  }
		  },
		  permissionUri:function(value,item){
			  if(value.length>60){
				  return '权限url长度不应超过60个字符';
			  }
		  }
	  });
	  form.on('submit(*)', function(data){   
		var type = $("#type").val();
		if(type == 0){
			//url权限验证
			if($("#permissionModelId").val()== 0){
				  parent.layer.alert("请选择权限模块");
				  return ;
	 		 }
		} else{
			//权限模块验证
		}
		
		/*   parent.layer.load(3,{shade:[0.5,'#000'],time:0});
		  $('.layui-form').submit(); */
		  parent.layer.confirm("确定保存吗？",{},function(){
			  $.post("<%=request.getContextPath()%>"+"/adpermission/add.html",
					  $("#saveForm").serialize(),
					  function(data){
						  if(data.resultCode == "10000"){
							  parent.layer.closeAll();
							  location.href="index.html?modelId=${modelId}";
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