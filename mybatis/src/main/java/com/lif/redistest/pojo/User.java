package com.lif.redistest.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author: lifan
 * @Date: 2019/11/20 17:52
 */
@Alias("t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 6485389076686933837L;
    private Long id;
    private String userName;
    private String note;

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
}
