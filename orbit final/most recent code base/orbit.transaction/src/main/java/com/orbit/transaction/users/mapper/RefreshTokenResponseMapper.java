package com.orbit.transaction.users.mapper;

import com.orbit.transaction.users.dto.response.RefreshTokenResponse;
import com.orbit.transaction.users.models.RefreshToken;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RefreshTokenResponseMapper implements Function<RefreshToken, RefreshTokenResponse> {
    @Override
    public RefreshTokenResponse apply(RefreshToken refreshToken) {
        return RefreshTokenResponse.builder()
                .refreshToken(refreshToken.getToken())
                .expiredAt(refreshToken.getExpiryDate())
                .build();
    }
}
