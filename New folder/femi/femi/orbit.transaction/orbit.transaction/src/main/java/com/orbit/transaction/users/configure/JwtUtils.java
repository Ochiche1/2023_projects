package com.orbit.transaction.users.configure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private static final long REFRESH_TOKEN_EXPIRATION_TIME_MS = 7 * 24 * 60 * 60 * 1000; // 7 days
    public String generateRefreshToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME_MS);

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }


//    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//
//        @Value("${jwt.secret}")
//    private String secret;
//    @Value("${jwt.jwtExpirationMs}")
//    private int jwtExpirationMs;
//
//    @Value("${jwt.jwtCookieName}")
//    private String jwtCookie;
//
//    public String getJwtFromCookies(HttpServletRequest request) {
//        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
//        if (cookie != null) {
//            return cookie.getValue();
//        } else {
//            return null;
//        }
//    }
//
//    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
//        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
//        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
//        return cookie;
//    }
//
//    public ResponseCookie getCleanJwtCookie() {
//        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
//        return cookie;
//    }
//
//    public String getUserNameFromJwtToken(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException e) {
//            logger.error("Invalid JWT signature: {}", e.getMessage());
//        } catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
//        } catch (ExpiredJwtException e) {
//            logger.error("JWT token is expired: {}", e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
//        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
//        }
//
//        return false;
//    }
//
//    public String generateTokenFromUsername(String username) {
//        return Jwts.builder()
//            .setSubject(username)
//            .setIssuedAt(new Date())
//            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//            .signWith(SignatureAlgorithm.HS512, secret)
//            .compact();
//    }
}
//        private String secret = "jwt_secret";
//
//        public String generateToken(UserDetails userDetails) {
//            Map<String, Object> claims = new HashMap<>();
//            return Jwts.builder()
//                    .setClaims(claims)
//                    .setSubject(userDetails.getUsername())
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                    .signWith(SignatureAlgorithm.HS512, secret)
//                    .compact();
//        }
//
//        public Boolean validateToken(String token, UserDetails userDetails) {
//            final String username = getUsernameFromToken(token);
//            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//        }
//
//        private Boolean isTokenExpired(String token) {
//            final Date expiration = getExpirationDateFromToken(token);
//            return expiration.before(new Date());
//        }
//
//        private Date getExpirationDateFromToken(String token) {
//            return Jwts.parser()
//                    .setSigningKey(secret)
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getExpiration();
//        }
//
//        private String getUsernameFromToken(String token) {
//            return Jwts.parser()
//                    .setSigningKey(secret)
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getSubject();
//        }
  // }
