package com.yi.yiserverconfig.contract;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping(value = "/query")
    public void test() {
        System.out.println("222222222");
    }
}
