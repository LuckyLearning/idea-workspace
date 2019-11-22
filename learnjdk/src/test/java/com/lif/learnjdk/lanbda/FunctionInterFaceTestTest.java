package com.lif.learnjdk.lanbda;

import com.lif.learnjdk.lanbda.inter.FunctionInterface;
import com.lif.learnjdk.lanbda.inter.InterfaceParam;
import com.lif.learnjdk.lanbda.inter.InterfaceReturn;
import org.junit.Test;

import static org.junit.Assert.*;

public class FunctionInterFaceTestTest {

    @Test
    public void testLanbda() {
        func(() -> System.out.println("Hello Lambda test"));
        func1((name) -> System.out.println("Hello Lambda test" + name));
        boolean func2 = func2((name) -> {
            System.out.println("::" + "a".equals(name));
            return "a".equals(name);
        });
        System.out.println("===>" + func2);
    }

    private void func(FunctionInterface functionInterface) {
        functionInterface.test();
    }

    private void func1(InterfaceParam interfaceParam) {
        String name = "a";
        interfaceParam.test(name);
    }

    private boolean func2(InterfaceReturn interfaceReturn) {
        String name = "a";
        interfaceReturn.test(name);
        return false;
    }
}