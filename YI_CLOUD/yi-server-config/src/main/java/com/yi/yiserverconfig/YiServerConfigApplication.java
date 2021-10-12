package com.yi.yiserverconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class YiServerConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiServerConfigApplication.class, args);
    }

}
