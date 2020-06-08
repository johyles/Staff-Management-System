<%@ page import="com.gy.dao.impl.SysDeptDaoImpl" %>
<%@ page import="com.gy.service.SysDeptService" %>
<%@ page import="com.gy.service.impl.SysDeptServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: 16090
  Date: 2019/12/23
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title></title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/amazeui/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/amazeui/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/amazeui/css/amazeui.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/amazeui/css/admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
	<script type="text/javascript">
		function openModDlg(id)
		{
			$("#deptModDlg input[name=id]").val(id);
			$("#deptModDlg").modal({});
		}


        function crmDelete(id)
        {
            $('#my-confirm').modal({
                relatedTarget: this,
                onConfirm: function(options) {
                    // 访问删除的action
                    // var url = "delete.dept?id=" + id;
                    window.location.href=("${pageContext.request.contextPath}/delete.dept?id="+id);
                },
                onCancel: function() {
                    alert('算求,不弄了');
                }
            });
        }
		
	</script>
</head>
<body>

<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong>ITANY-MIS</strong> <small>后台管理</small>
    </div>
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>
    <div class="am-collapse am-topbar-collapse" id="topbar-collapse" >
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li class="am-hide-sm-only">
                <a href="javascript:;">
                    <img src="images/default-head.jpg" alt="" class="am-circle am-fr" width="15%" >
                </a>
            </li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="userInfo.jsp"><span class="am-icon-user"></span> 个人资料</a></li>
                    <li><a href="login.jsp"><span class="am-icon-power-off"></span> 退出</a></li>
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
                            <a href="${pageContext.request.contextPath}/deptManage.dept" class="am-cf">
                                <span class="am-icon-check"></span>
                                部门管理
                                <span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/empManage.emp">
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
                    <p>2019年12月23日 10:13:44 </p>
                    <hr>
                    <p>暂无系统公告</p>
                </div>
            </div>
        </div>
    </div>

    <!-- 左侧导航结束 -->

    <%--<%
        SysDeptService sysDeptService = new SysDeptServiceImpl();
        List<dept> deptList = sysDeptService.returndeptList(1,3);
    %>--%>
    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding am-padding-bottom-0">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">部门管理</strong> / <small>Department Manage</small></div>
            </div>

            <hr>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-6">
                    <div class="am-btn-toolbar">
                        <div class="am-btn-group am-btn-group-xs">
                            <button data-am-modal="{target:'#deptAddDlg'}" type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span>添加部门</button>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-5">
                    <div class="am-input-group am-input-group-sm">
                        <form  action="${pageContext.request.contextPath}/search.dept" method="post">
                            <input type="text" class="am-form-field" name="searchName" id="searchName" >
                            <span class="am-input-group-btn">
                            <%--<button class="am-btn am-btn-default" type="button">搜索</button>--%>
                                <input type="submit" name="" value="搜索" class="am-btn am-btn-default">
                            </span>
                        </form>
                    </div>
                </div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12">
                    <table class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="table-check">
                                <input type="checkbox" />
                            </th>
                            <th class="table-id">ID</th>
                            <th class="table-title">部门名称</th>
                            <th class="table-type">部门地址</th>
                            <th class="table-set">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${deptList}" var="dept1">
                            <tr>
                                <td><input type="checkbox" /></td>
                                <td>${dept1.deptid}</td>
                                <td><a href="#">${dept1.name}</a></td>
                                <td>${dept1.addr}</td>
                                <td>
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <button onclick="openModDlg()" class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                                <span class="am-icon-pencil-square-o"></span> 编辑
                                            </button>
                                            <button onclick="crmDelete(${dept1.deptid})" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
                                                <span  class="am-icon-trash-o"></span> 删除
                                            </button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    <div class="am-cf">
                        <div class="am-fr">
                            <ul class="am-pagination">
                                <li class="am-disabled"><a href="${pageContext.request.contextPath}/deptManage.dept?page=1">«</a></li>
                                <c:forEach begin="1" end="${pagecount}" var="nowpage">
                                    <li ><a href="${pageContext.request.contextPath}/deptManage.dept?page=${nowpage}">${nowpage}</a></li>
                                </c:forEach>
                                <li ><a href="${pageContext.request.contextPath}/deptManage.dept?page=${pagecount}">»</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <footer>
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

<!--     弹出的部门添加---模态框 -->
<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="deptAddDlg">
    <div class="am-modal-dialog up-frame-parent up-frame-radius">
        <div class="am-modal-hd up-frame-header">
            <label>部门添加</label>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd  up-frame-body">

            <form method="post" class="am-form" action="${pageContext.request.contextPath}/add.dept">
                <div class="login-form-div">
                    <input type="text" name="addname" id="addname" value="" placeholder="请输入部门名称">
                </div>
                <div class="login-form-div">
                    <input type="text" name="addaddr" id="addaddr" value="" placeholder="请输入部门地址">
                </div>
                <div class="am-cf login-form-div">
                    <input type="submit" name="" value="添加" class="am-btn am-btn-primary am-btn-lg  am-btn-block am-fl">
                </div>
            </form>

        </div>
    </div>
</div>
<!--     弹出的部门添加---模态框 -->


<!--     弹出的部门修改---模态框 -->
<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="deptModDlg">
    <div class="am-modal-dialog up-frame-parent up-frame-radius">
        <div class="am-modal-hd up-frame-header">
            <label>部门修改</label>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd  up-frame-body">

            <form method="post" class="am-form" action="${pageContext.request.contextPath}/update.dept?page=<%=request.getParameter("page")%>">
                <div class="login-form-div">
                    <input type="text" name="deptid" id="deptid" value="" placeholder="请输入部门ID">
                </div>
                <div class="login-form-div">
                    <input type="text" name="updatename" id="updatename" value="" placeholder="请输入部门名称">
                </div>
                <div class="login-form-div">
                    <input type="text" name="updateaddr" id="updateaddr" value="" placeholder="请输入部门地址">
                </div>
                <div class="am-cf login-form-div">
                    <input type="submit" name="" value="修改" class="am-btn am-btn-primary am-btn-lg  am-btn-block am-fl">
                </div>
            </form>

        </div>
    </div>
</div>
<!--     弹出的部门修改---模态框 -->


<!--     删除模态框 -->
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">用户删除</div>
        <div class="am-modal-bd">
            你,确定要删除这条记录吗?
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
<!--     删除模态框 -->

<script src="js/jquery-3.2.1.js"></script>
<script src="amazeui/js/amazeui.js"></script>
<script src="amazeui/js/app.js"></script>
</body>
</html>