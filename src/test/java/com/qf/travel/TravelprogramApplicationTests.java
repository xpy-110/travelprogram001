package com.qf.travel;

import com.qf.travel.mapper.ScenicMapper;
import com.qf.travel.mapper.UserMapper;
import com.qf.travel.pojo.Scenic;
import com.qf.travel.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelprogramApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScenicMapper scenicMapper;
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
}
