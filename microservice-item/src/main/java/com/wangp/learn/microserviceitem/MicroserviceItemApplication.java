package com.wangp.learn.microserviceitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MicroserviceItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceItemApplication.class, args);
    }

}
