<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/23
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
    <link rel="alternate icon" type="image/png" href="${pageContext.request.contextPath}/amazeui/i/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/amazeui/css/amazeui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
</head>
<body>
<div class="header">
    <div class="am-g">
        <h1>找回密码</h1>
    </div>
    <hr />
</div>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <form method="post" class="am-form" action="${pageContext.request.contextPath}/find.sys">
            <div class="login-form-div">
                <input type="text" name="username" id="username" value="" placeholder="请输入登录名" >
            </div>
            <div class="login-form-div">
                <input type="password" name="pwd" id="pwd" value="" placeholder="请重新设计密码">
            </div>
            <div class="login-form-div">
                <input type="email" name="email" id="email" value="" placeholder="请输入邮箱">
            </div>
            <div class="am-cf login-form-div">
                <input type="submit" name="" value="修改" class="am-btn am-btn-primary am-btn-lg  am-btn-block am-fl">
            </div>
        </form>
        <hr>
        <p class="am-text-center">&copy; 2017 Designed by yxq   <a href="mailTo:liyan@itany.com">liyan@itany.com</a> </p>
    </div>
</div>
</body>
</html>
