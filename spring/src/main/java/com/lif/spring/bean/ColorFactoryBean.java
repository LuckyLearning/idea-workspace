package com.lif.spring.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: lifan
 * @Date: 2020/2/20 10:39
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    @Override
    public Color getObject() throws Exception {
        System.out.println();
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 是否单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
