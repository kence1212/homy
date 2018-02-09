<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<link rel="stylesheet" href="<%=contextPath%>/resource/layui/css/layui.css"  media="all">
<script src="<%=contextPath%>/resource/layui/layui.js"></script>
<script src="<%=contextPath%>/resource/javascript/modernizr.min.js"></script>
<style>
	.numberControlBox{ 

display:inline-block; 

height:26px; overflow:hidden; 

vertical-align: middle;
margin-left:-26px;
}
.ncb_up,.ncb_down{ 

font-size:0px; 

display:block; 

height:10px; 

background-color:#f4f4f4; 

background:-moz-linear-gradient(top,rgb(255,255,255) 0%,rgb(230,230,230) 50%,rgb(255,255,255) 100%);
width:24px;
border:1px solid #d1d1d1;
cursor:pointer; 

}
.ncb_up{
margin-bottom:1px; 

}
.numberControlBox .ncb_ico{
display:block; 

height:10px;
 background-image:url(../img/arrow.png);background-repeat:no-repeat; 

}
.ncb_up .ncb_ico{
background-position: -22px center;
}
.ncb_down .ncb_ico{
background-position: 1px center;
}
.ncb_btn_hover{ 

border:1px solid #9dc7e7;
background-color:#dff2fc; 

background:-moz-linear-gradient(top,rgb(255,255,255) 0%,rgb(210,237,250) 50%,rgb(255,255,255) 100%);
}
.ncb_btn_selected{ border:1px solid #6198c2;
background-color:#aee1fb; 

background:-moz-linear-gradient(top,rgb(255,255,255) 0%,rgb(174,225,251) 50%,rgb(255,255,255) 100%);
}
</style>
<script>
jQuery.fn.numbertype = function(){
	var numberFlag = null,
		timeInterval = 180,
		isKeyPress = false,
		changeAction = function(step, numberText){
			var value = numberText.value,
				maxNum = jQuery(numberText).attr('max') * 1,
				minNum = jQuery(numberText).attr('min') * 1,
				result = 0;
			if(value === "" || !/^\d+$/.test(value)){
				value = 0;
			}
			result = value * 1 + step;
			if((step < 0 && result < minNum) || (step > 0 && result > maxNum)){
				clearTimeout(numberFlag);
				return;
			}
			numberText.value = result;
			if(timeInterval <= 10){
				timeInterval = 10;
			}else{
				timeInterval -= 10;
			}
			numberFlag = setTimeout(function(){changeAction(step, numberText)}, timeInterval);
		},
		upAction = function(numberText, currentObj){
			var step = jQuery(numberText).attr('step');
			if(step === undefined || !/^\d+$/.test(step)){
				step = 1;
			}
			step *= 1;
			if(currentObj !== undefined){
				jQuery(currentObj).addClass('ncb_btn_selected');
			}
			timeInterval = 180;
			changeAction(step, numberText);
		},
		downAction = function(numberText, currentObj){
			var step = jQuery(numberText).attr('step');
			if(step === undefined || !/^\d+$/.test(step)){
				step = 1;
			}
			step *= -1;
			if(currentObj !== undefined){
				jQuery(currentObj).addClass('ncb_btn_selected');
			}
			timeInterval = 180;
			changeAction(step, numberText);
		},
		construct = function(height, numberText){
			var numberControlBox = document.createElement('span'),
				upBtn = document.createElement('span'),
				ico_up = document.createElement('span'),
				ico_down = document.createElement('span'),
				downBtn = document.createElement('span');

			numberControlBox.className = "numberControlBox";
			numberControlBox.style.height = height + "px";
			upBtn.className = "ncb_up";
			upBtn.style.height = Math.floor(height/2 - 3) + "px";
			downBtn.className = "ncb_down";
			downBtn.style.height = Math.floor(height/2 - 3) + "px";
			ico_up.className = "ncb_ico";
			ico_down.className = "ncb_ico";
			ico_up.style.height = Math.floor(height/2 - 3) + "px";
			ico_down.style.height = Math.floor(height/2 - 3) + "px";
			upBtn.appendChild(ico_up);
			downBtn.appendChild(ico_down);
			numberControlBox.appendChild(upBtn);
			numberControlBox.appendChild(downBtn);

			jQuery(upBtn).mousedown(function(){
				upAction(numberText, this);
			}).mouseenter(function(){
				jQuery(this).addClass('ncb_btn_hover');
			}).mouseleave(function(){
				jQuery(this).removeClass('ncb_btn_hover');
				clearTimeout(numberFlag);
			}).mouseup(function(){
				jQuery(this).removeClass('ncb_btn_selected');
				clearTimeout(numberFlag);
			});
			jQuery(downBtn).mousedown(function(){
				downAction(numberText, this);
			}).mouseenter(function(){
				jQuery(this).addClass('ncb_btn_hover');
			}).mouseleave(function(){
				jQuery(this).removeClass('ncb_btn_hover');
				clearTimeout(numberFlag);
			}).mouseup(function(){
				jQuery(this).removeClass('ncb_btn_selected');
				clearTimeout(numberFlag);
			});
			jQuery(numberText).keydown(function(event){
				var keycode = event.keyCode;
				if(isKeyPress){
					return false;
				}
				if(keycode === 38){
					upAction(numberText);
				}else if(keycode === 40){
					downAction(numberText);
				}
				isKeyPress = true;
			}).keyup(function(){
				clearTimeout(numberFlag);
				isKeyPress = false;
			});
			return numberControlBox;
		};
	this.each(function(index){
		var numberText = jQuery(this);
		jQuery(construct(numberText.outerHeight(), this)).insertAfter(numberText);
		
	});
	jQuery(document).mouseup(function(){	
		jQuery('.ncb_up').removeClass('ncb_btn_selected');
		jQuery('.ncb_down').removeClass('ncb_btn_selected');
	});
	return this;
};
(function(){
	if(!Modernizr.inputtypes.number){
		jQuery('input[type=number]').numbertype();
	}
})();
</script>