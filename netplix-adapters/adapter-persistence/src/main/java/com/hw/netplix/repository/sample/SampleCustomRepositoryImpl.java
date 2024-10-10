package com.hw.netplix.repository.sample;

import com.hw.netplix.entity.QSampleEntity;
import com.hw.netplix.entity.sample.SampleEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SampleCustomRepositoryImpl implements SampleCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SampleEntity> findAllByAbc() {
        return jpaQueryFactory.selectFrom(QSampleEntity.sampleEntity)
                .fetch();
    }
}
