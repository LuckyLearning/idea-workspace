package com.lif.springcloud.service;

import com.lif.springcloud.entitys.Dept;

import java.util.List;

/**
 * 部门server接口
 * @author lifan
 */
public interface DeptService {
    public boolean add(Dept dept);
    public Dept get(Long id);
    public List<Dept> list();
}
