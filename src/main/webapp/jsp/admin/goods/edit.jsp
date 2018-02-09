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
                <a href="javascript:history.go(-1);"><button class="btn btn-secondary"><i class="icon-chevron-left"></i>返回</button></a>
            </div>
        </div>
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">新增商品</h2>
            </header>
            <hr>
        </section>
        <form action="edit.html" class="layui-form" method="POST">
        <input type="hidden" name="id" value="${goodsBase.id }">
        <div class="form-group-col-2">
            <div class="form-label">商品名称：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品名称" name="goodsName" value="${goodsBase.goodsName }" lay-verify="required|goodsName" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品介绍：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品介绍" name="goodsDesc" value="${goodsBase.goodsDesc }" lay-verify="goodsDesc" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品价格：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品价格" value="${goodsBase.defaultPrice }" name="defaultPrice" lay-verify="required|number" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品型号：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品型号" value="${goodsBase.goodsTypeNumber }" name="goodsTypeNumber" lay-verify="required|goodsTypeNum" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品图标：</div>
            <div id="goods_icon_container" class="form-cont">
				<input type="text" style="display:none;" name="goodsIcon" value="${goodsBase.goodsIcon }">
				<ul class="imgList">
					<li class="mb-10" id="goodsIconPicks">
						<img src='<%=contextPath%>${goodsBase.goodsIcon }' width='200' height='150'>
					</li>
				</ul>
			</div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品类别：</div>
            <div class="form-cont">
                <select style="width:auto;" name="goodsCategoryId">
                	<c:forEach items="${categoryList }" var="cate">
                    <option value="${cate.id }" <c:if test="${cate.id == goodsBase.goodsCategoryId }"> selected="selected"</c:if>>${cate.categoryName }</option>
                	</c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品品牌：</div>
            <div class="form-cont">
                <select style="width:auto;" name="brandId">
                	<c:forEach items="${brandList }" var="brand">
                    <option value="${brand.brandId }" <c:if test="${brand.brandId == goodsBase.brandId }"> selected="selected"</c:if>>${brand.brandName }</option>
                	</c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品风格：</div>
            <div class="form-cont">
                <select style="width:auto;" name="goodsStyleId">
                    <c:forEach items="${styleList }" var="style">
                    <option value="${style.id }" <c:if test="${style.id == goodsBase.goodsStyleId }"> selected="selected"</c:if>>${style.styleName }</option>
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
        </form>
        <!--开始::结束-->
    </div>
</div>

<script>
    $(function(){
    	layui.use('form', function(){
   		  var form = layui.form;
   		  form.verify({
   			  goodsName:function(value,item){
 				  if(value.length>20){
 					  return '商品名称长度不能超过20个字符';
 				  }
 			  },
 			  goodsDesc:function(value,item){
 				  if(value.length>30){
 					  return '商品介绍长度不能超过30个字符';
 				  }
 			  },
 			  goodsTypeNum:function(value,item){
 				  if(value.length>20){
 					  return '商品型号长度不能超过20个字符';
 				  }
 			  },
   		  });
   		  form.on('submit(*)', function(data){   
   			  if((!data.field.goodsIcon)||data.field.goodsIcon===''){
   				  parent.layer.msg('请上传”商品图标“',{icon:2});
   				  return;
   			  }
   			parent.layer.load(3,{shade:[0.5,'#000'],time:0});
   			  $('.layui-form').submit();
   		  });  
   		});
    	 $('.addmore').on('click',function(){
    	        var input = $(this).parents('.form-cont').find("input");//所有input元素
    	        var firstInput = $(this).parents('.form-cont').find("input").eq(0);//原显示的Input元素
    	        var countInput = $(this).parents('.form-cont').find(".countInput").eq(0);//隐藏储存input数量的input元素
    	        var fristInputName = firstInput.attr('name');//原显示的Input元素的name
    	        var deleteButton = $(this).parents('.form-cont').find('.deleteInput');
    	        var countInputValue = parseInt(countInput.val());//显示出来的input数量

    	        var html = '<br><input type="text" name="'+fristInputName+'" class="form-control form-boxed addInput mr-5 mb-5" style="width: 200px;"> ' +
    	                '<button type="button" class="btn btn-danger deleteInput"><i class="icon-minus"></i></button>';
    	        if(deleteButton.length){
    	            deleteButton.eq(deleteButton.length-1).after(html);
    	        }else{
    	            input.eq(input.length-2).after(html);
    	        }
    	        countInput.val(input.length-1);
    	    });
    	 
    	    goodsIconUpload();
    	    
    	    //商品icon上传(单个图片上传)
    	    function goodsIconUpload(){
    	    	var goodsIcon = new plupload.Uploader({
    	    	    runtimes : 'html5,flash,silverlight,html4',
    	    	    browse_button : 'goodsIconPicks', // you can pass in id...
    	    	    container: document.getElementById('goods_icon_container'),
    	    	    url : '../plupload?fileFolder=/goods_icon',
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
    	    	goodsIcon.bind('BeforeUpload', function(uploader, file) {//上传之前
    	    		/*
    	    		if(iconUnique){
    	    			if (uploader.files.length > 1) {
           	                alert('只允许选择一个文件！');
           	                uploader.stop();
           	                return;
           	            }  
    	    			iconUnique = false;
    	    		}else{
    	    			uploader.files = [uploader.files[uploader.files.length-1]]
    	    		}
   	    			*/
    	        });
    	    	goodsIcon.bind('FilesAdded', function(up, files) {//选择文件后
    	            up.refresh(); 
    	            goodsIcon.start();
    	        });
    	    	goodsIcon.bind('Error', function(up, err) {//出现错误
    	            alert(err.message);
    	            up.refresh();
    	        });
    	    	goodsIcon.bind('FileUploaded', function(up, file, info) {//上传完毕
    	            var response = $.parseJSON(info.response);
    	            if (response.status) {
    	               $("#goodsIconPicks").html("<img src='<%=contextPath%>"+response.fileUrl+"' width='200' height='150'>");
    	               $("input[name='goodsIcon']").val(response.fileUrl);
    	            }
    	        });
    	    	goodsIcon.init();
    	    }
    })
</script>
</body>
</html>