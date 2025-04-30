package com.hw.netplix.repository.subscription;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hw.netplix.entity.subscription.UserSubscriptionEntity;

public interface UserSubscriptionJpaRepository extends JpaRepository<UserSubscriptionEntity, String> {
	Optional<UserSubscriptionEntity> findByUserId(String userId);
}