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
</head>
<body>
<div class="main-wrap">
	<div class="page-wrap">
		<!--开始::内容-->
		<section class="page-hd">
			<header>
				<h2 class="title">轮播图列表</h2>
				<p class="title-description">
					轮播图list
				</p>
			</header>
			<hr>
		</section>
		<div class="clear mt-10 mb-10">
			<div class="fl">
				<a href="toAdd.html"><button class="btn btn-secondary"><i class="icon-plus"></i>新增</button></a>
			</div>
			<!-- <div class="fr input-group">
				<input type="text" class="form-control" placeholder="搜索..." style="width:290px;">
				<button class="btn btn-secondary-outline">搜索</button>
			</div> -->
		</div>
		<table class="table mb-15">
			<thead>
			<tr>
				<th><input type="checkbox"/></th>
				<th>轮播图标题</th>
				<th>轮播图类型</th>
				<th>轮播图地址</th>
				<th>轮播图排序</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${listAll }" var="bean">
			<tr class="cen">
				<td><input type="checkbox"/></td>
				<td class="lt"><a href="#">${bean.title}</a></td>
				<td>
					<c:if test="${bean.type==1}">商品</c:if>
					<c:if test="${bean.type==2}">链接</c:if>
				</td>
				<td>${bean.targetUrl}</td>
				<td>${bean.sort}</td>
				<td><fmt:formatDate value="${bean.createTime }" pattern="yyyy-MM-dd"/></td>
				<td>
					<a title="编辑" class="mr-5" href="toEdit.html?id=${bean.id }">编辑</a>
					<a title="详情" class="mr-5">详情</a>
					<a title="删除" onclick="deleteData(${bean.id })">删除</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		
	</div>
</div>

</body>
<script type="text/javascript">
	
	function deleteData(id){
		parent.layer.confirm("确定删除么？",{},function(){
			$.post(
				"delete.html",
				{"id":id},
				function(data){
					if(data.success){
						parent.layer.msg("删除成功");
						reflush();
					}else{
						parent.layer.msg(data.msg,{"icon":2});
					}
				},
				"json"
			);
			
		});
	}
</script>
</html>