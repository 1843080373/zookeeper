package org.microservicecloud.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class ZuulStartup extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(ZuulStartup.class);

    public static void main(String[] args) {

        SpringApplication.run(ZuulStartup.class, args);
        logger.info("Application Start Success!");
    }
    
    // 当SpringBoot项目打成war包发布时,需要继承SpringBootServletInitializer接口实现该方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder  builder) {
        return builder.sources(ZuulStartup.class);
    }

}
