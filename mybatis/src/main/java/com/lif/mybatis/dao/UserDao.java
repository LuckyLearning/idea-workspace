package com.lif.mybatis.dao;

import com.lif.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: lifan
 * @Date: 2019/10/30 19:22
 */
@Repository
public interface UserDao {
    User getUser(Long id);
    int insertUser(User user);
}
