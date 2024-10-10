package com.hw.netplix.auth;

import com.hw.netplix.token.CreateTokenUseCase;
import com.hw.netplix.token.FetchTokenUseCase;
import com.hw.netplix.token.UpdateTokenUseCase;
import com.hw.netplix.token.response.TokenResponse;
import com.hw.netplix.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService implements FetchTokenUseCase, CreateTokenUseCase, UpdateTokenUseCase {
    @Override
    public TokenResponse createNewToken(String userId) {
        return null;
    }

    @Override
    public Boolean validateToken(String accessToken) {
        return null;
    }

    @Override
    public String getTokenFromKakao(String code) {
        return "";
    }

    @Override
    public UserResponse findUserByAccessToken(String accessToken) {
        return null;
    }
}
