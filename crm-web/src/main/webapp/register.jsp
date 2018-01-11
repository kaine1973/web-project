<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>欢迎你</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
</head>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<body>
<div class="container" >
      <form class="form-signin" action="userMgr.do?act=register" method="post">
        <h2 class="form-signin-heading">请注册</h2>
        <label for="uname" class="sr-only">用户名：</label>
        <input type="text" name="uname" id="uname" value="${name }" class="form-control"  placeholder="User Name" required="" autofocus="" >
        <label for="pwd" class="sr-only">密 &nbsp;码：</label>
        <input type="password" name="pwd" id="pwd" class="form-control" placeholder="Password" required="" >
        <span  style="color:red;height:100px">${result.msg }</span>
         <input name="method" value="regist" hidden=""/>
        <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="return regist()">注册</button>
	<a href="login.jsp" style="font-size:13px;float:right">我要登录</a>
      </form>
    </div> 
</body>
<script type="text/javascript">
	function regist(){
		if($("#uname").val()!=""||$("#pwd").val()!=""){
			return true;
		}else{
			alert("用户名或密码不能为空");
			return false;
		}
	}
</script>
</html>