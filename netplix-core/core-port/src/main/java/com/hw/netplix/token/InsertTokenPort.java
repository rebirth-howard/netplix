package com.hw.netplix.token;

public interface InsertTokenPort {
    TokenPortResponse create(String userId, String accessToken, String refreshToken);
}
