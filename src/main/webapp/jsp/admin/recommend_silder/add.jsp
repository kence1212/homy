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
				<h2 class="title">新增轮播图</h2>
			</header>
			<hr>
		</section>
		<form action="add.html" class="layui-form" method="POST">
		
		<div class="form-group-col-2">
			<div class="form-label">轮播图标题：</div>
			<div class="form-cont">
				<input type="text" name="title" lay-verify="required|title" placeholder="轮播图标题" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div id="typeDiv" class="form-group-col-2">
		  <div class="form-label">轮播图类型：</div>
            <div class="form-cont">
                <select id="typeId" lay-filter='typeId' style="width:auto;" name="type">
                	<option value="1">商品</option>
                	<option value="2">连接</option>
                </select>
            </div>
		</div>
		
		<div id="urlDiv" class="form-group-col-2" style="display: none">
			<div class="form-label">轮播图地址：</div>
			<div class="form-cont">
				<input id="targetUrl" type="text" name="targetUrl" lay-filter="targetUrl"  placeholder="轮播图地址" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div id="goodsDiv" class="form-group-col-2">
			<div class="form-label">商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品：</div>
			<div class="form-cont">
				<input id="goodsId" type="hidden" name="goodsId">
				<input type="text"  id="goodsName"  placeholder="请选择" class="form-control form-boxed formpoup formpoupIframe" data-url="<%=request.getContextPath()%>/silder/classify.html" style="width:300px;">
			</div>
		</div>
		
		<div  class="form-group-col-2" >
			<div class="form-label">轮播图排序：</div>
			<div class="form-cont">
				<input type="text" name="sort" lay-verify="required|number" placeholder="9位以内数字" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">图片上传：</div>
			<div id="icon_container" class="form-cont">
				<input type="text" style="display:none;" name="imgPath" >
				<ul class="imgList">
					<li class="mb-10" id="iconPicks">
						<input class="img-file-upload" type="file" multiple="multiple" name="">
						<div class="uploadImg">
							<i class="iconfont2 addicon">&#xe931;</i>
							<i class="iconfont2 imgicon">&#xe915;</i>
						</div>
					</li>
				</ul>
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
layui.use(['form','jquery'],function(){
	var form = layui.form,$=layui.jquery;
	form.verify({
		  title:function(value,item){
			  if(value.length>20){
				  return '轮播图标题长度不能超过20个字符';
			  }
		  },
		  targetUrl:function(value,item){
			  if(value.length>200){
				  return '轮播图地址长度不能超过200字符';
			  }
		  }
	  });
	form.on('select(typeId)',function(data){
		var v = data.value;
		if(2 == v){
			//连接
			$("#urlDiv").show();
			$("#goodsDiv").hide();
			$("#goodsId").val("");
			$("#goodsName").val("");
			
		}else if(1 == v){
			//商品
			$("#urlDiv").hide();
			$("#goodsDiv").show();
			$("#targetUrl").val("");
		}
	});
});

function setTextAndValue(t,v){
	$('.formpoupIframe').val(t);
	  $("input[name='goodsId']").val(v);
}


$(function(){
	
	imgUpload();
	
	layui.use('form', function(){
	  var form = layui.form;
	  form.on('submit(*)', function(data){   
		  if((!data.field.imgPath)||data.field.imgPath===''){
			  parent.layer.msg('请上传图片',{icon:2});
			  return;
		  }
		  if(data.field.type&&data.field.type==1){
			  if((!data.field.goodsId)||data.field.goodsId==='0'){
				  parent.layer.msg('商品必选',{icon:2});
				  return;
			  }
		  }else if(data.field.type&&data.field.type==2){
			  if((!data.field.targetUrl)||data.field.targetUrl===''){
				  parent.layer.msg('地址必填',{icon:2});
				  return;
			  }
		  }
		  parent.layer.load(3,{shade:[0.5,'#000'],time:0});
		  $('.layui-form').submit();
	  });  
	});
	//商品弹窗iframe
	$('.formpoupIframe').on('click', function () {
		var iframeUrl = $(this).attr('data-url');
		var width = $(this).attr('data-width')?$(this).attr('data-width')+'px':'700px';
		var height = $(this).attr('data-height')?$(this).attr('data-height')+'px':'450px';
		var title = $(this).parents('.form-group-col-2').find('.form-label').text().trim();
		parent.layer.open({
			type: 2,
			title:title,
			area:[width,height],
			fixed:false,
			maxmin:true,
			content:iframeUrl
		});
	});
});

function imgUpload(){
	var categoryIcon = new plupload.Uploader({
	    runtimes : 'html5,flash,silverlight,html4',
	    browse_button : 'iconPicks', // you can pass in id...
	    container: document.getElementById('icon_container'),
	    url : '../plupload?fileFolder=/goods_silder_img',
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
           $("#iconPicks").html("<img src='<%=request.getContextPath()%>"+response.fileUrl+"' width='200' height='150'>");
           $("input[name='imgPath']").val(response.fileUrl);
          
        }
    });
	categoryIcon.init();
}


</script>
</html>