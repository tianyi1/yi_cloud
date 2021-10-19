package com.yi.yiserversecurity.contract;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Secured("ROLE_abc")
    @RequestMapping("/toMain")
    public String toMain(){
        System.out.println("进来了吗？");
        return "redirect:main.html";
    }

    @RequestMapping("/toFail")
    public String toFail(){
        return "redirect:error.html";
    }

    @RequestMapping("/demo")
    @ResponseBody
    public String demo(){
        return "demo";
    }

}
