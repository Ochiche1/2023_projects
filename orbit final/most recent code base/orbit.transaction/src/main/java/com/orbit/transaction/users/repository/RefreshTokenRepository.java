package com.orbit.transaction.users.repository;

import com.orbit.transaction.users.models.RefreshToken;
import com.orbit.transaction.users.models.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {
    private final JdbcTemplate jdbcTemplate;

    public RefreshToken findByToken(String token) {
        String sql = "SELECT * FROM refresh_token WHERE token = ?";
        List<RefreshToken> refreshTokens = jdbcTemplate.query(sql, new Object[]{token}, new RefreshTokenRepository.RefreshTokenMapper());
        return refreshTokens.size() > 0 ? refreshTokens.get(0) : null;
    }

        public RefreshToken save(RefreshToken refreshToken) {
        String sql = "INSERT INTO refresh_token (user_id, token, expiryDate) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, refreshToken.getSecurityUser().getId(), refreshToken.getToken(), refreshToken.getExpiryDate());

        return refreshToken;
    }


    private static final class RefreshTokenMapper implements RowMapper<RefreshToken> {
        public RefreshToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setId(rs.getInt("id"));
            refreshToken.setToken(rs.getString("token"));
            refreshToken.setExpiryDate(rs.getDate("expiryDate"));
            SecurityUser user = new SecurityUser();
            user.setId(rs.getInt("id"));
            return refreshToken;
        }
    }
}
