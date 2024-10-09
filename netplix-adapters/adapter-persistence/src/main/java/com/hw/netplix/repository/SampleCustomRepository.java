package com.hw.netplix.repository;

import com.hw.netplix.entity.SampleEntity;

import java.util.List;

public interface SampleCustomRepository {
    List<SampleEntity> findAllByAbc();
}
