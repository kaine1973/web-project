<%@ page import="org.po.Web_User" %>
<%@ page import="org.po.Groups" %>
<%@ page import="org.po.ResultInfo" %>
<%@ page import="org.crm.service.DealService" %>
<%@ page import="org.crm.dao.SqlLibrary" %>
<%@ page import="org.po.Friends" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Basic Panel - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css"
          href="easyui/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
</head>

<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height: 100px;"></div>

<div data-options="region:'east',iconCls:'icon-reload',title:'好友列表',split:true"
        style="width: 250px;">

    <%
        Web_User user = (Web_User)request.getSession().getAttribute("user");
        Object[] params = {user.getId()};
        ResultInfo<Groups> groupQueryInfo = DealService.doQuery(SqlLibrary.SQL_SHOW_GROUP,params, Groups.class);
        request.getSession().setAttribute("groupQueryInfo",groupQueryInfo);
        ResultInfo<Friends> friendsQueryInfo = DealService.doQuery(SqlLibrary.SQL_SHOW_FRIEND, params, Friends.class);

        request.getSession().setAttribute("friendsQueryInfo",friendsQueryInfo);
    %>

    <c:choose>
        <%-- 暂无记录的情况 --%>
        <c:when test="${groupQueryInfo.status<1}">
            <h3>没有分组，请添加</h3>
        </c:when>
        <%-- 已经存在类别的情况 --%>
        <c:otherwise>
            <!-- 循环遍历 类别列表 -->
            <c:forEach var="temp" items="${groupQueryInfo.t }" varStatus="tt">
                <div id="aa" class="easyui-accordion" data-options="selected:false">
                    <div title="${temp.groups}">
                        <c:choose>
                            <%-- 暂无记录的情况 --%>
                            <c:when test="${groupQueryInfo.status<1}">
                                <h3>没有好友，请添加</h3>
                            </c:when>
                            <%-- 已经存在类别的情况 --%>
                            <c:otherwise>
                                <!-- 循环遍历 类别列表 -->
                                <c:forEach var="element" items="${friendsQueryInfo.t }" varStatus="ts">
                                    <div id="bb${element.id }" class="easyui-accordion" data-options="selected:false">
                                        <c:if test="${temp.id == element.gid}">
                                            <div title="${element.nick}">
                                                <a id="del"
                                                   href="javascript:deleteFriends('${element.id}','${element.nick}')"
                                                   class="easyui-linkbutton"
                                                   style="position:relative;left:10px;top:-5px">刪除</a>
                                                <a id="ch"
                                                   href="javascript:chatting('${element.id}','${element.nick}','${user.nick }')"
                                                   class="easyui-linkbutton"
                                                   style="position:relative;left:100px;top:-5px">聊天</a>
                                            </div>
                                        </c:if>
                                    </div>

                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </c:forEach>
        </c:otherwise>
    </c:choose>


</div>
<div data-options="region:'west',title:'West'" style="width: 250px;">
    <div id="photo" style='width:200px;height:200px;position:relative;margin:0 auto'></div>
    </br>
    <div id="nick" style='width:80px;position:relative;margin:0 auto'><h2>${user.nick }</h2></div>
    <div id="age" style='width:80px;position:relative;margin:0 auto'><h2>age:[${user.age }]</h2></div>
    <div id="label" style='width:220px;height:100px;position:relative;margin:0 auto'>个性签名：<h2>${user.mood }</h2></div>
    </br>
    <div style="position:relative;left:20px"><input type="button" id="userManage" style="width:200px;height:50px"
                                                    value='修改个人信息'/></div>
</div>
<div data-options="region:'center'">
    <div id="room" class="easyui-tabs" ></div>
</div>
</body>
<script>
    $(function () {
        $.ajax({
            type: 'POST',
            url: 'groupMgr.do',
            data: 'method=query',
            success:function () {

            }
        });
        $.ajax({
            type: 'POST',
            url: 'friends',
            data: 'method=queryFriends',
        });
    });

    function deleteFriends(id, nick) {
        $.ajax({
            type: 'POST',
            url: 'friends',
            data: 'method=deleteFriends&id=' + id,
            dataType: 'JSON',
            success: function (data) {
                if (1 == data.status) {
                    /* alert('删除好友成功')
                    console.log(id)
                    console.log($('#bb'+id)) */
                    $('#bb' + id).accordion('remove', nick);

                }
            }
        });
    }

    var msg = "";
    if ('WebSocket' in window) {
        var path = window.location.host;
        socket = new WebSocket("ws://" + path + "/chat");
    } else {
        alert('当前浏览器不支持websocket');
    }
    socket.onerror = function () {
        setMessage("连接失败");
    }
    socket.onopen = function () {
        setMessage("连接成功");
    }
    window.onbeforeunload = function () {
        socket.close();
    }
    //接收消息
    socket.onmessage = function (event) {
        console.log(event.data)
        setMessage(event.data);
    }


    function chatting(id, to_nick, from_nick) {
        msg = from_nick + "+" + to_nick + "+";
        $('#room').tabs('add', {
            title: to_nick,
            content: "<div style='width:100%;height:100%;background-color: #ffa8a8'>"
            +"<div style='width:550px;height:500px;border:1px solid #86697e;background-color: white;position:relative;margin:0 auto; '>"
            + "<div id='show_msg' style='width:550px;height:450px;position:relative;margin:0 auto'></div>"
            + "<input id='write_msg' type='text' style='width:400px;height:40px;position:relative;left:20px;top:0px'/>"
            + "<input id='go_msg' type='button' onclick='chat()' style='width:60px;height:50px;position:relative;right:-60px;top:0px' value='发送'/></div></div>",
            closable: true,

        });
    }


    function chat() {
        console.log($("#write_msg").val())
        var message = $("#write_msg").val();
        if (null == message || "" == message) {
            return;
        }
        msg += message;
        socket.send(msg);
        console.log(message)
        $("#write_msg").val("");

    }

    //显示消息
    function setMessage(content) {
        console.log(content)
        $("#show_msg").append("<img style='width:30px;height:30px src=''>" + content + "</br>");
    }
</script>
</html>