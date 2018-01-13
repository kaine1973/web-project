<%--
  Created by IntelliJ IDEA.
  User: kaida
  Date: 2018/01/12
  Time: 下午 7:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="../bs/css/bootstrap.min.css" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="../bs/css/bootstrap-theme.css" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../bs/js/bootstrap.min.js" crossorigin="anonymous"></script>

<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script src="../easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="../easyui/themes/material/easyui.css"/>
<link rel="stylesheet" href="../easyui/themes/icon.css"/>
<head>
    <title>article</title>
</head>
<style>
    li{
        font-family: 微软雅黑;
    }
</style>
<body>
<c:if test="${articleResult.status != 1}">
    <h3>${articleResult.msg}</h3>
</c:if>
<c:if test="${articleResult.status == 1}">
    <ul class="list-group">

        <c:forEach items="${articleResult.t}" var="article">
            <li class="list-group-item">
                <a><span style="font-size: large">${article.title }</span></a>

                <span style="font-size: small;float: right;">最后一次修改：${article.update_date }</span>

            </li>
        </c:forEach>
    </ul>

</c:if>
</body>
<script>
</script>
</html>
