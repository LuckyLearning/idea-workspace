package com.lif.strategy.duck;

import com.lif.strategy.flybehavior.GoodFlyBehavior;
import com.lif.strategy.quackbehavior.GaGaQuackBehavior;

/**
 * @Author: lifan
 * @Date: 2020/3/2 11:36
 */
public class GreenHeadDuck extends Duck {

    public GreenHeadDuck() {
        mFlyBehavior = new GoodFlyBehavior();
        mQuackBehavior = new GaGaQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("**GreenHead**");
    }
}
