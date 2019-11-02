package com.lif.mybatis.service;

import com.lif.mybatis.pojo.User;

/**
 * @Author: lifan
 * @Date: 2019/10/30 19:30
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param id
     * @Return: com.lif.mybatis.pojo.User
     * @createDate: 2019/10/30 19:30
     */
    public User getUser(Long id);

    /**
     * 新增用户
     * @param user
     * @Return: int
     * @createDate: 2019/10/30 19:32
     */
    public int insertUser(User user);

}
