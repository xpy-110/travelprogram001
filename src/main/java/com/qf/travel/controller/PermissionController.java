package com.qf.travel.controller;

import com.qf.travel.pojo.Permission;
import com.qf.travel.pojo.Role;
import com.qf.travel.service.PermissionService;
import com.qf.travel.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @RequiresPermissions(value = {"superManage"})
    @RequestMapping("/permission")
    public String permission(@RequestParam(required = false,defaultValue = "1")int page,
                             @RequestParam(required = false,defaultValue = "10")int rows,
                             Model model){
        int maxpage = permissionService.getMaxpage(rows);
        if (page > maxpage){
            page = 1;
        }
        if (page < 1){
            page = maxpage;
        }
        List<Permission> permissions = permissionService.loadAll(page, rows);
        model.addAttribute("maxpage",maxpage);
        model.addAttribute("currentpage",page);
        model.addAttribute("permission",permissions);
        return "permission";
    }
    @RequiresPermissions(value = {"superManage"})
    @RequestMapping("/deletePermi")
    public String deletePermi(int pid){
        System.out.println(pid);
        boolean bool = permissionService.deletePermission(pid);
        System.out.println(bool);
        return bool?"redirect:permission":"error";
    }
    @RequiresPermissions(value = {"superManage"})
    @RequestMapping("/addPer")
    public String addPer(){
        return "PermissionAdd_1";
    }
    @RequiresPermissions(value = {"superManage"})
    @ResponseBody
    @RequestMapping("/savePer")
    public boolean savePer(String pname,String mname){
        Permission permission = new Permission();
        permission.setPname(pname);
        permission.setMname(mname);
        boolean bool = permissionService.addPermission(permission);
        return bool;
    }
    @RequiresPermissions(value = {"superManage"})
    @ResponseBody
    @RequestMapping("/checkpname")
    public boolean checkpname(String pname){
        return permissionService.checkPname(pname);
    }
    @RequiresPermissions(value = {"superManage"})
    @ResponseBody
    @RequestMapping("/checkmname")
    public boolean checkmname(String mname){
        return permissionService.checkMname(mname);
    }


    @RequiresPermissions(value = {"superManage"})
    @RequestMapping("/permissionRole")
    public String permissionRole(int rid,Model model){
        List<Permission> permissions = permissionService.loadAll1();
        List<Permission> permissions1 = permissionService.loadAllById(rid);
        List<Permission> permission1 = new ArrayList<>();
        for (int i = 0; i < permissions.size(); i++) {
            for (int j = 0; j < permissions1.size(); j++) {
                if (permissions.get(i).getPid() == permissions1.get(j).getPid()){
                    permissions.remove(i);
                }
            }
        }
        model.addAttribute("rid",rid);
        model.addAttribute("permissions",permissions);
        model.addAttribute("permission1",permissions1);
        return "permissionRole";
    }

}
