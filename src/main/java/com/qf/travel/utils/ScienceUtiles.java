package com.qf.travel.utils;

import com.qf.travel.pojo.Scenic;
import com.qf.travel.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScienceUtiles {
    @Autowired
    private ScenicService scenicService;
    public static Scenic buildScience(int sid, String sname, int sindent, int scllect, int scomment,
                                      String scity, double sprice, String simgs, String stype,
                                      String sfeature){
        Scenic scenic = new Scenic();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stime = dateFormat.format(new Date());
        scenic.setSid(sid);
        scenic.setSname(sname);
        scenic.setSindent(sindent);
        scenic.setScllect(scllect);
        scenic.setScomment(scomment);
        scenic.setScity(scity);
        scenic.setSprice(sprice);
        scenic.setSimgs(simgs);
        scenic.setStype(stype);
        scenic.setStime(stime);
        scenic.setSfeature(sfeature);
        return scenic;
    }
    public static Scenic buildScience1(String sname,int sindent,int scllect,int scomment,
                                       String scity,double sprice,String simgs,String stype,
                                       String sfeature,int sstate){
        Scenic scenic = new Scenic();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stime = dateFormat.format(new Date());
        scenic.setSname(sname);
        scenic.setSindent(sindent);
        scenic.setScllect(scllect);
        scenic.setScomment(scomment);
        scenic.setScity(scity);
        scenic.setSprice(sprice);
        scenic.setSimgs(simgs);
        scenic.setStype(stype);
        scenic.setStime(stime);
        scenic.setSfeature(sfeature);
        scenic.setSstate(sstate);
        return scenic;
    }

    //上传图片
    public static String saveImg(MultipartFile uploadfile, HttpServletRequest request){
        String filepath = "";
        String path = request.getSession().getServletContext().getRealPath("/upload");
        File uploadRoot = new File(path);
        if (!uploadRoot.exists()){
            uploadRoot.mkdir();
        }
        try {
            InputStream inputStream = uploadfile.getInputStream();
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b,0,b.length);
            inputStream.close();
            OutputStream outputStream = new FileOutputStream(uploadRoot+"/"+uploadfile.getOriginalFilename());
            outputStream.write(b,0,b.length);
            outputStream.flush();
            outputStream.close();
            filepath = "upload/"+uploadfile.getOriginalFilename();
            System.out.println(uploadRoot+"/"+uploadfile.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filepath;
    }
}
