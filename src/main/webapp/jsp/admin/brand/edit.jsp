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
				<a href="index.html"><button class="btn btn-secondary" ><i class="icon-chevron-left"></i>返回</button></a>
			</div>
		</div>
		<!--开始::内容-->
		<section class="page-hd">
			<header>
				<h2 class="title">编辑家具品牌</h2>
			</header>
			<hr>
		</section>
		<form action="edit.html" class="layui-form" method="POST">
		<input type="hidden" name="brandId" value="${bean.brandId }">
		<div class="form-group-col-2">
			<div class="form-label">品牌名称：</div>
			<div class="form-cont">
				<input type="text" name="brandName" value="${bean.brandName }" lay-verify="required|brandName"  placeholder="品牌名称" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">品牌代码：</div>
			<div class="form-cont">
				<input type="text" name="brandCode" value="${bean.brandCode }"  lay-verify="required|brandCode"  placeholder="品牌代码" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">品牌排序：</div>
			<div class="form-cont">
				<input type="text" lay-verify="required|number|tsort" name="tsort" value="${bean.tsort }"  placeholder="品牌排序" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">品牌图标：</div>
			<div id="icon_container" class="form-cont">
				<input type="text" style="display:none;" name="icon" value="${bean.icon }"  >
				<ul class="imgList">
					<li class="mb-10" id="iconPicks">
						<div class="uploadImg">
							<i class="iconfont2 addicon">&#xe931;</i>
							<i class="iconfont2 imgicon">&#xe915;</i>
						</div>
					</li>
					<li class="mb-10" id="iconShow">
						<img src="<%=request.getContextPath() %>${bean.icon }" height="100%" />
					</li>
				</ul>
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">app图标：</div>
			<div id="icon_container2" class="form-cont">
				<input type="text" style="display:none;" name="appClientIcon" value="${bean.appClientIcon }"  >
				<ul class="imgList">
					<li class="mb-10" id="iconPicks2">
						<div class="uploadImg">
							<i class="iconfont2 addicon">&#xe931;</i>
							<i class="iconfont2 imgicon">&#xe915;</i>
						</div>
					</li>
					<li class="mb-10" id="iconShow2">
						<img src="<%=request.getContextPath() %>${bean.appClientIcon }" height="100%" />
					</li>
				</ul>
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">品牌摘要：</div>
			<div class="form-cont">
				<input type="text" name="brandDesc" lay-verify="brandDesc" value="${bean.brandDesc }" placeholder="摘要" class="form-control form-boxed">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">备注：</div>
			<div class="form-cont">
				<textarea name="remark" class="form-control form-boxed">${bean.remark }</textarea>
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
	img1();
	img2();

	layui.use('form', function(){
	  var form = layui.form;
	  form.verify({
		  brandName:function(value,item){
			  if(value.length>20){
				  return '品牌名称长度不能超过20个字符';
			  }
		  },
		  brandCode:function(value,item){
			  if(value.length>20){
				  return '品牌代码长度不能超过20个字符';
			  }
		  },
		  tsort:function(value,item){
			  if(!(/^\d{1,9}$/.test(value))){
				  return '品牌排序只能是不超过9位的整数';
			  }
		  },
		  brandDesc:function(value,item){
			  if(value.length>200){
				  return '品牌摘要不能超过200个字符';
			  }
		  }
	  });
	  form.on('submit(*)', function(data){   
		  if((!data.field.icon)||data.field.icon===''){
			  parent.layer.msg('请上传图片',{icon:2});
			  return;
		  }
		  parent.layer.load(3,{shade:[0.5,'#000'],time:0});
		  $('.layui-form').submit();
	  });  
	});
});

function img1(){
	var categoryIcon = new plupload.Uploader({
	    runtimes : 'html5,flash,silverlight,html4',
	    browse_button : 'iconPicks', // you can pass in id...
	    container: document.getElementById('icon_container'),
	    url : '../plupload?fileFolder=/goods_brand_icon',
	    flash_swf_url : 'js/Moxie.swf',
	    chunk_size : '30mb',//分块大小，小于这个大小的不分块
	    unique_names : true,//生成唯一文件名
	    silverlight_xap_url : 'js/Moxie.xap',
	    multi_selection:false,
	    filters : {
	        max_file_size : '2m',
	        mime_types: [
	            {title : "image", extensions : "png,jpg"}
	        ]
	    },
	});
	categoryIcon.bind('BeforeUpload', function(uploader, file) {//上传之前
        /*
		if (uploader.files.length > 1) {
            alert('只允许选择一个文件！');
            uploader.stop();
            return;
        }*/
    });
	categoryIcon.bind('FilesAdded', function(up, files) {//选择文件后
        up.refresh(); 
        categoryIcon.start();
    });
	categoryIcon.bind('Error', function(up, err) {//出现错误
        alert(err.message);
        up.refresh();
    });
	categoryIcon.bind('FileUploaded', function(up, file, info) {//上传完毕
        var response = $.parseJSON(info.response);
        if (response.status) {
           $("#iconShow").html("<img src='<%=contextPath%>"+response.fileUrl+"' width='200' height='150'>");
           $("input[name='icon']").val(response.fileUrl);
        }
    });
	categoryIcon.init();
}
function img2(){
	var categoryIcon = new plupload.Uploader({
	    runtimes : 'html5,flash,silverlight,html4',
	    browse_button : 'iconPicks2', // you can pass in id...
	    container: document.getElementById('icon_container2'),
	    url : '../plupload?fileFolder=/goods_brand_appClientIcon',
	    flash_swf_url : 'js/Moxie.swf',
	    chunk_size : '30mb',//分块大小，小于这个大小的不分块
	    unique_names : true,//生成唯一文件名
	    silverlight_xap_url : 'js/Moxie.xap',
	    multi_selection:false,
	    filters : {
	        max_file_size : '2m',
	        mime_types: [
	            {title : "image", extensions : "png,jpg"}
	        ]
	    },
	});
	categoryIcon.bind('BeforeUpload', function(uploader, file) {//上传之前
        /*
		if (uploader.files.length > 1) {
            alert('只允许选择一个文件！');
            uploader.stop();
            return;
        }*/
    });
	categoryIcon.bind('FilesAdded', function(up, files) {//选择文件后
        up.refresh(); 
        categoryIcon.start();
    });
	categoryIcon.bind('Error', function(up, err) {//出现错误
        alert(err.message);
        up.refresh();
    });
	categoryIcon.bind('FileUploaded', function(up, file, info) {//上传完毕
        var response = $.parseJSON(info.response);
        if (response.status) {
           $("#iconShow2").html("<img src='<%=contextPath%>"+response.fileUrl+"' width='200' height='150'>");
           $("input[name='appClientIcon']").val(response.fileUrl);
        }
    });
	categoryIcon.init();
}
</script>
</html>