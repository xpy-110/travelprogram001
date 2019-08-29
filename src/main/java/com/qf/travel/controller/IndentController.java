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
import java.text.SimpleDateFormat;
import java.util.*;

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

    /*新增订单的方法*/
    @ResponseBody
    @RequestMapping("/saveIndent")
    public int saveIndent(String sid, String uname , String icount, String iprice){
        int sids = Integer.parseInt(sid);
        int icounts = Integer.parseInt(icount);
        int iprices = Integer.parseInt(iprice);
        System.out.println("sids = " + sids);
        Indent indent = new Indent();
        indent.setSid(sids);
        indent.setIcount(icounts);
        indent.setUname(uname);
        indent.setIprice(iprices);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        System.out.println("date = " + date);
        indent.setItime(date);
        System.out.println("indent = " + indent);
        boolean b = indentService.saveIndent(indent);
        int id = 0;
        if (b){
            id = indentService.getByItime(date);
        }
        return id;
    }

    @RequestMapping("indent_detail")
    public String indent_detail(int id , Model model){
        System.out.println("id = " + id);
        Indent indent = indentService.getIndentById(id);
        int sid =indent.getSid();
        model.addAttribute("indent",indent);
        return "indent_detail";
    }
    @RequestMapping("/updateIstateById")
    public String updateIstateById(int id ,Model model){
        boolean b = indentService.updateIstateById(id);
        return "indent";
    }
}
