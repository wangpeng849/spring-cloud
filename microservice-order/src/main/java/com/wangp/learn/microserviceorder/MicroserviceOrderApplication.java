package com.wangp.learn.microserviceorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableHystrix
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.wangp.learn.microserviceorder.feign")
@ComponentScan(basePackages = {"com.wangp.learn.microserviceorder.controller", "com.wangp.learn.microserviceorder.service","com.wangp.learn.microserviceorder.properties","com.wangp.learn.microserviceorder.feign"})
public class MicroserviceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceOrderApplication.class, args);
    }

    /**
     * 向Spring容器中定义RestTemplate对象
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}
