package com.example.springbootshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;


@Configuration
public class ShiroConfig {
    @Autowired
    private UserRealm userRealm;
    @Autowired
    private DefaultSecurityManager securityManager;

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        //设置权限过滤器
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        // anon 无需认证
        //  authc 认证之后才能访问
        //  user 记住我之后才能访问
        //  perms 拥有某个资源的权限才能访问
        //   role 拥有某个角色权限才能访问
        //授权
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        filterMap.put("/user/*", "perms[user:*]");
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登陆页
        bean.setLoginUrl("/toLogin");
        //设置未授权页面
        bean.setUnauthorizedUrl("/auth");
        return bean;
    }

    //DefaultWebSecurityManager 对象管理者
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
