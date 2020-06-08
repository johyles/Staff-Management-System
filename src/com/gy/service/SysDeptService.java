package com.gy.service;

import com.gy.entity.Dept;

import java.util.List;

public interface SysDeptService {

    //查找
    public List<Dept> returndeptList(int nowPage, int num, String name) throws Exception;

    public boolean updateDept(int id,String name,String addr) throws Exception;

    public boolean deleteDept(int id) throws Exception;

    public boolean addDept(String name,String addr) throws Exception;

    //最大的ID
    public int MaxDeptID() throws Exception;

    //模糊查询
    public List<Dept> search(String name, int nowPage, int pageSize) throws Exception;

    //模糊查询总记录数
    public int searchCount(String name);


}
