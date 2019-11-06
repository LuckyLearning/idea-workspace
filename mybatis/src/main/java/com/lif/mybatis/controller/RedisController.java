package com.lif.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @Author: lifan
 * @Date: 2019/11/4 16:19
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/stringAndMap")
    @ResponseBody
    public Map<String, Object> testStrignAndMap() {
        redisTemplate.opsForValue().set("key1", "value1");
        // 默认使用的是jdk的序列化器，Redis存储时不是整数，所以不能运算
        redisTemplate.opsForValue().set("int_key", "1");
        // 使用运算
        stringRedisTemplate.opsForValue().increment("int", 1);
        // 获取底层Jedis的连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        // 减1操作，这个命令RedisTemplate不支持，所以获取底层连接再操作
        jedis.decr("int");

        HashMap<String, String> hash = new HashMap<>();
        hash.put("field1", "values1");
        hash.put("field2", "values2");
        // 存入一个散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash", hash);
        // 新增一个字段
        stringRedisTemplate.opsForHash().put("hash", "field3", "values3");
        BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
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
        Set<String> setRange = zSetOps.range(1, 6);
        Set<String> setScore = zSetOps.rangeByScore(0.2, 0.6);
        // 定义值范围
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        // 大于value3
        RedisZSetCommands.Range gtValue3 = range.gt("value3");
        // 大于等于value3
        RedisZSetCommands.Range gteValue3 = range.gte("value3");
        // 小于等于value8
        RedisZSetCommands.Range ltValue8 = range.lte("value8");
        // 按值排序，注意这个排序是按字符串排序
        Set<String> setLex = zSetOps.rangeByLex(range);
        // 删除元素
        zSetOps.remove("value3", "value7");
        // 求分数
        Double value4 = zSetOps.score("value4");
        // 在下标区间，按分数排序，同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> rangSet = zSetOps.rangeWithScores(1, 6);
        // 在分数区间，按分数排序，同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> sourceSet = zSetOps.rangeByScoreWithScores(0.1, 0.6);
        // 按从大到小排序
        Set<String> reverseRange = zSetOps.reverseRange(2, 8);

        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result.toString();
    }
}
