package com.yi.service.logic.controller;


import com.yi.service.logic.entity.UserVo;
import com.yi.service.logic.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/one")
public class UserController {
    @Resource
    private UserService userService;
    @ApiOperation(value = "新增用户")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK", response = UserVo.class) })
    @RequestMapping(value = "/one-service/saveUser", method = RequestMethod.POST, consumes = "application/json")
    public String saveUser(@RequestBody UserVo userVo) {
        System.out.println("2222进来了");
//        return null;
        return userService.saveUser(userVo);
    }

    @ApiOperation(value = "新增用户")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK", response = UserVo.class) })
    @RequestMapping(value = "/one-service/queryUser", method = RequestMethod.POST, consumes = "application/json")
    public String queryUser(@RequestBody UserVo userVo) {
        System.out.println("222222222");
        return userService.queryUser(userVo);
    }
}
