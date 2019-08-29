package com.qf.travel.controller;

import com.qf.travel.pojo.Indent;
import com.qf.travel.pojo.User;
import com.qf.travel.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
}
