package com.hw.netplix.user;

import com.hw.netplix.user.command.UserResponse;

public interface FetchUserUseCase {
    UserResponse findUserByEmail(String email);
}
