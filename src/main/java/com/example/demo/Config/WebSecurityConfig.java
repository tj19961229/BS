package com.example.demo.Config;

import com.example.demo.service.impl.UserDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailInfo urluserservice;
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/loginout","/register","/registeruser","/login3","/js/**","/css/**","/images/*","/fonts/**","/**/*.png","/**/*.jpg","/**/*.css","/static/**","/**/*.ico","/webjars/**","/dist/**").permitAll()
                .antMatchers("/adduser").access("hasRole('ROLE_ADMIN')")//页面权限控制
                .antMatchers("/items").access("hasRole('ROLE_TEACHER')")//页面权限控制
                .antMatchers("/examinations").access("hasRole('ROLE_TEACHER')")//页面权限控制
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and().logout()
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/denied")
        .and().cors();
//                .and().sessionManagement().invalidSessionUrl("/login");
        http.csrf().disable();
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(urluserservice).passwordEncoder(new BCryptPasswordEncoder());
    }
}
