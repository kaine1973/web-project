<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <title>主页</title>
<head>
    <meta charset="utf-8" />
    <title></title>
    <style>
        body{
            background-image: image("img/133042-106.jpg");
        }
        #westtabs div {
            height: 50%;
        }
    </style>
</head>

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="bs/css/bootstrap.min.css" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="bs/css/bootstrap-theme.css" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bs/js/bootstrap.min.js" crossorigin="anonymous"></script>

<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script src="easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css" />
<link rel="stylesheet" href="easyui/themes/icon.css" />

<body class="easyui-layout" onload="startTime()" `>
<%  %>
<div data-options="region:'north'" style="height:120px"></div>

<div data-options="region:'east',split:true" title="Chat" style="width:24%;">

    <div id="westtabs" class="easyui-accordion">
        <div title="Online">
            <table id="online"></table>暂无项目</div>

    </div>

</div>
<div data-options="region:'west',split:true" title="West" style="width:18%;">

    <button type="button" class="btn btn-primary btn-lg">（大按钮）Large button</button>
    <button type="button" class="btn btn-default btn-lg">（大按钮）Large button</button>

</div>
<div id="divcenter" data-options="region:'center',title:'Center'" ></div>
<div data-options="region:'south'" style="height:32px;font-size: 20px;">
    <c:if test="${user.name == null}">
        <div style="display:inline-block;float: left;">You are now offline...</div>
    </c:if>
    <c:if test="${user.name != null}">
        <div style="display:inline-block;float: left;">已作为[${user.name}]登录&nbsp;&nbsp;<a href="javascript:logout()">注销登陆</a></div>
    </c:if>
    <div style="display:inline-block;float: right;">time：<span id="nowDateTimeSpan"></span></div>

</div>
</body>
<script type="text/javascript">
    function startTime() {
        var today = new Date();
        var yyyy = today.getFullYear();
        var MM = today.getMonth() + 1;
        var dd = today.getDate();
        var hh = today.getHours();
        var mm = today.getMinutes();
        var ss = today.getSeconds();
        MM = checkTime(MM);
        dd = checkTime(dd);
        mm = checkTime(mm);
        ss = checkTime(ss);
        var day;
        if(today.getDay() == 0) day = "Sun"
        if(today.getDay() == 1) day = "Mon"
        if(today.getDay() == 2) day = "Tue"
        if(today.getDay() == 3) day = "Wed"
        if(today.getDay() == 4) day = "Tur"
        if(today.getDay() == 5) day = "Fri"
        if(today.getDay() == 6) day = "Sat"
        document.getElementById('nowDateTimeSpan').innerHTML = yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "   " + day;
        setTimeout('startTime()', 1000);
    }

    function checkTime(i) {
        if(i < 10) {
            i = "0" + i;
        }
        return i;
    }
    function logout() {
        $.ajax({
            type:'post',
            url:"userMgr.do?act=logout",
            success:function(){
                location.reload();
            }
        })
    }
</script>

</html>
