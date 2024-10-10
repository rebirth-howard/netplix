package com.hw.netplix.user;

import com.hw.netplix.exception.UserException;
import com.hw.netplix.user.command.UserRegistrationCommand;
import com.hw.netplix.user.response.UserRegistrationResponse;
import com.hw.netplix.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements FetchUserUseCase, RegisterUserUseCase {

    private final FetchUserPort fetchUserPort;
    private final InsertUserPort insertUserPort;

    @Override
    public UserResponse findUserByEmail(String email) {
        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserException.UserDoesNotExistException();
        }

        UserPortResponse userPortResponse = byEmail.get();

        return UserResponse.builder()
                .userId(userPortResponse.getUserId())
                .email(userPortResponse.getEmail())
                .password(userPortResponse.getPassword())
                .phone(userPortResponse.getPhone())
                .role(userPortResponse.getRole())
                .username(userPortResponse.getUsername())
                .build();
    }

    @Override
    public UserResponse findByProviderId(String providerId) {
        return null;
    }


    @Override
    public UserRegistrationResponse register(UserRegistrationCommand command) {
        String email = command.getEmail();
        // 사용자 조회
        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);

        // 만약 잇으면?
        if (byEmail.isPresent()) {
            // 예외를 던지기
            throw new UserException.UserAlreadyExistException();
        }

        // 회원가입 시도
        UserPortResponse response = insertUserPort.create(CreateUser.builder()
                    .username(command.getUsername())
                    .encryptedPassword(command.getEncryptedPassword())
                    .email(command.getEmail())
                    .phone(command.getPhone())
                    .build()
                );

        // UserRegistrationResponse 리턴
        return new UserRegistrationResponse(response.getUsername(), response.getEmail(), response.getPhone());
    }
}
