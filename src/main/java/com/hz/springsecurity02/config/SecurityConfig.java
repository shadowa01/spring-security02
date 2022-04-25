package com.hz.springsecurity02.config;

import com.hz.springsecurity02.util.AjaxAuthenticationEntryPoint;
import com.hz.springsecurity02.util.AjaxLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;
    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //去掉CSRF
        http.csrf().disable()
        .exceptionHandling()
                .authenticationEntryPoint(ajaxAuthenticationEntryPoint).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //登录处理
                .and()
                .formLogin()
                .loginProcessingUrl("user/login")
                .permitAll()
                //登录和权限控制
                .and()
                .authorizeRequests()
                .anyRequest()
                .access("@rbacAuthorityService.hasPermission(request,authentication)")
                .and().logout()
                //默认注销行为
                .logoutUrl("/user/loginOut")
                //设置调用路径
                .logoutSuccessHandler(ajaxLogoutSuccessHandler).permitAll();

    }
}
