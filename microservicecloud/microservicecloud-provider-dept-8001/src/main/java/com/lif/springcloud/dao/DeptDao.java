package com.lif.springcloud.dao;

import com.lif.springcloud.entitys.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // mybatis注解
public interface DeptDao {

    public  boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();
}
