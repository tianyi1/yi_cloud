package com.yi.yiserversecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailServiveImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(!"admin".equals(s)){
            throw  new UsernameNotFoundException("用户不存在！");
        }
        String encode = passwordEncoder.encode("123");
        //角色规则 ROLE_***
        return new User(s,encode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_abc"));
    }
}
