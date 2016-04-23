<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Bootstrap 101 Template</title>
  <!-- Bootstrap -->
  <link href="<%=path%>css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=path%>css/common.css" rel="stylesheet">
  <link href="<%=path%>css/main.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" media="all" href="<%=path%>js/daterangepicker/daterangepicker-bs3.css" />
  <link href="<%=path%>js/showLoading/showLoading.css"  rel="stylesheet" type="text/css" /> 
  <link rel="stylesheet" type="text/css" href="<%=path%>thirdparty/uploadify/uploadify.css">
  <link href="<%=path%>js/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
 

 
 
   
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
    <script src="<%=path%>js/html5shiv.min.js"></script>
  <![endif]-->
</head>

<body> 
<!--头部-->
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid clear">
      <div class="navbar-header">
        <a class="navbar-brand" href="#"><img src="<%=path%>images/main/ttl_admin.png" alt=""></a>
      </div>
      <div class="fr">
        <ul class="toolnav">
          <li class="user"><a class="name" href="#">刘晓东</a></li>
          <li class="set"><a href="#">系统设置</a></li>
          <li class="loginout"><a href="#">注销</a></li>
        <!-- <li class="advice"><a href="#">举报及建议</a></li> -->
        </ul>
      </div>
    </div>
    <div class="clear navbar-menu-wrap">
      <div class="fl navbar-menu">
        <ul class="clear fms f14">
          <li class="fl tc on"><a href="#"  class="block">内容管理</a></li>
          <li class="fl tc"><a href="#" class="block">栏目管理</a></li>
          <li class="fl tc"><a href="#" class="block">安全信息</a></li>
        </ul>
      </div>
      <div class="fr">
        <form action="">
          <div class="searchBar clear"><input type="text" class="txt" name="" id="" placeholder="请输入关键词"><input type="button" value="" class="inputBtn" value=""></div>
        </form>
      </div>
    </div>
  </nav>
  <div class="container-fluid">
    <div class="row">
      <!--侧栏-->
      <div class="sidebar">
        <ul class="sidemenu fms ">
          <li class="closeli">
            <p class="perTtl f14"><span class="block">内容管理</span></p>
            <ul class="submenu lastclose">
              <li data-remote="newslist/"><a href="#newslist"  class="block">信息管理</a></li>
              <li data-remote="linklist/"><a href="#linklist"  class="block">超链接管理</a></li>
              <li data-remote="newstypelist/"><a href="#newstypelist"  class="block">信息栏目管理</a></li>
             </ul>
          </li>
            
          
          
        </ul>
      </div>

      <!--右边主体部分-->
<div class="container-right" id="container-right">
</div>
<div id="modal_edit" class="modal fade in" style=""></div>
<!-- 弹框 -->

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script type="text/javascript" src="<%=path%>js/jquery-1.10.2.min.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<%=path%>js/common.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<%=path%>js/bootstrap-modal.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<%=path%>js/showLoading/jquery.showLoading.min.js" charset="UTF-8" ></script>
  <script type="text/javascript" src="<%=path%>thirdparty/uploadify/jquery.uploadify.min.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<%=path%>thirdparty/jquery-validation/dist/jquery.validate.min.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<%=path%>js/validate/validateForm.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<%=path%>js/daterangepicker/moment.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<%=path%>js/daterangepicker/daterangepicker.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<%=path%>js/datetimepicker/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
  <script type="text/javascript" src="<%=path%>js/datetimepicker/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
 
  <script language="javascript">
  $(document).ready(function(){

	/*左侧菜单*/
	$(".closeli p").click(function(){
		var $this = $(this);
		var mainmenu = $this.parent("li");
		var submenu = $this.siblings(".submenu");
		$(".submenu").hide();
		submenu.slideDown(200);
		mainmenu.siblings("li.openli").attr("class","closeli");
		mainmenu.attr("class","openli");
	});
	
	$(".clear li").click(function(){
		var $this = $(this);
		alert("123");
	});
	
	$(".openli p").click(function(){
		var $this = $(this);
		var mainmenu = $this.parent("li");
		var submenu = $this.siblings(".submenu");
		submenu.slideUp(200);
		mainmenu.attr("class","closeli");
	});
	
	$(".submenu li").click(function(){
	  menuActive($(this));
	  url="<%=path%>manage/"+$(this).data('remote');
	  loadPage(url);
	});
	
	$(".fnli").click(function(){
		menuActive($(this));
		$(".submenu").hide();
		$(".openli").attr("class","closeli");
	});
	function menuActive(obj){
		$(".sidemenu").find("li").removeClass("active");
		obj.addClass("active");
	};
 })


  </script>

</body>
</html>