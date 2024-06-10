package com.orbit.transaction.inward.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EndpointResponse {
    private String username;
    private String role;
    private String originalUserName;
    private String accessToken;

    private RefreshToken refreshToken;

}
