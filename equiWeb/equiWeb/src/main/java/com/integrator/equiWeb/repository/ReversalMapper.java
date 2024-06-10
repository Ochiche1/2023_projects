package com.integrator.equiWeb.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReversalMapper implements RowMapper<ReversalRequest> {
    @Override
    public ReversalRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ReversalRequest.builder()
            .sBranchNo(rs.getString("sBranchNo"))
            .sClearingGL(rs.getString("sClearingGL"))
            .sReceivableGL(rs.getString("sReceivableGL"))
            .build();
    }
}
