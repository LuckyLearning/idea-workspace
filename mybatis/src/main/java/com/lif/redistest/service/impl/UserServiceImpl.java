package com.lif.redistest.service.impl;

import com.lif.redistest.dao.UserDao;
import com.lif.redistest.pojo.User;
import com.lif.redistest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lifan
 * @Date: 2019/11/21 17:30
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 获取id，取参数id缓存用户
     * @param id
     * @return: com.lif.redistest.pojo.User
     * @createDate: 2019/11/23 0:35
     */
    @Override
    @Transactional
    @Cacheable(value = "redisCache", key = "'redis_user' + #id")
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    /**
     * 插入用户，最后mybatis会回填id，取结果id缓存用户
     * @param user
     * @Return: com.lif.redistest.pojo.User
     * @createDate: 2019/11/21 17:41
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache", key = "'redis_user' + #result.id")
    public User insertUser(User user) {
        userDao.insertUser(user);
        return user;
    }

    /**
     * 更新数据后更新缓存，如果condition配置项使结果返回null，不缓存
     * @param id
     * @param userName
     * @Return: com.lif.redistest.pojo.User
     * @createDate: 2019/11/21 17:43
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result != 'null'", key = "'redis_user' + #id")
    public User updateUserName(Long id, String userName) {
        // 此处调用 getUser 方法，该方法缓存注解失效
        // 所以这里还会执行 SQL ，将查询到数据库最新数据
        User user = this.getUser(id);
        if(user == null) {
            return null;
        }
        user.setUserName(userName);
        userDao.updateUser(user);
        return user;
    }

    /**
     * 命中率低，所以不采用缓存
     * @param userName
     * @param note
     * @Return: java.util.List<com.lif.redistest.pojo.User>
     * @createDate: 2019/11/21 17:49
     */
    @Override
    @Transactional
    public List<User> findUsers(String userName, String note) {
        return userDao.findUsers(userName, note);
    }

    /**
     * 移除缓存
     * beforeInvocation = false 是默认值
     * @param id
     * @return: int
     * @createDate: 2019/11/21 17:51
     */
    @Override
    @Transactional
    @CacheEvict(value = "redisCache", key = "'redis_user' + #id")
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
}
