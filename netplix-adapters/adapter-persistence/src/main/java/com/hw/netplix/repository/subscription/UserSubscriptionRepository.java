package com.hw.netplix.repository.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.hw.netplix.entity.subscription.UserSubscriptionEntity;
import com.hw.netplix.subscription.FetchUserSubscriptionPort;
import com.hw.netplix.subscription.InsertUserSubscriptionPort;
import com.hw.netplix.subscription.UpdateUserSubscriptionPort;
import com.hw.netplix.subscription.UserSubscription;

@Repository
@RequiredArgsConstructor
public class UserSubscriptionRepository implements FetchUserSubscriptionPort, UpdateUserSubscriptionPort, InsertUserSubscriptionPort {

	private final UserSubscriptionJpaRepository userSubscriptionJpaRepository;

	@Override
	@Transactional
	public Optional<UserSubscription> findByUserId(String userId) {
		return userSubscriptionJpaRepository.findByUserId(userId)
			.map(UserSubscriptionEntity::toDomain);
	}

	@Override
	@Transactional
	public void create(String userId) {
		UserSubscription userSubscription = UserSubscription.newSubscription(userId);
		UserSubscriptionEntity entity = UserSubscriptionEntity.toEntity(userSubscription);
		userSubscriptionJpaRepository.save(entity);
	}


	@Override
	@Transactional
	public void update(UserSubscription userSubscription) {
		userSubscriptionJpaRepository.save(UserSubscriptionEntity.toEntity(userSubscription));
	}
}
