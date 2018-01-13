<%--
  Created by IntelliJ IDEA.
  User: kaida
  Date: 2018/01/12
  Time: 下午 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="bs/css/bootstrap.min.css" crossorigin="anonymous">
<link rel="stylesheet" href="bs/css/bootstrap-theme.css" crossorigin="anonymous">
<script src="bs/js/bootstrap.min.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script src="easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css"/>
<link rel="stylesheet" href="easyui/themes/icon.css"/>
<head>
    <title>list articles</title>
</head>
<body>
<c:if test="${articleResult.status != 1}">
    <h3>${articleResult.msg}</h3>
</c:if>
<c:if test="${articleResult.status == 1}">
    <ul class="list-group">

        <c:forEach items="${articleResult.t}" var="article">
            <li class="list-group-item">${article.title }&nbsp;&nbsp;&nbsp;&nbsp;${article.create_date }</li>
        </c:forEach>
    </ul>

</c:if>
</body>
</html>
