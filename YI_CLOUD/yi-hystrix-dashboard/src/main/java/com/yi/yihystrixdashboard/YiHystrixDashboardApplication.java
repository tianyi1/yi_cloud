package com.yi.yihystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard  // 表示开启HystrixDashboard
public class YiHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiHystrixDashboardApplication.class, args);
    }

}
