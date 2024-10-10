package com.hw.netplix.token;

public interface SearchTokenPort {
    TokenPortResponse findByUserId(String userId);
}
