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
				<h2 class="title">新增热门城市</h2>
			</header>
			<hr>
		</section>
		<form action="add.html" class="layui-form" method="POST">
		
		<div id="goodsDiv" class="form-group-col-2">
			<div class="form-label">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市：</div>
			<div class="form-cont">
				<input id="goodsId" type="hidden" name="id">
				<input type="text"  id="goodsName"  placeholder="请选择" class="form-control form-boxed formpoup formpoupIframe" data-url="<%=request.getContextPath()%>/amregion/classify.html" style="width:300px;">
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
	  $("input[name='id']").val(v);
}


$(function(){
	
	
	layui.use('form', function(){
	  var form = layui.form;
	  form.verify({
	
		
	  });
	  form.on('submit(*)', function(data){   
		  if((!data.field.id)||data.field.id===''){
			  parent.layer.msg('请选择城市',{icon:2});
			  return;
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


</script>
</html>