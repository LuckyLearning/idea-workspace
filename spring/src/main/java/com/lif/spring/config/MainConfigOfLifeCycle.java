package com.lif.spring.config;

import com.lif.spring.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * bean的生命周期
 *      bean创建---初始化---销毁的过程
 * 容器管理bean的生命周期：
 * 我们可以自定义初始化和销毁方法：容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 构建（对象创建）
 *      单实例：在容器启动的时候创建对象
 *      多实例：在每次获取的时候创建对象
 * 初始化：
 *      对象创建创建完成，并赋值好，调用初始化方法。。。
 * 销毁：
 *      单实例：容器关闭的时候
 *      多实例：容器不会管理这个bean，容器不会调用销毁方法
 *
 * 1.指定初始化和销毁方法
 *      通过@Bean指定init-method和destory-method
 * 2.通过让Bean实现InitializingBean（定义初始化逻辑）；
 *      DisposableBean（销毁逻辑）
 * @Author: lifan
 * @Date: 2020/2/20 11:10
 */
@Configuration
public class MainConfigOfLifeCycle {

    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destory")
    public Car car() {
        return new Car();
    }
}
