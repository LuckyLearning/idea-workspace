package com.lif.springcloud.service.impl;

import com.lif.springcloud.dao.DeptDao;
import com.lif.springcloud.entitys.Dept;
import com.lif.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门逻辑实现类
 * @Author: lifan
 * @Date: 2020/1/31 11:46
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao dao;

    @Override
    public boolean add(Dept dept) {
        return dao.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Dept> list() {
        return dao.findAll();
    }
}
