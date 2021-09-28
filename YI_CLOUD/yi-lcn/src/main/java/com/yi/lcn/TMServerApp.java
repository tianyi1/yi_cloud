package com.yi.lcn;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer
public class TMServerApp {
    public static void main(String[] args) {
        SpringApplication.run(TMServerApp.class, args);
    }
}
