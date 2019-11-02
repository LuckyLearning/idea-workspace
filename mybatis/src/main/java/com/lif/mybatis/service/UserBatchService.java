package com.lif.mybatis.service;

import com.lif.mybatis.pojo.User;

import java.util.List;

/**
 * @Author: lifan
 * @Date: 2019/11/2 13:44
 */
public interface UserBatchService {
    public int insertUsers(List<User> userList);
}
