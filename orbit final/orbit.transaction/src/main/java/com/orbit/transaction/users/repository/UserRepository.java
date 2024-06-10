package com.orbit.transaction.users.repository;



import com.orbit.transaction.users.models.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SecurityUser findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<SecurityUser> securityUsers = jdbcTemplate.query(sql, new Object[]{username}, new UserMapper());
        return securityUsers.size() > 0 ? securityUsers.get(0) : null;
    }
    public SecurityUser findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<SecurityUser> securityUsers = jdbcTemplate.query(sql, new Object[]{id}, new UserMapper());
        return securityUsers.size() > 0 ? securityUsers.get(0) : null;
    }
    public void save(SecurityUser securityUser) {
        String sql = "INSERT INTO users (username, password, roles) VALUES (?, ?,?)";
        jdbcTemplate.update(sql, securityUser.getUsername(), securityUser.getPassword(), securityUser.getRoles());
    }

    private static final class UserMapper implements RowMapper<SecurityUser> {
        public SecurityUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            SecurityUser securityUser = new SecurityUser();
            securityUser.setId(rs.getInt("id"));
            securityUser.setUsername(rs.getString("username"));
            securityUser.setPassword(rs.getString("password"));
            securityUser.setRoles(rs.getString("roles"));
            return securityUser;
        }
    }
}
