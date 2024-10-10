package com.hw.netplix.user;


import com.hw.netplix.user.command.UserRegistrationCommand;
import com.hw.netplix.user.response.UserRegistrationResponse;

public interface RegisterUserUseCase {
    UserRegistrationResponse register(UserRegistrationCommand command);
    UserRegistrationResponse registerSocialUser(String username, String provider, String providerId);

}
