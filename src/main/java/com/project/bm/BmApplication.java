package com.project.bm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author :LX
 * @CreateTime :2020/5/11
 * @Description :
 */
//排除security认证的自动装配
//@SpringBootApplication(exclude ={SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@SpringBootApplication(exclude={org.activiti.spring.boot.SecurityAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableTransactionManagement //开启事务
public class BmApplication {
    public static void main(String[] args) {
        SpringApplication.run(BmApplication.class, args);
    }
}
