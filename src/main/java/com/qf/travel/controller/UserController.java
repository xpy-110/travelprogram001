package com.qf.travel.controller;

import com.qf.travel.pojo.User;
import com.qf.travel.service.UserService;
import com.qf.travel.utils.ExportExcel;
import com.qf.travel.utils.ImportExcel;
import com.qf.travel.utils.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    //登录页展示
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLogin(){
        return "login";
    }
    /**
     * 登录处理
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dealLogin")
    public boolean dealLogin(@RequestParam("uname")String uname,
                            @RequestParam("upwd")String upwd , HttpServletRequest request) {
        boolean bool = false;
        try {
            User User=userService.findUserByUname(uname);
            Subject subject = SecurityUtils.getSubject();//从安全管理器中获取主体对象
            UsernamePasswordToken token=new UsernamePasswordToken(uname,upwd); //构建令牌对象
            subject.login(token);
            if(subject.isAuthenticated()){//判断是否正确登录
                //用户信息与权限信息存储
                System.out.println("登陆成功");
                request.getSession().setAttribute("user",User);
                bool = true;
                return bool;
            }
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("登录失败");
        }
        return bool;
    }
    /**
     * 登录判断
     * @param uname
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login1",method = RequestMethod.POST)
    public int login1(@RequestParam("uname")String uname,
                      @RequestParam("upwd")String password,String checkcode,String source1,String source2,String newString1,String newString2,
                      HttpServletRequest request,HttpSession session) {
        System.out.println(checkcode);
        String code =(String) session.getAttribute("number");
        User u = new User();
        u.setUname(uname);
        User b=userService.findUser(u);
        int a=0;
        if (b!=null) {
            System.out.println("login1111"+b);
            User user=userService.findUserByUname(uname);
            List<Integer> rids=userService.findUR(user.getUid());
            request.getSession().setAttribute("user", b);
            if(!code.equalsIgnoreCase(checkcode)){
                a=3;
            }else {
                for (int i = 0; i < rids.size(); i++) {
                    if(rids.get(i)!=1){
                        a = 2;
                    }else {
                        a = 1;
                        break;
                    }
                }
            }
        }
        return a;
    }
    /**
     * 登录前台
     * @param uname
     * @param request
     * @return
     */
    @RequestMapping("memberSave")
    public Object memberSave(String uname,HttpServletRequest request){
        System.out.println("uname="+uname);
        User u=new User();
        u.setUname(uname);
        User b=userService.findUser(u);
        request.getSession().setAttribute("currentUser",b);
        boolean bool=true;
        if(b!=null){
            bool=false;
        }
        request.getSession().setAttribute("islogin",bool);
        return "member";
    }

    /**
     * 登录验证码
     * @param response
     * @param session
     * @throws IOException
     */
    @GetMapping("/createImage")
    public void createImage(HttpServletResponse response, HttpSession session) throws IOException {
        int width=80,height=30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random r = new Random();
        g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        g.fillRect(0, 0, width, height);
        //干扰线
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }
        //获取生成的验证码
        String code = getNumber();
        //绑定验证码
        session.setAttribute("number", code);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        g.drawString(code, 5, 25);
        //设置消息头
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "jpeg", os);
    }
    public String getNumber(){
        String str="abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        for(int i= 0;i<4;i++){
            int index = (int)(Math.random()*str.length());
            code+=str.charAt(index);
        }
        return code;
    }
    /**
     * 进入前端页面
     * @return
     */
    @RequestMapping("/member")
    public String member(HttpServletRequest request,HttpSession session){
        if(request.getSession().getAttribute("islogin")==null){
            request.getSession().setAttribute("islogin",true);
        }
        return "member";
    }
    //权限不足时，响应的页面
    @RequestMapping("/unauth")
    public String showPermission(){
        return "unauth";
    }
    //用户注销时
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();//用户登录（清除用户在shiro中的驻留信息）
        request.getSession().invalidate();
        return "redirect:member";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    //管理员管理
    @RequiresPermissions(value = {"admManage"})
    @RequestMapping("/adminis")
    public String adminis(@RequestParam(required = false,defaultValue = "1")int page,
                          @RequestParam(required = false,defaultValue = "10")int rows,
                          Model model){
        int maxPage = userService.getMaxPage(rows,2);
        if (page < 1){
            page = maxPage;
        }
        if (page > maxPage){
            page = 1;
        }
        List<User> users = userService.loadUserId(2,page,rows);
        model.addAttribute("adminUsers",users);
        model.addAttribute("adminmaxpage",maxPage);
        model.addAttribute("admincurrentpage",page);
        return "adminis";
    }
    //模糊查询管理员
    @RequiresPermissions(value = {"admManage"})
    @RequestMapping("/QueryAdmins")
    public String QueryAdmins(String uuu,Model model){
        List<User> users = userService.inquireUser(2,uuu);
        model.addAttribute("users",users);
        return "adminisquery";
    }
    //修改管理员信息
    @RequiresPermissions(value = {"updateAdmin"})
    @RequestMapping("/editAdmin")
    public String editAdmin(String userId,Model model){
        int uid = Integer.parseInt(userId);
        User user = userService.getUserById(uid);
        model.addAttribute("adminadit",user);
        return "Adminedit";
    }
    @ResponseBody
    @RequiresPermissions(value = {"updateAdmin"})
    @RequestMapping("/admedited")
    public boolean admedited(int uid,String uname,String upwd,String email,String realname,
                             String tel){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        User user = new User();
        user.setUid(uid);
        user.setUname(uname);
        user.setUpwd(upwd);
        user.setEmail(email);
        user.setRealname(realname);
        user.setTel(tel);
        user.setCreatetime(date);
        boolean bool = userService.updateUser(user);
        System.out.println(bool);
        return bool;
    }
    //删除管理员信息
    @RequiresPermissions(value = {"deleteAdm"})
    @RequestMapping("/deleteAdmin")
    public String deleteAdmin(String userId){
        int uid = Integer.parseInt(userId);
        List<Integer> list = new ArrayList<>();
        list.add(uid);
        boolean bool = userService.deleteUser(list);
        return bool?"redirect:adminis":"error";
    }
    //批量删除管理员信息
    @RequiresPermissions(value = {"deleteAdm"})
    @ResponseBody
    @RequestMapping("/deleteAdmins")
    public boolean deleteAdmins(String ids){
        String[] uids = ids.split("-");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < uids.length; i++) {
            int id = Integer.parseInt(uids[i]);
            list.add(id);
        }
        boolean bool = userService.deleteUser(list);
        return bool;
    }
    //增加管理员
    @RequiresPermissions(value = {"addAdm"})
    @RequestMapping("/addAdmin")
    public String addAdmin(){
        return "Adminadd";
    }
    @ResponseBody
    @RequestMapping("/addadmed")
    public boolean addadmed(String uname,String upwd,String email,String realname,
                            String tel,String birth,String sex){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        User user = new User();
        user.setUname(uname);
        user.setUpwd(upwd);
        user.setEmail(email);
        user.setRealname(realname);
        user.setTel(tel);
        user.setBirth(birth);
        user.setCreatetime(date);
        user.setSex(sex);
        boolean bool = userService.saveUser(user,9);
        System.out.println(bool);
        return bool;
    }
    //注册
    @ResponseBody
    @RequestMapping("/zhuce")
    public int registerUser (String checkcode,String uname, String upwd, String email, String realname, String tel, String sex,String birth,HttpSession session){
        User user = new User();
        user.setUname(uname);
        String password = Md5Utils.getpawd(upwd);
        user.setUpwd(password);
        user.setEmail(email);
        user.setRealname(realname);
        user.setTel(tel);
        user.setSex(sex);
        user.setBirth(birth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createtime = dateFormat.format(new Date());
        user.setCreatetime(createtime);
        String code =(String) session.getAttribute("number");
        int aa=0;
        if(!code.equalsIgnoreCase(checkcode)){
            aa=1;
        }else if(userService.save(user)==true){
            aa=2;
        }
        return aa;
    }

    @ResponseBody
    @RequestMapping("/reguname")
    public boolean registerUname(String uname){
        boolean b = userService.getUserByName(uname);
        return b;
    }

    @ResponseBody
    @RequestMapping("/regemail")
    public boolean registerEmail(String email){
        boolean b = userService.getUserByEmail(email);
        return b;
    }

    @ResponseBody
    @RequestMapping("/regetel")
    public boolean registerTel(String tel){
        boolean b = userService.getUserByName(tel);
        return b;
    }


    //会员管理
    @RequiresPermissions(value = {"memManage"})
    @RequestMapping("/meManager")
    public String meManager(@RequestParam(required = false,defaultValue = "1")int page,
                            @RequestParam(required = false,defaultValue = "10")int rows,
                            Model model){
        int maxPage = userService.getMaxPage(rows,1);
        if (page < 1){
            page = maxPage;
        }
        if (page > maxPage){
            page = 1;
        }
        List<User> users = userService.loadUserId(1,page,rows);
        model.addAttribute("memUsers",users);
        model.addAttribute("memmaxpage",maxPage);
        model.addAttribute("memcurrentpage",page);
        return "meManager";
    }
    //删除会员信息
    @RequiresPermissions(value = {"deleteMe"})
    @RequestMapping("/deleteMem")
    public String deleteMem(String userId){
        int uid = Integer.parseInt(userId);
        List<Integer> list = new ArrayList<>();
        list.add(uid);
        boolean bool = userService.deleteUser(list);
        return bool?"redirect:meManager":"error";
    }
    //批量删除会员信息
    @ResponseBody
    @RequiresPermissions(value = {"deleteMe"})
    @RequestMapping("/deleteMems")
    public boolean deleteMems(String ids){
        String[] uids = ids.split("-");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < uids.length; i++) {
            int id = Integer.parseInt(uids[i]);
            list.add(id);
        }
        boolean bool = userService.deleteUser(list);
        return bool;
    }
    //增加会员
    @RequiresPermissions(value = {"addMem"})
    @RequestMapping("/addMem")
    public String addMem(){
        return "addMem";
    }
    @ResponseBody
    @RequiresPermissions(value = {"addMem"})
    @RequestMapping("/addMems")
    public boolean addMems(String uname,String upwd,String email,String realname,
                            String tel,String birth,String sex){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        User user = new User();
        user.setUname(uname);
        user.setUpwd(upwd);
        user.setEmail(email);
        user.setRealname(realname);
        user.setTel(tel);
        user.setBirth(birth);
        user.setCreatetime(date);
        user.setSex(sex);
        boolean bool = userService.saveUser(user,1);
        return bool;
    }
    //修改会员信息
    @RequiresPermissions(value = {"updateMem"})
    @RequestMapping("/editMem")
    public String editMem(String userId,Model model){
        int uid = Integer.parseInt(userId);
        User user = userService.getUserById(uid);
        model.addAttribute("meminadit",user);
        return "Meminedit";
    }
    @ResponseBody
    @RequiresPermissions(value = {"updateMem"})
    @RequestMapping("/Memdited")
    public boolean Memdited(int uid,String uname,String upwd,String email,String realname,
                             String tel){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        User user = new User();
        user.setUid(uid);
        user.setUname(uname);
        user.setUpwd(upwd);
        user.setEmail(email);
        user.setRealname(realname);
        user.setTel(tel);
        user.setCreatetime(date);
        boolean bool = userService.updateUser(user);
        return bool;
    }
    //模糊查询管理员
    @RequiresPermissions(value = {"memManage"})
    @RequestMapping("/meQuery")
    public String meQuery(String uuu,Model model){
        List<User> users = userService.inquireUser(1,uuu);
        model.addAttribute("users",users);
        return "mequery";
    }

    //导出管理员表
    @ResponseBody
    @RequestMapping("/ExportAdmins")
    public boolean EcportAdmin(){
        List<User> users = userService.loadAdmin();
        boolean bool = ExportExcel.exportUser(users,"管理员表.xls");
        return bool;
    }
    //导入管理员表
    @ResponseBody
    @RequestMapping("/ImportAdmins")
    public boolean ImportAdmins(){
        String filename = "管理员表.xls";
        boolean bool = false;
        List<User> list = ImportExcel.importUser(filename);
        for (int i = 0; i < list.size(); i++) {
            bool = userService.save(list.get(i));
        }
        return bool;
    }
    //导出会员表
    @ResponseBody
    @RequestMapping("/ExportMem")
    public boolean EcportMem(){
        List<User> users = userService.loadMem();
        boolean bool = ExportExcel.exportUser(users,"会员表.xls");
        return bool;
    }
    //导入会员表
    @ResponseBody
    @RequestMapping("/ImportMem")
    public boolean ImportMem(){
        String filename = "会员表.xls";
        boolean bool = false;
        List<User> list = ImportExcel.importUser(filename);
        for (int i = 0; i < list.size(); i++) {
            bool = userService.save(list.get(i));
        }
        return bool;
    }
}
