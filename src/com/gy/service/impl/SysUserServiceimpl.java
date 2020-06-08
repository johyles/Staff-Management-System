package com.gy.service.impl;

import com.gy.dao.SysUserDao;
import com.gy.dao.impl.SysUserDaoImpl;
import com.gy.entity.SysUser;
import com.gy.service.SysUserService;

public class SysUserServiceimpl implements SysUserService {

    @Override
    public SysUser login(SysUser user) throws Exception {
        SysUserDao sysUserDao = new SysUserDaoImpl();
        return sysUserDao.selectUserByName(user);
    }

    @Override
    public SysUser checkName(SysUser user) throws Exception {
        SysUserDao sysUserDao = new SysUserDaoImpl();
        return sysUserDao.selectUserByName1(user);
    }

    @Override
    public void insertSysUser(SysUser user) throws Exception {
        SysUserDao sysUserDao = new SysUserDaoImpl();
        sysUserDao.insertUserInfo(user);
    }

    @Override
    public SysUser modifyPwd(SysUser sysUser) throws Exception {
        SysUserDao sysUserDao = new SysUserDaoImpl();
        if(sysUserDao.selectByNE(sysUser)!=null) {
            System.out.println("qq");
            return sysUserDao.modifypwd(sysUser);
        }else
        {
            System.out.println("q");
            return null;
        }
    }

    @Override
    public void insertInfo(SysUser sysUser) throws Exception {
        SysUserDao sysUserDao = new SysUserDaoImpl();
        sysUserDao.insertInfo(sysUser);
    }

    @Override
    public void insertImage(SysUser user) throws Exception {
        SysUserDao sysUserDao = new SysUserDaoImpl();
        sysUserDao.insertImage(user);
    }
}
