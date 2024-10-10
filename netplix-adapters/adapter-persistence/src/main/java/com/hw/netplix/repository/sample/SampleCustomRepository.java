package com.hw.netplix.repository.sample;

import com.hw.netplix.entity.sample.SampleEntity;

import java.util.List;

public interface SampleCustomRepository {
    List<SampleEntity> findAllByAbc();
}
