package com.qf.travel.mapper;

import com.qf.travel.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {
    List<Role> loadAll();
    List<Role> loadByRid(int rid);
    int deleteRole(int rid);
    int addRole(@Param("role")Role role);

    int loadByRname(String pname);
    int delRolePer(Map<String,Integer> map);
    int addRolePer(Map<String,Integer> map);

    int deleteRoP(int rid);

    List<Role> loadUserRole(int uid);
    int saveUserRole(Map<String,Integer> map);
    int delUserRole(Map<String,Integer> map);

    int getRidByName(String rname);
}
