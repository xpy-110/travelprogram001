package com.qf.travel.utils;

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
                user.setUid(uid);
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
}
