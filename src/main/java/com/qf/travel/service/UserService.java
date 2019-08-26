package com.qf.travel.service;

import com.qf.travel.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 根据用户名查用户信息
     * @param uname
     * @return
     */
    public User findUserByUname(String uname);

    /**
     * 通过已知输入的用户信息判断是否输入正确
     * @param user
     * @return
     */
    public User findUser(User user);
    /**
     * 查询用户角色表
     * @return
     */
    public int findUR(int uid);
    //获取所有会员的信息
    List<User> loadUserId(int rid,int page,int rows);
    //根据id查询用户
    User getUserById(int uid);
    //修改用户
    boolean updateUser(User user);
    //增加用户
    boolean saveUser(User user,int rid);
    //根据id删除用户
    boolean deleteUser(List<Integer> list);
    //获取最大页
    int getMaxPage(int rows,int rid);

    /**
     * 注册界面判定名字、手机号、email是否重复
     * @param uname
     * @return
     */
    public boolean getUserByName(String uname);
    public boolean getUserByTel(String tel);
    public boolean getUserByEmail(String email);

    /**
     * 注册保存
     * @param user
     * @return
     */
    public boolean save(User user);
    /*模糊查询*/
    public List<User> inquireUser(int rid,String uuu);
}
