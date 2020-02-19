package com.lif.springcloud.entitys;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: lifan
 * @Date: 2020/1/31 10:17
 */
//@AllArgsConstructor // 全参构造函数
@NoArgsConstructor // 空参构造函数
@Data // set,get
@Accessors(chain = true) // 链式风格访问
public class Dept implements Serializable { //必须序列化
    // 主键
    private Long deptno;
    // 部门名称
    private String dname;
    // 来自哪个数据库，因为微服务框架可以一个服务对应一个数据库，同一个信息被存储到不同数据库
    private String db_source;

    public Dept(String dname) {
        super();
        this.dname = dname;
    }
}
