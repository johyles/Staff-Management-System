package com.gy.action;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String currentURL = request.getRequestURI();
        String path = request.getContextPath();
        String nowPath = currentURL.substring(path.length());
        if(!("/jsp/login.jsp".equals(nowPath))){
            HttpSession session = request.getSession();
            if ("/jsp/deptManage.jsp".equals(nowPath) || "/jsp/empAdd.jsp".equals(nowPath) || "/jsp/empManage.jsp".equals(nowPath) || "/jsp/empModify.jsp".equals(nowPath)) {
                if (session == null || session.getAttribute("username") == null) {
                    response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
                } else {
                    chain.doFilter(request, response);
                }
            }else
            {
                chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
