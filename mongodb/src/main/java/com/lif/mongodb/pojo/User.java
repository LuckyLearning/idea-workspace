package com.lif.mongodb.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: lifan
 * @Date: 2019/11/26 20:42
 */
@Document
public class User implements Serializable {

    private static final long serialVersionUID = 4732091161361206398L;

    /**
     * MongoDB文档编号，主键
     */
    @Id
    private Long id;

    /**
     * 在MongoDB中使用user_name保存属性
     */
    @Field("user_name")
    private String userName;

    private String note;

    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
