package com.lif.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @Author: lifan
 * @Date: 2019/11/4 16:19
 */
@SuppressWarnings("ConstantConditions")
@Controller
@RequestMapping("/redis")
public class RedisController {

    private final RedisTemplate<String, String> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisController(RedisTemplate<String, String> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @RequestMapping("/stringAndMap")
    @ResponseBody
    public Map<String, Object> testStrignAndMap() {
        redisTemplate.opsForValue().set("key1", "value1");
        // 默认使用的是jdk的序列化器，Redis存储时不是整数，所以不能运算
        redisTemplate.opsForValue().set("int_key", "1");
        // 使用运算
        stringRedisTemplate.opsForValue().increment("int", 1);
        // 获取底层Jedis的连接
        Jedis jedis = (Jedis) Objects.requireNonNull(stringRedisTemplate.getConnectionFactory()).getConnection().getNativeConnection();
        // 减1操作，这个命令RedisTemplate不支持，所以获取底层连接再操作
        jedis.decr("int");

        HashMap<String, String> hash = new HashMap<>();
        hash.put("field1", "values1");
        hash.put("field2", "values2");
        // 存入一个散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash", hash);
        // 新增一个字段
        stringRedisTemplate.opsForHash().put("hash", "field3", "values3");
        BoundHashOperations<String, Object, Object> hashOps = stringRedisTemplate.boundHashOps("hash");
        hashOps.delete("field1", "field2");
        hashOps.put("field4", "values4");
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/testList")
    @ResponseBody
    public String testList() {
        // 下面两个list的顺序不同
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5", "v6");
        // 绑定list2链表进行操作
        BoundListOperations<String, String> list2Ops = stringRedisTemplate.boundListOps("list2");
        // 从右边弹出一个成员
        list2Ops.rightPop();
        //获取定位元素，Redis从0开始
        String index = list2Ops.index(1);
        // 从左边插入元素
        list2Ops.leftPush("v0");
        Long size = list2Ops.size();
        List<String> range = list2Ops.range(0, size - 2);
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("index", index);
        result.put("szie", size);
        result.put("list", range);
        return result.toString();
    }

    @RequestMapping("/testSet")
    @ResponseBody
    public String testSet() {
        // 集合不允许重复，所以最后只有5个元素
        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");
        BoundSetOperations<String, String> setOps = stringRedisTemplate.boundSetOps("set1");
        setOps.add("v6", "v7");
        setOps.remove("v1", "v7");
        Set<String> members = setOps.members();
        Long size = setOps.size();
        // 求交集
        Set<String> inter = setOps.intersect("set2");
        // 求交集，并用新集合inter保存
        setOps.intersectAndStore("set2", "inter");
        // 求差集
        Set<String> diff = setOps.diff("set2");
        // 求差集，并用新集合diff保存
        setOps.diffAndStore("set2", "diff");
        // 求并集
        Set<String> union = setOps.union("set2");
        // 求并集，并用新集合union保存
        setOps.unionAndStore("set2", "union");
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("members", members);
        result.put("size", size);
        result.put("inter", inter);
        result.put("diff", diff);
        result.put("union", union);
        return result.toString();
    }

    @RequestMapping("/zset")
    @ResponseBody
    public String testZset() {
        HashSet<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for(int i = 1; i <= 9; i++) {
            // 分数
            double score = i * 0.1;
            // 创建一个TypedTuple对象，存入值和分数
            DefaultTypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }
        // 往有序集合插入元素
        stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);
        BoundZSetOperations<String, String> zSetOps = stringRedisTemplate.boundZSetOps("zset1");
        zSetOps.add("value10", 0.26);
        zSetOps.range(1, 6);
        zSetOps.rangeByScore(0.2, 0.6);
        // 定义值范围
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        // 大于value3
        range.gt("value3");
        // 大于等于value3
        range.gte("value3");
        // 小于等于value8
        range.lte("value8");
        // 按值排序，注意这个排序是按字符串排序
        zSetOps.rangeByLex(range);
        // 删除元素
        zSetOps.remove("value3", "value7");
        // 求分数
        zSetOps.score("value4");
        // 在下标区间，按分数排序，同时返回value和score
       zSetOps.rangeWithScores(1, 6);
        // 在分数区间，按分数排序，同时返回value和score
        zSetOps.rangeByScoreWithScores(0.1, 0.6);
        // 按从大到小排序
        zSetOps.reverseRange(2, 8);

        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result.toString();
    }

    /*@RequestMapping("/testMulti")
    @ResponseBody
    @RequestMapping("/testPipeline")
    @ResponseBody
    public String testPipeLine() {
        long start = System.currentTimeMillis();
        List<Object> list = redisTemplate.executePipelined((RedisOperations<String, Object> operation) -> {
            for (int i = 0; i < 100000; i++) {
                operation.opsForValue().set("pipeline_" + i, "value_" + i);
                String value = (String) operation.opsForValue().get("pipeline_" + i);
                if (i % 100000 == 0) {
                    System.out.println("命令只是进入队列，所以值为空【"+value+"】");
                }
            }
            return null;
        });

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end-start) + "毫秒");
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("list", list.toString());
        return result.toString();
    }*/

    /**
    public String testMulti() {
        redisTemplate.setValueSerializer(redisTemplate.getStringSerializer());
        redisTemplate.opsForValue().set("key1", "value1");
        List list = (List) redisTemplate.execute((RedisOperations<String, String> operations) -> {
            // 设置要监控的key1
            operations.watch("key1");
            // 开启事务，在exec命令执行前，全部都只是进入队列
            operations.multi();
            operations.opsForValue().set("key2", "value2");
            operations.opsForValue().increment("key1", 1);
            // 获取值将为null，因为Redis只是把命令放入队列
            Object value2 = operations.opsForValue().get("key2");
            System.out.println("命令在队列，所以value值为null::【" + value2 + "】");
            // 执行exec命令，将先判别keyl是否在监控后被修改过，如果是则不执行事务，否则就执行事务
            return  operations.exec();
        });
        System.out.println(list);
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result.toString();
    }

     * 测试Lua脚本
     * @param
     * @Return: java.lang.String
     * @createDate: 2019/11/18 19:08
     */

    @RequestMapping("testLua")
    @ResponseBody
    public String testLua() {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        //设置脚本
        redisScript.setScriptText("return 'hello Lua!'");
        // 定义返回类型，注意： 如果没有这个定义，Spring不会有返回值
        redisScript.setResultType(String.class);
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        // 执行Lua脚本
        String str = redisTemplate.execute(redisScript, stringSerializer, stringSerializer, null);
        return str;
    }


}
