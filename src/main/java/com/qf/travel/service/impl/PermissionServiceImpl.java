package com.qf.travel.service.impl;

import com.qf.travel.mapper.PermissionMapper;
import com.qf.travel.pojo.Permission;
import com.qf.travel.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> loadAll() {
        return permissionMapper.loadAll();
    }

    @Override
    public List<Permission> loadAllById(int uid) {
        return permissionMapper.loadAllById(uid);
    }
    /**
     * 根据用户名查询该用户已分配的权限集合
     * @param uname  登录名（用户名）
     * @return  Permission对象的集合
     */
    @Override
    public List<Permission> findPermissionByUname(String uname) {
        List<Permission> permissionsByUname = permissionMapper.findPermissionsByUname(uname);
        return permissionsByUname;
    }
    /**
     *根据uid删除会员权限
     *uid
     */
    @Override
    public boolean deletePermissionByuid(int uid) {
        int c = permissionMapper.deletePermissionByuid(uid);
        return c>0?true:false;
    }
    /**
     *根据uid添加会员权限
     *uid
     */
    @Override
    public boolean savePermissionByuid(String[] pids, int uId) {
        boolean bool = false;
        for (int i = 0; i < pids.length; i++) {
            int pid = Integer.parseInt(pids[i]);
            Map<String,Integer> map = new HashMap<>();
            map.put("uid",uId);
            map.put("pid",pid);
            int c = permissionMapper.savePermissionByuid(map);
            bool = c>0?true:false;
        }
        return bool;
    }


    @Override
    public boolean addPermission(Permission permission) {
        int c = permissionMapper.addPermission(permission);
        return c>0?true:false;
    }

    @Override
    public boolean deletePermission(int id) {
        boolean bool = false;
        int a = permissionMapper.deleteUserPermission(id);
        bool = a>0?true:false;
        if (bool){
            int c = permissionMapper.deletePermission(id);
            bool = c>0?true:false;
        }
        return bool;
    }

    @Override
    public boolean updatePermission(Permission permission) {
        int c = permissionMapper.updatePermission(permission);
        return c>0?true:false;
    }

    @Override
    public List<Permission> loadPermissionByPid(int id) {
        return permissionMapper.loadPermissionByPid(id);
    }

}
