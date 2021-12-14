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
        return "redirect:main.html";
    }

    @RequestMapping("/toFail")
    public String toFail(){
        return "redirect:error.html";
    }

    @RequestMapping("/demo")
    public String demo(){
        return "thymeleaf";
    }

}
