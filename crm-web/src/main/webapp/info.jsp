<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="https://v3.bootcss.com/favicon.ico">

		<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
		<link rel="stylesheet" href="bs/css/bootstrap.min.css" crossorigin="anonymous">
		<link rel="stylesheet" href="bs/css/bootstrap-theme.css" crossorigin="anonymous">
		<script src="bs/js/bootstrap.min.js" crossorigin="anonymous"></script>
		<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
		<script src="easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css"/>
		<link rel="stylesheet" href="easyui/themes/icon.css"/>
		<meta charset="utf-8" />
		<title>JSP Page</title>
	</head>

	<body>
		<div style="margin-left: 5px;margin-top: 20px;">
			<div style="margin-top: 20px;width: 50%;float: left;">
				<form class="form-horizontal" method="post" action="userMgr.do?act=update" enctype="multipart/form-data">
					<div class="form-group">
						<label for="nick" class="col-sm-2 control-label">昵称：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="nick" name="nick" value="${user.nick}" placeholder="昵称">
						</div>
					</div>
					<div class="form-group">
						<label for="mood" class="col-sm-2 control-label">心情：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="mood" name="mood" value="${user.mood}" placeholder="心情" maxlength="100">
						</div>
					</div>
					<div class="form-group">
						<label for="age" class="col-sm-2 control-label">年龄：</label>
						<div class="col-sm-10">
							<input type="number" class="form-control" id="age" name="age" value="${user.age}" placeholder="年龄" maxlength="3">
						</div>
					</div>
					<div class="form-group">
						<label for="head" class="col-sm-2 control-label">头像：</label>
						<div class="col-sm-5">
							<input name="head" id="head" type="file" class="form-control" value="${user.head}"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">保存</button>
						</div>
					</div>
				</form>
			</div>
			<div style="float: left;margin-left: 25px;"><img class="img-circle" src="
<c:if test="${user.head==null}">
	../img/1.jpg
</c:if>
<c:if test="${user.head!=null}">
	../head/${user.head}
</c:if>

" style="width: 200px;height:200px;"/> </div>
		</div>
	</body>
<script>

</script>
</html>