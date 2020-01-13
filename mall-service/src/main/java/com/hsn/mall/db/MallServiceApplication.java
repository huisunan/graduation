package com.hsn.mall.db;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huisunan
 * @date 2020/1/13 12:40
 */
@SpringBootApplication
@EnableDubboConfiguration
public class MallServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallServiceApplication.class,args);
    }
}
