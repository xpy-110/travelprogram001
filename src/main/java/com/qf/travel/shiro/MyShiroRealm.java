package com.qf.travel.shiro;


import com.qf.travel.pojo.Permission;
import com.qf.travel.pojo.User;
import com.qf.travel.service.UserService;
import com.qf.travel.service.impl.PermissionServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义安全策略
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Resource
    private PermissionServiceImpl permissionService;

    //系统的授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject= SecurityUtils.getSubject();//主体对象
        String uname=(String)subject.getPrincipal();//获取用户身份信息

        List<Permission> permissions=permissionService.findPermissionByUname(uname);//根据用户名获取权限信息
        //权限去重
        System.out.println(permissions);
        Set<String> pers=new HashSet<>();
        for(Permission Permission:permissions){
            pers.add(Permission.getMname());
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(pers);//授权

        return simpleAuthorizationInfo;
    }
    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=(String)authenticationToken.getPrincipal();//获取用户信息
        //根据用户信息查询数据库获取后端的用户身份，转交到secrityManager判断
        User user=userService.findUserByUname(username);
        if(user!=null){
            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(user.getUname(),user.getUpwd(),getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}

