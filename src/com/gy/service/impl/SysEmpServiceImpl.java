package com.gy.service.impl;

import com.gy.dao.SysEmpDao;
import com.gy.dao.impl.SysEmpDaoImpl;
import com.gy.entity.Dept;
import com.gy.entity.Emp;
import com.gy.service.SysEmpService;

import java.util.List;
import java.util.function.DoubleToIntFunction;

public class SysEmpServiceImpl implements SysEmpService {

    //插入操作
    @Override
    public boolean add(Emp emper) throws Exception {
        SysEmpDao sysEmpDao=new SysEmpDaoImpl();
        return sysEmpDao.insertEmpByid(emper);
    }

    //查部门
    @Override
    public Dept selectnameByid(int dept_id) throws Exception {
        SysEmpDao sysEmpDao=new SysEmpDaoImpl();
        return sysEmpDao.selectnameByid(dept_id);
    }

    //更新操作
    @Override
    public boolean updateEmp(Emp emp) throws Exception {
        SysEmpDao sysEmpDao=new SysEmpDaoImpl();
        return sysEmpDao.updateEmpByid(emp);
    }

    //模糊查询
    @Override
    public List vagueselect(int pagesize, int nowpage, String ss) throws Exception {
        SysEmpDao sysEmpDao=new SysEmpDaoImpl();
        if(ss==null){
            return sysEmpDao.selectEmp(pagesize,nowpage);
        }else{
            return sysEmpDao.vagueSelect(pagesize, nowpage, ss);
        }
    }

    //模糊查询总记录
    @Override
    public int EmpSelectCount(String ss) throws Exception {
        SysEmpDao sysEmpDao=new SysEmpDaoImpl();
        if(ss==null){
            return sysEmpDao.EmpCount();
        }else{
            return sysEmpDao.EmpSelectCount(ss);
        }
    }

    //删除
    @Override
    public boolean delEmp(int id) throws Exception {
        SysEmpDao sysEmpDao=new SysEmpDaoImpl();
        return sysEmpDao.deleteEmpByid(id);
    }

    //查询部门数
    @Override
    public List DeptList() throws Exception {
        SysEmpDao sysEmpDao=new SysEmpDaoImpl();
        return sysEmpDao.DeptList();

    }

}
