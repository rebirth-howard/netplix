package com.hw.netplix.repository.token;

import com.hw.netplix.entity.token.QTokenEntity;
import com.hw.netplix.entity.token.TokenEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenCustomRepositoryImpl implements TokenCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<TokenEntity> findByUserId(String userId) {
        return jpaQueryFactory.selectFrom(QTokenEntity.tokenEntity)
                .where(QTokenEntity.tokenEntity.userId.eq(userId))
                .fetch()
                .stream().findFirst();
    }
}
