package com.gy.action;

import com.gy.entity.Dept;
import com.gy.service.SysDeptService;
import com.gy.service.impl.SysDeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DeptServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String path = req.getServletPath();
        if("/deptManage.dept".equals(path)){
            try {
                doshow(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/add.dept".equals(path)){
            try {
                doadd(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/delete.dept".equals(path)){
            try {
                dodelete(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/update.dept".equals(path)){
            try {
                doupadte(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/search.dept".equals(path)){
            try {
                doshow(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/userInfo.dept".equals(path)){
            resp.sendRedirect(req.getContextPath()+"/jsp/userInfo.jsp");
        }else if("/empManage.dept".equals(path)){
            //resp.sendRedirect(req.getContextPath()+"/jsp/empManage.jsp");
        }
    }

    //展示数据
    private  List<Dept> deptlist = new ArrayList<>();
    private int pageCount;
    SysDeptService sysDeptService = new SysDeptServiceImpl();
    private void doshow(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");

        deptlist.clear();
        String page = req.getParameter("page");
        String name = req.getParameter("searchName");
        int inpage=0;
        if (page==null){
            inpage=1;
        }
        else{
            inpage = Integer.parseInt(page);
        }
        if (name==null){
            pageCount =(sysDeptService.MaxDeptID()+2)/3;
        }else{
            pageCount =(sysDeptService.searchCount(name)+2)/3;
        }
        SysDeptService sysDeptService = new SysDeptServiceImpl();
        try {
            deptlist = sysDeptService.returndeptList(inpage, 3,name);
            //System.out.println(deptlist.get(1).getDeptid());

            req.setAttribute("pagecount",pageCount);
            req.setAttribute("deptList", deptlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/jsp/deptManage.jsp").forward(req, resp);
        //resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
    }

    //添加数据
    private void doadd(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");

        String addname = req.getParameter("addname");
        String addaddr = req.getParameter("addaddr");

        SysDeptService sysDeptService = new SysDeptServiceImpl();
        int count = sysDeptService.MaxDeptID();
        int intpage=((count+2)/3);

        sysDeptService.addDept(addname,addaddr);

        req.getRequestDispatcher("/deptManage.dept?page="+intpage).forward(req, resp);

    }

    //删除
    private void dodelete(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");

        String id = req.getParameter("id");
        int deptid = Integer.parseInt(id);

        SysDeptService sysDeptService = new SysDeptServiceImpl();

        sysDeptService.deleteDept(deptid);

        req.getRequestDispatcher("/deptManage.dept").forward(req, resp);

    }

    //更新编辑
    private void doupadte (HttpServletRequest req, HttpServletResponse resp) throws Exception{
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");

        String page = req.getParameter("page");
        String id = req.getParameter("deptid");
        String name = req.getParameter("updatename");
        String addr = req.getParameter("updateaddr");

        int intpage = Integer.parseInt(page);
        int deptid = Integer.parseInt(id);

        SysDeptService sysDeptService = new SysDeptServiceImpl();

        sysDeptService.updateDept(deptid,name,addr);

        req.getRequestDispatcher("/deptManage.dept?page="+intpage).forward(req, resp);

    }

    //搜查
    private void dosearch (HttpServletRequest req, HttpServletResponse resp) throws Exception{

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        System.out.println("111111111111111111");

        SysDeptService sysDeptService = new SysDeptServiceImpl();
        String name = req.getParameter("searchName");
        String page = req.getParameter("page");
        int count = sysDeptService.searchCount(name);
        int pageCount1 = (count+2)/3;
        deptlist.clear();
        int inpage=1;
        if (page==null){
            inpage=1;
        }
        else{
            inpage = Integer.parseInt(page);
        }
        deptlist.clear();

        deptlist = sysDeptService.search(name,inpage,3);

        req.setAttribute("deptList", deptlist);
        req.setAttribute("pageCount1", pageCount1);
        req.getRequestDispatcher("/jsp/deptManage.jsp").forward(req, resp);
    }
}