<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String contextPath = request.getContextPath();%>
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
                <a href="index.html"><button class="btn btn-secondary"><i class="icon-chevron-left"></i>返回</button></a>
            </div>
        </div>
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">编辑APP应用</h2>
            </header>
            <hr>
        </section>
        <form id="editForm" action="add.html" class="layui-form" method="POST">
        
        <input type="hidden" name="id" value="${bean.id }">
        
        <div class="form-group-col-2">
            <div class="form-label">包名：</div>
            <div class="form-cont">
                <input type="text" value="${bean.appCode }" placeholder="包名" name="appCode" lay-verify="required|appCode" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">版本数字号：</div>
            <div class="form-cont">
                <input type="text" value="${bean.appVersion }" placeholder="版本数字号" name="appVersion" lay-verify="required|number|appVersion" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">版本名称：</div>
            <div class="form-cont">
                <input type="text" value="${bean.appVersionStr }" placeholder="版本名称" name="appVersionStr" lay-verify="required|appVersionStr" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
        	<label class="form-label">类型：</label>
		    <div class="layui-input-block">
		      <input type="radio" name="appType" value="1" title="Android" <c:if test="${bean.appType == 1 }">checked</c:if> />
		      <input type="radio" name="appType" value="0" title="IOS" <c:if test="${bean.appType == 0 }">checked</c:if>  />
		    </div>
		</div>
		<div class="form-group-col-2">
			<label class="form-label">是否强制更新：</label>
		    <div class="layui-input-block">
		      <input type="radio" name="compelUpdate" value="1" title="是" <c:if test="${bean.compelUpdate == true }">checked</c:if> />
		      <input type="radio" name="compelUpdate" value="0" title="否" <c:if test="${bean.compelUpdate == false }">checked</c:if> />
		    </div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">下载地址：</div>
			<div class="form-cont">
				<input type="text" value="${bean.downloadUrl }" placeholder="下载地址..." name="downloadUrl" lay-verify="required|downloadUrl" class="form-control form-boxed">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">更新介绍：</div>
			<div class="form-cont">
				<textarea class="form-control form-boxed" name="appInfo">${bean.appInfo }</textarea>
			</div>
		</div>
        
        <div class="form-group-col-2">
            <div class="form-label"></div>
            <div class="form-cont">
                <input type="button" lay-submit lay-filter="*" class="btn btn-primary" value="编辑" />
            </div>
        </div>
        </form>
        <!--开始::结束-->
    </div>
</div>
<script type="text/javascript">

$(function(){
	layui.use('form', function(){
	  var form = layui.form;
	  form.verify({
		  appCode:function(value,item){
			  if(value.length>20){
				  return '包名长度不能超过20个字符';
			  }
		  },
		  appVersion:function(value,item){
			  if(value.length>9){
				  return '版本数字号不能超过9位数字';
			  }
		  },
		  appVersionStr:function(value,item){
			  if(value.length>60){
				  return '版本名称不能超过60个字符';
			  }
		  },
		  downloadUrl:function(value,item){
			  if(value.length>200){
				  return '下载地址不能超过200个字符';
			  }
		  }
	  });
	  form.on('submit(*)', function(data){
		  
		  parent.layer.confirm("确定编辑吗？",{},function(){
		  $.post("<%=request.getContextPath()%>"+"/adapp/edit.html",
				  $("#editForm").serialize(),
				  function(data){
					  if(data.success){
						  parent.layer.closeAll();
						  location.href="index.html";
					  } else{
						  parent.layer.alert(data.msg)
					  }
				  },"json")
		  })
	  });
	});
})




</script>
</body>
</html>