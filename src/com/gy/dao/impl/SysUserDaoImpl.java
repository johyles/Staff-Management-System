package com.gy.dao.impl;

import com.gy.Utill.JdbcUtil;
import com.gy.dao.SysUserDao;
import com.gy.entity.SysUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SysUserDaoImpl implements SysUserDao {

    @Override
    public SysUser selectUserByName(SysUser user) throws Exception {
        String sql = "select * from userinfo where name=? and pwd=?";

        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getPwd());
        ResultSet rs =  ps.executeQuery();
        SysUser sysUser =null;
        while (rs.next()){
            sysUser = new SysUser();
            sysUser.setName(rs.getString("name"));
            sysUser.setPwd(rs.getString("pwd"));
            sysUser.setEmail(rs.getString("email"));
            sysUser.setUsername(rs.getString("username"));
            sysUser.setPhone(rs.getString("phone"));
            sysUser.setQq(rs.getString("qq"));
            sysUser.setWeibo(rs.getString("weibo"));
            sysUser.setIntro(rs.getString("intro"));
            sysUser.setUrl(rs.getString("url"));
        }

        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, rs);

        return sysUser;
    }

    @Override
    public SysUser selectUserByName1(SysUser user) throws Exception {
        String sql = "select * from userinfo where name=?";

        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
        ps.setString(1, user.getName());
        ResultSet rs =  ps.executeQuery();
        SysUser sysUser =null;
        while (rs.next()){
            sysUser = new SysUser();
            sysUser.setName(rs.getString("name"));
        }

        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, rs);

        return sysUser;
    }

    @Override
    public void insertUserInfo(SysUser user) throws Exception {
        int i=1,nextId;
        String sql = "select max(id) from userinfo";
        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
        ResultSet rs =  ps.executeQuery();
        if(rs.next()) {
            nextId = rs.getInt(1)+1;
            System.out.println(nextId);
            String sql1 = "insert into userinfo (id,name,pwd,email,status) values (?,?,?,?,?)";
            PreparedStatement ps1 =  JdbcUtil.getPreparedStatement(sql1);
            ps1.setInt(1,nextId);
            ps1.setString(2,user.getName());
            ps1.setString(3,user.getPwd());
            ps1.setString(4,user.getEmail());
            ps1.setInt(5,i);
            ps1.executeUpdate();
        }
        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, rs);
    }

    @Override
    public SysUser modifypwd(SysUser sysUser) throws Exception {
        String sql1 = "update userinfo set pwd = ? where name = ? and email = ?";
        PreparedStatement ps1 = JdbcUtil.getPreparedStatement(sql1);
        System.out.println(sysUser.getPwd());
        ps1.setString(1, sysUser.getPwd());
        ps1.setString(2, sysUser.getName());
        ps1.setString(3, sysUser.getEmail());
        ps1.executeUpdate();
        //ResultSet rs = ps1.executeQuery();
        JdbcUtil.closeAll(JdbcUtil.getConn(), ps1, null);
        return sysUser;
    }

    @Override
    public SysUser selectByNE(SysUser sysUser) throws Exception {
        String sql = "select * from userinfo where name = ? and email = ?";
        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
        ps.setString(1,sysUser.getName());
        ps.setString(2,sysUser.getEmail());
        ResultSet rs = ps.executeQuery();
        SysUser sysUser1 = null;
        if(rs.next())
        {
            sysUser1 = new SysUser();
            sysUser1.setId(rs.getInt(1));
            sysUser1.setName(rs.getString(2));
            sysUser1.setPwd(rs.getString(3));
            sysUser1.setEmail(rs.getString(4));
        }
        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, rs);
        return sysUser1;
    }

    @Override
    public void insertInfo(SysUser sysUser) throws Exception {
        String sql = "update userinfo set username=?,email=?,phone=?,qq=?,weibo=?,intro=? where name = ?";
        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
        ps.setString(1,sysUser.getUsername());
        ps.setString(2,sysUser.getEmail());
        ps.setString(3,sysUser.getPhone());
        ps.setString(4,sysUser.getQq());
        ps.setString(5,sysUser.getWeibo());
        ps.setString(6,sysUser.getIntro());
        ps.setString(7,sysUser.getName());
        ps.executeUpdate();
        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, null);
    }

    @Override
    public void insertImage(SysUser user) throws Exception {
        String sql = "update userinfo set url = ? where name = ?";
        PreparedStatement ps =  JdbcUtil.getPreparedStatement(sql);
        ps.setString(1,user.getUrl());
        ps.setString(2,user.getName());
        ps.executeUpdate();
        JdbcUtil.closeAll(JdbcUtil.getConn(), ps, null);
    }
}
