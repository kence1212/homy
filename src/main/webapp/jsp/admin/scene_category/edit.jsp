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
				<h2 class="title">编辑场景分类</h2>
			</header>
			<hr>
		</section>
		<form id="saveForm" action="add.html" class="layui-form" method="POST">
		<input type="hidden" name="id" value="${bean.id }">
		<div class="form-group-col-2">
			<div class="form-label">分类名称：</div>
			<div class="form-cont">
				<input type="text" name="name" value="${bean.name }" lay-verify="required|name" placeholder="分类名称" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">分类编码：</div>
			<div class="form-cont">
				<input type="text" name="code" value="${bean.code }" lay-verify="required|name" placeholder="分类编码" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">分类排序：</div>
			<div class="form-cont">
				<input type="tel" name="sort" value="${bean.sort }" lay-verify="required|number|code" placeholder="场景排序" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">分类描述：</div>
			<div class="form-cont">
				<input type="tel" name="desc" value="${bean.desc }" lay-verify="required" placeholder="场景描述" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">图标上传：</div>
			<div id="icon_container" class="form-cont">
				<input type="text" style="display:none;" name="icon" value="${bean.icon }" >
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

	imgUpload();
	
	layui.use('form', function(){
	  var form = layui.form;
	  form.verify({
		  name:function(value,item){
			  if(value.length>60){
				  return '模块长度不应超过60个字符';
			  }
		  },
		  code:function(value,item){
			  if(value.length>20){
				  return '模块编码长度不应超过20个字符';
			  }
		  },
	  });
	  form.on('submit(*)', function(data){   
		/*   parent.layer.load(3,{shade:[0.5,'#000'],time:0});
		  $('.layui-form').submit(); */
		  if((!data.field.icon)||data.field.icon===''){
			  parent.layer.msg('请上传图片',{icon:2});
			  return;
		  }
		  
		  parent.layer.confirm("确定保存吗？",{},function(){
			  $.post("<%=request.getContextPath()%>"+"/amscenecategory/edit.html",
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


function imgUpload(){
	var categoryIcon = new plupload.Uploader({
	    runtimes : 'html5,flash,silverlight,html4',
	    browse_button : 'iconPicks', // you can pass in id...
	    container: document.getElementById('icon_container'),
	    url : '../plupload?fileFolder=/scene_category_icon',
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
        	$("#iconShow").html("<img src='<%=request.getContextPath()%>/"+response.fileUrl+"' width='200' height='150'>");
            $("input[name='icon']").val(response.fileUrl);
           
        }
    });
	categoryIcon.init();
}



</script>
</html>