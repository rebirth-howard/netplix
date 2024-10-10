package com.hw.netplix.controller.user;

import com.hw.netplix.controller.NetplixApiResponse;
import com.hw.netplix.controller.user.request.UserLoginRequest;
import com.hw.netplix.controller.user.request.UserRegistrationRequest;
import com.hw.netplix.security.NetplixAuthUser;
import com.hw.netplix.token.FetchTokenUseCase;
import com.hw.netplix.token.UpdateTokenUseCase;
import com.hw.netplix.user.FetchUserUseCase;
import com.hw.netplix.user.RegisterUserUseCase;
import com.hw.netplix.user.command.UserRegistrationCommand;
import com.hw.netplix.user.response.UserRegistrationResponse;
import com.hw.netplix.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final FetchTokenUseCase fetchTokenUseCase;
    private final FetchUserUseCase fetchUserUseCase;
    private final UpdateTokenUseCase updateTokenUseCase;

    @PostMapping("/api/v1/user/register")
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

    @PostMapping("/api/v1/user/login")
    public NetplixApiResponse<String> login(@RequestBody UserLoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(token);

        NetplixAuthUser principal = (NetplixAuthUser) authenticate.getPrincipal();

        return NetplixApiResponse.ok("access-token");
    }

    @PostMapping("/api/v1/user/callback")
    public NetplixApiResponse<String> kakaoCallback(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        String accessTokenFromKakao = fetchTokenUseCase.getTokenFromKakao(code);
        UserResponse kakaoUser = fetchUserUseCase.findKakaoUser(accessTokenFromKakao);

        // 소셜 사용자가 이미 존재하는지 확인을 해야 하고
        UserResponse byProviderId = fetchUserUseCase.findByProviderId(kakaoUser.getProviderId());

        if (ObjectUtils.isEmpty(byProviderId)) {
            // 만약 존재하지 않으면, 회원가입을 하는 부분
            registerUserUseCase.registerSocialUser(
                    kakaoUser.getUsername(),
                    kakaoUser.getProvider(),
                    kakaoUser.getProviderId());
        }

        // 토큰을 발급해서 반환
        return NetplixApiResponse.ok(updateTokenUseCase.upsertToken(kakaoUser.getProviderId()));
    }


}
