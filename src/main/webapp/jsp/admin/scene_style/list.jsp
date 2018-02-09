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
				<h2 class="title">场景风格列表</h2>
				<p class="title-description">
					场景风格list
				</p>
			</header>
			<hr>
		</section>
		<div class="clear mt-10 mb-10">
			<div class="fl">
				<a href="toAdd.html"><button class="btn btn-secondary"><i class="icon-plus"></i>新增</button></a>
			</div>
			<div class="fr input-group">
				<input type="text" id="keyword" class="form-control" placeholder="搜索..." style="width:290px;">
				<button class="btn btn-secondary-outline" onclick="search()">搜索</button>
			</div>
		</div>
		<table class="table mb-15">
			<thead>
			<tr>
				<th><input type="checkbox"/></th>
				<th>场景标题</th>
				<th>是否上架</th>
				<th>场景排序</th>
				<th>场景描述</th>
				<th>场景分类</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
		</table>
	</div>
	<div class="panel panel-default">
         <div class="panel-bd">
             <div class="pagination table-pagination mt-5 mb-10"></div>
         </div>
     </div>
</div>

</body>
<script type="text/javascript">
	
function search(){
	 $('.pagination').unbind('click');
	 var keyword = $("#keyword").val();
	 $.post("<%=request.getContextPath()%>"+"/amscenestyle/getList.html",
			 {keyword:keyword},
			function(data){
				 $('#tbody').html('');
				    var listPageCount = 10,length = data.totalCount; //每页数量，总共记录数
				    var pageCount = Math.ceil(length/listPageCount); //页数
				    var html2 = '';
				    var json = data.list;
				    for(var x in json){
				    	var stateStr = "上架";
				    	if(json[x].state == 0){
				    		stateStr = "下架";
				    	}
				        html2 += '<tr class="cen">'
					                +'<td><input type="checkbox"/></td>'
					                +'<td>'+json[x].title+'</td>'
					                +'<td>'+stateStr+'</td>'
					                +'<td>'+json[x].sort+'</td>'
					                +'<td>'+json[x].sceneDesc+'</td>'
					                +'<td>'+json[x].categoryName+'</td>'
					                +'<td>'+json[x].createTime+'</td>'
					                +'<td><a title="编辑" class="mr-5" href="toEdit.html?id='+json[x].id+'">编辑</a><a title="详情" class="mr-5">详情</a>'
					                +'<a title="上架" onclick="up('+json[x].id+')">上架</a> <a title="下架" onclick="down('+json[x].id+')">下架</a> <a title="删除" onclick="deleteData('+json[x].id+')">删除</a></td>'
				                +'</tr>';
				    }
				    $('#tbody').html(html2);
				    $(".pagination").createPage({
				        pageCount:pageCount,
				        current:1,
				        backFn:function(p){
				        	 var keyword = $("#keyword").val();
						 $.post("<%=request.getContextPath()%>"+"/amscenestyle/getList.html",
								 {keyword:keyword,
							 	page:p,
							 	pageSize:10},
								 function(data){
									 var html = '';
									 var json = data.list;
								     for(var x in json){
								    	 var parentName = json[x].parentName;
									    	if(!parentName){
									    		parentName = "";
									    	}
								         html +='<tr class="cen">'
								                +'<td><input type="checkbox"/></td>'
								                +'<td>'+json[x].title+'</td>'
								                +'<td>'+json[x].sort+'</td>'
								                +'<td>'+json[x].sceneDesc+'</td>'
								                +'<td>'+json[x].categoryName+'</td>'
								                +'<td>'+json[x].createTime+'</td>'
								                +'<td><a title="编辑" class="mr-5" href="toEdit.html?id='+json[x].id+'">编辑</a><a title="详情" class="mr-5">详情</a>'
								                +'<a title="上架" onclick="up('+json[x].id+')">上架</a> <a title="下架" onclick="down('+json[x].id+')">下架</a> <a title="删除" onclick="deleteData('+json[x].id+')">删除</a></td>'
							                +'</tr>';
								     }
								     $('#tbody').html(html);
								 },"json"
								 )
									        }
									    });
									    
									    
								 },"json"	 
						 )
	
}   

search();
	
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
	function up(id){
		parent.layer.confirm("确定上架么？",{},function(){
			$.post(
				"up.html",
				{"id":id},
				function(data){
					if(data.success){
						parent.layer.msg("上架成功");
						reflush();
					}else{
						parent.layer.msg(data.msg,{"icon":2});
					}
				},
				"json"
			);
			
		});
	}
	function down(id){
		parent.layer.confirm("确定下架么？",{},function(){
			$.post(
				"down.html",
				{"id":id},
				function(data){
					if(data.success){
						parent.layer.msg("下架成功");
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