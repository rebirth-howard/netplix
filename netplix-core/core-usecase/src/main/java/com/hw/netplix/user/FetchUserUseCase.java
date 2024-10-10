package com.hw.netplix.user;

import com.hw.netplix.user.response.UserResponse;

public interface FetchUserUseCase {
    UserResponse findUserByEmail(String email);

    UserResponse findByProviderId(String providerId);

    UserResponse findKakaoUser(String accessToken);
}
