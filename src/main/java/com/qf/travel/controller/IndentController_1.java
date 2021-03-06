package com.qf.travel.controller;

import com.qf.travel.pojo.Indent;
import com.qf.travel.pojo.User;
import com.qf.travel.service.IndentService;
import com.qf.travel.service.IndentService_1;
import com.qf.travel.service.UserService;
import com.qf.travel.utils.ExportExcel;
import com.qf.travel.utils.ImportExcel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndentController_1 {
    @Autowired
    private UserService userService;
    @Autowired
    private IndentService_1 indentService_1;
    @Autowired
    private IndentService indentService;
    @RequiresPermissions(value = {"ordManage"})
    @RequestMapping("/IndentMen")
    public String IndentMen(String uname, Model model,
                            @RequestParam(required = false,defaultValue = "1")int page,
                            @RequestParam(required = false,defaultValue = "10")int rows){
        int maxpage = indentService_1.getAllmaxpage(rows);
        if (page > maxpage){
            page = 1;
        }
        if (page < 1){
            page = maxpage;
        }
        System.out.println(uname);
        List<Indent> indents = indentService_1.findIndent(page,rows,uname);
        model.addAttribute("maxpage",maxpage);
        model.addAttribute("currentpage",page);
        model.addAttribute("indents",indents);
        return "IndentMen";
    }
    @RequiresPermissions(value = {"ordAud"})
    @RequestMapping("/IndentManage")
    public String IndentManage( Model model,
                            @RequestParam(required = false,defaultValue = "1")int page,
                            @RequestParam(required = false,defaultValue = "10")int rows){
        int maxpage = indentService_1.getAllmaxpage(rows);
        if (page > maxpage){
            page = 1;
        }
        if (page < 1){
            page = maxpage;
        }
        String istate = "已付款";
        List<Indent> indents = indentService_1.loadAll(page, rows, istate);
        model.addAttribute("maxpage",maxpage);
        model.addAttribute("currentpage",page);
        model.addAttribute("indents",indents);
        return "IndentManage";
    }
    @RequiresPermissions(value = {"ordAud"})
    @RequestMapping("/editIndent")
    public String editIndent(int id){
       System.out.println(id);
        String istate = "交易已完成";
        boolean bool = indentService_1.IndentManUp(istate,id);
        return "redirect:IndentManage";
    }
    @ResponseBody
    @RequestMapping("/ImportIndent")
    public boolean ImportIndent(){
       String filename = "订单表.xls";
       boolean bool = false;
       List<Indent> list = ImportExcel.importIndent(filename);
       for (int i = 0; i < list.size(); i++) {
           bool = indentService.saveIndent(list.get(i));
       }
       return bool;
    }
    //导出会员表
    @ResponseBody
    @RequestMapping("/ExportIndent")
    public boolean EcportMem(){
        List<Indent> indents = indentService_1.loadAll11();
        boolean bool = ExportExcel.exportIndent(indents,"订单表.xls");
        return bool;
    }
}
