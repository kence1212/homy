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
                <h2 class="title">编辑商品材质</h2>
            </header>
            <hr>
        </section>
        <input type="hidden" name="goodsId" value="${goodsId}">
        <div class="form-group-col-2">
            <div class="form-label">商品材质：</div>
            <c:forEach items="${textureList }" var="bean">
            <div class="form-cont">
                <input type="text" name="name" value="${bean.texture_name }" readonly="readonly" class="form-control form-boxed mr-5 mb-5" style="width: 300px;">
                <button type="button" class="btn btn-danger" onclick="deleteById(${bean.id})"><i class="icon-minus"></i></button>
            </div>
            </c:forEach>
            
            <div class="form-cont add-block">
                <input type="text" name="textureName" value="" class="form-control form-boxed mr-5 mb-5" style="width: 300px;">
                <button type="button" class="btn btn-primary" onclick="addTexture()"><i class="icon-plus"></i></button>
            </div>
        </div>
        <!--开始::结束-->
    </div>
</div>

<script>
    function deleteById(id){
    	parent.layer.confirm("删除此项？",function(){
	    	$.post(
	    		"deleteGoodsTexture.do",
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
    
    function addTexture(){
    	var goodsId = $("input[name='goodsId']").val();
    	var textureName = $("input[name='textureName']").val();
    	parent.layer.confirm("增加此项？",function(){
	    	$.post(
	    		"addGoodsTexture.do",
	    		{"goodsId":goodsId,"name":textureName},
	    		function(result){
	    			if(result.resultCode == "10000"){
	    				parent.layer.msg("添加成功");
	    				$("input[name='textureName']").val("");
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