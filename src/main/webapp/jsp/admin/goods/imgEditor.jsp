<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>list</title>
    <meta name="keywords"  content="设置关键词..." />
    <meta name="description" content="设置描述..." />
    <meta name="author" content="DeathGhost" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/resource/imageCropping/cropper.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/resource/imageCropping/main.css" />
<jsp:include page="../common/_cssjs.jsp"></jsp:include>

</head>
<body>
<div class="main-wrap">
    <div class="page-wrap">
      
<div class="container">
  <div class="row">
    <div class="col-md-9">
      <!-- <h3>Demo:</h3> -->
      <div class="img-container">
        <img id="image" src="<%=contextPath%>/resource/images/picture.jpg" alt="Picture">
      </div>
    </div>
    <div class="col-md-3">
      <!-- <h3>Preview:</h3> -->
      <div class="docs-preview clearfix">
        <div class="img-preview preview-lg"></div>
        <div class="img-preview preview-md"></div>
        <div class="img-preview preview-sm"></div>
        <div class="img-preview preview-xs"></div>
      </div>

      <!-- <h3>Data:</h3> -->
      <div class="docs-data">
        <div class="input-group input-group-sm">
          <label class="input-group-addon" for="dataX">X</label>
          <input type="text" class="form-control" id="dataX" placeholder="x">
          <span class="input-group-addon">px</span>
        </div>
        <div class="input-group input-group-sm">
          <label class="input-group-addon" for="dataY">Y</label>
          <input type="text" class="form-control" id="dataY" placeholder="y">
          <span class="input-group-addon">px</span>
        </div>
        <div class="input-group input-group-sm">
          <label class="input-group-addon" for="dataWidth">Width</label>
          <input type="text" class="form-control" id="dataWidth" placeholder="width">
          <span class="input-group-addon">px</span>
        </div>
        <div class="input-group input-group-sm">
          <label class="input-group-addon" for="dataHeight">Height</label>
          <input type="text" class="form-control" id="dataHeight" placeholder="height">
          <span class="input-group-addon">px</span>
        </div>
        <div class="input-group input-group-sm">
          <label class="input-group-addon" for="dataRotate">Rotate</label>
          <input type="text" class="form-control" id="dataRotate" placeholder="rotate">
          <span class="input-group-addon">deg</span>
        </div>
        <div class="input-group input-group-sm">
          <label class="input-group-addon" for="dataScaleX">ScaleX</label>
          <input type="text" class="form-control" id="dataScaleX" placeholder="scaleX">
        </div>
        <div class="input-group input-group-sm">
          <label class="input-group-addon" for="dataScaleY">ScaleY</label>
          <input type="text" class="form-control" id="dataScaleY" placeholder="scaleY">
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-9 docs-buttons">
      <!-- <h3>Toolbar:</h3> -->
      <div class="btn-group">
        <label class="btn btn-primary btn-upload" for="inputImage" title="Upload image file">
          <input type="file" class="sr-only" id="inputImage" name="file" accept=".jpg,.jpeg,.png,.gif,.bmp,.tiff">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="选择新图片">
              <span class="fa fa-upload"></span>
            </span>
        </label>
      </div>
      <div class="btn-group">
        <button type="button" class="btn btn-primary" data-method="setDragMode" data-option="move" title="Move">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="拖动剪切框">
              <span class="fa fa-arrows"></span>
            </span>
        </button>
        <button type="button" class="btn btn-primary" data-method="setDragMode" data-option="crop" title="Crop">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="重定剪切框">
              <span class="fa fa-crop"></span>
            </span>
        </button>
      </div>

      <div class="btn-group">
        <button type="button" class="btn btn-primary" data-method="zoom" data-option="0.1" title="Zoom In">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="放大">
              <span class="fa fa-search-plus"></span>
            </span>
        </button>
        <button type="button" class="btn btn-primary" data-method="zoom" data-option="-0.1" title="Zoom Out">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="缩小">
              <span class="fa fa-search-minus"></span>
            </span>
        </button>
      </div>

      <div class="btn-group">
        <button type="button" class="btn btn-primary" data-method="move" data-option="-10" data-second-option="0" title="Move Left">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="图片向左移动">
              <span class="fa fa-arrow-left"></span>
            </span>
        </button>
        <button type="button" class="btn btn-primary" data-method="move" data-option="10" data-second-option="0" title="Move Right">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="图片向右移动">
              <span class="fa fa-arrow-right"></span>
            </span>
        </button>
        <button type="button" class="btn btn-primary" data-method="move" data-option="0" data-second-option="-10" title="Move Up">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="图片向上移动">
              <span class="fa fa-arrow-up"></span>
            </span>
        </button>
        <button type="button" class="btn btn-primary" data-method="move" data-option="0" data-second-option="10" title="Move Down">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="图片向下移动">
              <span class="fa fa-arrow-down"></span>
            </span>
        </button>
      </div>

      <div class="btn-group">
        <button type="button" class="btn btn-primary" data-method="rotate" data-option="-45" title="Rotate Left">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="图片逆时针旋转45度">
              <span class="fa fa-rotate-left"></span>
            </span>
        </button>
        <button type="button" class="btn btn-primary" data-method="rotate" data-option="45" title="Rotate Right">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="图片顺时针旋转45度">
              <span class="fa fa-rotate-right"></span>
            </span>
        </button>
      </div>

      <div class="btn-group">
        <button type="button" class="btn btn-primary" data-method="scaleX" data-option="-1" title="Flip Horizontal">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="">
              <span class="fa fa-arrows-h"></span>
            </span>
        </button>
        <button type="button" class="btn btn-primary" data-method="scaleY" data-option="-1" title="Flip Vertical">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="">
              <span class="fa fa-arrows-v"></span>
            </span>
        </button>
      </div>

      <div class="btn-group btn-group-crop">
        <button type="button" class="btn btn-success" data-method="getCroppedCanvas" data-option="{ &quot;maxWidth&quot;: 4096, &quot;maxHeight&quot;: 4096 }">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="生成剪切图片">
              选择此切图
            </span>
        </button>
      </div>
		<div class="col-md-3 docs-toggles">
      <!-- <h3>Toggles:</h3> -->
      <div class="btn-group d-flex flex-nowrap" data-toggle="buttons">
        <label class="btn btn-primary">
          <input type="radio" class="sr-only" id="aspectRatio0" name="aspectRatio" value="1.7777777777777777">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="剪切框比例： 16 / 9">
              16:9
            </span>
        </label>
        <label class="btn btn-primary">
          <input type="radio" class="sr-only" id="aspectRatio1" name="aspectRatio" value="1.3333333333333333">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="剪切框比例 ：4 / 3">
              4:3
            </span>
        </label>
        <label class="btn btn-primary">
          <input type="radio" class="sr-only" id="aspectRatio2" name="aspectRatio" value="1">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="剪切框比例： 1 / 1">
              1:1
            </span>
        </label>
        <label class="btn btn-primary">
          <input type="radio" class="sr-only" id="aspectRatio3" name="aspectRatio" value="0.6666666666666666">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="剪切框比例： 2 / 3">
              2:3
            </span>
        </label>
        <label class="btn btn-primary">
          <input type="radio" class="sr-only" id="aspectRatio4" name="aspectRatio" value="NaN">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="剪切框比例： 无比例">
              Free
            </span>
        </label>
       <label class="btn btn-primary active">
          <input type="radio" class="sr-only" id="aspectRatio4" name="aspectRatio" value="1.1830065359477124">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="剪切框比例： 362*306">
              362*306
            </span>
        </label>
      </div>
    </div><!-- /.docs-toggles -->
      <!-- Show the cropped image in modal -->
      <div class="modal fade docs-cropped" id="getCroppedCanvasModal" aria-hidden="true" aria-labelledby="getCroppedCanvasTitle" role="dialog" tabindex="-1">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="getCroppedCanvasTitle">Cropped</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
              <button type="button" class="btn btn-warning radius" data-dismiss="modal">关闭</button>
              <!--<button type="button" id="download" class="btn btn-primary">确定</button>-->
              <a class="btn btn-primary" id="download" href="javascript:void(0);" data-dismiss="modal">确定</a>
            </div>
          </div>
        </div>
      </div><!-- /.modal -->
    </div><!-- /.docs-buttons -->

    
  </div>
</div>
		  
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<script src="https://fengyuanchen.github.io/js/common.js"></script>
<script src="<%=contextPath%>/resource/imageCropping/cropper.js"></script>
<script src="<%=contextPath%>/resource/imageCropping/mobileFix.mini.js"></script>
<script src="<%=contextPath%>/resource/imageCropping/exif.js"></script>
<script src="<%=contextPath%>/resource/imageCropping/lrz.js"></script>
<script src="<%=contextPath%>/resource/imageCropping/main.js"></script>
<script>
  $("#download").on('click',function () {
	  var img = $(this);
    var baseImg = img.attr('href');
    var realWidth;//真实的宽度
    var realHeight;//真实的高度
    //这里做下说明，$("<img/>")这里是创建一个临时的img标签，类似js创建一个new Image()对象！
    var testImg = new Image();
    testImg.onload = function(){
	    realWidth = this.width;
	    realHeight = this.height;
	    //如果真实的宽度大于浏览器的宽度就按照100%显示
	    if(realWidth>800){
	    	lrz(dataURLtoBlob(baseImg),{width: 800}, function (results) {
			  // 你需要的数据都在这里，可以以字符串的形式传送base64给服务端转存为图片。
		      baseImg = results.base64;
		      var iframe = parent.$('.tab-cont[style="display: block;"]').find('iframe');
		   	  iframe[0].contentWindow.setHeadImg(baseImg);
		      parent.layer.closeAll('iframe');
		  });
	    }
	    else{//如果小于浏览器的宽度按照原尺寸显示
	    	var iframe = parent.$('.tab-cont[style="display: block;"]').find('iframe');
	     	iframe[0].contentWindow.setHeadImg(baseImg);
	        parent.layer.closeAll('iframe');
	    }
    }
    testImg.src = baseImg; 
  });
//**dataURL to blob**
  function dataURLtoBlob(dataurl) {
      var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
          bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
      while (n--) {
          u8arr[n] = bstr.charCodeAt(n);
      }
      return new Blob([u8arr], { type: mime });
  }
</script>
</body>
</html>