<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Bootstrap 101 Template</title>
  <!-- Bootstrap -->
  <link href="<%=path%>css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=path%>css/signin.css" rel="stylesheet">
  
   <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
    <script src="<%=path%>js/html5shiv.min.js"></script>
  <![endif]-->
</head>

<body>

  <div class="container">

    <form class="form-signin" name="userlogin" id="userlogin">
      <h2><img src="<%=path%>images/signin/ttl_login.png" alt=""></h2>
      <p class="form-tip">输入用户名和密码登录：</p>
      <label for="inputEmail" class="sr-only">用户名：</label>
      <input type="text" id="userid" name="userid" class="form-control" placeholder="请输入用户名" required autofocus>
      <label for="inputPassword" class="sr-only">密　码：</label>
      <input type="password" id="userpwd" name="userpwd" class="form-control" placeholder="请输入密码" required>
      <div class="checkbox">
        <label>
          <input type="checkbox" value="remember-me">记住密码
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" id="btlogin" type="button">登录</button>
    </form>
    <div class="signin-copy">AKMI 1.0 XX公司版权所有</div>
  </div> <!-- /container -->
 <script src="<%=path%>js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="<%=path%>thirdparty/jquery-validation/dist/jquery.validate.min.js"></script>
  <script type="text/javascript" src="<%=path%>js/validate/login.js"></script>
 
</body>
</html>
