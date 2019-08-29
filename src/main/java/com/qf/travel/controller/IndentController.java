package com.qf.travel.controller;

import com.qf.travel.pojo.Indent;
import com.qf.travel.pojo.User;
import com.qf.travel.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class IndentController {
    @Autowired
    private IndentService indentService;
    @RequestMapping("/findIndent")
    public String findIndent(@RequestParam(required = false,defaultValue = "1") int page,
                             @RequestParam(required = false,defaultValue = "6") int rows,
                             Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("currentUser");
        String uname = user.getUname();
        int maxPage=indentService.calcMaxPage(uname,rows);
        if(page<1){
            page=maxPage;
        }
        if(page>maxPage){
            page=1;
        }
        List<Indent> indents=indentService.findIndent(uname,page, rows);
        System.out.println(indents);
        model.addAttribute("indents",indents);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        return "indent";
    }
    @RequestMapping("/findIndent1")
    public String findIndent1(@RequestParam(required = false,defaultValue = "1") int page,
                             @RequestParam(required = false,defaultValue = "6") int rows,
                             Model model, HttpServletRequest request,String istate){
        System.out.println(istate);
        User user = (User) request.getSession().getAttribute("currentUser");
        String uname = user.getUname();
        int maxPage=indentService.calcMaxPage1(uname,istate,rows);
        if(page<1){
            page=maxPage;
        }
        if(page>maxPage){
            page=1;
        }
        if(istate.equals("已付款")){
            List<Indent> indents=indentService.findIndent1(uname,istate,rows,page);
            System.out.println(indents);
            model.addAttribute("indents",indents);
            model.addAttribute("currentPage",page);
            model.addAttribute("maxPage",maxPage);
            return "indent_2";
        }else{
            List<Indent> indents=indentService.findIndent1(uname,istate,rows,page);
            System.out.println(indents);
            model.addAttribute("indents",indents);
            model.addAttribute("currentPage",page);
            model.addAttribute("maxPage",maxPage);
            return "indent_1";
        }

    }
    /**
     * 单个删除
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(int id){
       indentService.deleteById(id);
        return "redirect:indent";
    }

    /**
     * 批量删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/delIndent")
    public boolean delIndent(String ids){
        System.out.println(ids);
        String[] uids = ids.split("-");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < uids.length; i++) {
            int id = Integer.parseInt(uids[i]);
            list.add(id);
        }
        boolean bool = indentService.deleteIds(list);
        return bool;
    }

}
