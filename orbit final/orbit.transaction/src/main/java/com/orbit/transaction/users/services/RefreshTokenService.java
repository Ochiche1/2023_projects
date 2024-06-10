package com.orbit.transaction.users.services;


import com.orbit.transaction.users.exceptions.TokenRefreshException;
import com.orbit.transaction.users.models.RefreshToken;
import com.orbit.transaction.users.models.SecurityUser;
import com.orbit.transaction.users.repository.RefreshTokenRepository;
import com.orbit.transaction.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
  @Value("${jwt.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;


  private final UserRepository userRepository;

private final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(SecurityUser user) {
    RefreshToken refreshToken = new RefreshToken();

    refreshToken.setSecurityUser(user);

    refreshToken.setExpiryDate(Date.from(Instant.now().plusMillis(refreshTokenDurationMs)));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }


}
