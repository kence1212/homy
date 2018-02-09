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
				<h2 class="title">用户列表</h2>
			</header>
			<hr>
		</section>
		<div class="clear mt-10 mb-10">
			<div class="fl">
				<a href="toAdd.html"><button class="btn btn-secondary"><i class="icon-plus"></i>新增</button></a>
			</div>
			<div class="fr input-group">
				<input type="text" class="form-control" name="searchKey" placeholder="搜索..." style="width:290px;" value="${searchKey }">
				<button class="btn btn-secondary-outline" onclick="find()">搜索</button>
			</div>
		</div>
		<table class="table mb-15">
			<thead>
			<tr>
				<th><input type="checkbox"/></th>
				<th>手机号</th>
				<th>用户名</th>
				<th>类型</th>
				<th>创建时间</th>
				<th>注册IP</th>
				<th>企业</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${userList }" var="bean">
			<tr class="cen">
				<td><input type="checkbox"/></td>
				<td class="lt"><a href="#">${bean.phone}</a></td>
				<td class="lt"><a href="#">${bean.username}</a></td>
				<td>
					<c:if test="${bean.grade == 1 }">个人</c:if>
					<c:if test="${bean.grade == 2 }">企业</c:if>
				</td>
				<td><fmt:formatDate value="${bean.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${bean.registerIp}</td>
				<td>${bean.company}</td>
				<td>
					<a title="详情" class="mr-5">详情</a>
					<a title="删除" onclick="deleteData(${bean.userId })">删除</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<!-- <div class="table-pagination mt-5 mb-10">
			<span class="disabled">上一页</span>
			<span class="current">1</span>
			<a href="javascript:;" class="tcdNumber">2</a>
			<a href="javascript:;" class="tcdNumber">3</a>
			<a href="javascript:;" class="tcdNumber">4</a>
			<a href="javascript:;" class="tcdNumber">5</a>
			<a href="javascript:;" class="nextPage">下一页</a>
		</div> -->
		<!--开始::结束-->
	</div>
</div>

</body>
<script type="text/javascript">
	function find(){
		var searchKey = $("input[name='searchKey']").val();
		if(searchKey == ""){
			return ;
		}
		var abc = encodeURI("index.html?searchKey="+searchKey);
		alert(abc);
		location.href = encodeURI("index.html?searchKey="+searchKey);
	}
	
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