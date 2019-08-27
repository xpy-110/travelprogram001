package com.qf.travel.service.impl;

import com.qf.travel.mapper.RoleMapper;
import com.qf.travel.pojo.Role;
import com.qf.travel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> loadAll() {
        return roleMapper.loadAll();
    }

    @Override
    public List<Role> loadByRid(int rid) {
        return roleMapper.loadByRid(rid);
    }

    @Override
    public boolean deleteRole(int rid) {

        int c = roleMapper.deleteRole(rid);
        boolean bool = c>0?true:false;
        if (bool){
            int a = roleMapper.deleteRoP(rid);
            bool = a>0?true:false;
        }
        return bool;
    }
    @Override
    public boolean addRole(Role role) {
        int c = roleMapper.addRole(role);
        return c>0?true:false;
    }
    //角色权限维护
    @Override
    public boolean delRolePer(int rid,List<String> pnames) {
        boolean bool = false;
        for (int i = 0; i < pnames.size(); i++) {
            Map<String,Integer> map = new HashMap<>();
            int pid = roleMapper.loadByRname(pnames.get(i));
            map.put("rid",rid);
            map.put("pid",pid);
            int c = roleMapper.delRolePer(map);
            bool = c>0?true:false;
        }
        return bool;
    }

    @Override
    public boolean addRolePer(int rid,List<String> pnames) {
        boolean bool = false;
        for (int i = 0; i < pnames.size(); i++) {
            Map<String,Integer> map = new HashMap<>();
            int pid = roleMapper.loadByRname(pnames.get(i));
            map.put("rid",rid);
            map.put("pid",pid);
            int c = roleMapper.addRolePer(map);
            bool = c>0?true:false;
        }
        return bool;
    }
    //管理员角色维护
    @Override
    public List<Role> loadUserRole(int uid) {
        return roleMapper.loadUserRole(uid);
    }

    @Override
    public boolean saveUserRole(int uid, List<String> rnames) {
        boolean bool = false;
        for (int i = 0; i < rnames.size(); i++) {
            int rid = roleMapper.getRidByName(rnames.get(i));
            Map<String,Integer> map = new HashMap<>();
            map.put("uid",uid);
            map.put("rid",rid);
            int c = roleMapper.saveUserRole(map);
            bool = c>0?true:false;
        }
        return bool;
    }

    @Override
    public boolean delUserRole(int uid, List<String> rnames) {
        boolean bool = false;
        for (int i = 0; i < rnames.size(); i++) {
            int rid = roleMapper.getRidByName(rnames.get(i));
            Map<String,Integer> map = new HashMap<>();
            map.put("uid",uid);
            map.put("rid",rid);
            int c = roleMapper.delUserRole(map);
            bool = c>0?true:false;
        }
        return bool;
    }


}
