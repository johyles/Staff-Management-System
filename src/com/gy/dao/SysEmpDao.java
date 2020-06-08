package com.gy.dao;

import com.gy.entity.Dept;
import com.gy.entity.Emp;

import java.util.List;

public interface SysEmpDao {
    public List selectEmp(int pagesize, int nowpage) throws Exception;
    public int EmpCount() throws Exception;
    public boolean updateEmpByid(Emp emper) throws Exception;
    public boolean deleteEmpByid(int id) throws Exception;
    public boolean insertEmpByid(Emp emper) throws Exception;
    public Dept selectnameByid(int deptid) throws Exception;
    public List DeptList() throws Exception ;
    public List vagueSelect(int pagesize, int nowpage, String ss) throws Exception;
    public int EmpSelectCount(String ss) throws Exception;
}
