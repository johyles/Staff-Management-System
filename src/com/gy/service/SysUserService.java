package com.gy.service;

import com.gy.entity.SysUser;

public interface SysUserService {

    public SysUser login(SysUser user) throws Exception;
    public SysUser checkName(SysUser user) throws Exception;
    public void insertSysUser(SysUser user) throws Exception;

    public SysUser modifyPwd(SysUser sysUser) throws Exception;

    public void insertInfo(SysUser sysUser) throws Exception;
    public void insertImage(SysUser user) throws Exception;
}
