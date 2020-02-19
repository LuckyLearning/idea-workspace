package com.lif.spring;

import com.lif.spring.bean.Person;
import com.lif.spring.config.MainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//        Person bean = context.getBean(Person.class);
//        String[] type = context.getBeanNamesForType(Person.class);
//        System.out.println(bean);
//        for (String name: type) {
//            System.out.println(name);
//        }
        System.out.println("ioc容器创建完成。。。");
        context.getBean("person");
        context.getBean("person");
    }

}
