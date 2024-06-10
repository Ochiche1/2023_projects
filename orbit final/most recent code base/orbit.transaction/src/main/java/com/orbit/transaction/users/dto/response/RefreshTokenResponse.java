package com.orbit.transaction.users.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponse {
    private String refreshToken;
    private Date expiredAt;
}
