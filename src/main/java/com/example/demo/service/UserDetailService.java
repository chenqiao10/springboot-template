package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.model.Visit;

@Service
public class UserDetailService implements UserDetailsService {
 
    @Autowired
    private AdminDao userDetailsDao;
 
    /**
     * 获取所属角色
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 System.out.println(username);
        //查出用户名、密码、角色信息
       Visit users = userDetailsDao.findByUserName(username);
 
        if (users==null) {
            throw new UsernameNotFoundException("找不到该账户信息！");
        }
 
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();   //GrantedAuthority是security提供的权限类，
        list.add(new SimpleGrantedAuthority("ROLE_"+users.getRoles()));
 
        User auth_user = new User(users.getUsername(), users.getPassword(), list);
 System.out.println(auth_user.toString());
          
        return auth_user;
 
    }
 
}
 