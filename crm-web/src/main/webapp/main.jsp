<%@ page import="org.po.Web_User" %>
<%@ page import="org.po.Groups" %>
<%@ page import="org.crm.service.DealService" %>
<%@ page import="org.crm.dao.SqlLibrary" %>
<%@ page import="org.po.ResultInfo" %>
<%@ page import="org.po.Friends" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>主页</title>
<head>
    <link rel="icon" href="https://v3.bootcss.com/favicon.ico">

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="bs/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="bs/css/bootstrap-theme.css" crossorigin="anonymous">
    <script src="bs/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css"/>
    <link rel="stylesheet" href="easyui/themes/icon.css"/>
    <meta charset="utf-8"/>
    <title></title>

</head>

<style>
    body {
        font-family: 微软雅黑;
    }
    .panel {
        margin-top: 5px;
        border-radius: 7px;
    }
</style>
<body class="easyui-layout" onload="startTime()" `>

<div class="container" data-options="region:'north'" style="background:url(img/bg.jpg) ;height:180px">
    <img src="
<c:if test="${user.head==null}">
	/img/1.jpg
</c:if>
<c:if test="${user.head!=null}">
	../head/${user.head}
</c:if>
" style="margin-left: 50px;border-radius:50%;float: left;width: 175px;height:175px"/>
    <span class="menu-text" style="margin-left: 30px;margin-top: 80px;font-family: 微软雅黑;font-size: xx-large;color: #1b1b1b">
        ${user.nick}
        <br/><span style="margin-top: 5px;font-size: small;font-family: 微软雅黑">
        <c:if test="${user.mood==null}">
            还没设置心情。。。
        </c:if>
        <c:if test="${user.mood!=null}">
            ${user.mood}
        </c:if>
        </span>
    </span>



</div>
<%
    Web_User user = (Web_User) request.getSession().getAttribute("user");
    if(user!=null){
    Object[] params = new Object[]{user.getId()};
    ResultInfo<Groups> groupQueryInfo = DealService.doQuery(SqlLibrary.SQL_SHOW_GROUP, params, Groups.class);
    request.getSession().setAttribute("groupQueryInfo", groupQueryInfo);
    ResultInfo<Friends> friendsQueryInfo = DealService.doQuery(SqlLibrary.SQL_SHOW_FRIEND, params, Friends.class);
        System.out.println(friendsQueryInfo);
    request.getSession().setAttribute("friendsQueryInfo", friendsQueryInfo);
    }
%>
<div data-options="region:'east',title:'好友列表',split:true" style="width:249px;">
    <input id="writeFriend" type="text" style="width: 90px;height: 25px" />
    <select id="cc" class="easyui-combobox" name="dept" style="width:90px;height: 30px">
        <c:forEach var="temp" items="${groupQueryInfo.t }" varStatus="tt">
            <option value="${temp.id}">${temp.groups}</option>
        </c:forEach>
    </select>
    <button id="addFriend" style="width: 50px;height: 30px;" >添加</button>
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

<div data-options="region:'west',split:true" style="width:240px;">
    <div class="panel panel-success">
        <div class="panel-heading" onclick="accordion()">我的帖子</div>
        <div class="panel-body" hidden=true>
            <ul class="list-group">
                <li class="list-group-item"><a href="javascript:listMyArticle('帖子列表', 'article/article.jsp')"
                                               style="width:100%"
                                               class="btn btn-default">我的帖子</a></li>
                <li class="list-group-item"><a href="javascript:addTab('新的帖子','article/new.jsp')" style="width:100%"
                                               class="btn btn-default">写新帖子</a></li>

            </ul>
        </div>
    </div>
    <div class="panel panel-info">
        <div class="panel-heading" onclick="accordion2()">我的好友</div>
        <div class="panel-body">
            <ul class="list-group">
            </ul>
        </div>
    </div>
    <div class="panel panel-warning">
        <div class="panel-heading" onclick="accordion3()">个人管理</div>
        <div class="panel-body" hidden=true>
            <ul class="list-group" >
                <li class="list-group-item" >
                    <a class="btn btn-default" style="width:100%;" href="javascript:addTab('修改信息','info.jsp')">修改信息</a>
                </li>
                <li class="list-group-item" >
                    <a class="btn btn-default" style="width:100%;" href="javascript:addTab('修改密码','password.jsp')">修改密码</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div id="divcenter" data-options="region:'center'">
    <div id="tt" class="easyui-tabs" style="height: 99%; font-size: 100%;">

    </div>
</div>

<div data-options="region:'south'" style="height:32px;font-size: 20px;">
    <c:if test="${user.uname == null}">
        <div style="display:inline-block;float: left;">You are now offline...</div>
    </c:if>
    <c:if test="${user.uname != null}">
        <input type="hidden" id="myName" value=${user.nick}>
        <div style="display:inline-block;float: left;">已作为[${user.uname}]登录&nbsp;&nbsp;<a
                href="javascript:logout()">注销登陆</a></div>
    </c:if>
    <div style="display:inline-block;float: right;">time：<span id="nowDateTimeSpan"></span></div>

</div>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div id="myModalbody" class="modal-body">
                ...
            </div>
            <div id="myModalDate"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    $(document).ready(function () {
        $.ajax({
            type: 'POST',
            dataType: "json",
            url: 'groupMgr.do?method=query',
            success: function (data) {
                $.each(data.t, function (i, n) {
                    console.log(data)
                    $('#aa').accordion('add', {
                        title: n.groups,
                        selected: false,

                        content: "<div id='a' class='easyui-accordion' style='width:250px;'>"
                        + "<div title='Title2'>" + "<a id='btn' href='#' class='easyui-linkbutton' >easyui</a>"
                        + "<a id='btn' href='#' class='easyui-linkbutton' >easyui</a>"
                        + "<a id='btn' href='#' class='easyui-linkbutton' >easyui</a></div></div>",
                    });
                })
            }
        })
    })
    $('#aa').accordion({
        onSelect: function (title, index) {
            $("ul[name='" + title + "']").tree({
                url: 'menu/getModules?menuName=' + title,
            });

        }
    });


    function showModal(title, content, date) {
        $('#myModalLabel').html(title);
        $('#myModalbody').html(content);
        $('#myModalDate').html(date);
        $('#myModal1').modal('show');
    }

    function listMyArticle(text, url) {
        $.ajax({
            url: 'articleMgr.do',
            type: 'post',
            data: {
                act: 'list'
            },
            success: function (data) {
                if ($("#tt").tabs("exists", text)) {
                    var pp = $('#tt').tabs('getSelected');
                    $("#tt").tabs("select", text);
                } else {
                    var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
                    $("#tt").tabs("add", {
                        title: text,
                        closable: true,
                        content: content
                    });
                }
            }
        })
        if ($("#tt").tabs("exists", text)) {
            $("#tt").tabs("select", text);
        } else {
            var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
            $("#tt").tabs("add", {
                title: text,
                closable: true,
                content: content
            });
        }
    }

    function accordion() {
        var pan = $('.panel-success>div:last').prop('hidden');
        $('.panel-success>div:last').prop('hidden', !pan);
    }

    function accordion2() {
        var pan = $('.panel-info>div:last').prop('hidden');
        $('.panel-info>div:last').prop('hidden', !pan);
    }

    function accordion3() {
        var pan = $('.panel-warning>div:last').prop('hidden');
        $('.panel-warning>div:last').prop('hidden', !pan);
    }

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
        if (today.getDay() == 0) day = "Sun"
        if (today.getDay() == 1) day = "Mon"
        if (today.getDay() == 2) day = "Tue"
        if (today.getDay() == 3) day = "Wed"
        if (today.getDay() == 4) day = "Tur"
        if (today.getDay() == 5) day = "Fri"
        if (today.getDay() == 6) day = "Sat"
        document.getElementById('nowDateTimeSpan').innerHTML = yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "   " + day;
        setTimeout('startTime()', 1000);
    }

    function checkTime(i) {
        if (i < 10) {
            i = "0" + i;
        }
        return i;
    }

    function logout() {
        $.ajax({
            type: 'post',
            url: "userMgr.do?act=logout",
            success: function () {
                location.reload();
            }
        })
    }

    function addTab(text, url) {
        if ($("#tt").tabs("exists", text)) {
            $("#tt").tabs("select", text);
        } else {
            var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
            $("#tt").tabs("add", {
                title: text,
                closable: true,
                content: content
            });
        }
    }
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
        setMessage(event.data);
    }

    var mto_nick ;
    var mfrom_nick;
    function chatting(id, to_nick, from_nick) {
        mto_nick = to_nick;
        mfrom_nick = from_nick;
        $('#tt').tabs('add', {
            title: to_nick,
            content: "<div style='width:100%;height:100%;background-color: #ffa8a8'>"
            +"<div style='width:550px;height:450px;border:1px solid #86697e;background-color: white;position:relative;margin:0 auto; '>"
            + "<div id='show_msg' style='width:550px;height:400px;position:relative;margin:0 auto'></div>"
            + "<input id='write_msg' type='text' style='width:400px;height:40px;position:relative;left:20px;top:0px'/>"
            + "<input id='go_msg' type='button' onclick='chat()' style='width:60px;height:50px;position:relative;right:-60px;top:0px' value='发送'/></div></div>",
            closable: true,

        });
    }


    function chat() {
        var message = $("#write_msg").val();
        if (null == message || "" == message) {
            return;
        }
        var content = {'fromName':mfrom_nick, "toName":mto_nick, content:message};
        var newContent = JSON.stringify(content); // 将json对象转化成string
//        JSON.parse(jsonstring); // 将json字符串转化成json对象
        socket.send(newContent);
        $("#write_msg").val("");

    }

    //显示消息
    function setMessage(msg) {
        var obj = JSON.parse(msg);
        var my_name = $("#myName").val();
        console.log(my_name)
        if(my_name == obj.name){
            $("#show_msg").append("<p>"+obj.name+":"+obj.content + "</p>");
        }else{
            $("#show_msg").append("<p style='text-align:right;text-justify:inter-ideograph'>"+obj.content+":"+obj.name + "</p>");
        }
    }

    //查询昵称是否存在
    $("#writeFriend").blur(function(){
        var nick =  $("#writeFriend").val();
        if("" == nick || null == nick){

            alert("该昵称不存在")
        }
        $.ajax({
            type : 'post',
            url : 'friends',
            data : 'method=queryNick&nick='+nick,
            dataType : 'JSON',
            success : function(data){
                if(0 == data.status){
                    alert("该昵称不存在")
                }
            }
        });
    });


    //查询添加的好友是否chenggong
    $("#addFriend").click(function(){
        var nick =  $("#writeFriend").val();
        var groupId = $('#cc').combobox('getValue');
       alert(nick+","+groupId);
        $.ajax({
            type : 'post',
            url : 'friends',
            data : 'method=addFriends&nick='+nick+'&groupId='+groupId,
            dataType : 'JSON',
            success : function(data){
                if(1 == data.status){
                    alert("添加成功")
                    return;
                }
                alert("添加失败")
            }
        });
    });

</script>

</html>
