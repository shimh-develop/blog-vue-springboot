package com.blog.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 短信微服务的启动类
 */
@SpringBootApplication

public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }

    /**
     * 初始化SmsUtil
     */
    @Bean
    public SmsUtil smsUtil(){
        return new SmsUtil();
    }
}
