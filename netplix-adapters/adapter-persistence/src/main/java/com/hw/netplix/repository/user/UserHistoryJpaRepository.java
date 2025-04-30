package com.hw.netplix.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hw.netplix.entity.user.UserHistoryEntity;

public interface UserHistoryJpaRepository extends JpaRepository<UserHistoryEntity, Long> {

}