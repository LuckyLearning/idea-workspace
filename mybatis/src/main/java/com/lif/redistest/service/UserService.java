package com.lif.redistest.service;


import com.lif.redistest.pojo.User;

import java.util.List;

/**
 * @Author: lifan
 * @Date: 2019/11/21 17:20
 */
public interface UserService {
    // 获取单个用户
    User getUser(Long id);
    // 保存单个用户
    User insertUser(User user);
    // 修改用户,指定mybatis的参数名称
    User updateUserName(Long id, String userName);
    // 查询用户，指定mybatis的参数名称
    List<User> findUsers(String userName, String note);
    // 删除用户
    int deleteUser(Long id);
}
