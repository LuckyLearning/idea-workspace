package com.lif.spring;

import com.lif.spring.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: lifan
 * @Date: 2020/2/20 11:16
 */
public class IocTest_LifeCycle {

    @Test
    public void test01() {
        // 1.创建ioc容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成。。。");

        context.getBean("car");
        // 容器关闭
        context.close();
    }
}
