package com.hw.netplix.entity.subscription;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import com.hw.netplix.subscription.SubscriptionType;
import com.hw.netplix.subscription.UserSubscription;

@Getter
@Entity
@Table(name = "user_subscriptions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSubscriptionEntity {
	@Id
	@Column(name = "USER_SUBSCRIPTION_ID")
	private String userSubscriptionId;

	@Column(name = "USER_ID")
	private String userId;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "SUBSCRIPTION_NAME")
	private SubscriptionType subscriptionName;

	@Column(name = "START_AT")
	private LocalDateTime subscriptionStartAt;

	@Column(name = "END_AT")
	private LocalDateTime subscriptionEndAt;

	@Column(name = "VALID_YN")
	private Boolean validYn;

	public UserSubscription toDomain() {
		return UserSubscription.builder()
			.userId(this.userId)
			.subscriptionType(this.subscriptionName)
			.startAt(this.subscriptionStartAt)
			.endAt(this.subscriptionEndAt)
			.validYn(this.validYn)
			.build();
	}

	public static UserSubscriptionEntity toEntity(UserSubscription userSubscription) {
		return new UserSubscriptionEntity(
			UUID.randomUUID().toString(),
			userSubscription.getUserId(),
			userSubscription.getSubscriptionType(),
			userSubscription.getStartAt(),
			userSubscription.getEndAt(),
			userSubscription.getValidYn()
		);
	}
}