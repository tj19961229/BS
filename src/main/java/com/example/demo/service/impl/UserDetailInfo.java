package com.example.demo.service.impl;

import com.example.demo.entity.Sysuser;
import com.example.demo.mapper.SysuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailInfo implements UserDetailsService {
    @Autowired
    private SysuserService sysuserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Sysuser sysuser=sysuserService.User_select(username);
        if(sysuser==null)
        {
               System.out.println("user null");
               throw new UsernameNotFoundException("用户不存在");
        }

        System.out.println(sysuser.getUsername());
        System.out.println(sysuser.getPassword());
        System.out.println(sysuser.getName());
        System.out.println(sysuser.getBJ());
        System.out.println(sysuser.getRoles());
        UserDetails	user = new User(sysuser.getUsername(),sysuser.getPassword(),getAuthorities(sysuser.getRoles()));
        return user;
    }
    public Collection<GrantedAuthority> getAuthorities(Integer access){
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            if(access.compareTo(2)==1){
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            if(access.compareTo(1)==1){
                authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
            }
            return authorities;
    }
}
