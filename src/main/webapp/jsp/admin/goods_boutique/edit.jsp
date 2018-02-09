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
				<h2 class="title">新增推荐</h2>
			</header>
			<hr>
		</section>
		<form action="edit.html" class="layui-form" method="POST">
		
		<input type="hidden" name="id" value="${bean.id }">
		
		<div class="form-group-col-2">
			<div class="form-label">推荐类型：</div>
			<div class="form-cont">
				<select  id="typeSelect" lay-filter='typeId' style="width:auto;" name="type">
				<option value="1">大牌制造推荐</option>
				<option value="2">每周小物</option>
				</select>
			</div>
		</div>
		
		<c:if test="${bean.type==1 }">
			<div id="positionDiv" class="form-group-col-2" >
				<div class="form-label">图片位置：</div>
				<div class="form-cont">
					<input id="position" type="text" name="position" lay-filter="position" value="${bean.position }" placeholder="图片位置" class="form-control form-boxed" style="width:300px;">
				</div>
			</div>
		</c:if>
			
		<c:if test="${bean.type==2 }">
			<div id="positionDiv" class="form-group-col-2" style="display: none">
				<div class="form-label">图片位置：</div>
				<div class="form-cont">
					<input id="position" type="text" name="position"  placeholder="图片位置" class="form-control form-boxed" style="width:300px;">
				</div>
			</div>
		</c:if>
		
		<div id="goodsDiv" class="form-group-col-2">
			<div class="form-label">商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品：</div>
			<div class="form-cont">
				<input id="goodsId" type="hidden" name="goodsId">
				<input type="text"  id="goodsName"  placeholder="请选择" class="form-control form-boxed formpoup formpoupIframe" data-url="<%=request.getContextPath()%>/boutique/classify.html" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">图片上传：</div>
			<div id="icon_container" class="form-cont">
				<input type="text" style="display:none;" name="imgPath" value="${bean.imgPath }">
				<ul class="imgList">
					<li class="mb-10" id="iconPicks">
						<div class="uploadImg">
							<i class="iconfont2 addicon">&#xe931;</i>
							<i class="iconfont2 imgicon">&#xe915;</i>
						</div>
					</li>
					<li class="mb-10" id="iconShow">
						<img src="<%=request.getContextPath() %>${bean.imgPath }" height="100%" />
					</li>
				</ul>
			</div>
		</div>
		
		<div id="positionDiv" class="form-group-col-2" >
			<div class="form-label">推荐排序：</div>
			<div class="form-cont">
				<input  type="text" name="sort" value="${bean.type }" lay-verify="required|number" placeholder="推荐排序" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">推荐描述：</div>
			<div class="form-cont">
				<input type="text" name="desc" value="${bean.desc }" lay-verify="required|desc" placeholder="推荐描述" class="form-control form-boxed">
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

//选中类型
$("#typeSelect option[value='${bean.type}']").attr("selected",true);

//渲染商品
$.post("<%=request.getContextPath()%>"+"/amgoods/getJson.html",
		{id:"${bean.goodsId }"},
		function(data){
			$("#goodsId").val(data.id);
			$("#goodsName").val(data.goodsName);
		}
		,"json"
) 

layui.use(['form','jquery'],function(){
	var form = layui.form,$=layui.jquery;
	form.on('select(typeId)',function(data){
		var v = data.value;
		if(v==1){
			//大牌
			$("#positionDiv").show();
		}else if(v==2){
			//小物
			$("#positionDiv").hide();
			$("#position").val("");
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
	  form.verify({
		  position:function(value,item){
			  if(value.length>3){
				  return '图片位置数字超过三位';
			  }
		  },
		  desc:function(value,item){
			  if(value.length>200){
				  return '推荐描述字符不应超过200字符';
			  }
		  }
	  });
	  form.on('submit(*)', function(data){   
		  if((!data.field.imgPath)||data.field.imgPath===''){
			  parent.layer.msg('请上传图片',{icon:2});
			  return;
		  }
		  if((!data.field.goodsId)||data.field.goodsId===''){
			  parent.layer.msg('请选择商品',{icon:2});
			  return;
		  }
		  if(data.field.type&&data.field.type==1){
			  if((!data.field.position)||data.field.position===''){
				  parent.layer.msg('图片位置必填',{icon:2});
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
	    url : '../plupload?fileFolder=/goods_boutique_img',
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
	           $("#iconShow").html("<img src='<%=request.getContextPath()%>"+response.fileUrl+"' width='200' height='150'>");
	           $("input[name='imgPath']").val(response.fileUrl);
	          
	        }
    });
	categoryIcon.init();
}

function fileUpload(){
	var categoryFile = new plupload.Uploader({
	    runtimes : 'html5,flash,silverlight,html4',
	    browse_button : 'modelFile', // you can pass in id...
	    url : '../plupload?fileFolder=/goods_model_file',
	    flash_swf_url : 'js/Moxie.swf',
	    chunk_size : '30mb',//分块大小，小于这个大小的不分块
	    unique_names : true,//生成唯一文件名
	    silverlight_xap_url : 'js/Moxie.xap',
	    multi_selection:false,
	    filters : {
	        max_file_size : '100m',
	        mime_types: [
	            {title : "Zip", extensions : "zip"}
	        ]
	    },
	});
	categoryFile.bind('BeforeUpload', function(uploader, file) {//上传之前
        /*
		if (uploader.files.length > 1) {
            alert('只允许选择一个文件！');
            uploader.stop();
            return;
        }*/
    });
	categoryFile.bind('FilesAdded', function(up, files) {//选择文件后
		$("#fileName").val(files[0].name);
        up.refresh(); 
        categoryFile.start();
    });
	
	categoryFile.bind('Error', function(up, err) {//出现错误
        alert(err.message);
        up.refresh();
    });
	
	categoryFile.bind('UploadProgress', function (uploader, file) { //上传进行中
		
		var AppFilePercent;
		if(file.percent == 100){
		     AppFilePercent = 99;
		 }else{
			 AppFilePercent = file.percent;
		}
		var speed = categoryFile.total.bytesPerSec/1048576;
		$("#process").val(AppFilePercent);
		 $(".progress-text").html(AppFilePercent+"% "+ "&nbsp;"+speed.toFixed(2)+"M/S");
    });
	
	categoryFile.bind('FileUploaded', function(up, file, info) {//上传完毕
        var response = $.parseJSON(info.response);
        $(".progress-text").html(100+"% ");
        $("input[name='modelPath']").val(response.fileUrl);
    });
	categoryFile.init();
}

</script>
</html>