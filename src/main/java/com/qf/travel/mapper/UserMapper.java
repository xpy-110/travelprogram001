package com.qf.travel.mapper;

import com.qf.travel.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * 通过登录名查询用户信息
     * @param uname 登录名
     * @return
     */
    public User findUserInfoByUname(@Param("uname") String uname);
    /**
     * 通过已知输入的用户信息判断是否输入正确
     * @param user
     * @return
     */
    public User findUser(User user);

    /**
     * 查询用户角色
     * @return
     */
    public List<Integer> findUR(int uid);
    //根据角色id获取所有用户的信息
    List<User> loadUserId(int rid);
    //根据id查询用户
    User getUserById(int uid);
    //修改用户
    int updateUser(@Param("user")User user);
    //增加用户
    int saveUser(@Param("user")User user);
    int saveRoleUser(Map<String,Integer> map);
    int getUidByName(String uname);
    //根据id删除用户
    int deleteUser(List<Integer> list);
    int deleteUserRole(List<Integer> list);
    //获得当前查询类型的用户的数量
    int getUserCount(int rid);

    /**
     * 根据姓名查询user
     * @param uname
     * @return
     */
    public User getUserByName(String uname);

    /**
     * tel email
     * @param tel
     * @return
     */
    public User getUserByTel(String tel);
    public User getUserByEmail(String email);

    /**
     * 注册
     * @param user
     * @return
     */
    public int save(User user);
    /*模糊查询*/
    public List<User> inquireUser(Map<String,Object> map);
}
