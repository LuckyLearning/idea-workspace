package strategy;

import com.lif.strategy.duck.Duck;
import com.lif.strategy.duck.GreenHeadDuck;
import com.lif.strategy.duck.RedHeadDuck;
import com.lif.strategy.flybehavior.NoFlyBehavior;
import com.lif.strategy.quackbehavior.NoQuackBehavior;
import org.junit.Test;

import java.util.*;

/**
 * 测试策略模式
 *
 * @Author: lifan
 * @Date: 2020/3/2 11:42
 */
public class TestDuck {

    @Test
    public void test01() {
        Duck greenHeadDuck = new GreenHeadDuck();
        Duck redHeadDuck = new RedHeadDuck();

        greenHeadDuck.display();
        greenHeadDuck.fly();
        greenHeadDuck.quack();
        greenHeadDuck.swim();

        redHeadDuck.display();
        redHeadDuck.fly();
        redHeadDuck.quack();
        redHeadDuck.swim();
        System.out.println("---------");
        redHeadDuck.setFlyBehavior(new NoFlyBehavior());
        redHeadDuck.fly();
        redHeadDuck.setQuackBehavior(new NoQuackBehavior());
        redHeadDuck.quack();
    }

    @Test
    public void testnum() {
        ArrayList<List<String>> lists = getLists();
        long start = System.currentTimeMillis();
        HashMap<String, Integer> map = getMap(lists);
        //输出
        outPln(lists, map);
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end-start));

    }

    private HashMap<String, Integer> getMap(ArrayList<List<String>> lists) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (List<String> list : lists) {
            for (int i = 0; i < list.size(); i++) {
                String num = list.get(i);
                if (map.containsKey(num)) {
                    int count = map.get(num);
                    map.put(num, ++count);
                } else {
                    map.put(num, 1);
                }
            }
        }
        return map;
    }

    private void outPln(ArrayList<List<String>> lists, HashMap<String, Integer> map) {
        System.out.println(map);
        int maxCount = lists.size();
        System.out.println("maxCunt：" + maxCount);
        System.out.println("交集：");
        ArrayList<String> outArr = new ArrayList<String>();
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            int count = map.get(key);
            if (count == maxCount) {
                outArr.add(key);
            }
        }
        if (outArr.isEmpty()) {
            System.out.println("没有交集");
        } else {
            System.out.println(outArr);
        }
    }

    private ArrayList<List<String>> getLists() {
        //生成10个随机id
        ArrayList<String> idArr = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            idArr.add(UUID.randomUUID().toString().replace("-", ""));
        }
        ArrayList<List<String>> lists = new ArrayList<List<String>>();
        for (int i = 0; i < 10; i++) {
            List<String> list = getRandomList(idArr);
            lists.add(list);
        }
        return lists;
    }

    private List<String> getRandomList(ArrayList<String> idArr) {
        ArrayList<String> list = new ArrayList<String>();
        Random r = new Random();
        while (list.size() < 9) {
            int nextInt = r.nextInt(10);
            String id = idArr.get(nextInt);
            if (!list.contains(id)) {
                list.add(id);
            }
        }
        System.out.println(list);
        return list;
    }
}
