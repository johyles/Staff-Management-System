package com.gy.dao;

import com.gy.entity.Dept;

import java.util.List;


public interface SysDeptDao {

    //查找
    public List<Dept> selectDeptMessage(int nowPage, int num, String name) throws Exception;

//    public ResultSet selectDeptMessage(int nowPage, int num) throws Exception;

    //最大的ID
    public int MaxDeptID() throws Exception;

    //编辑更新
    public boolean updateDept(int id,String name,String addr) throws Exception;

    //删除
    public boolean deleteDept(int id) throws Exception;

    //添加
    public boolean addDept(String name,String addr) throws Exception;

    //模糊查询
    public List<Dept> search(String name, int nowPage, int pageSize) throws Exception;

    //模糊查询总记录数
    public int searchCount(String uname);



}
