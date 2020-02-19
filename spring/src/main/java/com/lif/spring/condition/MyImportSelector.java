package com.lif.spring.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑，返回需要导入的组件
 * @Author: lifan
 * @Date: 2020/2/19 13:57
 */
public class MyImportSelector implements ImportSelector {

    /**
     * @param importingClassMetadata    当前标注@Import注解的类的所有注解信息。
     * @return  就是要导入到容器中的组件的全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("--->?");
        return new String[]{"com.lif.spring.testjdbc.Student"};
    }
}
