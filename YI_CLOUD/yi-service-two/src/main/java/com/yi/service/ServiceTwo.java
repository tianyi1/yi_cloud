package com.yi.service;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDistributedTransaction
@EnableFeignClients
@EnableCircuitBreaker  //用来开启断路器
public class ServiceTwo {
    public static void main(String[] args) {
        SpringApplication.run( ServiceTwo.class, args );
    }
}
