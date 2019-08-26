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

import java.util.List;

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
    public void save(){
        Scenic scenic = new Scenic();
        scenic.setSname("一家");
        scenic.setSindent(2323);
        scenic.setScllect(213);
        scenic.setScomment(32);
        scenic.setScity("西安");
        scenic.setSprice(32);
        scenic.setSimgs("1212");
        scenic.setStype("酒店");
        scenic.setStime("122121");
        scenic.setSfeature("212121");
        scenic.setSstate(1);
        int c = scenicMapper.saveSecien(scenic);
        System.out.println(c);
    }

}
