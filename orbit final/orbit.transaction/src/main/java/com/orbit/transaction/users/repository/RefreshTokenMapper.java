package com.orbit.transaction.users.repository;

import com.orbit.transaction.users.models.RefreshToken;

import com.orbit.transaction.users.models.SecurityUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;


public class RefreshTokenMapper implements RowMapper<RefreshToken> {


    @Override
    public RefreshToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RefreshToken.builder()
                .id(rs.getInt("id"))
                .token(rs.getString("token"))
                .expiryDate(rs.getDate("expiryDate"))
                .securityUser((SecurityUser) rs.getObject("securityUser"))
                .build();

    }


}
