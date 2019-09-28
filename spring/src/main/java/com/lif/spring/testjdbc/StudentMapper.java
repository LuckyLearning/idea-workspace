package com.lif.spring.testjdbc;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    /**
     * @param rs
     * @param rowNum
     * @Return: com.lif.spring.testjdbc.Student
     * @createDate: 2019/9/21 15:59
     */
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        return student;
    }
}
