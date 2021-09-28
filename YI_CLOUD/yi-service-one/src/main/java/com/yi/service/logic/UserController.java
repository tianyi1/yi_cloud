package com.yi.service.logic;


import org.springframework.web.bind.annotation.*;
import sun.misc.Contended;

import javax.annotation.Resource;

@RestController
@RequestMapping("/one")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/one-service/saveUser", method = RequestMethod.POST, consumes = "application/json")
    public String saveUser(@RequestBody UserVo userVo) {
        System.out.println("2222进来了");
//        return null;
        return userService.saveUser(userVo);
    }
}
