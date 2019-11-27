package com.lif.redistest.dao;

import com.lif.redistest.pojo.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lifan
 * @Date: 2019/11/21 16:04
 */
@Repository
public interface UserDao {
    // 获取单个用户
    User getUser(Long id);
    // 保存单个用户
    int insertUser(User user);
    // 更新单个用户
    int updateUser(User user);
    // 查询用户，指定mybatis的参数名称
    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);
    // 删除用户
    int deleteUser(Long id);
}
