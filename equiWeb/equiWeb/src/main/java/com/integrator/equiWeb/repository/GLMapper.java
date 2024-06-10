package com.integrator.equiWeb.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GLMapper implements RowMapper<GLRequest> {
    @Override
    public GLRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        return GLRequest.builder()
            .gl_acct_non_proprietary(rs.getString("gl_acct_non_proprietary"))
            .build();
    }
}
