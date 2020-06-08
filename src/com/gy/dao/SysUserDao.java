package com.gy.dao;

import com.gy.entity.SysUser;

import java.sql.SQLException;

public interface SysUserDao {
    public SysUser selectUserByName(SysUser user) throws Exception;
    public SysUser selectUserByName1(SysUser user) throws Exception;

    public void insertUserInfo(SysUser user) throws Exception;

    public SysUser modifypwd(SysUser sysUser) throws Exception;
    public SysUser selectByNE(SysUser sysUser) throws Exception;

    public void insertInfo(SysUser sysUser) throws Exception;
    public void insertImage(SysUser user) throws Exception;
}
