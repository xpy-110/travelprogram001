package com.qf.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.travel.mapper.UserMapper;
import com.qf.travel.pojo.User;
import com.qf.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    /**
     * 根据用户名查用户信息
     * @param uname
     * @return
     */
    @Override
    public User findUserByUname(String uname) {
        System.out.println(uname);
        User userInfoByUname = userMapper.findUserInfoByUname(uname);
        System.out.println(userInfoByUname);
        return userInfoByUname;
    }
    /**
     * 通过已知输入的用户信息判断是否输入正确
     * @param user
     * @return
     */
    @Override
    public User findUser(User user) {
        System.out.println(user);
        User user1 = userMapper.findUser(user);
        return user1;
    }

    /**
     * 查询用户角色表
     * @return
     */
    @Override
    public List<Integer> findUR(int uid) {
        List<Integer> ur = userMapper.findUR(uid);
        return ur;
    }
    @Override
    public List<User> loadUserId(int rid,int page,int rows) {
        PageHelper.startPage(page,rows);
        return userMapper.loadUserId(rid);
    }

    @Override
    public User getUserById(int uid) {
        return userMapper.getUserById(uid);
    }

    @Override
    public boolean updateUser(User user) {
        int c = userMapper.updateUser(user);
        return c>0?true:false;
    }

    @Override
    public boolean saveUser(User user,int rid) {
        int c = userMapper.saveUser(user);
        boolean bool = c>0?true:false;

        if (bool){
            int uid = userMapper.getUidByName(user.getUname());
            Map<String,Integer> map = new HashMap<>();
            map.put("rid",rid);
            map.put("uid",uid);
            int n = userMapper.saveRoleUser(map);
            bool = n>0?true:false;
        }
        return bool;
    }

    @Override
    public boolean deleteUser(List<Integer> list) {
        int a = userMapper.deleteUserRole(list);
        boolean bool = a>0?true:false;
        if (bool){
            int c = userMapper.deleteUser(list);
            bool = c>0?true:false;
        }
        return bool;
    }

    @Override
    public int getMaxPage(int rows,int rid) {
        int c = userMapper.getUserCount(rid);
        int MaxPage = c%rows==0?c/rows:c/rows+1;
        return MaxPage;
    }

    @Override
    public boolean getUserByName(String uname) {
        System.out.println("uname = " + uname);
        User user = userMapper.getUserByName(uname);
        System.out.println("user = " + user);
        return user!=null?true:false;
    }

    @Override
    public boolean getUserByTel(String tel) {
        User user = userMapper.getUserByTel(tel);
        return user!=null?true:false;
    }

    @Override
    public boolean getUserByEmail(String email) {
        User user = userMapper.getUserByEmail(email);
        return user!=null?true:false;
    }

    @Override
    public boolean save(User user) {
        System.out.println("user = " + user);
        int count = userMapper.save(user);
        return count>0?true:false;
    }
    /*模糊查询*/
    public List<User> inquireUser(int rid,String uuu){
        Map<String,Object> map = new HashMap<>();
        map.put("rid",rid);
        map.put("uuu",uuu);
        return userMapper.inquireUser(map);
    }
}
