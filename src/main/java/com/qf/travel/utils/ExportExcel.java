package com.qf.travel.utils;

import com.qf.travel.pojo.Indent;
import com.qf.travel.pojo.Scenic;
import com.qf.travel.pojo.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//导出excel
public class ExportExcel {
    public static boolean exportUser(List<User> list,String fileName){
        boolean bool = false;
        //创建HHSWorkBook对象（Excel文档对象）
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建excel表
        HSSFSheet sheet = hssfWorkbook.createSheet();
        //在当前表建行，第一行也就是rowNum为0的行一般为表头行
        HSSFRow titleRow = sheet.createRow(0);
        //编辑表头
        //给当前行titleRow创建单元格，并使用setCellValue方法赋值
        titleRow.createCell(0).setCellValue("用户id");
        titleRow.createCell(1).setCellValue("账号");
        titleRow.createCell(2).setCellValue("用户密码");
        titleRow.createCell(3).setCellValue("用户邮箱");
        titleRow.createCell(4).setCellValue("真实姓名");
        titleRow.createCell(5).setCellValue("电话");
        titleRow.createCell(6).setCellValue("性别");
        titleRow.createCell(7).setCellValue("生日");
        titleRow.createCell(8).setCellValue("注册时间");
        for (User u:list) {
            //获取当前最大行
            int maxRow = sheet.getLastRowNum();
            //新创建一行操作
            HSSFRow dataRow = sheet.createRow(maxRow+1);
            dataRow.createCell(0).setCellValue(u.getUid());
            dataRow.createCell(1).setCellValue(u.getUname()==null?"":u.getUname());
            dataRow.createCell(2).setCellValue(u.getUpwd()==null?"":u.getUpwd());
            dataRow.createCell(3).setCellValue(u.getEmail()==null?"":u.getEmail());
            dataRow.createCell(4).setCellValue(u.getRealname()==null?"":u.getRealname());
            dataRow.createCell(5).setCellValue(u.getTel()==null?"":u.getTel());
            dataRow.createCell(6).setCellValue(u.getSex()==null?"":u.getSex());
            dataRow.createCell(7).setCellValue(u.getBirth()==null?"":u.getBirth());
            dataRow.createCell(8).setCellValue(u.getCreatetime()==null?"":u.getCreatetime());
        }
        try {
            FileOutputStream outputStream = new FileOutputStream("D:\\"+fileName);
            hssfWorkbook.write(outputStream);
            hssfWorkbook.close();
            bool = true;
            return bool;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }
    public static boolean exportIndent(List<Indent> list, String fileName){
        boolean bool = false;
        //创建HHSWorkBook对象（Excel文档对象）
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建excel表
        HSSFSheet sheet = hssfWorkbook.createSheet();
        //在当前表建行，第一行也就是rowNum为0的行一般为表头行
        HSSFRow titleRow = sheet.createRow(0);
        //编辑表头
        //给当前行titleRow创建单元格，并使用setCellValue方法赋值
        titleRow.createCell(0).setCellValue("订单id");
        titleRow.createCell(1).setCellValue("sid");
        titleRow.createCell(2).setCellValue("用户名");
        titleRow.createCell(3).setCellValue("数量");
        titleRow.createCell(4).setCellValue("价格");
        titleRow.createCell(5).setCellValue("时间");
        titleRow.createCell(6).setCellValue("状态");
        for (Indent i:list) {
            //获取当前最大行
            int maxRow = sheet.getLastRowNum();
            //新创建一行操作
            HSSFRow dataRow = sheet.createRow(maxRow + 1);
            dataRow.createCell(0).setCellValue(i.getId());
            dataRow.createCell(1).setCellValue(i.getSid());
            dataRow.createCell(2).setCellValue(i.getUname() == null ? "" : i.getUname());
            dataRow.createCell(3).setCellValue(i.getIcount());
            dataRow.createCell(4).setCellValue(i.getIprice());
            dataRow.createCell(5).setCellValue(i.getItime() == null ? "" : i.getItime());
            dataRow.createCell(6).setCellValue(i.getIstate() == null ? "" : i.getIstate());
            try {
                FileOutputStream outputStream = new FileOutputStream("D:\\" + fileName);
                hssfWorkbook.write(outputStream);
                hssfWorkbook.close();
                bool = true;
                return bool;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }
    public static boolean exportScenic(List<Scenic> list, String fileName){
        boolean bool = false;
        //创建HHSWorkBook对象（Excel文档对象）
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建excel表
        HSSFSheet sheet = hssfWorkbook.createSheet();
        //在当前表建行，第一行也就是rowNum为0的行一般为表头行
        HSSFRow titleRow = sheet.createRow(0);
        //编辑表头
        //给当前行titleRow创建单元格，并使用setCellValue方法赋值
        titleRow.createCell(0).setCellValue("旅游id");
        titleRow.createCell(1).setCellValue("名称");
        titleRow.createCell(2).setCellValue("订单数");
        titleRow.createCell(3).setCellValue("收藏数");
        titleRow.createCell(4).setCellValue("评论次数");
        titleRow.createCell(5).setCellValue("所属城市");
        titleRow.createCell(6).setCellValue("价格");
        titleRow.createCell(7).setCellValue("图片简介");
        titleRow.createCell(8).setCellValue("数据类型");
        titleRow.createCell(9).setCellValue("信息时间");
        titleRow.createCell(10).setCellValue("特色");
        titleRow.createCell(11).setCellValue("审核状态");
        for (Scenic s:list) {
            //获取当前最大行
            int maxRow = sheet.getLastRowNum();
            //新创建一行操作
            HSSFRow dataRow = sheet.createRow(maxRow + 1);
            dataRow.createCell(0).setCellValue(s.getSid());
            dataRow.createCell(1).setCellValue(s.getSname() == null ? "" : s.getSname());
            dataRow.createCell(2).setCellValue(s.getSindent());
            dataRow.createCell(3).setCellValue(s.getScllect());
            dataRow.createCell(4).setCellValue(s.getScomment());
            dataRow.createCell(5).setCellValue(s.getScity() == null ? "" : s.getScity());
            dataRow.createCell(6).setCellValue(s.getSprice());
            dataRow.createCell(7).setCellValue(s.getSimgs() == null ? "" : s.getSimgs());
            dataRow.createCell(8).setCellValue(s.getStype() == null ? "" : s.getStype());
            dataRow.createCell(9).setCellValue(s.getStime() == null ? "" : s.getStime());
            dataRow.createCell(10).setCellValue(s.getSfeature() == null ? "" : s.getSfeature());
            dataRow.createCell(11).setCellValue(s.getSstate());
            try {
                FileOutputStream outputStream = new FileOutputStream("D:\\" + fileName);
                hssfWorkbook.write(outputStream);
                hssfWorkbook.close();
                bool = true;
                return bool;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }
}
