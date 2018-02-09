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
    <div class="page-wrap pt-50">
        <div class="clear mb-10 comeback-b">
            <div class="fl">
                <a href="javascript:history.go(-1);"><button class="btn btn-secondary"><i class="icon-chevron-left"></i>返回</button></a>
            </div>
        </div>
        <!--开始::内容-->
        <section class="page-hd">
            <header>
                <h2 class="title">新增商品</h2>
                <button class="btn btn-secondary-outline radius size-m pright cacheBtn">缓存表单数据</button>
                <button class="btn btn-secondary-outline radius size-m pright useCacheBtn">使用缓存数据</button>
                <button class="btn btn-secondary-outline radius size-m pright resetCache">重置</button>
            </header>
            <hr>
        </section>
        <form action="add.html" class="layui-form" method="POST">
        <div class="form-group-col-2">
            <div class="form-label">商品名称：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品名称" name="goodsName" lay-verify="required|goodsName" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品介绍：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品介绍" name="goodsDesc" lay-verify="goodsDesc" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品价格：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品价格" value="100" name="defaultPrice" lay-verify="required|number" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品型号：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品型号" value="未知" name="goodsTypeNumber" lay-verify="required|goodsTypeNum" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        <div class="form-group-col-2 overHidden">
            <div class="form-label">商品图标：</div>
            <div id="goods_icon_container" class="form-cont">
				<input type="text" style="display:none;" name="goodsIcon" value="">
				<ul class="imgList">
					<li class="mb-10" id="goodsIconPicks">
						<input class="img-file-upload" type="file" name="">
						<div class="uploadImg">
							<i class="iconfont2 addicon">&#xe931;</i>
							<i class="iconfont2 imgicon">&#xe915;</i>
						</div>
					</li>
				</ul>
			</div>
        </div>
        <div class="form-group-col-2 overHidden">
            <div class="form-label">商品图片：</div>
            <div id="goods_img_container" class="form-cont">
				<input type="text" style="display:none;" name="goodsImg" value="">
				<ul class="imgList">
					<li class="mb-10" id="goodsImgPicks">
						<input class="img-file-upload" type="file" multiple="multiple" name="">
						<div class="uploadImg">
							<i class="iconfont2 addicon">&#xe931;</i>
							<i class="iconfont2 imgicon">&#xe915;</i>
						</div>
					</li>
				</ul>
			</div>
        </div>
        <div class="form-group-col-2 overHidden">
            <div class="form-label">商品介绍：</div>
            <div id="goods_desc_img_container" class="form-cont">
				<input type="text" style="display:none;" name="goodsDescImg" value="">
				<ul class="imgList">
					<li class="mb-10" id="goodsDescImgPicks">
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
            <div class="form-label">商品类别：</div>
            <div class="form-cont">
                <select style="width:auto;" name="goodsCategoryId">
                	<c:forEach items="${categoryList }" var="cate">
                    <option value="${cate.id }">${cate.categoryName }</option>
                	</c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品品牌：</div>
            <div class="form-cont">
                <select style="width:auto;" name="brandId">
                	<c:forEach items="${brandList }" var="brand">
                    <option value="${brand.brandId }">${brand.brandName }</option>
                	</c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品风格：</div>
            <div class="form-cont">
                <select style="width:auto;" name="goodsStyleId">
                    <c:forEach items="${styleList }" var="style">
                    <option value="${style.id }">${style.styleName }</option>
                	</c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品颜色：</div>
            <div class="form-cont">
                <input type="text" name="goodsColors" lay-verify="goodsColors" class="form-control form-boxed mr-5 mb-5" style="width: 200px;">
                <button type="button" class="btn btn-primary addmore"><i class="icon-plus"></i></button>
                <input type="text" class="countInput" name="colorInputCount" value="1"  style="display:none;"/>
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">商品规格：</div>
            <div class="form-cont">
                <input type="text" name="goodsSizes" lay-verify="goodsSizes" class="form-control form-boxed mr-5 mb-5" style="width: 200px;">
                <button type="button" class="btn btn-primary addmore"><i class="icon-plus"></i></button>
                <input type="text" class="countInput" name="specsInputCount" value="1"  style="display:none;"/>
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label">材质：</div>
            <div class="form-cont">
                <input type="text" name="goodsTextures" lay-verify="goodsTextures" class="form-control form-boxed mr-5 mb-5" style="width: 200px;">
                <button type="button" class="btn btn-primary addmore"><i class="icon-plus"></i></button>
                <input type="text" class="countInput" name="meeInputCount" value="1"  style="display:none;"/>
            </div>
        </div>
        <div class="form-group-col-2 attrInput-group">
            <input type="text" style="display: none;" class="attrInputCount" name="attrInputCount">
            <div class="form-label">增加属性：</div>
            <div class="form-cont">
                <button type="button" class="btn btn-primary addAttrInput"><i class="icon-plus"></i></button>
            </div>
        </div>
        <div class="form-group-col-2">
            <div class="form-label"></div>
            <div class="form-cont">
                <input type="button" lay-submit lay-filter="*" class="btn btn-primary" value="新增" />
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
   			  goodsColors:function(value,item){
   				  if(value.length>200){
   					  return '商品颜色长度不能超过200个字符';
   				  }
   			  },
   			  goodsSizes:function(value,item){
 				  if(value.length>200){
 					  return '商品规格长度不能超过200个字符';
 				  }
 			  },
   			  goodsTextures:function(value,item){
   				  if(value.length>200){
   					  return '材质长度不能超过200个字符';
   				  }
   			  }
   		  });
   		  form.on('submit(*)', function(data){   
   			  /* if((!data.field.goodsIcon)||data.field.goodsIcon===''){
   				  parent.layer.msg('请上传”商品图标“',{icon:2});
   				  return;
   			  }
   			  if((!data.field.goodsImg)||data.field.goodsImg===''){
   				  parent.layer.msg('请上传”商品图片“',{icon:2});
   				  return;
   			  } */
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
    	    $('.form-cont').on('click','.deleteInput', function () {
    	        var formCount = $(this).parents('.form-cont');
    	        var input = formCount.find('input');
    	        var br = formCount.find('br');
    	        var index = formCount.find('.deleteInput').index(this);
    	        br.eq(index).remove();
    	        input.eq(index+1).remove();
    	        $(this).remove();
    	        resetInput(formCount);
    	    });
    	    //重新排列Input
    	    function resetInput(doc){
    	        var input = $(doc).find('input');
    	        var length = input.length;
    	        var firstInputName = input.eq(0).attr('name');
    	        input.each(function (idx,element) {
    	            if(idx !== 0 && idx !== length-1){
    	               $(element).attr('name',firstInputName+idx) ;
    	            }
    	        });
    	        input.eq(length-1).val(input.length-1);
    	    }
    	    $('.attrInput-group').on('click','.addAttrInput',function(){
    	        var group = $(this).parents('.attrInput-group');
    	        var attrInputCount = group.find('.attrInputCount').eq(0);
    	        var attrInputs = group.find('.attrInput');
    	        var inputCount = parseInt(attrInputs.length);
    	        var addBtnCount = $(this).parents('.form-cont');

    	        var html = '<div class="form-cont mt-10 attrInput mb-5">'+
    	                        '<input type="text" placeholder="属性名" name="otherAttrKey'+(inputCount+1)+'" class="form-control form-boxed mr-5" style="width: 200px;">'+
    	                        '<input type="text" placeholder="属性值" name="otherAttrValue'+(inputCount+1)+'" class="form-control form-boxed mr-5" style="width: 200px;">'+
    	                        '<button type="button" class="btn btn-danger deleteAttrInput"><i class="icon-minus"></i></button>'+
    	                    '</div>';
    	        attrInputCount.val(inputCount+1);
    	        addBtnCount.before(html);
    	    });
    	    $('.attrInput-group').on('click','.deleteAttrInput', function () {
    	        var group = $(this).parents('.attrInput-group');
    	        var thisAttrInput = $(this).parents('.attrInput');

    	        thisAttrInput.remove();
    	        resetAttrInput(group);
    	    });
    	    function resetAttrInput(doc){
    	        var group = $(doc);
    	        var attrInputs = group.find('.attrInput');
    	        var attrInputCount = group.find('.attrInputCount').eq(0);
    	        attrInputCount.val(attrInputs.length);
    	        attrInputs.each(function (idx, element) {
    	            var input = $(element).find('input');
    	            input.eq(0).attr('name','otherAttrKey'+(idx+1));
    	            input.eq(1).attr('name','otherAttrValue'+(idx+1));
    	        });
    	    }
  			
    	    goodsIconUpload();
    	    goodsImgsUpload();
    	    goodsDescImgsUpload();
    	    
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
    	    
    	  //商品图片上传(多个图片上传)
    	    function goodsImgsUpload(){
    	    	var goodsImg = new plupload.Uploader({
    	    	    runtimes : 'html5,flash,silverlight,html4',
    	    	    browse_button : 'goodsImgPicks', // you can pass in id...
    	    	    container: document.getElementById('goods_img_container'),
    	    	    url : '../plupload?fileFolder=/goods_img',
    	    	    flash_swf_url : 'js/Moxie.swf',
    	    	    chunk_size : '30mb',//分块大小，小于这个大小的不分块
    	    	    unique_names : false,//生成唯一文件名
    	    	    silverlight_xap_url : 'js/Moxie.xap',
    	    	    filters : {
    	    	        max_file_size : '2m',
    	    	        mime_types: [
    	    	            {title : "image", extensions : "png,jpg"}
    	    	        ]
    	    	    },
    	    	});
    	    	goodsImg.bind('BeforeUpload', function(uploader, file) {//上传之前
    	            //if (uploader.files.length > 1) {
    	              //  alert('只允许选择一个文件！');
    	              //  uploader.stop();
    	               // return;
    	           // }
    	        });
    	    	goodsImg.bind('FilesAdded', function(up, files) {//选择文件后
    	            up.refresh(); 
    	            goodsImg.start();
    	        });
    	    	goodsImg.bind('Error', function(up, err) {//出现错误
    	            alert(err.message);
    	            up.refresh();
    	        });
    	    	goodsImg.bind('FileUploaded', function(up, file, info) {//上传完毕
    	            var response = $.parseJSON(info.response);
    	            if (response.status) {
    	               $("#goodsImgPicks").parents(".imgList").append("<li class='mb-10 mr-5' ><input type='hidden' name='goodsImg' value='" + response.fileUrl + "'/><img src='<%=contextPath%>"+response.fileUrl+"' width='200' height='150'></li>");
    	            }
    	        });
    	    	goodsImg.init();
    	    }
    	  
    	  
    	    function goodsDescImgsUpload(){
    	    	var goodsDescImg = new plupload.Uploader({
    	    	    runtimes : 'html5,flash,silverlight,html4',
    	    	    browse_button : 'goodsDescImgPicks', // you can pass in id...
    	    	    container: document.getElementById('goods_img_container'),
    	    	    url : '../plupload?fileFolder=/goods_desc_img',
    	    	    flash_swf_url : 'js/Moxie.swf',
    	    	    chunk_size : '30mb',//分块大小，小于这个大小的不分块
    	    	    unique_names : false,//生成唯一文件名
    	    	    silverlight_xap_url : 'js/Moxie.xap',
    	    	    filters : {
    	    	        max_file_size : '2m',
    	    	        mime_types: [
    	    	            {title : "image", extensions : "png,jpg"}
    	    	        ]
    	    	    },
    	    	});
    	    	goodsDescImg.bind('BeforeUpload', function(uploader, file) {//上传之前
    	            //if (uploader.files.length > 1) {
    	              //  alert('只允许选择一个文件！');
    	              //  uploader.stop();
    	               // return;
    	           // }
    	        });
    	    	goodsDescImg.bind('FilesAdded', function(up, files) {//选择文件后
    	            up.refresh(); 
    	            goodsDescImg.start();
    	        });
    	    	goodsDescImg.bind('Error', function(up, err) {//出现错误
    	            alert(err.message);
    	            up.refresh();
    	        });
    	    	goodsDescImg.bind('FileUploaded', function(up, file, info) {//上传完毕
    	            var response = $.parseJSON(info.response);
    	            if (response.status) {
    	               $("#goodsDescImgPicks").parents(".imgList").append("<li class='mb-10 mr-5' ><input type='hidden' name='goodsDescImg' value='" + response.fileUrl + "'/><img src='<%=contextPath%>"+response.fileUrl+"' width='200' height='150'></li>");
    	            }
    	        });
    	    	goodsDescImg.init();
    	    }
    })
</script>
</body>
</html>