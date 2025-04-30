package com.hw.netplix.subscription;

public enum SubscriptionType {
	FREE("무료 구독권"), // 영화 조회 기능
	BRONEZE("브론즈 구독권"), // 영화 조회 기능 + 다운로드 5회 + 좋아요/실어요
	SILVER("실버 구독권"), // 영화 조회 기능 + 다운로드 10회 + 좋아요/실어요
	GOLD("골드 구독권"), // 영화 조회 기능 + 다운로드 무제한 + 좋아요/실어요
	;

	private final String desc;

	SubscriptionType(String desc) {
		this.desc = desc;
	}

	public String toRole() {
		return "ROLE_" + this.name();
	}
}
