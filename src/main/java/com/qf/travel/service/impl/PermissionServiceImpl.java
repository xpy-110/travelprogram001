package com.qf.travel.service.impl;

import com.github.pagehelper.PageHelper;
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
    public List<Permission> loadAll(int page,int rows) {
        PageHelper.startPage(page, rows);
        return permissionMapper.loadAll();
    }

    @Override
    public List<Permission> loadAll1() {
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
        int a = permissionMapper.deleteRolePermission(id);
            int c = permissionMapper.deletePermission(id);
            bool = c>0?true:false;
        return bool;
    }


    @Override
    public List<Permission> loadPermissionByPid(int id) {
        return permissionMapper.loadPermissionByPid(id);
    }

    @Override
    public int getMaxpage(int rows) {
        int c = permissionMapper.getCount();
        int maxpage = c%rows==0?c/rows:c/rows+1;
        return maxpage;
    }

    @Override
    public boolean checkMname(String mname) {
        Permission p = permissionMapper.checkMname(mname);
        boolean bool = false;
        if(p == null){
            bool = true;
        }
        return bool;
    }

    @Override
    public boolean checkPname(String pname) {
        Permission p = permissionMapper.checkPname(pname);
        boolean bool = false;
        if(p == null){
            bool = true;
        }
        return bool;
    }

}
