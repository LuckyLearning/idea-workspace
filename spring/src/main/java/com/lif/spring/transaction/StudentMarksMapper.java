package com.lif.spring.transaction;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: lifan
 * @Date: 2019/9/21 16:01
 */
public class StudentMarksMapper implements RowMapper<StudentMarks> {
    @Override
    public StudentMarks mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentMarks studentMarks = new StudentMarks();
        studentMarks.setId(rs.getInt("id"));
        studentMarks.setName(rs.getString("name"));
        studentMarks.setAge(rs.getInt("age"));
        studentMarks.setSid(rs.getInt("sid"));
        studentMarks.setMarks(rs.getInt("marks"));
        studentMarks.setYear(rs.getInt("year"));
        return studentMarks;
    }
}
