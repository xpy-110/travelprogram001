package com.qf.travel.config;

import com.qf.travel.shiro.MyShiroRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashMap;
import java.util.Map;

/**
 * 自定义shiro配置管理
 */
@Configuration
public class ShiroConfig {
    //创建安全过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给过滤器装配安全策略
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String ,String> map  = new HashMap<>();
        //定义过滤规则
        map.put("/main","authc");//需要登录之后才可访问的资源

        map.put("/adminis","authc");
        // map.put("/one","perms[user_edit]");//需要登录后，且拥有user/edit权限的账户才可访问
        // map.put("/two","perms[user_forbidden]");//需要登录后，且拥有user/edit权限的账户才可访问

//        被aop注解替代
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);//需要过滤连的定义
        shiroFilterFactoryBean.setLoginUrl("/login");//设置默认的登录页，
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");//权限不足时显示的页面
        return shiroFilterFactoryBean;
    }
    //    配置安全管理器（注入Realm对象）
    @Bean(name="defaultWebSecurityManager")
    public DefaultWebSecurityManager  defaultWebSecurityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //组装realm到securityManager中
        defaultWebSecurityManager.setRealm(myShiroRealm);
        return defaultWebSecurityManager;
    }

    @Bean(name="myShiroRealm")  //使用该注解是的Realm对象由spring容器管理
    public MyShiroRealm  myShiroRealm(){
        MyShiroRealm shiroRealm = new MyShiroRealm();
        return shiroRealm;
    }

    //    通过aop代理拦截权限设定
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);//设置代理的模式为cglib proxy/jdk proxy
        return advisorAutoProxyCreator;
    }
    //设置注解拦截源码中的权限设定
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager webSecurityManager
    ){
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(webSecurityManager);
        return sourceAdvisor;
    }

}

