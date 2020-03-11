package com.lif.strategy.duck;

import com.lif.strategy.flybehavior.FlyBehavior;
import com.lif.strategy.quackbehavior.QuackBehavior;

/**
 * 策略模式下，鸭子的超类
 *
 * @Author: lifan
 * @Date: 2020/3/2 11:19
 */
public abstract class Duck {

    FlyBehavior mFlyBehavior;
    QuackBehavior mQuackBehavior;

    public Duck() {

    }

    public void fly() {
        mFlyBehavior.fly();
    }

    public void quack() {
        mQuackBehavior.quack();
    }

    /**
     * 鸭子的外观描述
     */
    public abstract void display();

    public void setQuackBehavior(QuackBehavior qb) {
        this.mQuackBehavior = qb;
    }

    public void setFlyBehavior(FlyBehavior fb) {
        this.mFlyBehavior = fb;
    }

    public void swim() {
        System.out.println("~~swin~~");
    }
}
