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

<c:if test="${listArticle.status != 1}">
    <h3>${listArticle.msg}</h3>
</c:if>
<c:if test="${listArticle.status == 1}">
    <button class="btn btn-default" onclick="showButtons()">管理</button>
    <button class="btn btn-danger">查找</button>
    <ul id="mgbtn" class="list-group">

        <c:forEach items="${listArticle.t}" var="article" >
            <li class="list-group-item">
                <input type="hidden" id="id" value="${article.id }"/>
                <div id="content${article.id }" hidden="hidden">${article.content }</div>
                <a href="javascript:showModal('${article.id }','${article.title }','${article.update_date }','${article.create_date }')"><span style="font-size: large">${article.title }</span></a>
                <div class="test" style="float: left;margin-right: 5px" hidden=true>
                <button class="btn btn-warning btn-sm" onclick="alterThisArticle('${article.title }','${article.id }')">修改</button>
                <button class="btn btn-danger btn-sm" onclick="deleteThisArticle(${article.id })">删除</button>
                </div>
                <span style="font-size: small;float: right;">最后一次修改：${article.update_date }</span>

            </li>

        </c:forEach>
    </ul>
</c:if>

</body>
<script>
    function alterThisArticle(title,id) {
        $.ajax({
            url:'../articleMgr.do',
            type:'POST',
            data:{
                act:'querySingle',
                id:id
            },
            success:function (data) {
                window.parent.addTab('修改文章','../article/edit.jsp');
            }
        })
    }
    function deleteThisArticle(id) {
        var content = $("#content"+id).parent();
        var conf = confirm('Are you sure?');
        if(!conf){
            return;
        }else{
            $.ajax({
                url:'../articleMgr.do',
                type:'POST',
                data:{
                    act:'delete',
                    id:id
                },
                success:function (data) {
                    if(data==1){
                        content.remove();
                    }else{
                        alert('删除失败');
                    }
                },
                error:function () {
                    alert('删除失败,服务器出错');
                }
            })
        }
    }
    function showModal(id,title,update_date,create_date) {
        var date = '<span style="margin-left: 10px">创建时间：'+create_date+'&nbsp;&nbsp;最后一次修改：'+update_date+'</span>';
        var content = $("#content"+id).html();
        $('#myModalLabel').html(title);
        $('#myModalbody').html(content);
        $('#myModalDate').html(date);
        window.parent.showModal(title,content,date);
        //$('#myModal').modal('show');

    }
    function showButtons() {
        var a = $('#mgbtn .test').prop('hidden');
        $('#mgbtn .test').prop('hidden',!a);
    }
</script>
</html>
