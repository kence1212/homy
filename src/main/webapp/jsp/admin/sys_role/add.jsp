<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>权限页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
  	<jsp:include page="../common/_cssjs.jsp"></jsp:include>
	<jsp:include page="../common/_validformcssjs.jsp"></jsp:include>
    <style>
        .perBlock{
            display: block;
        }
        .perBlock h3{
            background: #f0f0f0;
            padding: 3px 15px;
            margin-bottom: 10px;
        }
        .perBlock>li{
            display: inline-block;
            padding:10px;
            width:200px;
            margin: 5px;
            vertical-align: top;
            background: #f9f9f9;
            border-top: 5px solid #d7dde4;
        }
        .perCheckBlock>li{
            margin-left: 25px;
        }
        .perCheckBlock>li:first-child{
            margin: 0 0 5px 5px;
            border-bottom: solid 1px #c8d0da;
        }
        .perCheckBlock>li:first-child .check-box{
            margin-bottom: 0px;
        }
        .checkIshave span:before {
            content: "\f096";
            color: #4bcf99;
        }
        .checkIshave span{
            position: relative;
        }
        .checkIshave span:after {
            content: "\f0c8";
            color: #4bcf99;
            margin: 0 3px;
            font-family: "iconfont";
            font-weight: normal;
            font-style: normal;
            text-decoration: inherit;
            -webkit-font-smoothing: antialiased;
            display: inline;
            width: auto;
            height: auto;
            line-height: normal;
            vertical-align: baseline;
            background-image: none;
            background-position: 0 0;
            background-repeat: repeat;
            margin-top: 0;
            position: absolute;
            top:3px;
            left:0;
            font-size: 12px;
        }
    </style>
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
				<h2 class="title">新增角色</h2>
			</header>
			<hr>
		</section>
		<form id="saveForm" action="add.html" class="layui-form" method="POST">
		<div class="form-group-col-2">
			<div class="form-label">角色名称：</div>
			<div class="form-cont">
				<input type="text" name="roleName" lay-verify="required|styleName" placeholder="角色名称" class="form-control form-boxed" style="width:300px;">
				<input type="button" id="addBtn" class="btn btn-primary" style="display:inline-block;float:right;" value="新建"  />
			</div>
		</div>
		<div class="form-group-col-2">
			<div class="form-label">角色代码：</div>
			<div class="form-cont">
				<input type="tel" name="roleCode" lay-verify="required|styleCode" placeholder="角色代码" class="form-control form-boxed" style="width:300px;">
			</div>
		</div>
        <ul class="perBlock">
        <c:forEach items="${modelList }" var="topModel">
            <li>
                <h3>${topModel.name }</h3>
                <c:forEach items="${topModel.childrenModels }" var="secondModel">
                <ul class="perCheckBlock">
                    <li>
                        <label class="check-box">
                            <input type="checkbox"/>
                            <span>${secondModel.name }</span>
                        </label>
                    </li>
                    <c:forEach items="${secondModel.modelPermissions }" var="permission">
                    <li>
                        <label class="check-box">
                            <input type="checkbox" name="permissions" value="${permission.id }"/>
                            <span>${permission.permissionName }</span>
                        </label>
                    </li>
                    </c:forEach>
                </ul>
                </c:forEach>
            </li>
         </c:forEach>
        </ul>
       	<!-- <div class="form-group-col-2">
			<div class="form-label"></div>
			<div class="form-cont">
				input type="button" lay-submit lay-filter="*" class="btn btn-primary" value="新建"  />
				<input type="button" id="addBtn" class="btn btn-primary" value="新建"  />
			</div>
		</div> -->
		<!--开始::结束-->
		</form>
    </div>
</div>
<script>
    //权限管理checkbox选择树事件处理
    $(".perCheckBlock input[type='checkbox']").on("change", function () {
        var checkboxs = $(this).parents(".perCheckBlock").find("input[type='checkbox']");
        var index = checkboxs.index(this);
        var length = checkboxs.length;
        if(index>0){
            $(this).parents(".perCheckBlock").find("input[type='checkbox']").each(function (idx, element) {
                if(idx>0){
                    if($(element).is(":checked")){
                        checkboxs.eq(0).parents("label").addClass("checkIshave");
                        $(element).attr("data-checked",true);
                    }else{
                        checkboxs.eq(0).prop("checked",false);
                        $(element).removeAttr("data-checked");
                    }
                }
            });
        }else{
            if($(this).is(":checked")){
                $(this).parents(".perCheckBlock").find("input[type='checkbox']").each(function (idx, element) {
                    if(idx>0){
                        $(element).prop("checked",true);
                        $(element).attr("data-checked",true);
                    }
                });
            }else{
                $(this).parents(".perCheckBlock").find("input[type='checkbox']").each(function (idx, element) {
                    if(idx>0){
                        $(element).prop("checked",false);
                        $(element).removeAttr("data-checked");
                    }
                });
            }
        }
        var i = $(this).parents(".perCheckBlock").find("input[data-checked=true]").length;
        if(length-1 === i){
            checkboxs.eq(0).prop("checked",true);
            checkboxs.eq(0).parents("label").removeClass("checkIshave");
        }
        if(i===0){
            checkboxs.eq(0).parents(".check-box").removeClass("checkIshave");
        }
    });
    
    
 /* layui.use('form', function(){
		  var form = layui.form;
		  form.verify({
		  });
		  form.on('submit(*)', function(data){   
			  parent.layer.load(3,{shade:[0.5,'#000'],time:0});
			  $('.layui-form').submit();
		  });  
}); */

$("#addBtn").on("click",function(){
	var roleName = $('input[name="roleName"]').val();
	var roleCode = $('input[name="roleCode"]').val();
	if(!(roleName&&roleName.length>1)){
		layer.msg('请填写角色名称',{icon:2});
		parent.layer.closeAll("loading");
		return;
	}
	if(!(roleCode&&roleCode.length>1)){
		layer.msg('请填写角色代码',{icon:2});
		parent.layer.closeAll("loading");
		return;
	}
	if(roleName.length>60){
		layer.msg('角色名称长度不应超过60个字符',{icon:2});
		parent.layer.closeAll("loading");
		return;
	}
	if(roleCode.length>20){
		layer.msg('角色代码长度不应超过20个字符',{icon:2});
		parent.layer.closeAll("loading");
		return;
	}
	parent.layer.load(3,{shade:[0.5,'#000'],time:0});
	$('.layui-form').submit();
});

function save(){
	$("#saveForm").submit();
}

</script>
</body>
</html>