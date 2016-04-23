<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path=request.getContextPath();
//System.out.println(path);
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>抽奖</title>
<style type="text/css">
.demo{width:417px; height:417px; position:relative; margin:50px auto}
#disk{width:417px; height:417px; background:url(img/disk.jpg) no-repeat}
#start{width:163px; height:320px; position:absolute; top:46px; left:130px; cursor:pointer;}
#start img{cursor:pointer}
</style>
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jQueryRotate.2.2.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easing.min.js"></script>
<script type="text/javascript">
$(function(){
        lottery();
});
function lottery(){
	$("#startbtn").click(function(){
		$.ajax({
			type:'POST',
			url: getContextPath() + "/zhuanpan",
			dataType: 'json',
			cache: false,
			error: function(){
				alert('出错了');
				return false;
			},
			success:function(json){
			
				var a = parseInt(json.angle); 
				var p = json.praisename;
				var n = json.num;
				if(p!="" && a!=0){
					$("#startbtn").rotate({
						duration:3000, 
						angle: 0, 
						animateTo:3600+a,
						easing: $.easing.easeOutSine,
						callback: function(){
							var con = confirm('恭喜你，中得'+p+'您还有'+n+'次抽奖次数，还要再来一次吗？');
							$("#startbtn").rotate({angle:0});
							$("#startbtn").css("cursor","pointer");
							if(!con){
								$("#startbtn").unbind('click').css("cursor","default");
							}
						}
					});
				}else{
					alert("您已经没有抽奖次数了！");
				}
			}
		})
	})
}
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	
	return result;
}
</script>
</head>

<body>

<div class="demo">
        <div id="disk"></div>
        <div id="start"><img src="<%=path%>/img/start.png" id="startbtn"></div>
   </div>
</body>
</html>