package com.qf.travel;

import com.qf.travel.mapper.IndentMapper;
import com.qf.travel.mapper.ScenicMapper;
import com.qf.travel.mapper.UserMapper;
import com.qf.travel.pojo.Indent;
import com.qf.travel.pojo.Scenic;
import com.qf.travel.pojo.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelprogramApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScenicMapper scenicMapper;
    @Autowired
    private IndentMapper indentMapper;
    @Test
    public void selectUserTest(){
        User user = userMapper.getUserById(1);
        System.out.println(user);
    }
    @Test
    public void load(){
        List<Scenic> scenics = scenicMapper.getScenicByType("景点");
        System.out.println(scenics);
    }
    @Test
    public void getuser(){
        Map<String,Object> map = new HashMap<>();
        map.put("rid",3);
        map.put("uuu","1");
        List<User> users = userMapper.inquireUser(map);
        System.out.println(users);
    }

   /*
   @Test
   public void loadall(){
        List<Indent> indents = indentMapper.findIndent("test");
        System.out.println(indents);
    }*/
    @Test
    public void queryAll(){
        List<Indent> indents = indentMapper.queryAll("北京");
        System.out.println(indents);
    }
    @Test
    public void queryAllByUname(){
        Map<String,String> map = new HashMap<>();
        map.put("uname","test");
        map.put("uuu","路线");
        List<Indent> indents = indentMapper.queryAllByUname(map);
        System.out.println(indents);
    }
    @Test
    public void jiami(){
        //75472477050d921dea5e7d0d1edadd28
        //75472477050d921dea5e7d0d1edadd28
        String s = "123456";
        String salt = "travel";
        Md5Hash md5Hash = new Md5Hash(s,salt,1024);
        System.out.println(md5Hash.toHex());
    }
    @Test
    public void simpljiami(){
        //75472477050d921dea5e7d0d1edadd28
        //75472477050d921dea5e7d0d1edadd28
        //75472477050d921dea5e7d0d1edadd28
        String type = "MD5";
        String s = "test";
        String salt = "abc";
        int d = 1024;
        SimpleHash simpleHash = new SimpleHash(type,s,salt,d);
        System.out.println(simpleHash.toHex());
        System.out.println(simpleHash);
    }
}
