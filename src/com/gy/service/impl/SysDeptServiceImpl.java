package com.gy.service.impl;

import com.gy.dao.SysDeptDao;
import com.gy.dao.impl.SysDeptDaoImpl;
import com.gy.entity.Dept;
import com.gy.service.SysDeptService;

import java.util.List;


public class SysDeptServiceImpl implements SysDeptService {

    //查找
    public List<Dept> returndeptList(int nowPage, int num, String name) throws Exception {
        SysDeptDao sysDeptDao = new SysDeptDaoImpl();
        return sysDeptDao.selectDeptMessage(nowPage,num,name);
    }

    //更新
    public boolean updateDept(int id,String name,String addr) throws Exception {
        SysDeptDao sysDeptDao = new SysDeptDaoImpl();
        return sysDeptDao.updateDept(id,name,addr);
    }

    //删除
    public boolean deleteDept(int id) throws Exception {

        SysDeptDao sysDeptDao = new SysDeptDaoImpl();
        return sysDeptDao.deleteDept(id);
    }

    //添加
    public boolean addDept(String name,String addr) throws Exception {
        SysDeptDao sysDeptDao = new SysDeptDaoImpl();
        return sysDeptDao.addDept(name,addr);
    }

    //最大的ID
    public int MaxDeptID() throws Exception{
        SysDeptDao sysDeptDao =new SysDeptDaoImpl();
        return sysDeptDao.MaxDeptID();
    }

    //模糊查询
    public List<Dept> search(String name, int nowPage, int pageSize) throws Exception{
        SysDeptDao sysDeptDao =new SysDeptDaoImpl();
        return sysDeptDao.search(name,nowPage,pageSize);
    }

    //模糊查询总记录数
    public int searchCount(String name){
        SysDeptDao sysDeptDao =new SysDeptDaoImpl();
        return sysDeptDao.searchCount(name);
    }

}
