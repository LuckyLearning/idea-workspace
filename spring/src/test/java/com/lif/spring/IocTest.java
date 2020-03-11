package com.lif.spring;

import com.lif.spring.bean.Person;
import com.lif.spring.config.MainConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @Author: lifan
 * @Date: 2020/2/19 10:56
 */
public class IocTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

    @Test
    public void testImport() {
        printBeansName(context);

        // 工厂Bean获取的是调用getObject创建的对象
        Object bean1 = context.getBean("colorFactoryBean");
        Object bean2 = context.getBean("colorFactoryBean");
        System.out.println("bean的类型：" + bean1.getClass());
        System.out.println(bean1 == bean2);
        // 获取工厂Bean本身
        Object bean3 = context.getBean("&colorFactoryBean");
        System.out.println("bean的类型：" + bean3.getClass());

    }

    private void printBeansName(ApplicationContext context) {
        String[] namesForType = context.getBeanDefinitionNames();
        for (String name : namesForType) {
            System.out.println(name);
        }
    }


    @Test
    public void test03() {
        String[] namesForType = context.getBeanNamesForType(Person.class);
        // 获取环境变量的值
        Environment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);


        for (String name : namesForType) {
            System.out.println(name);
        }
        Map<String, Person> ofType = context.getBeansOfType(Person.class);
        System.out.println(ofType);
    }

    @Test
    public void test02() {
        System.out.println("ioc容器创建完成。。。");
        context.getBean("person");
    }

    @Test
    public void test01() {
        Person bean = context.getBean(Person.class);
        String[] type = context.getBeanNamesForType(Person.class);
        System.out.println(bean);
        for (String name : type) {
            System.out.println(name);
        }
    }

}
