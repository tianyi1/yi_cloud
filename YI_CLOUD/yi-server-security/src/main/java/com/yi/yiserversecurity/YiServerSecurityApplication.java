package com.yi.yiserversecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//securoty注册方式
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class YiServerSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(YiServerSecurityApplication.class, args);
    }

}
