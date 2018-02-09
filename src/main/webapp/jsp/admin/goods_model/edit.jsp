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
				<h2 class="title">编辑模型</h2>
			</header>
			<hr>
		</section>
		<form id="saveForm" action="edit.html" class="layui-form" method="POST">
		<input type="hidden" name="id" value="${bean.id }">
		
		 <div class="form-group-col-2">
            <div class="form-label">类别：</div>
            <div class="form-cont">
                <select id="typeSelect" lay-filter='typeId' style="width:auto;" name="type">
                	<option value="1">模型文件Zip包</option>
                	<option value="2">图片</option>
                </select>
            </div>
        </div>
		
		<div class="form-group-col-2">
			<div class="form-label">模型高度：</div>
			<div class="form-cont">
				<input type="text" value="${bean.modelHeight }" name="modelHeight" lay-verify="number|modelHeight" placeholder="模型高度" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">模型宽度：</div>
			<div class="form-cont">
				<input type="tel" value="${bean.modelWidth }" name="modelWidth" lay-verify="number|modelWidth" placeholder="模型宽度" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">模型长度：</div>
			<div class="form-cont">
				<input type="text" value="${bean.modelLength }" name="modelLength" lang="en" lay-verify="number|modelLength""  placeholder="模型长度" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">模型版本：</div>
			<div class="form-cont">
				<input type="text" value="${bean.modelVersion }" name="modelVersion" lang="en" lay-verify="required"  placeholder="模型版本" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">模型标签：</div>
			<div class="form-cont">
				<input type="text" value="${bean.modelTag }" name="modelTag" lang="en" lay-verify="required"  placeholder="模型标签" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品：</div>
			<div class="form-cont">
				<input type="hidden" name="goodsId" id="goodsId">
				<input type="text" id="goodsName" lay-verify="required" placeholder="请选择" class="form-control form-boxed formpoup formpoupIframe" data-url="<%=request.getContextPath()%>/ammodel/classify.html" style="width:300px;">
			</div>
		</div>
		
		<div <c:if test="${bean.type == 2 }">style="display: none"</c:if> class="form-group-col-2" id="colorIdDiv">
			<div class="form-label">商品颜色：</div>
			<div class="form-cont">
			  <select id="colorId" style="width:auto;" lay-filter="colorId" name="colorId">
			
          	  </select>
			</div>
		</div>
		
		<div <c:if test="${bean.type == 2 }">style="display: none"</c:if> class="form-group-col-2" id="sizeIdDiv">
			<div class="form-label">商品尺寸：</div>
			<div class="form-cont">
			  <select style="width:auto;" id="sizeId" name="sizeId">
          	  </select>
			</div>
		</div>
		
		<div <c:if test="${bean.type == 2 }">style="display: none"</c:if> class="form-group-col-2" id="textureIdDiv">
			<div class="form-label">商品材质：</div>
			<div class="form-cont">
			  <select style="width:auto;" id="textureId" name="textureId">
          	  </select>
			</div>
		</div>
		
		<div class="form-group-col-2">
			<div class="form-label">图标上传：</div>
			<div id="icon_container" class="form-cont">
				<input type="text" value="${bean.modelIcon }" style="display:none;" name="modelIcon" >
				<ul class="imgList">
					<li class="mb-10" id="iconPicks">
						<div class="uploadImg">
							<i class="iconfont2 addicon">&#xe931;</i>
							<i class="iconfont2 imgicon">&#xe915;</i>
						</div>
					</li>
					<li class="mb-10" id="iconShow">
						<img src="<%=request.getContextPath() %>${bean.modelIcon }" height="100%" />
					</li>
				</ul>
			</div>
		</div>
	
		<div id="fileDiv" class="form-group-col-2"  <c:if test="${bean.type != 1 }">style="display: none"</c:if> >
			<div class="form-label">文件上传：</div>
			<div id="file_container" class="form-cont">
				<input type="text" id="fileName" value="已上传" class="form-control form-boxed" style="width:300px;">
				<input value="${bean.modelPath }" type="text" name="modelPath"  style="display: none">
				<button id="modelFile" class="btn btn-secondary fill-upload-btn">
					上传
				</button>
				<div class="mt-10 file-upload-progress">
					<progress id="process" value="0" max="100">
						<span class="progress" style="width:100%;">
							<i class="value" style="width:35%;"></i>
						</span>
					</progress>
					<span class="progress-text">0% &nbsp; 0M/S</span>
				</div>
			</div>
		</div>
		
		<div id="photoDiv"  class="form-group-col-2" <c:if test="${bean.type != 2 }">style="display: none"</c:if> >
					<div class="form-label">贴图上传：</div>
					<div id="icon_container2" class="form-cont">
						<ul class="imgList">
							<li class="mb-10" id="iconPicks2">
								<div class="uploadImg">
									<i class="iconfont2 addicon">&#xe931;</i>
									<i class="iconfont2 imgicon">&#xe915;</i>
								</div>
							</li>
							<li class="mb-10" id="iconShow2">
								<img <c:if test="${bean.type == 2 }">src="<%=request.getContextPath() %>${bean.modelPath }"</c:if> height="100%" />
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

var contextPath = "<%=request.getContextPath()%>";
function closeProperties(){
	/* $("#colorId").html("");
	$("#sizeId").html("");
	$("#textureId").html("");
	 layui.use(['form','jquery'],function(){
		  var form = layui.form,$=layui.jquery;
		  form.render('select');
  	}) */
  	$("#colorIdDiv").hide();
	$("#sizeIdDiv").hide();
	$("#textureIdDiv").hide();
}
function showProperties(){
	$("#colorIdDiv").show();
	$("#sizeIdDiv").show();
	$("#textureIdDiv").show();
}
function selectProperties(){
	$("#colorId option[value='${bean.colorId}']").attr("selected",true);
	$("#sizeId option[value='${bean.sizeId}']").attr("selected",true);
	$("#textureId option[value='${bean.textureId}']").attr("selected",true);
	 layui.use(['form','jquery'],function(){
		  var form = layui.form,$=layui.jquery;
		  form.render('select');
	  })
}

function setProperties(v){
	 //加载颜色select
	  $.ajax({
		  async: false,
			  type: "POST",
			  url:contextPath+"/amgoods/getGoodsColor.html",
			  data: "goodsId="+v,
			  success: function(data){
				  $("#colorId").html("");
				  var html = "";
				  for(var i in data){
					  html += '<option value="'+data[i].id+'">'+data[i].name+'</option>';
				  }
				  $("#colorId").html(html);
				  layui.use(['form','jquery'],function(){
					  var form = layui.form,$=layui.jquery;
				  })
				  
			  },
			  dataType: "json"})
	  //加载尺寸select
	   $.ajax({
		   async: false,
			  type: "POST",
			  url:contextPath+"/amgoods/getGoodsSize.html",
			  data: "goodsId="+v,
			  success: function(data){
				  $("#sizeId").html("");
				  var html = "";
				  for(var i in data){
					  html += '<option value="'+data[i].id+'">'+data[i].name+'</option>';
				  }
				  $("#sizeId").html(html);
				  layui.use(['form','jquery'],function(){
					  var form = layui.form,$=layui.jquery;
				  })
				  
			  },
			  dataType: "json"})
	  //加载材质select
	   $.ajax({
		   async: false,
			  type: "POST",
			  url:contextPath+"/amgoods/getGoodsTexture.html",
			  data: "goodsId="+v,
			  success: function(data){
				  $("#textureId").html("");
				  var html = "";
				  for(var i in data){
					  html += '<option value="'+data[i].id+'">'+data[i].texture_name+'</option>';
				  }
				  $("#textureId").html(html);
				  layui.use(['form','jquery'],function(){
					  var form = layui.form,$=layui.jquery;
					  form.render('select');
				  })
				  
			  },
			  dataType: "json"})
}

//图片编辑回调函数
function setHeadImg(baseImg){
    $("#iconShow").html("<i class='icon-cut'></i><img src='"+baseImg+"' width='200' height='150'>");
    $("#iconShow").addClass('editorImg');
    $("input[name='modelIcon']").val(baseImg);
}
$('#iconPicks').on('click',function(){
	parent.layer.open({
		type: 2,
		title:'图片编辑',
		area:['900px','900px'],
		fixed:false,
		maxmin:true,
		content:'<%=request.getContextPath()%>/amgoods/imgEditor.html'
	});
});

layui.use(['form','jquery'],function(){
	var form = layui.form,$=layui.jquery;
	form.on('select(typeId)',function(data){
		var v = data.value;
		if(v==1){
			$("#fileDiv").show();
			$("#photoDiv").hide();
			$("#modelPath").val("");
			showProperties();
			
		}else if(v==2){
			$("#photoDiv").show();
			$("#fileDiv").hide();
			$("#modelPath").val("");
			closeProperties();
		}
	});
});

$("#typeSelect option[value='${bean.type}']").attr("selected",true);



 <%-- console.log(<%=request.getContextPath()%>+"/amgoods/getJson.html"); --%>
 //渲染商品
 $.post("<%=request.getContextPath()%>"+"/amgoods/getJson.html",
		{id:"${bean.goodsId }"},
		function(data){
			$("#goodsId").val(data.id);
			$("#goodsName").val(data.goodsName);
		}
		,"json"
) 

//加载属性
setProperties("${bean.goodsId }");
//选中属性
selectProperties();

function setTextAndValue(t,v){
	$('.formpoupIframe').val(t);
	console.log(t,v);
	  $("input[name='goodsId']").val(v);
}


$(function(){
	
	
	imgUpload2();
	
	fileUpload();
	
	layui.use('form', function(){
	  var form = layui.form;
	  form.verify({
		  modelHeight:function(value,item){
				if(value>999999999){
					return '模型高度不应超过9位的数字';
				}
			},
			modelWidth:function(value,item){
				if(value>999999999){
					return '模型宽度不应超过9位的数字';
				}
			},
		  modelLength:function(value,item){
			if(value>999999999){
				return '模型长度不应超过9位的数字';
			}  
		  },
		  styleName:function(value,item){
			  if(value.length>20){
				  return '模型名称长度不能超过20个字符';
			  }
		  },
		  styleCode:function(value,item){
			  if(value.length>20){
				  return '模型代码长度不能超过20个字符';
			  }
		  },
		  tsort: function(value,item){
			  if(!(/^\d{1,9}$/.test(value))){
				  return '模型排序只能是不超过9位的整数';
			  }
		  },
		  modelIcon:function(value,item){
			  if(!value){
				  return '必须上传图片！';
			  }
		  },
		  modelPath:function(value,item){
			  if(!value){
				  return '必须上传模型文件！';
			  }
		  }
	  });
	  form.on('submit(*)', function(data){   
		  if((!data.field.modelIcon)||data.field.modelIcon===''){
			  parent.layer.msg('请上传图片',{icon:2});
			  return;
		  }
		  if((!data.field.modelPath)||data.field.modelPath===''){
			  parent.layer.msg('请上传模型文件',{icon:2});
			  return;
		  }
		/*   parent.layer.load(3,{shade:[0.5,'#000'],time:0});
		  $('.layui-form').submit(); */
		  
		  parent.layer.confirm("确定编辑？",{},function(){
	   			 $.post("<%=request.getContextPath()%>"+"/ammodel/edit.html",
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
	//分类弹窗iframe
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

function imgUpload2(){
	var categoryIcon = new plupload.Uploader({
	    runtimes : 'html5,flash,silverlight,html4',
	    browse_button : 'iconPicks2', // you can pass in id...
	    container: document.getElementById('icon_container2'),
	    url : '../plupload?fileFolder=/goods_model_file',
	    flash_swf_url : 'js/Moxie.swf',
	    chunk_size : '30mb',//分块大小，小于这个大小的不分块
	    unique_names : true,//生成唯一文件名
	    silverlight_xap_url : 'js/Moxie.xap',
	    multi_selection:false,
	    filters : {
	        max_file_size : '10m',
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
        	
       	   $("#iconShow2").html("<img src='<%=request.getContextPath()%>/"+response.fileUrl+"' width='200' height='150'>");
           $("input[name='modelPath']").val(response.fileUrl);
          
        }
    });
	categoryIcon.init();
}


function imgUpload(){
	var categoryIcon = new plupload.Uploader({
	    runtimes : 'html5,flash,silverlight,html4',
	    browse_button : 'iconPicks', // you can pass in id...
	    container: document.getElementById('icon_container'),
	    url : '../plupload?fileFolder=/goods_model_icon',
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
           $("input[name='modelIcon']").val(response.fileUrl);
          
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