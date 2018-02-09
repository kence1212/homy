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
		
		  <div class="clear mb-10">
            <div class="fl">
                <a href="<%=request.getContextPath() %>/amgoods/index.html"><button class="btn btn-secondary"><i class="icon-chevron-left"></i>返回</button></a>
            </div>
        </div>
		
		<section class="page-hd">
			<header>
				<h2 class="title">库存列表</h2>
				<p class="title-description">
					库存list
				</p>
			</header>
			<hr>
		</section>
		<div class="clear mt-10 mb-10">
			<div class="fl">
				<a href="toAdd.html?id=${goodsId }"><button class="btn btn-secondary"><i class="icon-plus"></i>新增</button></a>
			</div>
			<div class="fl ml-10">
				<a href="exportExcel.do?goodsId=${goodsId }"><button class="btn btn-secondary"><i class="icon-plus"></i>库存导出</button></a>
			</div>
			<div class="fl ml-10">
				<button class="btn btn-secondary uploadexcel"><i class="icon-cloud-upload"></i>库存导入(excel)</button>
			</div>
			<div class="fr input-group">
				<input id="keyword" type="text" class="form-control" placeholder="名称、颜色、尺寸、材质、城市" style="width:290px;">
				<button class="btn btn-secondary-outline" onclick="search()">搜索</button>
			</div>
		</div>
		<table class="table mb-15">
			<thead>
			<tr>
				<th><input type="checkbox"/></th>
				<th>商品名称</th>
				<th>颜色</th>
				<th>尺寸</th>
				<th>材质</th>
				<th>城市</th>
				<th>库存</th>
				<th>价格</th>
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

var goodsId = "${goodsId }";	
	
function search(){
	 $('.pagination').unbind('click');
	 var keyword = $("#keyword").val();
	 $.post("<%=request.getContextPath()%>"+"/amsku/getList.html",
			 {keyword:keyword,
		 		goodsId:goodsId},
			function(data){
				 $('#tbody').html('');
				    var listPageCount = 10,length = data.totalCount; //每页数量，总共记录数
				    var pageCount = Math.ceil(length/listPageCount); //页数
				    var html2 = '';
				    var json = data.list;
				    for(var x in json){
				        html2 += '<tr class="cen">'
				                +'<td><input type="checkbox"/></td>'
				                +'<td>'+json[x].goodsName+'</td>'
				                +'<td>'+json[x].colorName+'</td>'
				                +'<td>'+json[x].sizeName+'</td>'
				                +'<td>'+json[x].textureName+'</td>'
				                +'<td>'+json[x].cityName+'</td>'
				                +'<td>'+json[x].stock+'</td>'
				                +'<td>'+json[x].price+'</td>'
				                +'<td>'+json[x].createTime+'</td>'
				                +'<td><a title="编辑" class="mr-5" href="toEdit.html?id='+json[x].id+'">编辑</a><a title="详情" class="mr-5">详情</a><a title="删除" onclick="deleteData('+json[x].id+')">删除</a></td>'
				                +'</tr>';
				    }
				    $('#tbody').html(html2);
				    $(".pagination").createPage({
				        pageCount:pageCount,
				        current:1,
				        backFn:function(p){
				        	 var keyword = $("#keyword").val();
						 $.post("<%=request.getContextPath()%>"+"/amsku/getList.html",
								 {keyword:keyword,
							 	page:p,
							 	pageSize:10,
							 	goodsId:goodsId},
								 function(data){
									 var html = '';
									 var json = data.list;
								     for(var x in json){
								         html +='<tr class="cen">'
								        	  +'<td><input type="checkbox"/></td>'
								                +'<td>'+json[x].goodsName+'</td>'
								                +'<td>'+json[x].colorName+'</td>'
								                +'<td>'+json[x].sizeName+'</td>'
								                +'<td>'+json[x].textureName+'</td>'
								                +'<td>'+json[x].cityName+'</td>'
								                +'<td>'+json[x].stock+'</td>'
								                +'<td>'+json[x].price+'</td>'
								                +'<td>'+json[x].createTime+'</td>'
								                +'<td><a title="编辑" class="mr-5" href="toEdit.html?id='+json[x].id+'">编辑</a><a title="详情" class="mr-5">详情</a><a title="删除" onclick="deleteData('+json[x].id+')">删除</a></td>'
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

$(function(){
	search();
	$(".uploadexcel").on('click',function(){
		layer.open({
		  type: 1,
		  shade:false,
		  title:'库存导入(excel)',
		  skin: 'layui-layer-rim', //加上边框
		  area: ['500px', '200px'], //宽高
		  content: '<div class="main-wrap">'+
					    '<div class="page-wrap pt-50">'+
					    '<div id="fileDiv" class="form-group-col-2">'+
						'<div id="file_container" class="">'+
							'<input type="text" id="fileName" class="form-control form-boxed" style="width:200px;">'+
							'<input type="text" id="modelPath" name="modelPath"  style="display: none">'+
							'<button id="modelFile" class="btn btn-secondary fill-upload-btn ml-10">'+
								'选择文件'+
							'</button>'+
							'<button id="uploadok" class="btn btn-secondary fill-upload-btn ml-10">'+
								'上传'+
							'</button>'+
						'</div>'+
					'</div>'+
				  		'</div>'+
				   '</div>'
		});
		fileUpload();
	})
})
	
function deleteData(id){
	parent.layer.confirm("确定删除么？",{},function(){
		$.post(
			"delete.html",
			{"id":id},
			function(data){
				if(data.success){
					parent.layer.msg("删除成功");
					location.href="<%=request.getContextPath()%>/amsku/index.html?id=${goodsId }";
				}else{
					parent.layer.msg(data.msg,{"icon":2});
				}
			},
			"json"
		);
		
	});
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
	        max_file_size : '2m',
	        mime_types: [
	            {title : "EXCEL", extensions : "xls"}
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
        parent.layer.load(3,{shade:[0.5,'#000'],time:0});
        $.post(
   			"importExcel.do",
   			{"fileUrl":response.fileUrl,"goodsId":goodsId},
   			function(data){
   				parent.layer.closeAll();
   				if(data.success){
					parent.layer.msg("导入"+ data.obj + "条库存");
					location.href="<%=request.getContextPath()%>/amsku/index.html?id=${goodsId }";
				}else{
					parent.layer.msg(data.msg,{"icon":2});
				}
   			},
   			"json"
   		);
        parent.layer.closeAll();
        layer.closeAll();
    });
	categoryFile.init();
	$("#uploadok").on('click',function(){
		var filename = $("#fileName").val();
		categoryFile.start();
	});
}

</script>
</html>