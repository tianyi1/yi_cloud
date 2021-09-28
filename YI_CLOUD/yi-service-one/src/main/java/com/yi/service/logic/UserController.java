package com.yi.service.logic;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/yi-service-one/saveUser", method = RequestMethod.POST, consumes = "application/json")
    public String saveUser(@RequestBody UserVo userVo) {
        System.out.println("2222进来了");
//        return null;
        return userService.saveUser(userVo);
    }
}
