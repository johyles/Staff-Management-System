package com.gy.dao.impl;

import com.gy.Utill.JdbcUtil;
import com.gy.dao.SysDeptDao;
import com.gy.entity.Dept;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SysDeptDaoImpl implements SysDeptDao {

    //分页查找
    private List<Dept> deptlist= new ArrayList<>();
    ResultSet rs;
    public List<Dept> selectDeptMessage(int nowPage, int pageSize, String name) throws Exception {
        String sql;
        PreparedStatement ps;
        if (name==null){
            sql= "select * from t_dept limit ?,?";
            ps =  JdbcUtil.getPreparedStatement(sql);
            int qq= (nowPage-1)*pageSize;
            ps.setInt(1,qq);
            ps.setInt(2,pageSize);
             rs = ps.executeQuery();}
        else{
            sql = "select * from t_dept where name like '%"+name+"%' limit ?,?";
            ps =  JdbcUtil.getPreparedStatement(sql);
            int qq= (nowPage-1)*pageSize;
            ps.setInt(1,qq);
            ps.setInt(2,pageSize);

            rs = ps.executeQuery();}

        //deptlist.clear();

        while (rs.next()){
            Dept Dept =new Dept();
            Dept.setdeptid(rs.getInt(1));
            Dept.setName(rs.getString(2));
            Dept.setAddr(rs.getString(3));
            //System.out.println(Dept.getDeptid());

            deptlist.add(Dept);
        }

        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, rs);

        return deptlist;
    }

//    public ResultSet selectDeptMessage( int nowPage, int num) throws Exception {
//        List deptlist=null;
//        String sql = "select * from dept limit ?,?";
//
//        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
//        ps.setInt(1,nowPage);
//        ps.setInt(2,num);
//        ResultSet rs;
//        rs=  ps.executeQuery();
//
//        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, rs);
//
//        return rs;
//    }

    //编辑更新
    boolean upFlag=true;
    public boolean updateDept(int id,String name,String addr) throws Exception{

        try {
            String sql = "update t_dept set name=?,addr =? where id=?";

            PreparedStatement ps = JdbcUtil.getPreparedStatement(sql);
            ps.setString(1, name);
            ps.setString(2, addr);
            ps.setInt(3, id);
            ps.executeUpdate();
            JdbcUtil.closeAll(JdbcUtil.getConn(), ps, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return upFlag;
    }

    //删除
    boolean delFlag = true;
    public boolean deleteDept(int id) throws Exception{

        try {
            String sql = "delete from t_dept where id = " + id;
            PreparedStatement  ps = JdbcUtil.getPreparedStatement(sql);
            ps.executeUpdate();
            JdbcUtil.closeAll(JdbcUtil.getConn(), ps, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  delFlag ;
    }

    //添加
    boolean addFlag = true;
    public boolean addDept(String name,String addr) throws Exception{

        try {
            String sql = "insert into t_dept (name,addr) values (?,?)";
            PreparedStatement ps = JdbcUtil.getPreparedStatement(sql);
            ps.setString(1, name);
            ps.setString(2, addr);
            ps.executeUpdate();
            JdbcUtil.closeAll(JdbcUtil.getConn(), ps,null);
        }catch (Exception e){
            addFlag=false;
            e.printStackTrace();
        }

        return addFlag;
    }

    //最大的ID
    public int MaxDeptID() throws Exception{
        String sql = "select count(*) count from t_dept ";
        int count=4;
        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);

        ResultSet rs =  ps.executeQuery();
        if(rs.next()){
            count = rs.getInt(1);
        }
        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, rs);

        return count;
    }

    //模糊查询

    public List<Dept> search(String name, int nowPage, int pageSize) throws Exception{

//        String sql = "select * from deptList where name like'%"+name+"%' limit "+nowPage+","+pageSize;
//        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
//
//        ResultSet rs =  ps.executeQuery();
//        System.out.println("987");
//        while (rs.next()){
//            dept Dept =new dept();
//            Dept.setdeptid(rs.getInt(1));
//            Dept.setName(rs.getString(2));
//            Dept.setAddr(rs.getString(3));
//            deptlist.add(Dept);
//
//        }

        return deptlist;
    }

    //模糊查询总记录数
    public int searchCount(String uname) {

        int count=0;
        try{
            String sql = "select count(*) count from t_dept where name like '%?%'";
            PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
            ps.setString(1,uname);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()){
                count=rs.getInt(1);
            }
            System.out.println("总记录数:"+count);
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }


}
