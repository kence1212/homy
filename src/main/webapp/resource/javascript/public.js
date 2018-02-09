
$(function(){
	'use strict';
	//头部选项卡
	var tabArr = [];
	parent.layer.closeAll('loading');
	$('#mainTabNav li').each(function (idx, element) {
		var tabname = $(element).text().trim();
		tabArr.push(tabname);
	})
	$('.side-menu a').on('click',function(event){
		var href = $(this).attr('href'),
			mainHeight = $('.main-cont').height(),
			name = $(this).text().trim(),
			iframeHeight = mainHeight - 153;
		var nothistab = $.inArray(name,tabArr);
		var icon = $(this).find('i').eq(0).attr('class')||$(this).parents('dl').find('i').eq(0).attr('class');
		if(nothistab === -1){
			$('#mainTabNav li').removeClass('active');
			$('#mainCard .tab-cont').hide();
			tabArr.push(name);

			$('#mainTabNav').append('<li class="active"><span class="'+icon+'"></span>'+name+'<i></i></li>');
			$('#mainCard').append('<div class="tab-cont" style="display: block;"><iframe width=100% height='+iframeHeight+' frameborder=0 scrolling=auto src="'+href+'"></iframe></div>');
		}else{
			$('#mainTabNav li').eq(nothistab).addClass('active').siblings().removeClass('active');
			$('.tab-cont').eq(nothistab).fadeIn().siblings('.tab-cont').hide();
		}
		liReset(name);
		event.preventDefault();
	});
	//删除此项
	$('#mainTabNav').on('click','li i', function (event) {
		var mianLi = $('#mainTabNav li');
		var tbCont = $('#mainCard .tab-cont');
		if(mianLi.length==1){
			return;
		}
		var iIndex = $('.tab-nav li i').index(this);
		if(mianLi.eq(iIndex+1).hasClass('active')){
			mianLi.eq(iIndex).addClass('active').siblings().removeClass('active');
			tbCont.eq(iIndex).fadeIn().siblings('.tab-cont').hide();
		}
		mianLi.eq(iIndex+1).remove();
		tbCont.eq(iIndex+1).remove();
		tabArr.splice(iIndex+1,1);
		event.preventDefault();
	});
	//重新排列选项卡
	function liReset(str){
		var newLiWidth = 58+ str.length*14,
			cardheadWidth = $('#mainCard').width()-104,
			offsetLeft= 0,
			liToThisWidth = (function(name){
				var width = 0;
				$('#mainTabNav li').each(function (idx, element) {
					var $element = $(element);
					if($element.text() === name){
						offsetLeft = $(element)[0].offsetLeft;
						return false;
					}
					width += $(element)[0].clientWidth;
				});
				return width;
			})(str),
			liAllWidth = (function () {
				var width = 0;
				$('#mainTabNav li').each(function (idx, element) {
					var $element = $(element);
					width += $(element)[0].clientWidth;
				});
				return width;
			})();
		if(liAllWidth<cardheadWidth){
			$('#mainTabNav').animate({'left':0+'px'},500);
			return;
		}
		var left = parseInt($('#mainTabNav').css('left'));
		if(left===0&&liToThisWidth+newLiWidth>cardheadWidth){
			$('#mainTabNav').animate({'left':cardheadWidth-(liToThisWidth+newLiWidth)+'px'},500);
		}else if(left!=0){
			if(-left>=offsetLeft){
				$('#mainTabNav').animate({'left':-offsetLeft+'px'},500);
			}else{
				if(liToThisWidth+newLiWidth>cardheadWidth){
					$('#mainTabNav').animate({'left':cardheadWidth-(liToThisWidth+newLiWidth)+'px'},500);
				}
			}
		}
	}
	//选项卡左右移动
	$('.nav-to-icon').on('click',function(){
		var howLong = 300;
		var type = $(this).attr('type'),
			cardheadWidth = $('#mainCard').width()-104,
			liAllWidth = (function(){
				var width = 0;
				$('#mainTabNav li').each(function (idx, element) {
					width += $(element)[0].clientWidth;
				});
				return width;
			})();
		var left = parseInt($('#mainTabNav').css('left'));
		if(cardheadWidth<liAllWidth && left<=0) {
			if ('left' === type) {
				if (left + howLong < -liAllWidth) {
					$('#mainTabNav').animate({'left': -liAllWidth + 'px'}, 500);
				} else {
					if(left + howLong > 0){
						$('#mainTabNav').animate({'left': 0 + 'px'}, 500);
					}else{
						$('#mainTabNav').animate({'left': left + howLong + 'px'}, 500);
					}
					//$('#mainTabNav').animate({'left': left + 50 + 'px'}, 500);
				}

			} else {
				if (left - howLong-cardheadWidth < -liAllWidth) {
					$('#mainTabNav').animate({'left': -liAllWidth+cardheadWidth-5 + 'px'}, 500);
				} else {
					if (left - howLong > 0) {
						$('#mainTabNav').animate({'left': 0 + 'px'}, 500);
					} else {
						$('#mainTabNav').animate({'left': left - howLong + 'px'}, 500);
					}
				}
			}
		}
	});
	//刷新按钮
	$('.nav-refresh').on('click',function(){
		var mainHeight = $('.main-cont').height(),
			iframeHeight = mainHeight - 153;
		var active = $('#mainTabNav .active');
		var index = $('#mainTabNav li').index(active);
		var tabCont = $('#mainCard .tab-cont').eq(index);
		var iframe = tabCont.find('iframe');
		var loadHtml = '<div class="iframeLoading" style="height:'+mainHeight+'px;line-height:'+mainHeight+'px;"><span class="icon-spin icon-refresh"></span></div>'
		if(iframe&&iframe.attr('src')){
			tabCont.append(loadHtml);
			var i = document.createElement("iframe");
			i.src = iframe.attr('src');
			//i.src = 'http://www.baidu.com';
			if (i.attachEvent){
				i.attachEvent("onload", function(){
					$('.iframeLoading').hide();
				});
			} else {
				i.onload = function(){
					$('.iframeLoading').hide();
				};
			}
			i.width = '100%';
			i.height = iframeHeight;
			i.scrolling = 'auto';
			i.setAttribute('frameborder', '0', 0);
			tabCont.html(i);
		}
	});
	//关闭当前
	$('#closeOne').on('click',function(){
		var active = $('#mainTabNav .active');
		var index = $('#mainTabNav li').index(active);
		if(!active.attr('canntclose')){
			var name = active.text();
			var nextName = $('#mainTabNav li').eq(index-1).text();
			liReset(nextName);
			tabArr.pop(name);
			active.remove();
			$('#mainTabNav li').eq(index-1).addClass('active');
			$('#mainCard .tab-cont').eq(index).remove();
			$('.tab-cont').eq(index-1).fadeIn();
		}
	});
	//关闭所有
	$('#closeAll').on('click', function () {
		var tabCont = $('#mainCard .tab-cont');
		$('#mainTabNav li').each(function (idx, element) {
			var $element = $(element);
			if(!$element.attr('canntclose')){
				var name = $element.text();
				tabArr.pop(name);
				$element.remove();
				tabCont.eq(idx).remove();
			}else{
				$element.addClass('active');
				tabCont.eq(idx).fadeIn();
				liReset($element.text());
			}
		})
	})
	//左侧导航菜单效果
	$('.side-menu li dt').click(function(){
		$(this).parents('li').addClass('open');
		$(this).parents('.side-menu').find('.InitialPage').removeClass('active');
		$(this).parents('li').siblings().removeClass('open');
		
	});
	$('.side-menu dd a').click(function(){
		$(this).parents('li').addClass('open');
		$(this).parents('li').siblings().removeClass('open')
	});
	$('.side-menu li').each(function(){
		//判断链接相当（当前页面导航样式）
		if($(this).find('a').attr('href') == window.location.pathname){
			$(this).addClass('open');
			$(this).siblings().find('.InitialPage').removeClass('active');
			$('.InitialPage').removeClass('active');
		}else if($('.side-menu h2 a').attr('href') == window.location.pathname){
			$('.InitialPage').addClass('active');
		}   
	});
    //Tab选项卡.
    //$('.tab-nav li').click(function(){
    //	var liIndex = $('.tab-nav li').index(this);
    //	$(this).addClass('active').siblings().removeClass('active');
    //	$('.tab-cont').eq(liIndex).fadeIn().siblings('.tab-cont').hide();
    //});
	$('.tab-nav').on('click','li',function(){
		var liIndex = $('.tab-nav li').index(this);
		$(this).addClass('active').siblings().removeClass('active');
		$('.tab-cont').eq(liIndex).fadeIn().siblings('.tab-cont').hide();
		liReset($(this).text().trim());
	});
    //Button下拉菜单
    $('.btn-drop-group .btn').click(function(e){
    	$(this).siblings('.drop-list').show();
    	$(this).parent().siblings().find('.drop-list').hide();
    	$(document).one('click', function(){
	        $('.btn-drop-group .drop-list').hide();
	    });
	    e.stopPropagation();
    });
    	//点击list将当前值复制于button
	    $('.btn-drop-group .drop-list li').click(function(){
	    	$(this).parent().parent().hide();
	    });
	//左侧菜单收缩
	$('.top-hd .hd-lt').click(function(){
		$('.side-nav').toggleClass('hide');
		$('.top-hd,.main-cont,.btm-ft').toggleClass('switchMenu');
		$('.top-hd .hd-lt a').toggleClass('icon-exchange');
		localStorage.setItem('setLayOut1','hide');
		localStorage.setItem('setLayOut2','switchMenu');
		localStorage.setItem('setLayOut3','icon-exchange');
		if(!$('.side-nav').hasClass('hide')){
			localStorage.removeItem('setLayOut1');
			localStorage.removeItem('setLayOut2');
			localStorage.removeItem('setLayOut3');
		}
	});
	$('.side-nav').addClass(localStorage.getItem('setLayOut1'));
	$('.top-hd,.main-cont,.btm-ft').addClass(localStorage.getItem('setLayOut2'));
	$('.top-hd .hd-lt a').addClass(localStorage.getItem('setLayOut3'));
	

	//弹出层基础效果（确认/取消/关闭）
	$('.JyesBtn').click(function(){
		$(this).parents('.dialog').hide();
		if($('.mask').attr('display','block')){
			$('.mask').hide();
		}
	});
	$('.JnoBtn,.JclosePanel').click(function(){
		$(this).parents('.dialog').hide();
		if($('.mask').attr('display','block')){
			$('.mask').hide();
		}
	});
	//基础弹出窗
	$('.JopenPanel').click(function(){
		$('.dialog').show();
		$('.dialog').css('box-shadow','0 0 30px #d2d2d2');
	});
	//带遮罩
	$('.JopenMaskPanel').click(function(){
		$('.dialog,.mask').show();
		$('.dialog').css('box-shadow','none');
	});
	$('.mask').click(function(){
		$(this).hide();
		$('.dialog').hide();
	});
	//自动关闭消息窗口
	$('.Jmsg').click(function(){
		$('dialog').show().delay(5000).hide(0);
	});	
	//安全退出
	$('#JsSignOut').click(function(){
		layer.confirm('确定登出管理中心？', {
		  title:'系统提示',
		  btn: ['确定','取消']
		}, function(){
		  location.href = 'login.html';
		});
	});
	
	//存储列表页的选择框勾选个数。
	var listCheckedNum = [];
	
	//列表页全选勾选
	$("#selectAll").on("click",function(){
		var td = $(this).parents(".table").find(".cen td input[type='checkbox']");
		var isChecked = $(this).is(':checked');
		if(td&&td.length&&isChecked){
			td.each(function(idx,element){
				var $element = $(element);
				$element.prop("checked",true);
				var value = $(element).val();
				listCheckedNum = editorListCheckedNum(listCheckedNum,value,"push");
			});
			/*listCheckedNum = td.length;*/
		}else if(td&&td.length&&!isChecked){
			td.each(function(idx,element){
				var $element = $(element);
				$element.prop("checked",false);
				var value = $(element).val();
				listCheckedNum = editorListCheckedNum(listCheckedNum,value,"pop");
			});
			/*listCheckedNum = 0;*/
		}
		deleteToggle(listCheckedNum);
	});
	//点击单个选择框的处理
	$(".cen td input[type='checkbox']").on("click",function(){
		var isChecked = $(this).is(':checked');
		var value = $(this).val();
		if(isChecked){
			listCheckedNum = editorListCheckedNum(listCheckedNum,value,"push");
		}else{
			listCheckedNum = editorListCheckedNum(listCheckedNum,value,"pop");
		}
		deleteToggle(listCheckedNum);
	});
	//点击批量删除按钮的处理
	$("#deleteList").on("click",function(){
		var url = $(this).attr("data-href");
		var message = $(this).attr("data-message")||'是否要批量操作';
		if(url){
			parent.layer.confirm(message, function(index){
			 parent.layer.close(index);
			 	var ids = listCheckedNum.join(",")
			 	console.log(ids);
			 	/*$.ajax({
					url:url,
					type:'POST',
					data:{ids:listCheckedNum},
					success:function(data){
						
					}
				});*/
			});
		}
	});
	
	/*var thisUrl = window.location.href;
	console.log(window.location.href,/\/homy\/\w{3,8}\/index.html'/.test(thisUrl));
	if(/\/homy\/ambrand\/index.html'/.test(thisUrl)){
		parent.window.location.href = '/homy/qem/index.html';
	}*/
});

//分类弹窗iframe
$('.formpoupIframe').on('click', function () {
	var iframeUrl = $(this).attr('data-url');
	var width = $(this).attr('data-width')?$(this).attr('data-width')+'px':'700px';
	var height = $(this).attr('data-height')?$(this).attr('data-height')+'px':'450px';
	var title = $(this).parents('.form-group-col-2').find('.form-label').text().trim();
	parent.layer.open({
		type: 2,
		title:title,
		area:[width,height],
		fixed:false,
		maxmin:true,
		content:iframeUrl
	});
});

function reflush(){
	parent.reflush();
}

//批量删除按钮切换
function deleteToggle(listCheckedNum){
	if(listCheckedNum||listCheckedNum.length===0){
		if(listCheckedNum.length>1){
			$("#deleteBlock").show();
		}else{
			$("#deleteBlock").hide();
		}
	}
}
//处理listCheckedNum值
function editorListCheckedNum(listCheckedNum,value,popOrPush){
	if(listCheckedNum&&value&&popOrPush){
		if(popOrPush==="push"){
			listCheckedNum.push(value);
			$.unique(listCheckedNum.sort());
		}else{
			listCheckedNum.pop(value);
		}
		return listCheckedNum;
	}
	return false;
}
