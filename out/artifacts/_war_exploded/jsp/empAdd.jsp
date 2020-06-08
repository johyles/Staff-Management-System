
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/23
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>首页</title>
    <meta name="keywords" content="user">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/amazeui/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/amazeui/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/amazeui/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/amazeui/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/amazeui/plugin/amazeui.cropper.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/amazeui/plugin/custom_up_img.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css" />
</head>

<body>
<%
    String name = (String)session.getAttribute("username");
    if(name!=null){
        String userImage = (String) session.getAttribute("new");
        //System.out.println(userImage);
%>
<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong>ITANY-MIS</strong> <small>后台管理</small>
    </div>
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>
    <div class="am-collapse am-topbar-collapse" id="topbar-collapse" >
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li class="am-hide-sm-only">
                <a href="javascript:;">
                    <img src="../images/default-head.jpg" alt="" class="am-circle am-fr" width="15%" >
                </a>
            </li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="userInfo.html"><span class="am-icon-user"></span> 个人资料</a></li>
                    <li><a href="login.html"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>
<div class="am-cf admin-main">
    <!-- 左侧导航开始 start -->
    <div class="admin-sidebar am-offcanvas overflow-hidden" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li>
                    <a href="index.jsp">
                        <span class="am-icon-home"></span>
                        首页
                    </a>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}">
                        <span class="am-icon-file"></span>
                        企业管理
                        <span class="am-icon-angle-right am-fr am-margin-right"></span>
                    </a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                        <li>
                            <a href="deptManage.dept" class="am-cf">
                                <span class="am-icon-check"></span>
                                部门管理
                                <span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
                            </a>
                        </li>
                        <li>
                            <a href="empManage.emp">
                                <span class="am-icon-puzzle-piece"></span>
                                员工管理
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#userInfo'}">
                        <span class="am-icon-cog"></span>
                        设置
                        <span class="am-icon-angle-right am-fr am-margin-right"></span>
                    </a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="userInfo">
                        <li>
                            <a href="${pageContext.request.contextPath}/userinfo.sys" class="am-cf">
                                <span class="am-icon-check"></span>
                                个人信息
                            </a>
                        </li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/quit.sys"><span class="am-icon-sign-out"></span> 注销</a></li>
            </ul>
            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-bookmark"></span> 公告</p>
                    <p>2017年5月30日 10:13:44 </p>
                    <hr>
                    <p>暂无系统公告</p>
                </div>
            </div>
        </div>
    </div>

    <!-- 左侧导航结束 -->


    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding am-padding-bottom-0">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">员工信息添加</strong> / <small>Employee Information Add</small></div>
            </div>
            <hr/>


            <div class="am-g">


                <div class="am-u-sm-12 am-u-md-6 am-u-md-push-6">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd">
                            <div class="am-g">
                                <div class="am-u-md-12">
                                    <img class="am-img-circle am-img-thumbnail" id="headImg" src="${pageContext.request.contextPath}/download.sys?fileName=<%=userImage%>" width="60%" alt="" />
                                    <form class="am-form">
                                        <div class="am-form-group">
                                            <input type="file" id="user_pic" class="display-none">
                                            <!-- <p class="am-form-help">请选择要上传的文件...</p> -->
                                            <a id="showUpDlgBtn" class="am-btn am-btn-primary am-btn-block">头像修改</a>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>



                <div class="am-u-sm-12 am-u-md-6 am-u-md-pull-6">
                    <form method="post" action="${pageContext.request.contextPath}/empAdd.emp" class="am-form am-form-horizontal">
                        <div class="am-form-group">
                            <div class="am-u-sm-11">
                                <input type="text" id="username" name="username" placeholder="姓名 / Name">
                                <small>输入你的名字，让我们记住你。</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <div class="am-u-sm-11">
                                <select name="age">
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                </select>
                                <small>年龄</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <div class="am-u-sm-11">
                                <span>爱好</span>
                                <input id="code" type="checkbox" name="hobby" value="写代码">
                                <label for="code">写代码</label>
                                <input id="bug" type="checkbox" name="hobby" value="写bug">
                                <label for="bug">写bug</label>
                                <input id="girl" type="checkbox" name="hobby" value="撩妹">
                                <label for="girl">撩妹</label>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <div class="am-u-sm-11">
                                <span>性别</span>
                                <input id="m" type="radio" name="sex" value="男">
                                <label for="m">男</label>
                                <input id="f" type="radio" name="sex" value="女">
                                <label for="f">女</label>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <div class="am-u-sm-11">
                                <input type="date" id="hiredate" name="hiredate" placeholder="入职日期">
                                <small>入职日期</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <div class="am-u-sm-11">

                                <select name="deptid">
                                    <c:forEach var="dept3" items="${deptList}">
                                    <option value="${dept3.deptid}">${dept3.name}</option>
                                    </c:forEach>
                                </select>

                                <small>所在部门</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <div class="am-u-sm-11">
                                <textarea class="" rows="5" id="useinfo" name="useinfo" placeholder="输入个人简介"></textarea>
                                <small>250字以内写出你的一生...</small>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <div class="am-u-sm-11 am-u-sm-push-3">
                                <a href="#">
                                <button type="submit" class="am-btn am-btn-primary">保存</button>
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <footer class="admin-content-footer">
            <hr>
            <p class="am-padding-left">&copy; 2017 Designed by yxq</p>
        </footer>
    </div>
    <!-- content end -->
</div>
<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
<footer>
    <hr>
    <p class="am-padding-left">&copy; 2017 Designed by yxq</p>
</footer>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
<script src="${pageContext.request.contextPath}/amazeui/js/amazeui.js"></script>
<script src="${pageContext.request.contextPath}/amazeui/js/app.js"></script>
<script src="${pageContext.request.contextPath}/amazeui/plugin/cropper.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/amazeui/plugin/custom_up_img.js" charset="utf-8"></script>




<!--图片上传框-->
<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="updateHeadDlg">
    <div class="am-modal-dialog up-frame-parent up-frame-radius">
        <div class="am-modal-hd up-frame-header">
            <label>修改头像</label>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd  up-frame-body">
            <div class="am-g am-fl">
                <div class="am-form-group am-form-file">
                    <div class="am-fl">
                        <button type="button" class="am-btn am-btn-default am-btn-sm">
                            <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
                    </div>
                    <input type="file" id="inputImage">
                </div>
            </div>
            <div class="am-g am-fl" >
                <div class="up-pre-before up-frame-radius">
                    <img alt="" src="" id="image" >
                </div>
                <div class="up-pre-after up-frame-radius">
                </div>
            </div>
            <div class="am-g am-fl">
                <div class="up-control-btns">
                    <span class="am-icon-rotate-left"  onclick="rotateimgleft()"></span>
                    <span class="am-icon-rotate-right" onclick="rotateimgright()"></span>
                    <span class="am-icon-check" id="up-btn-ok" url="${pageContext.request.contextPath}/upload1.sys"></span>
                </div>
            </div>

        </div>
    </div>
</div>


<%
    }else{
        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
    }
%>


</body>

</html>
