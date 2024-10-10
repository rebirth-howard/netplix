package com.hw.netplix.controller.user;

import com.hw.netplix.controller.NetplixApiResponse;
import com.hw.netplix.controller.user.request.UserRegistrationRequest;
import com.hw.netplix.user.RegisterUserUseCase;
import com.hw.netplix.user.command.UserRegistrationCommand;
import com.hw.netplix.user.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final PasswordEncoder passwordEncoder;

    @RequestMapping("/api/v1/user/register")
    public NetplixApiResponse<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest request) {
        UserRegistrationResponse register = registerUserUseCase.register(
                UserRegistrationCommand.builder()
                        .email(request.getEmail())
                        .username(request.getUsername())
                        .encryptedPassword(request.getPassword())
                        .phone(request.getPhone())
                        .build()
        );

        return NetplixApiResponse.ok(register);
    }

}
