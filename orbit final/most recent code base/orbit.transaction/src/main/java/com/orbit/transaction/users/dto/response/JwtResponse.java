package com.orbit.transaction.users.dto.response;

import com.orbit.transaction.users.models.RefreshToken;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponse {
    private String accessToken;
    private String username;
    private String message;
    private RefreshTokenResponse refreshTokenResponse;
}
