package com.lif.spring.condition;

import com.lif.spring.bean.Person;
import com.lif.spring.config.MyType;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: lifan
 * @Date: 2020/2/19 14:12
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry  BeanDefinition注册类；
     *                  把所有需要添加到容器中的bean，调用BeanDefinitionRegistry.registerBeanDefinition手动注册进来
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean exist = registry.containsBeanDefinition("com.lif.spring.testjdbc.Student");
        if (exist) {
            // 指定bean的定义信息：（bean的类型。。。）
            RootBeanDefinition definition = new RootBeanDefinition(MyType.class);
            // 注册一个bean，指定bean名
            registry.registerBeanDefinition("registry_mytype", definition);
        }

    }
}
