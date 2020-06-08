package com.gy.dao.impl;

import com.gy.dao.SysEmpDao;
import com.gy.entity.Dept;
import com.gy.entity.Emp;
import com.gy.Utill.JdbcUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SysEmpDaoImpl implements SysEmpDao {

    //分页查询信息
    @Override
    public List selectEmp(int pagesize ,int nowpage) throws Exception {
        List<Emp> emp_list=new ArrayList<>();
        String sql = "select * from t_emp limit ?,?";
        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
        int qq=(nowpage-1)*pagesize;
        ps.setInt(1, qq);
        ps.setInt(2,pagesize);
        ResultSet rs =  ps.executeQuery();

        while (rs.next()){
            Emp emp=new Emp();
            emp.setId(rs.getInt(1));
            emp.setUsername(rs.getString(2));
            emp.setAge(rs.getInt(3));
            emp.setInfo(rs.getString(4));
            emp.setHobby(rs.getString(5));
            emp.setSex(rs.getString(6));
            emp.setHireDate(rs.getDate(7));
            emp.setImgUrl(rs.getString(8));
            emp.setDept(selectnameByid(rs.getInt(9)));
            /*String sql1="select name from t_dept where id=?";
            PreparedStatement ps1 =  JdbcUtil.getPreparedStatement(sql1);
            ps1.setInt(1, rs.getInt(9));
            ResultSet rs1 =  ps1.executeQuery();

            String dept_name="";
            while(rs1.next()){
                dept_name=rs1.getString(1);
            }
            emp.setDeptname(dept_name);
            System.out.println("获得的部门名字"+dept_name);
            JdbcUtil.closeAll(JdbcUtil.getConn(), ps1, rs1);*/

            emp_list.add(emp);
        }

        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, rs);
        return emp_list;
    }

    //查总记录
    @Override
    public int EmpCount() throws Exception {
        String sql="select count(*) from t_emp ";
        PreparedStatement ps = JdbcUtil.getPreparedStatement(sql);
        ResultSet rs=ps.executeQuery();
        int count=0;
        if(rs.next()){
            count=rs.getInt(1);
        }
        JdbcUtil.closeAll(JdbcUtil.getConn(),null,rs);
        return count;
    }


    //编辑更新功能
    boolean updateFlag = false;
    public boolean updateEmpByid(Emp emper) throws Exception {
        try{
            String sql = "update t_emp set username=?, age=?, info=?, hobby=?, sex=?, hireDate=?, imgUrl=?, dept_id=? where id=?";

            PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);

            ps.setString(1, emper.getUsername());
            ps.setInt(2, emper.getAge());
            ps.setString(3, emper.getInfo());
            ps.setString(4, emper.getHobby());
            ps.setString(5, emper.getSex());
            Date sqlDate= new Date(new java.util.Date().getTime());
            sqlDate= (Date) emper.getHireDate();
            ps.setDate(6, sqlDate);
            ps.setString(7, emper.getImgUrl());
            ps.setInt(8,emper.getDept().getDeptid());
            ps.setInt(9, emper.getId());

            ps.executeUpdate();//更新
            updateFlag = true;
            JdbcUtil.closeAll(JdbcUtil.getConn(), ps,null);
        }catch(Exception e){
            updateFlag =false;
            e.printStackTrace();
        }

        return updateFlag;
    }

    //删除功能
    boolean delFlag = true;
    public boolean deleteEmpByid(int id) throws Exception {
            try{
                String sql = "delete from t_emp where id=?";
                PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
                ps.setInt(1,id);
                ps.executeUpdate();
                delFlag=true;
                JdbcUtil.closeAll(JdbcUtil.getConn(), ps,null);
            }catch(Exception e){
                delFlag = false;
                e.printStackTrace();
            }
            return delFlag;
    }

    //添加插入
    boolean addFlag = false;
    public boolean insertEmpByid(Emp emper) throws Exception {

        try{
            //String sql1 = "select max(id) from t_emp ";
            //ResultSet rs=JdbcUtil.getResuluset(sql1);
            //int max_id=rs.getInt(1);
            String sql = "insert into t_emp(username,age,info,hobby,sex,hireDate,imgUrl,dept_id) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
            //ps.setInt(1,max_id);
            ps.setString(1, emper.getUsername());
            ps.setInt(2, emper.getAge());
            ps.setString(3, emper.getInfo());
            ps.setString(4, emper.getHobby());
            ps.setString(5, emper.getSex());
            Date sqlDate= new Date(new java.util.Date().getTime());
            sqlDate= (Date) emper.getHireDate();
            ps.setDate(6, sqlDate);
            ps.setString(7, emper.getImgUrl());

            ps.setInt(8,emper.getDept().getDeptid());

            ps.executeUpdate();
            addFlag = true;
            JdbcUtil.closeAll(JdbcUtil.getConn(), ps,null);
        }catch(Exception e){
            addFlag = false;
            e.printStackTrace();
        }

        return addFlag;
    }

    //从部门id获取部门mz
    public Dept selectnameByid(int dept_id) throws Exception {
        String sql="select * from t_dept where id="+dept_id;
        ResultSet rs1=JdbcUtil.getResuluset(sql);
        Dept dept=new Dept();
        while(rs1.next()){
            dept.setdeptid(rs1.getInt(1));
            dept.setName(rs1.getString(2));
            dept.setAddr(rs1.getString(3));
        }

        JdbcUtil.closeAll(null,null,rs1);
        return dept;
    }

    //从部门mz获取部门id
    /*public int selectidByname(String name) throws Exception {
        String sql="select id from t_dept where name="+name;
        ResultSet rs2=JdbcUtil.getResuluset(sql);
        int dept_id=1;
        while(rs2.next()){
            dept_id=rs2.getInt(1);
        }
        JdbcUtil.closeAll(null,null,rs2);
        return dept_id;
    }*/

    //模糊查询
    @Override
    public List vagueSelect(int pagesize, int nowpage,String ss) throws Exception {
        List<Emp> emp_xlist=new ArrayList<>();
        int qq=(nowpage-1)*pagesize;
        String sql = "select * from t_emp where  username like '%"+ss+"%' limit "+qq+","+pagesize+"";
        ResultSet rs= JdbcUtil.getResuluset(sql);
        Emp emp=null;
        while (rs.next()){
            emp=new Emp();
            emp.setId(rs.getInt(1));
            emp.setUsername(rs.getString(2));
            emp.setAge(rs.getInt(3));
            emp.setInfo(rs.getString(4));
            emp.setHobby(rs.getString(5));
            emp.setSex(rs.getString(6));
            emp.setHireDate(rs.getDate(7));
            emp.setImgUrl(rs.getString(8));

            emp.setDept(selectnameByid(rs.getInt(9)));


            emp_xlist.add(emp);
        }
        JdbcUtil.closeAll(JdbcUtil.getConn(), null, rs);
        return emp_xlist;

    }

    //模糊查询总记录
    @Override
    public int EmpSelectCount(String ss) throws Exception {
        String sql="select count(*) from t_emp where username like '%"+ss+"%'";
        ResultSet rs=JdbcUtil.getResuluset(sql);
        int count=0;
        if(rs.next()){
            count=rs.getInt(1);
        }
        JdbcUtil.closeAll(JdbcUtil.getConn(),null,rs);
        return count;
    }

    //查部门总记录
    @Override
    public List DeptList() throws Exception {
        List<Dept> dept_list=new ArrayList<>();
        String sql="select * from t_dept ";
        PreparedStatement ps = JdbcUtil.getPreparedStatement(sql);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            Dept dept=new Dept();
            dept.setdeptid(rs.getInt(1));
            dept.setName(rs.getString(2));
            dept.setAddr(rs.getString(3));
            dept_list.add(dept);
        }
        JdbcUtil.closeAll(JdbcUtil.getConn(),null,rs);
        return dept_list;
    }

}
