package com.orbit.transaction.users.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.orbit.transaction.users.configure.JwtUtils;
import com.orbit.transaction.users.dto.request.LoginRequest;
import com.orbit.transaction.users.dto.response.JwtResponse;
import com.orbit.transaction.users.exceptions.ServiceException;
import com.orbit.transaction.users.mapper.RefreshTokenResponseMapper;
import com.orbit.transaction.users.models.SecurityUser;
import com.orbit.transaction.users.repository.UserRepository;
import java.util.ArrayList;
import static java.util.Arrays.stream;

import java.util.Arrays;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TEGA
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {

    private final JwtUtils jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final RefreshTokenResponseMapper refreshTokenResponseMapper;
    @Value("${jwt.secret}")
    private String secret;

    public JwtResponse login(LoginRequest loginRequest) throws AuthenticationException {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateJwtToken(authenticate);
        SecurityUser user = getCurrentUser();
        return JwtResponse.builder()
                .accessToken(token)
                .username(user.getUsername())
                .refreshTokenResponse(refreshTokenResponseMapper.apply(refreshTokenService.createRefreshToken(user)))
                .build();
    }

    @Transactional(readOnly = true)
    public SecurityUser getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername());
    }
}