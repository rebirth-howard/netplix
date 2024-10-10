package com.hw.netplix.repository.sample;

import com.hw.netplix.entity.sample.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleJpaRepository extends JpaRepository<SampleEntity, String>, SampleCustomRepository {
}
