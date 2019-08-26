package com.qf.travel.controller;

import com.qf.travel.pojo.Permission;
import com.qf.travel.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/permission")
    public String permission(){
        return "permission";
    }
    @ResponseBody
    @RequestMapping("/loadAllpermission")
    public List<Permission> loadPermission(){
        List<Permission> permissions = permissionService.loadAll();
        System.out.println(permissions);
        return permissions;
    }
    @RequestMapping("/adminpermission")
    public String adminpermission(int userId, Model model){
        model.addAttribute("aId",userId);
        return "assignPermission";
    }
    @ResponseBody
    @RequestMapping("/permissionroleid")
    public List<Permission> permissionroleid(int aId){
        List<Permission> permissions = permissionService.loadAll();
        List<Permission> permission = permissionService.loadAllById(aId);
        System.out.println("permissions"+permissions);
        System.out.println("permission"+permission);
        for (Permission p:permission) {
            for (Permission per:permissions){
                if (p.getId() == per.getId()){
                    per.setCheck("true");
                    break;
                }
            }
        }
        return permissions;
    }
    @ResponseBody
    @RequestMapping("/savePermission")
    public boolean savepermission(String pids,int aId){
        String[] pid = pids.split("-");
        List<Permission> list = permissionService.loadAllById(aId);
        boolean bool = true;
        if (list.size() != 0){
            bool = permissionService.deletePermissionByuid(aId);
        }
        boolean b = permissionService.savePermissionByuid(pid,aId);
        return bool&b?true:false;
    }


    @RequestMapping("/PermissionAdd")
    public String PermissionAdd(){
        return "PermissionUpdate_1";
    }
    //添加权限信息
    @ResponseBody
    @RequestMapping("/addPermission")
    public boolean addPermission(int pId,String pname,String mname){
        Permission permission = new Permission();
        permission.setPname(pname);
        permission.setpId(pId+1);
        permission.setMname(mname);
        permission.setCheck("false");
        boolean bool = permissionService.addPermission(permission);
        return bool;
    }
    //删除权限信息
    @ResponseBody
    @RequestMapping("/deletePermission")
    public boolean deletePermission(int id){
        List<Permission> permissions = permissionService.loadPermissionByPid(id);
        boolean bool = false;
        for (Permission p:permissions) {
            bool = permissionService.deletePermission(p.getId());
        }
        return bool;
    }
    //修改权限信息
    @RequestMapping("/updatePermission")
    public String updatePermission(int id,Model model){

        return "permissionUpdate";
    }

    @RequestMapping("/gtrt")
    public void gtrt(String treeId){
        System.out.println(treeId);
    }
}
