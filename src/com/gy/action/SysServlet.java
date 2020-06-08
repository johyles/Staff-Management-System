package com.gy.action;

import com.google.gson.Gson;
import com.gy.Utill.MD5;
import com.gy.Utill.MakeCode;
import com.gy.entity.SysUser;
import com.gy.service.SysUserService;
import com.gy.service.impl.SysUserServiceimpl;

import javax.management.openmbean.TabularData;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class SysServlet  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if("/login.sys".equals(path))
        {
            doLogin(req,resp);
        }else if("/toregist.sys".equals(path)){
            req.getRequestDispatcher("/jsp/regist.jsp").forward(req,resp);
        }else if("/check.sys".equals(path)){
            check(req,resp);
        }else if("/doregist.sys".equals(path))
        {
            try {
                regist(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/find.sys".equals(path))
        {
            try {
                reLogin(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/tofind.sys".equals(path))
        {
            req.getRequestDispatcher("/jsp/findPwd.jsp").forward(req,resp);
        }else if("/quit.sys".equals(path))
        {
            HttpSession session = req.getSession();
            session.removeAttribute("username");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
        }else if("/userInfo.sys".equals(path))
        {
            try {
                reuser(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/upload.sys".equals(path))
        {
            try {
                upload(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/dept.sys".equals(path))
        {
            req.getRequestDispatcher("/jsp/deptManage.jsp").forward(req,resp);
        }else if("/deptEmp.sys".equals(path))
        {
            req.getRequestDispatcher("/jsp/empManage.jsp").forward(req,resp);
        }else if("/userinfo.sys".equals(path))
        {
            req.getRequestDispatcher("/jsp/userInfo.jsp").forward(req,resp);
        }else if("/download.sys".equals(path)){
            try {
                download(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/upload1.sys".equals(path))
        {
            try {
                upload1(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/MakeCode.sys".equals(path)) {
            MakeCode mc = new MakeCode();
            String str = mc.getCode(0, 0, resp.getOutputStream());
            req.getSession().setAttribute("checkCode", str);
        }
    }

    private void upload1(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String dataURL = req.getParameter("image");
        System.out.println(dataURL+"---------------");

        // 将dataURL开头的非base64字符删除
        String base64 = dataURL.substring(dataURL.indexOf(",") + 1);

        // 上传路径
        String uploadPath = getServletContext().getRealPath("/WEB-INF/upload/");

        // 上传文件名字
        String uploadFilename = UUID.randomUUID().toString()+".jpg";
        HttpSession session = req.getSession();
        session.setAttribute("new",uploadFilename);
        System.out.println(uploadFilename);
        System.out.println("111");

        File file = new File(uploadPath,uploadFilename);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();//创建父级文件路径
        }

        // 创建输出流
        FileOutputStream write = new FileOutputStream(file);
        // 将图片base64文本转为byte数组
        byte[] decoderBytes = Base64.getDecoder().decode(base64);
        for(int i=0;i<decoderBytes.length;++i) {
            if (decoderBytes[i] < 0) {//调整异常数据
                decoderBytes[i] += 256;
            }
        }

        write.write(decoderBytes);
        write.close();

        // 如果想保存图片路径至数据库表中，需要做sql操作


        // 构建返回值json格式
        Map map = new HashMap<String, Object>();
        map.put("result", "ok");
        map.put("fileName",uploadFilename);
        System.out.println(map);
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(map));
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    public void download(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {

        // 获取图片路径
        String fileName = request.getParameter("fileName");
        System.out.println(fileName);

        // 只有在有图片的基础上才能下载
        if(fileName!=null && !"null".equals(fileName) && !"".equals(fileName))
        {
            // 上传路径
            String uploadPath = getServletContext().getRealPath("/WEB-INF/upload/");
            // 获取服务器中要下载的资源的路径
            OutputStream out = null;
            FileInputStream in =  null;
            try
            {
                in = new FileInputStream(new File(uploadPath,fileName));
                out = response.getOutputStream();
                response.setContentType("image/jpeg");
                byte[] b = new byte[2048];
                int len = 0;
                while ((len = in.read(b)) != -1)
                {
                    out.write(b, 0, len);
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    if (out != null)
                    {
                        out.close();
                    }

                    out = null;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                try
                {
                    if (in != null)
                    {
                        in.close();
                    }

                    in = null;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void upload(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String dataURL = req.getParameter("image");
        System.out.println(dataURL+"---------------");

        // 将dataURL开头的非base64字符删除
        String base64 = dataURL.substring(dataURL.indexOf(",") + 1);

        // 上传路径
        String uploadPath = getServletContext().getRealPath("/WEB-INF/upload/");

        // 上传文件名字
        String uploadFilename = UUID.randomUUID().toString()+".jpg";
        HttpSession session = req.getSession();
        String username = (String)session.getAttribute("username");
        SysUser sysUser = new SysUser();
        sysUser.setName(username);
        sysUser.setUrl(uploadFilename);
        SysUserService sysUserService = new SysUserServiceimpl();
        //System.out.println(sysUser.getUrl());
        sysUserService.insertImage(sysUser);
        session.setAttribute("user-Image",sysUser.getUrl());

        File file = new File(uploadPath,uploadFilename);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();//创建父级文件路径
        }

        // 创建输出流
        FileOutputStream write = new FileOutputStream(file);
        // 将图片base64文本转为byte数组
        byte[] decoderBytes = Base64.getDecoder().decode(base64);
        for(int i=0;i<decoderBytes.length;++i) {
            if (decoderBytes[i] < 0) {//调整异常数据
                decoderBytes[i] += 256;
            }
        }

        write.write(decoderBytes);
        write.close();

        // 如果想保存图片路径至数据库表中，需要做sql操作


        // 构建返回值json格式
        Map map = new HashMap<String, Object>();
        map.put("result", "ok");
        map.put("fileName",uploadFilename);
        System.out.println(map);
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(map));
    }


    private void reuser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("username");
        String username = req.getParameter("user-name");
        String useremail = req.getParameter("user-email");
        String userphone = req.getParameter("user-phone");
        String userQQ = req.getParameter("user-QQ");
        String userweibo = req.getParameter("user-weibo");
        String userintro = req.getParameter("user-intro");
        System.out.println(userphone);
        SysUser sysUser = new SysUser();
        sysUser.setName(name);
        sysUser.setEmail(useremail);
        sysUser.setPhone(userphone);
        sysUser.setQq(userQQ);
        sysUser.setUsername(username);
        sysUser.setWeibo(userweibo);
        sysUser.setIntro(userintro);
        session.setAttribute("user-name",sysUser.getUsername());
        session.setAttribute("user-email",sysUser.getEmail());
        session.setAttribute("user-phone",sysUser.getPhone());
        session.setAttribute("user-QQ",sysUser.getQq());
        session.setAttribute("user-weibo",sysUser.getWeibo());
        session.setAttribute("user-intro",sysUser.getIntro());
        SysUserService sysUserService = new SysUserServiceimpl();
        sysUserService.insertInfo(sysUser);
        System.out.println("111");
        out.print("<script>");
        out.print("alert('修改成功！');");
        out.print("document.location='jsp/userInfo.jsp';");
        out.print("</script>");
    }

    private void reLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        String email = req.getParameter("email");
        MD5 md5 = new MD5();
        pwd = md5.md5Change(pwd);
        SysUser sysUser = new SysUser();
        sysUser.setName(name);
        sysUser.setPwd(pwd);
        sysUser.setEmail(email);
        SysUserService sysUserService = new SysUserServiceimpl();
        SysUser sysUser1 = sysUserService.modifyPwd(sysUser);
        if(sysUser1!=null){
            System.out.println("11");
            out.print("<script>");
            out.print("alert('修改成功！');");
            out.print("document.location='jsp/login.jsp';");
            out.print("</script>");
            //resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
            //sysUserService.insertSysUser(sysUser);
        }else{
            System.out.println("1");
            out.print("<script>");
            out.print("alert('用户名或email错误！');");
            out.print("document.location='jsp/findPwd.jsp';");
            out.print("</script>");
            //req.getRequestDispatcher("/jsp/findPwd.jsp").forward(req, resp);
        }
    }

    private void regist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //HttpSession session = req.getSession();
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        String email = req.getParameter("email");
        MD5 md5 = new MD5();
        pwd = md5.md5Change(pwd);
        SysUser sysUser = new SysUser();
        sysUser.setName(name);
        sysUser.setPwd(pwd);
        sysUser.setEmail(email);
        SysUserService sysUserService = new SysUserServiceimpl();
        SysUser sysUser1 = sysUserService.checkName(sysUser);
        if(sysUser1==null){
            out.print("<script>");
            out.print("alert('注册成功！');");
            out.print("document.location='jsp/login.jsp';");
            out.print("</script>");
            //resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
            sysUserService.insertSysUser(sysUser);
        }else{
            out.print("<script>");
            out.print("alert('注册失败！');");
            out.print("document.location='jsp/regist.jsp';");
            out.print("</script>");
            //req.getRequestDispatcher("/jsp/regist.jsp").forward(req, resp);
        }
        //cc.closeConn();
        out.flush();
        out.close();
    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("username");
        if(name.length()<6){
            out.print("用户名不能小于6位");
        }else{
            SysUser sysUser = new SysUser();
            sysUser.setName(name);
            SysUserService sysUserService = new SysUserServiceimpl();
            SysUser sysUser1 = null;
            try {
                sysUser1 = sysUserService.checkName(sysUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(sysUser1 == null)
            {
                out.print("可以注册");
            }else
            {
                out.print(sysUser1.getName()+"用户名已存在");
            }
        }

        out.flush();
        out.close();
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        if (req.getParameter("checkbox")!=null) {
            //记住密码:生成新的cookie用来保存账号密码
            //System.out.println("qqq");
            Cookie username1 = new Cookie("username",req.getParameter("username"));
            username1.setMaxAge(1296000);//设置cookie最长保存时间15天
            Cookie password1 = new Cookie("password",req.getParameter("pwd"));
            password1.setMaxAge(1296000);
            //覆盖旧的cookie
            resp.addCookie(username1);
            resp.addCookie(password1);
        } else
        {
            //System.out.println("www");
            Cookie [] cookies = req.getCookies();
            Cookie username1=null;
            Cookie password1=null;
            //寻找是否已经存在cookie
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("username")) {
                    username1=cookie;
                } else
                if (cookie.getName().equals("pwd")) {
                    password1=cookie;
                }
            }
            //若cookie存在则通过设置cookie保存时间为0的方法来删除cookie
            if (username1!=null) {
                username1.setMaxAge(0);
                resp.addCookie(username1);
            }
            if (password1!=null) {
                password1.setMaxAge(0);
                resp.addCookie(password1);
            }
        }

        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        String Code = req.getParameter("checkCode");
        String randCode = (String)session.getAttribute("checkCode");
        MD5 md5 = new MD5();
        pwd = md5.md5Change(pwd);
        SysUser sysUser = new SysUser();
        sysUser.setName(username);
        sysUser.setPwd(pwd);
        SysUserService sysUserService = new SysUserServiceimpl();
        if(randCode.equals(Code)) {
            try {
                sysUser = sysUserService.login(sysUser);
                //System.out.println(sysUser.getName());
                if (sysUser == null) {
                    out.print("<script>");
                    out.print("alert('用户名不存在或密码错误');");
                    out.print("document.location='jsp/login.jsp';");
                    out.print("</script>");
                    //System.out.println(sysUser.getName());
                    System.out.println("1");
                    //resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
                } else {
                    //重定向
                    //System.out.println("11");
                    session.setAttribute("username", sysUser.getName());
                    session.setAttribute("user-name",sysUser.getUsername());
                    session.setAttribute("user-email",sysUser.getEmail());
                    session.setAttribute("user-phone",sysUser.getPhone());
                    session.setAttribute("user-QQ",sysUser.getQq());
                    session.setAttribute("user-weibo",sysUser.getWeibo());
                    session.setAttribute("user-intro",sysUser.getIntro());
                    session.setAttribute("user-Image",sysUser.getUrl());
                    //System.out.println(sysUser.getUrl());
                    req.getRequestDispatcher("/jsp/userInfo.jsp").forward(req, resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //转发
                resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            }
        }else
        {
            //System.out.println("111");
            out.print("<script>");
            out.print("alert('验证码错误');");
            out.print("document.location='jsp/login.jsp';");
            out.print("</script>");
            //resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
        }

    }
}
