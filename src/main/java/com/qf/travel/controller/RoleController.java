package com.qf.travel.controller;

import com.qf.travel.pojo.Role;
import com.qf.travel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/role")
    public String role(Model model){
        List<Role> roles = roleService.loadAll();
        model.addAttribute("roles",roles);
        return "role";
    }
    @RequestMapping("/addRole")
    public String addRole(){
        return "roleAdd";
    }
    @ResponseBody
    @RequestMapping("/checkrname")
    public boolean checkrname(String rname){
        List<Role> roles = roleService.loadAll();
        boolean bool = true;
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getRname().equals(rname)){
                bool = false;
            }
        }
        return bool;
    }
    @ResponseBody
    @RequestMapping("/saveRole")
    public boolean saveRole(String rname){
        Role role = new Role();
        role.setRname(rname);
        boolean bool = roleService.addRole(role);
        return bool;
    }
    @RequestMapping("/deleteRole")
    public String deleteRole(int rid){
        boolean bool = roleService.deleteRole(rid);
        return bool?"redirect:role":"error";
    }
    @ResponseBody
    @RequestMapping("/delRolePermission")
    public boolean delRolePermission(String rnames,int rid){
        String[] rnamess = rnames.split("=");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < rnamess.length; i++) {
            list.add(rnamess[i]);
        }
        boolean bool = roleService.delRolePer(rid,list);
        return bool;
    }
    @ResponseBody
    @RequestMapping("/addRolePermission")
    public boolean addRolePermission(String rnames,int rid){
        String[] rnamess = rnames.split("=");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < rnamess.length; i++) {
            list.add(rnamess[i]);
        }
        boolean bool = roleService.addRolePer(rid,list);
        return bool;
    }
    //管理员角色维护
    @RequestMapping("/adminpermission")
    public String adminpermission(int userId,Model model){
        List<Role> roles1 = roleService.loadAll();
        List<Role> roles2 = roleService.loadUserRole(userId);

        for (int i = 0; i < roles1.size(); i++) {
            for (int j = 0; j < roles2.size(); j++) {
                if (roles1.get(i).getRid() == roles2.get(j).getRid()){
                    roles1.remove(roles1.get(i));
                }
            }
        }
        model.addAttribute("uid",userId);
        model.addAttribute("rolesNo",roles1);
        model.addAttribute("roleYes",roles2);
        return "assignPermission";
    }
    @ResponseBody
    @RequestMapping("/delUserRole")
    public boolean delUserRole(String rnames,int rid){
        String[] rname = rnames.split("=");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < rname.length; i++) {
            list.add(rname[i]);
        }
        boolean bool = roleService.delUserRole(rid,list);
        return bool;
    }
    @ResponseBody
    @RequestMapping("/addUserRole")
    public boolean addUserRole(String rnames,int rid){
        String[] rname = rnames.split("=");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < rname.length; i++) {
            list.add(rname[i]);
        }
        boolean bool = roleService.saveUserRole(rid,list);
        return bool;
    }
}
