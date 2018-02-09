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
                <h2 class="title">编辑商品图片</h2>
            </header>
            <hr>
        </section>
        <input type="hidden" name="goodsId" value="${goodsId}">
        <div class="form-group-col-2">
        	<div class="form-label">商品图片：</div>
        	<div class="form-cont">
        		<ul class="edit-img-list">
        			<c:forEach items="${imageList }" var="bean">
	        			<c:if test="${bean.type == 1 }">
	        			<input type="hidden" name="name" value="${bean.imagePath }">
	        			<li>
	        				<img alt="" src="<%=contextPath %>${bean.imagePath }" width="200" height="180"/>
	        				<div class="edit-img-btn" onclick="deleteById(${bean.id})">
	        					<i class="icon-minus-sign"></i>
	        				</div>
	        			</li>
	        			</c:if>
        			</c:forEach>
        		</ul>
        	</div>
        	<div class="form-label mt-20">介绍图片：</div>
        	<div class="form-cont mt-20">
        		<ul class="edit-img-list">
        			<c:forEach items="${imageList }" var="bean">
	        			<c:if test="${bean.type == 2 }">
	        			<input type="hidden" name="name" value="${bean.imagePath }">
	        			<li>
	        				<img alt="" src="<%=contextPath %>${bean.imagePath }" width="200" height="180"/>
	        				<div class="edit-img-btn" onclick="deleteById(${bean.id})">
	        					<i class="icon-minus-sign"></i>
	        				</div>
	        			</li>
	        			</c:if>
        			</c:forEach>
        		</ul>
        	</div>
        	<div class="form-label mt-20">上传图片：</div>
            <div class="form-cont add-block" id="img_container" style="text-align:center;">
            	<select name="goodsType" style="width:200px;">
            		<option value="1">商品图片</option>
            		<option value="2">介绍图片</option>
            	</select>
            	<!-- <input id="imgPicks"  type="text" name="name" value="" class="form-control form-boxed" style="width: 200px;"> -->
                <input type="hidden" name="imgurl" value="" class="form-control form-boxed" style="width: 200px;">
                <div class="add-block-img" id="imgPicks">
                	<div class="uploadImg">
						<i class="iconfont2 addicon">&#xe931;</i>
						<i class="iconfont2 imgicon">&#xe915;</i>
					</div>
                </div>
                <button type="button" class="btn btn-primary" onclick="addImg()" style="width: 200px;"><i class="icon-plus"></i></button>
            </div>
        </div>
        <!--开始::结束-->
    </div>
</div>

<script>
var goodsIcon;
	$(function(){
		
		goodsIcon = new plupload.Uploader({
    	    runtimes : 'html5,flash,silverlight,html4',
    	    browse_button : 'imgPicks', // you can pass in id...
    	    container: document.getElementById('img_container'),
    	    url : '../plupload?fileFolder=/goods_img',
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
               $("#imgPicks").html("<img src='<%=contextPath%>"+response.fileUrl+"' width='200' height='180'>");
               $("input[name='imgurl']").val(response.fileUrl);
            }
        });
    	goodsIcon.init();
		
	});

    function deleteById(id){
    	parent.layer.confirm("删除这张图片？",function(){
	    	$.post(
	    		"deleteGoodsImg.do",
	    		{"id":id},
	    		function(result){
	    			if(result.resultCode == "10000"){
	    				parent.layer.msg("删除成功");
	    				location.reload();
	    			}else{
	    				parent.layer.msg(result.errorMsg);
	    			}
	    		}
	    	);
    	});
    }
    
    function addImg(){
    	var goodsType = $("select[name='goodsType']").val();
    	var goodsId = $("input[name='goodsId']").val();
    	var imgurl = $("input[name='imgurl']").val();
    	parent.layer.confirm("确定添加图片？",function(){
	    	$.post(
	    		"addGoodsImage.do",
	    		{"goodsId":goodsId,"path":imgurl,"type":goodsType},
	    		function(result){
	    			if(result.resultCode == "10000"){
	    				parent.layer.msg("添加成功");
	    				$("#imgShow").html("");
	    				var imgurl = $("input[name='imgurl']").val("");
	    				location.reload();
	    			}else{
	    				parent.layer.msg(result.errorMsg);
	    			}
	    		}
	    	);
    	});
    }
    
</script>
</body>
</html>