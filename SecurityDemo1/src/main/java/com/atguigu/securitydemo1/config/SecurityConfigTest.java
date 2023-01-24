package com.atguigu.securitydemo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }
    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()   //自定义自己编写的登录页面
                .loginPage("/login.html")  //登录页面设置
                .loginProcessingUrl("/user/login")   //登录访问路径
                .defaultSuccessUrl("/test/index").permitAll()  //登录成功之后，跳转路径
                .and().authorizeRequests()
                    .antMatchers("/","/test/hello","/user/login").permitAll() //设置哪些路径可以直接访问，不需要认证
                .anyRequest().authenticated() // 除上面的多有请求全部需要健全认真
                .and().csrf().disable();  //关闭csrf防护
    }

}
