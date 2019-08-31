package com.qf.travel.controller;

import com.qf.travel.pojo.Indent;
import com.qf.travel.pojo.Scenic;
import com.qf.travel.pojo.User;
import com.qf.travel.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        String istate="已完成订单";
        User user = (User) request.getSession().getAttribute("currentUser");
        String uname = user.getUname();
        int maxPage=indentService.calcMaxPage1(istate,uname,rows);
        if(page<1){
            page=maxPage;
        }
        if(page>maxPage){
            page=1;
        }
        List<Indent> indents=indentService.findIndent1(istate,uname,page,rows);
        System.out.println(indents);
        model.addAttribute("indents",indents);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        return "indent";
    }
    @RequestMapping("/findIndent1")
    public String findIndent1(@RequestParam(required = false,defaultValue = "1") int page,
                             @RequestParam(required = false,defaultValue = "6") int rows,
                             Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("currentUser");
        String istate="待付款";
        String uname = user.getUname();
        int maxPage=indentService.calcMaxPage1(uname,istate,rows);
        if(page<1){
            page=maxPage;
        }
        if(page>maxPage){
            page=1;
        }
            List<Indent> indents=indentService.findIndent1(istate,uname,page,rows);
            System.out.println(indents);
            model.addAttribute("indents",indents);
            model.addAttribute("currentPage",page);
            model.addAttribute("maxPage",maxPage);
            return "indent_1";
    }
    @RequestMapping("/findIndent2")
    public String findIndent2(@RequestParam(required = false,defaultValue = "1") int page,
                              @RequestParam(required = false,defaultValue = "6") int rows,
                              Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("currentUser");
        String istate="已付款";
        String uname = user.getUname();
        int maxPage=indentService.calcMaxPage1(uname,istate,rows);
        if(page<1){
            page=maxPage;
        }
        if(page>maxPage){
            page=1;
        }
        List<Indent> indents=indentService.findIndent1(istate,uname,page,rows);
        System.out.println(indents);
        model.addAttribute("indents",indents);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        return "indent_2";
    }
    /**
     * 单个删除
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(int id){
       indentService.deleteById(id);
        return "redirect:findIndent“";
    }
    @RequestMapping("/deleteById1")
    public String deleteById1(int id){
        indentService.deleteById(id);
        return "redirect:findIndent1";
    }
    @RequestMapping("/deleteById2")
    public String deleteById2(int id){
        indentService.deleteById(id);
        return "redirect:findIndent2";
    }



    /**
     * 批量删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/delIndent")
    public boolean delIndent(String ids){
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
    /*根据id查询indent信息发送到详情界面*/
    @RequestMapping("indent_detail")
    public String indent_detail(int id , Model model){
        System.out.println("id = " + id);
        Indent indent = indentService.getIndentById(id);
        int sid =indent.getSid();
        Scenic scenic = indentService.getScenicBySid(sid);
        model.addAttribute("scenic",scenic);
        System.out.println("scenic = " + scenic + sid);
        model.addAttribute("indent",indent);
        return "indent_detail";
    }
    /*根据id查询indent信息发送到详情界面*/
    @RequestMapping("indent_detail1")
    public String indent_detail1(int id , Model model){
        Indent indent = indentService.getIndentById(id);
        System.out.println("id = " + id);
        System.out.println("indent = " + indent);
        int sid =indent.getSid();
        System.out.println("sid = " + sid);
        Scenic scenic = indentService.getScenicBySid(sid);
        model.addAttribute("scenic",scenic);
        System.out.println("scenic = " + scenic + sid);
        model.addAttribute("indent",indent);
        return "indent_detail1";
    }
    /*根据id查询indent信息发送到详情界面*/
    @RequestMapping("indent_detail2")
    public String indent_detail2(int id , Model model){
        System.out.println("id = " + id);
        Indent indent = indentService.getIndentById(id);
        int sid =indent.getSid();
        Scenic scenic = indentService.getScenicBySid(sid);
        model.addAttribute("scenic",scenic);
        System.out.println("scenic = " + scenic + sid);
        model.addAttribute("indent",indent);
        return "indent_detail2";
    }
    /*根据id查询indent信息发送到详情界面*/
    @RequestMapping("indent_detail3")
    public String indent_detail3(int id , Model model){
        System.out.println("id = " + id);
        Indent indent = indentService.getIndentById(id);
        int sid =indent.getSid();
        Scenic scenic = indentService.getScenicBySid(sid);
        model.addAttribute("scenic",scenic);
        System.out.println("scenic = " + scenic + sid);
        model.addAttribute("indent",indent);
        return "indent_detail3";
    }
    //改变订单状态为已付款
    @RequestMapping("/updateIstateById")
    public String updateIstateById(int id ,Model model){
        boolean b = indentService.updateIstateById(id);
        return "redirect:findIndent2";
    }
    //改变订单状态为已完成订单
    @RequestMapping("/updateIstateById1")
    public String updateIstateById1(int id ,Model model){
        boolean b = indentService.updateIstateById1(id);
        return "redirect:findIndent";
    }
    //改变订单中数量与价格
    @ResponseBody
    @RequestMapping("/updateIndent")
    public int updateIndent(int id,int icount,double iprice){
        int price;
        price=(int) iprice;
        System.out.println("price = " + price);
        int i = indentService.updateIndent(id, icount);
        int i1 = indentService.updateIndent1(id, price);
        System.out.println("i1 = " + i1);
        System.out.println("i = " + i);
        return i;
    }


}
