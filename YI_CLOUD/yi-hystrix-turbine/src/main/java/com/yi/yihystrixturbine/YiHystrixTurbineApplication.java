package com.yi.yihystrixturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine  // 开启Hystrix Turbine
public class YiHystrixTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiHystrixTurbineApplication.class, args);
    }

}
