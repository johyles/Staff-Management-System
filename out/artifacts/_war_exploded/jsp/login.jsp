<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>用户登录</title>
  <!-- 以上代码告诉IE浏览器,IE8/9及以后的版本都会以最高版本IE来渲染页面 -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="${pageContext.request.contextPath}/amazeui/i/favicon.png">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/amazeui/css/amazeui.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
  <script type="text/javascript">
    var now;
    function changeImg(){
      var codeImg = document.getElementById("codeImg");
      now = new Date();
      codeImg.src="${pageContext.request.contextPath}/MakeCode.sys?code="+now.getTime();
    }
  </script>

</head>
<body>
<%
  String username = "";
  String password = "";
  String s;
  Cookie [] cookies =request.getCookies();
  for (Cookie cookie:cookies) {
    s=cookie.getName(); //通过getName方法获得cookie的名称
    if (s.equals("username")) {
      if (cookie.getValue()!=null) {
        username=cookie.getValue(); //通过getValue方法获得cookie的值
      }
    } else
    if (s.equals("password")) {
      if (cookie.getValue()!=null) {
        password=cookie.getValue();
      }
    }
  }
%>
<div class="header">
  <div class="am-g">
    <h1>用户登录</h1>
  </div>
  <hr />
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <form method="post" class="am-form" action="${pageContext.request.contextPath}/login.sys">
      <div class="login-form-div">
        <input type="text" name="username" id="username" value="<%=username %>" placeholder="请输入邮箱或登录名">
      </div>
      <div class="login-form-div">
        <input type="password" name="pwd" id="pwd" value="<%=password %>" placeholder="请输入登录密码">
      </div>
      <div class="login-form-div">
        <input style="width: 250px;" type="text" name="checkCode" id="checkCode" placeholder="请输入下方验证码"/>
      </div>
      <div class="login-form-div" >
        <img style="width: 120px; height: 50px;" id="codeImg" src="${pageContext.request.contextPath}/MakeCode.sys" onclick="changeImg()"/>点击刷新
      </div>
      <div class="login-form-div">
        <label for="remember-me">
          <input id="remember-me" type="checkbox" name="checkbox" value="true" checked="">
          记住密码
        </label>
        <input type="button" name="" value="忘记密码 ^_^?" class="am-btn am-btn-default am-btn-sm am-fr" onclick="window.location.href='${pageContext.request.contextPath}/tofind.sys'">
      </div>
      <div class="am-cf login-form-div">
        <input type="submit" name="" value="登 录" class="am-btn am-btn-primary am-btn-lg  am-btn-block am-fl">
        <a href="${pageContext.request.contextPath}/toregist.sys" class="am-btn am-btn-danger am-btn-lg  am-btn-block am-fl">去注册</a>
      </div>
    </form>
    <hr>
    <p class="am-text-center">&copy; 2017 Designed by yxq   <a href="mailTo:liyan@itany.com">liyan@itany.com</a> </p>
  </div>
</div>
</body>
</html>
