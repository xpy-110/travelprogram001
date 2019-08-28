package com.qf.travel.service;

import com.qf.travel.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Role> loadAll();
    List<Role> loadByRid(int rid);
    boolean deleteRole(int rid);
    boolean addRole(Role role);
    boolean delRolePer(int rid,List<String> pnames);
    boolean addRolePer(int rid,List<String> pnames);
    List<Role> loadUserRole(int uid);
    boolean saveUserRole(int uid,List<String> rnames);
    boolean delUserRole(int uid,List<String> rnames);
}
