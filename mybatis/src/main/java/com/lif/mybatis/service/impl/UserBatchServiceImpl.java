package com.lif.mybatis.service.impl;

import com.lif.mybatis.pojo.User;
import com.lif.mybatis.service.UserBatchService;
import com.lif.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lifan
 * @Date: 2019/11/2 13:46
 */
@Service
public class UserBatchServiceImpl implements UserBatchService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int insertUsers(List<User> userList) {
        int count = 0;
        for(User user : userList) {
            count += userService.insertUser(user);
        }
        return count;
    }
}
