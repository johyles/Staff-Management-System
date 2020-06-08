package com.gy.service;

import com.gy.entity.Dept;
import com.gy.entity.Emp;

import java.util.List;

public interface SysEmpService {
    public boolean add(Emp emper) throws Exception;
    public Dept selectnameByid(int dept_id) throws Exception;
    public boolean updateEmp(Emp emp) throws Exception;
    public List vagueselect(int pagesize, int nowpage, String ss) throws Exception;
    public int EmpSelectCount(String ss) throws Exception;
    public boolean delEmp(int id) throws Exception;
    public List DeptList() throws Exception;
}
