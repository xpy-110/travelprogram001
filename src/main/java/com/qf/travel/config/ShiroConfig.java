package com.qf.travel.config;

import com.qf.travel.shiro.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
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
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
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
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("myRealm")MyShiroRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }
    @Bean("myRealm")
    public MyShiroRealm myRealm(@Qualifier("matcher")HashedCredentialsMatcher matcher){
        MyShiroRealm myRealm = new MyShiroRealm();
        myRealm.setCredentialsMatcher(matcher);
        myRealm.setAuthorizationCachingEnabled(false);
        return myRealm;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);//设置代理的模式为cglib proxy/jdk proxy
        return advisorAutoProxyCreator;
    }
    //设置注解拦截源码中的权限设定
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") DefaultWebSecurityManager webSecurityManager
    ){
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(webSecurityManager);
        return sourceAdvisor;
    }
    @Bean("matcher")
    public HashedCredentialsMatcher matcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setStoredCredentialsHexEncoded(true);
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(1024);
        return matcher;
    }
}

