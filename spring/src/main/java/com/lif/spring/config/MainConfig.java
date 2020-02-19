package com.lif.spring.config;

import com.lif.spring.bean.Person;
import com.lif.spring.condition.LinuxCondition;
import com.lif.spring.condition.MyImportSelector;
import com.lif.spring.condition.WindowsCondition;
import com.lif.spring.testjdbc.Student;
import org.springframework.context.annotation.*;

/**
 * @Author: lifan
 * @Date: 2020/2/10 14:09
 */
@Configuration
//@ComponentScan(value = {
//        @ComponentScan(value = "com.lif.spring", includeFilters = {
//                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTyoe.class})
//        }, useDefaultFilters = false)
//})
@Import({MyType.class, MyImportSelector.class})
public class MainConfig {

    /**
     * prototype: 多实例。ioc容器创建时并不会去调用方法创建对象放在容器中，
     * 而是每次获取的时候才会调用方法创建队形。
     * singleton：单实例，默认值.ioc容器启动时会调用方法创建对象放到ioc容器中，
     * 以后每次获取都是直接从容器（map.get()）中拿。
     * request：同一次请求创建一个实例
     * session：同一个session创建一个实例
     * <p>
     * 懒加载：
     * 单实例bean，默认在容器启动的时候创建对象。
     * 懒加载：容器启动时不创建对象，第一次使用（获取）Bean时创建对象，并初始化。
     */
//    @Scope()
    @Lazy
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加Person。。。");
        return new Person("lisi", 20);
    }

    /**
     * @Conditional, 按照一定的条件进行判断，满足条件给容器中注册bean
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02() {
        return new Person("linus", 48);
    }

    /**
     * 给容器中注册组件
     * 1.包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）
     * 2.@Bean（导入第三方包里面的组件）
     * 3.@Import（快速给容器中导入一个组件）
     *          1).@Import(要导入到容器中的组件)，容器中会字段注册这个组件，id默认是全类名。
     *          2).ImportSelector：返回需要导入的组件的全类名数组。
     *          3).ImportBeanDefinitionRegistrar:
     */

}
