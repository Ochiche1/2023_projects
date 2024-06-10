package com.orbit.transaction.users.configure;

import com.auth0.jwt.JWT;
import java.time.Instant;
import java.util.*;

import static java.util.Date.from;

import java.util.stream.Collectors;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.orbit.transaction.users.exceptions.ServiceException;
import com.orbit.transaction.users.models.RefreshToken;
import com.orbit.transaction.users.models.SecurityUser;
import com.orbit.transaction.users.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtils implements Serializable {
    private final UserRepository userRepository;
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.jwtExpirationMs}")
    private Long jwtExpirationMs;

//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public Date getIssuedAtDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getIssuedAt);
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
////    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
////        final Claims claims = getAllClaimsFromToken(token);
////        return claimsResolver.apply(claims);
////    }
////
////    private Claims getAllClaimsFromToken(String token) {
////        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
////    }

//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return doGenerateToken(claims, userDetails.getUsername());
//    }

//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
//    }
//
//    public Boolean canTokenBeRefreshed(String token) {
//        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private static final long REFRESH_TOKEN_EXPIRATION_TIME_MS = 7 * 24 * 60 * 60 * 1000; // 7 days
//
//    public String generateRefreshToken(String username) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME_MS);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }

    public String generateJwtToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(Date.from(Instant.now().plusMillis(jwtExpirationMs)))
                .withIssuedAt(Date.from(Instant.now()))
                .withClaim("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        return authorities;
    }

    public String generateJwtTokenUsingRefreshToken(RefreshToken refreshToken) {
        try {
            User principal = (User) User.builder()
                    .username(refreshToken.getSecurityUser().getUsername())
                    .authorities(getAuthorities())
                    .password(refreshToken.getSecurityUser().getPassword())
                    .disabled(false)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .build();
            return JWT.create()
                    .withSubject(refreshToken.getSecurityUser().getUsername())
                    .withExpiresAt(Date.from(Instant.now().plusMillis(jwtExpirationMs)))
                    .withIssuedAt(from(Instant.now()))
                    .withClaim("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(Algorithm.HMAC256(secret.getBytes()));
        } catch (JWTCreationException | IllegalArgumentException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public SecurityUser validateToken(String token) throws JWTVerificationException{
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            SecurityUser user = userRepository.findByUsername(username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return user;
        } else {
            SecurityContextHolder.clearContext();
            authentication.setAuthenticated(false);
            throw new ServiceException("This user does not exist or is not logged in");
        }
    }
}