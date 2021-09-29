package com.yi.service;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDistributedTransaction
@EnableSwagger2Doc
public class ServiceOne {
    public static void main(String[] args) {
        SpringApplication.run( ServiceOne.class, args );
    }
}
