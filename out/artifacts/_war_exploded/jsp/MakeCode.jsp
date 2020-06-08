<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/23
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="image/jpeg" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="image" scope="page" class="com.gy.Utill.MakeCode"></jsp:useBean>
<%
    String str = image.getCode(0,0,response.getOutputStream());
    session.setAttribute("checkCode",str);
    out.clear();
    out = pageContext.pushBody();
%>
