package com.hw.netplix.controller.user.request;

import lombok.Getter;

@Getter
public class UserRegistrationRequest {
    private final String username;

    private String password;

    private final String email;

    private final String phone;

    public UserRegistrationRequest(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
