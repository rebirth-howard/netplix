package com.hw.netplix.token;

import com.hw.netplix.user.response.UserResponse;

public interface FetchTokenUseCase {

    Boolean validateToken(String accessToken);

    String getTokenFromKakao(String code);

    UserResponse findUserByAccessToken(String accessToken);

}
