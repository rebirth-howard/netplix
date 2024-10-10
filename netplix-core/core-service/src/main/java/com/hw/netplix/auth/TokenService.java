package com.hw.netplix.auth;

import com.hw.netplix.token.*;
import com.hw.netplix.token.response.TokenResponse;
import com.hw.netplix.user.FetchUserUseCase;
import com.hw.netplix.user.response.UserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService implements FetchTokenUseCase, CreateTokenUseCase, UpdateTokenUseCase {

    @Value("${jwt.secret}")
    private String secretKey;

    private final InsertTokenPort insertTokenPort;
    private final UpdateTokenPort updateTokenPort;
    private final SearchTokenPort searchTokenPort;
    private final FetchUserUseCase fetchUserUseCase;

    @Override
    public TokenResponse createNewToken(String userId) {
        String accessToken = getToken(userId, Duration.ofHours(3));
        String refreshToken = getToken(userId, Duration.ofHours(24));

        TokenPortResponse tokenPortResponse = insertTokenPort.create(userId, accessToken, refreshToken);

        return TokenResponse.builder()
                .accessToken(tokenPortResponse.getAccessToken())
                .refreshToken(tokenPortResponse.getRefreshToken())
                .build();
    }

    @Override
    public Boolean validateToken(String accessToken) {
        Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken);

        return true;
    }

    @Override
    public String getTokenFromKakao(String code) {
        return null;
    }

    @Override
    public UserResponse findUserByAccessToken(String accessToken) {
        Claims claims = parseClaims(accessToken);

        Object userId = claims.get("user_Id");

        if (ObjectUtils.isEmpty(userId)) {
            throw new RuntimeException();
        }

        return fetchUserUseCase.findByProviderId(userId.toString());
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private String getToken(String userId, Duration expireAt) {
        Date now = new Date();
        Instant instant = now.toInstant();
        return Jwts.builder()
                .claim("userId", userId)
                .issuedAt(now)
                .expiration(Date.from(instant.plus(expireAt)))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
