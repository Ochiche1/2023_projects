package com.orbit.transaction.users.repository;


import com.orbit.transaction.users.models.SecurityUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<SecurityUser> {
    @Override
    public SecurityUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return SecurityUser.builder()
            .username(rs.getString("username"))
            .password(rs.getString("password"))
            .build();
    }
}
