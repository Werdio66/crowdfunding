<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <%-- 静态包含css页面 --%>
    <%@include file="common/css.jsp"%>
    <link rel="stylesheet" href="${PATH}/static/css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form id="loginForm" class="form-signin" role="form" action="/login" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
            <div class="form-group has-success has-feedback" style="color: red">
                ${SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
        </c:if>

        <div class="form-group has-success has-feedback"><%-- 密码错误后账号回写 ${param.username} --%>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="text" class="form-control" id="username" name="username" value="zhangsan" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="password" name="password" value="1" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>

        <div class="checkbox">
            <label>
                <input type="checkbox" name="remember-me" value="remember-me"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="reg.html">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
    </form>
</div>
<%-- 动态包含 --%>
<jsp:include page="common/js.jsp"/>
<script>
    function dologin() {

        // 表单校验
        var username = $("#username").val();
        var  password = $("#password").val();
        if (!$.trim(username) || !$.trim(password)){
            layer.msg("您输入的用户名或密码为空！", {icon : 5}, 2000);
            return false;
        }
        // 提交表单
        $("#loginForm").submit();
    }
</script>
</body>
</html>