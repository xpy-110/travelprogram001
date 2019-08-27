package com.qf.travel.mapper;

import com.qf.travel.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper {
    //获取所有权限
    List<Permission> loadAll();
    //根据id获取权限
    List<Permission> loadAllById(int rid);
    /**
     * 根据用户名查询该用户已分配的权限集合
     * @param uname  登录名（用户名）
     * @return  Permission对象的集合
     */
    public List<Permission> findPermissionsByUname(@Param("uname") String uname);
    /**
     *根据uid删除会员权限
     *uid
     */
    public int deletePermissionByuid(int uid);
    /**
     *根据uid添加会员权限
     *uid
     */
    public int savePermissionByuid(Map<String,Integer> map);
    /**
     * 添加权限信息
     * @param
     * @return  int
     */
    public int addPermission(@Param("permission") Permission permission);

    public int deletePermission(int id);

    public List<Permission> loadPermissionByPid(int id);

    public int getCount();
    int deleteRolePermission(int pid);
    Permission checkMname(String mname);
    Permission checkPname(String pname);
}
