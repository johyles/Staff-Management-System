<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>用户注册</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="${pageContext.request.contextPath}/amazeui/i/favicon.png">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/amazeui/css/amazeui.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
  <script type="text/javascript">
    function showNameInfo(str){
      var xmlhttp;
      if(str.length==0){
        document.getElementById("checkName").innerHTML="&nbsp;";
        return;
      }
      if(window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
      }else{
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
      }
      xmlhttp.onreadystatechange=function(){
        if(xmlhttp.readyState==4&&xmlhttp.status==200){
          //alert(xmlhttp.responseText);
          document.getElementById("checkName").innerHTML=xmlhttp.responseText;
        }
      }
      xmlhttp.open("GET","${pageContext.request.contextPath}/check.sys?username="+str,true);
      xmlhttp.send();
    }
  </script>
</head>
<body>
<div class="header">
  <div class="am-g">
    <h1>用户注册</h1>
  </div>
  <hr />
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <form method="post" class="am-form" action="${pageContext.request.contextPath}/doregist.sys">
      <div class="login-form-div">
        <input type="text" name="username" id="username" value="" placeholder="请输入登录名" onkeyup="showNameInfo(this.value)" autocomplete="off"><label id="checkName">&nbsp;</label>
      </div>
      <div class="login-form-div">
        <input type="password" name="pwd" id="pwd" value="" placeholder="请输入登录密码">
      </div>
      <div class="login-form-div">
        <input type="email" name="email" id="email" value="" placeholder="请输入邮箱">
      </div>
      <div class="am-cf login-form-div">
        <input type="submit" name="" value="注册" class="am-btn am-btn-primary am-btn-lg  am-btn-block am-fl">
      </div>
    </form>
    <hr>
    <p class="am-text-center">&copy; 2017 Designed by yxq   <a href="mailTo:liyan@itany.com">liyan@itany.com</a> </p>
  </div>
</div>
</body>
</html>
