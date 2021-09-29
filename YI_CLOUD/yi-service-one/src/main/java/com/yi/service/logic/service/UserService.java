package com.yi.service.logic.service;


import com.yi.service.logic.entity.UserVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String saveUser(UserVo userVo);
}
