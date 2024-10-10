package com.hw.netplix.token;

import com.hw.netplix.token.response.TokenResponse;

public interface CreateTokenUseCase {
    TokenResponse createNewToken(String userId);
}
