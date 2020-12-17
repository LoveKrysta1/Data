package com.weapon.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.weapon.entity.Account;
import com.weapon.realm.AccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 搭建shiro的一個整體的體系
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /**
         * 什麼請求對應什麼樣的處理
         */
        //權限設置
        Map<String,String> map = new Hashtable<>();
        //必須認證後
        map.put("/main","authc");
        //必須擁有什麼權限
        map.put("/manager","perms[manager]");
        //必須擁有什麼角色
        map.put("/administrator","roles[administrator]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //設置登錄頁面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //設置未授權頁面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        return  shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("accountRealm") AccountRealm accountRealm ){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        return manager;
    }

    @Bean
    public AccountRealm accountRealm(){
        return new AccountRealm();
    }

    /**
     * 整合thymeleaf的配置
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }


}
