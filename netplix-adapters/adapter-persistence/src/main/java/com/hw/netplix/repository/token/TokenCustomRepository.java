package com.hw.netplix.repository.token;

import com.hw.netplix.entity.token.TokenEntity;

import java.util.Optional;

public interface TokenCustomRepository {
    Optional<TokenEntity> findByUserId(String userId);
}
