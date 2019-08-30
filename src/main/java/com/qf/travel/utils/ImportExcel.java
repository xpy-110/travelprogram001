package com.qf.travel.utils;

import com.qf.travel.pojo.Indent;
import com.qf.travel.pojo.Scenic;
import com.qf.travel.pojo.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//导入Excel文档
public class ImportExcel {
    public static List<User> importUser(String fileName){
        ArrayList<User> list = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream("D:\\"+fileName);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row:sheet) {
                if (row.getRowNum() == 0){
                    continue;
                }
                Integer uid = (int)row.getCell(0).getNumericCellValue();
                String uname = row.getCell(1).getStringCellValue();
                String upwd = row.getCell(2).getStringCellValue();
                String email = row.getCell(3).getStringCellValue();
                String realname = row.getCell(4).getStringCellValue();
                String tel = row.getCell(5).getStringCellValue();
                String sex = row.getCell(6).getStringCellValue();
                String birth = row.getCell(7).getStringCellValue();
                String createtime = row.getCell(8).getStringCellValue();
                User user = new User();
                //user.setUid(uid);
                user.setUname(uname);
                user.setUpwd(upwd);
                user.setEmail(email);
                user.setRealname(realname);
                user.setTel(tel);
                user.setSex(sex);
                user.setBirth(birth);
                user.setCreatetime(createtime);
                list.add(user);
            }
            //关闭流
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    //导出订单信息
    public static List<Indent> importIndent(String fileName){
        ArrayList<Indent> list = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream("D:\\"+fileName);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row:sheet) {
                if (row.getRowNum() == 0){
                    continue;
                }
                Integer id = (int)row.getCell(0).getNumericCellValue();
                Integer sid = (int)row.getCell(1).getNumericCellValue();
                String uname = row.getCell(2).getStringCellValue();
                Integer icount = (int)row.getCell(3).getNumericCellValue();
                double iprice = (double) row.getCell(4).getNumericCellValue();
                String itime = row.getCell(5).getStringCellValue();
                String istate = row.getCell(6).getStringCellValue();

                Indent indent = new Indent();
                //indent.setId(id);
                indent.setSid(sid);
                indent.setUname(uname);
                indent.setIcount(icount);
                indent.setIprice(iprice);
                indent.setItime(itime);
                indent.setIstate(istate);
                list.add(indent);
            }
            //关闭流
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    //导出订单信息
    public static List<Scenic> importScenic(String fileName){
        ArrayList<Scenic> list = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream("D:\\"+fileName);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row:sheet) {
                if (row.getRowNum() == 0){
                    continue;
                }
                Integer sid = (int)row.getCell(0).getNumericCellValue();
                String sname = row.getCell(1).getStringCellValue();
                Integer sindent = (int)row.getCell(2).getNumericCellValue();
                Integer scllect = (int)row.getCell(3).getNumericCellValue();
                Integer scomment = (int)row.getCell(4).getNumericCellValue();
                String scity = row.getCell(5).getStringCellValue();
                double sprice = (double) row.getCell(6).getNumericCellValue();
                String simgs = row.getCell(7).getStringCellValue();
                String stype = row.getCell(8).getStringCellValue();
                String stime = row.getCell(9).getStringCellValue();
                String sfeature = row.getCell(10).getStringCellValue();
                int sstate = (int)row.getCell(11).getNumericCellValue();
                Scenic scenic = new Scenic();
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
                list.add(scenic);
            }
            //关闭流
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
