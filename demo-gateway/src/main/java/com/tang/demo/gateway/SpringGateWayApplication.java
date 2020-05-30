package com.tang.demo.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Classname SpringGateWayApplication
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/5/24 22:12
 * @Created by ASUS
 */
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.tang.demo.gateway.mapper"})
@SpringBootApplication
public class SpringGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringGateWayApplication.class, args);
    }
}