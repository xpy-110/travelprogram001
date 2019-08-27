package com.qf.travel.service;

import com.qf.travel.pojo.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    //获取所有权限
    List<Permission> loadAll(int page,int rows);
    List<Permission> loadAll1();
    //根据id获取权限
    List<Permission> loadAllById(int uid);
    /**
     * 根据用户名查询该用户已分配的权限集合
     * @param uname  登录名（用户名）
     * @return  Permission对象的集合
     */
    public List<Permission> findPermissionByUname(String uname);
    /**
     *根据uid删除会员权限
     *uid
    */
    public boolean deletePermissionByuid(int uid);
    /**
     *根据uid添加会员权限
     *uid
     */
    public boolean savePermissionByuid(String[] pids,int uId);
    /**
     * 添加权限信息
     * @param
     * @return  int
     */
    public boolean addPermission(Permission permission);
    /**
     * 删除权限信息
     * @param id
     * @return  int
     */
    public boolean deletePermission(int id);

    public List<Permission> loadPermissionByPid(int id);

    public int getMaxpage(int rows);

    boolean checkMname(String mname);
    boolean checkPname(String pname);
}
