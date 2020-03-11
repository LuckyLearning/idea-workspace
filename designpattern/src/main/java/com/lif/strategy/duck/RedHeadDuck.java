package com.lif.strategy.duck;

import com.lif.strategy.flybehavior.BadFlyBehavior;
import com.lif.strategy.quackbehavior.GeGeQuackBehavior;

/**
 * @Author: lifan
 * @Date: 2020/3/2 11:40
 */
public class RedHeadDuck extends Duck {

    public RedHeadDuck() {
        mFlyBehavior = new BadFlyBehavior();
        mQuackBehavior = new GeGeQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("**RedHead**");
    }
}
