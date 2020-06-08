package com.gy.action;

import com.gy.entity.Dept;
import com.gy.entity.Emp;
import com.gy.service.SysEmpService;
import com.gy.service.impl.SysEmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class EmpServlet extends HttpServlet {

    public  int myempid=1;
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path  =  req.getServletPath();
        if("/empManage.emp".equals(path)){
            try {
                doSelect(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("/empAdd.emp".equals(path)){
            // 增加人员操作
            try {
                doAdd(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("/empAddKuang.emp".equals(path)){
            // 增加人员操作
            try {
                doKuang(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("/empModify.emp".equals(path)){
            // 人员信息修改操作
            try {
                doModify(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("/empModifyKuang.emp".equals(path)){
            // 人员信息修改操作
            try {
                doNewKuang(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("/toModify.emp".equals(path)){
            //
            try {
                toModify(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("/empDel.emp".equals(path)){
            // 人员信息删除操作
            try {
                doDelete(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }


    //添加操作
    public void  doAdd(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        String username=req.getParameter("username");
        SysEmpService sysempService = new SysEmpServiceImpl();
        //System.out.println("=="+username+"==");
        int age=Integer.parseInt(req.getParameter("age"));
        String[] hobbyshuzu=req.getParameterValues("hobby");
        String hobby="";
        for(int i=0;i<hobbyshuzu.length;i++){
            hobby=hobby+" "+hobbyshuzu[i];
        }
        //System.out.println("=="+hobby+"==");
        String sex=req.getParameter("sex");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
         Date date= sdf.parse(req.getParameter("hiredate"));
         java.sql.Date hiredate = new java.sql.Date(date.getTime());
        String userinfo=req.getParameter("useinfo");
        /*List<Dept> DeptList =sysempService.DeptList();
        req.setAttribute("deptList",DeptList );
        System.out.println("部门名"+DeptList.get(1).getName());*/
        int deptid=Integer.valueOf(req.getParameter("deptid"));
        Dept deptt=sysempService.selectnameByid(deptid);
        HttpSession session = req.getSession();
        String user_pic= (String) session.getAttribute("new");
        session.removeAttribute("new");

        Emp emp=new Emp();
        emp.setUsername(username);
        emp.setAge(age);
        emp.setInfo(userinfo);
        emp.setHobby(hobby);
        emp.setSex(sex);
        emp.setHireDate(hiredate);
        emp.setImgUrl(user_pic);
        emp.setDept(deptt);


        try {
            if(sysempService.add(emp) == true){

                req.getRequestDispatcher("/empManage.emp").forward(req, resp);
            }else{
                req.getRequestDispatcher("/jsp/empAdd.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");

        }
        //  转发

    }

    //显示添加里的下拉框
    public void  doKuang(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");

        SysEmpService sysempService = new SysEmpServiceImpl();
        List<Dept> DeptList =sysempService.DeptList();
        req.setAttribute("deptList",DeptList );
        req.getRequestDispatcher("/jsp/empAdd.jsp").forward(req, resp);
    }

    //显示编辑里的下拉框
    public void  doNewKuang(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");

        SysEmpService sysempService = new SysEmpServiceImpl();
        List<Dept> DeptList =sysempService.DeptList();
        req.setAttribute("newdeptList",DeptList );
        req.getRequestDispatcher("/jsp/empModify.jsp").forward(req, resp);

        //  转发

    }

    //修改操作
    public void doModify(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        String username=req.getParameter("username");
        //System.out.println("=="+username+"==");
        int age=Integer.parseInt(req.getParameter("age"));
        String[] hobbyshuzu=req.getParameterValues("hobby");
        String hobby="";
        for(int i=0;i<hobbyshuzu.length;i++){
            hobby=hobby+" "+hobbyshuzu[i];
        }
        //System.out.println("=="+hobby+"==");
        String sex=req.getParameter("sex");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date= sdf.parse(req.getParameter("hiredate"));
        java.sql.Date hiredate = new java.sql.Date(date.getTime());

        SysEmpService sysempService = new SysEmpServiceImpl();
        String userinfo=req.getParameter("useinfo");


        int deptid=Integer.valueOf(req.getParameter("deptid"));
        Dept deptt=sysempService.selectnameByid(deptid);

        String user_pic=req.getParameter("user_pic");

        req.setAttribute("rempid", myempid);//隐藏域传值

        Emp emp=new Emp();
        emp.setId(myempid);
        emp.setUsername(username);
        emp.setAge(age);
        emp.setInfo(userinfo);
        emp.setHobby(hobby);
        emp.setSex(sex);
        emp.setHireDate(hiredate);
        emp.setImgUrl(user_pic);
        emp.setDept(deptt);


        try {
            if(sysempService.updateEmp(emp) == true){
                req.getRequestDispatcher("/empManage.emp").forward(req, resp);
            }else{
                req.getRequestDispatcher("/empManage.emp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");

        }
        //  转发
    }

    //编辑页面的获取id操作
    public void toModify(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //int empid1=1;
        if(req.getParameter("relempid")!=null)
        {
            myempid=Integer.parseInt(req.getParameter("relempid"));
            //System.out.println("===="+empid1+"===");

        }
        req.getRequestDispatcher("/empModifyKuang.emp").forward(req, resp);
    }

    //删除操作
    public void doDelete(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        int empid=Integer.valueOf(req.getParameter("id"));
        SysEmpService sysempService = new SysEmpServiceImpl();
        try {
            if(sysempService.delEmp(empid) == true){
                req.getRequestDispatcher("/empManage.emp").forward(req, resp);
            }else{
                req.getRequestDispatcher("/empManage.emp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");

        }

    }

    //模糊查询及分页操作
    public  void doSelect(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        SysEmpService sysEmpService=new SysEmpServiceImpl();
        int intPageCount;//总页数
        int intPageSize;//每页行数
        int intPage=0;//当前是第几页
        int intRowCount;
        int page1=1;
        intPageSize=2;
        String sousuo=req.getParameter("select");
        //System.out.println("搜索内容"+sousuo);
            intRowCount=sysEmpService.EmpSelectCount(sousuo);
            intPageCount= (intRowCount+intPageSize-1)/intPageSize;
            req.setAttribute("pagecount",intPageCount);//获取应该有的页数
            if(req.getParameter("page")!=null)
            {
                page1=Integer.valueOf(req.getParameter("page"));
            }else{
                page1=1;
            }
            if(page1>intPageCount){
                page1=intPageCount;
            }
            List<Emp> list_emp=new ArrayList<>();
            //System.out.println("一页多少"+intPageSize+"\npage当前页数"+page1);
            list_emp=sysEmpService.vagueselect(intPageSize,page1,sousuo);//获取显示的表单

            req.setAttribute("empList", list_emp);
        req.getRequestDispatcher("/jsp/empManage.jsp").forward(req, resp);
    }



}




