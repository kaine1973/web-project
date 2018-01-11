<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="https://v3.bootcss.com/favicon.ico">
    <title>欢迎登陆</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
  </head>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
  <body>

    <div class="container">
      <form class="form-signin" action="userMgr.do?act=login" method="post">
        <h2 class="form-signin-heading">请登陆</h2>
        <label for="uname" class="sr-only">账户：</label>
        <input type="text" id="uname" name="uname" value="${name }" class="form-control" placeholder="User Name" required="" autofocus="" autocomplete="off">
        <label for="pwd" class="sr-only">密码：</label>
        <input type="password" id="pwd" name="pwd" value="" class="form-control" placeholder="Password" required="" autocomplete="off">
        <div class="checkbox">
          <label>
            <input type="checkbox"  name="remember" id="remember"> 记住我 &nbsp; &nbsp;
          </label>
        </div>
        <input name="method" value="login" hidden=""/>
            <span style="color:red">${result.msg }</span>
        <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="return checkUser()">登录</button>
	<a href="javascript:iforgot()" style="font-size:15px;float:left">忘记密码？</a>
	<a href="register.jsp" style="font-size:15px;float:right">我要注册</a>
      </form>
    </div> <!-- /container -->

</body>
<script type="text/javascript">
	function iforgot(){
		var conf = confirm("忘了密码就去查数据库吧！");
		if(conf){
			alert("哦不，密码加密了，查出来你也用不了...")
		}
	}
	function checkUser(){
		if($("#uname").val()!=""||$("#pwd").val()!=""){
			return true;
		}else{
			return false;
		}
	}
</script>
</html>