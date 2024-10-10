package com.hw.netplix.user;

public interface KakaoUserPort {
    UserPortResponse findUserFromKakao(String accessToken);
}
