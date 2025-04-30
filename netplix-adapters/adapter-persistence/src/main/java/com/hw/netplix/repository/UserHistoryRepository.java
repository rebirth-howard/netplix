package com.hw.netplix.repository;

import org.springframework.stereotype.Repository;

import com.hw.netplix.entity.user.UserHistoryEntity;
import com.hw.netplix.repository.user.UserHistoryJpaRepository;
import com.hw.netplix.user.UserHistoryPort;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserHistoryRepository implements UserHistoryPort {

	private final UserHistoryJpaRepository userHistoryJpaRepository;

	@Override
	@Transactional
	public void create(String userId, String userRole, String clientIp, String reqMethod, String reqUrl,
		String reqHeader, String reqPayload) {
		userHistoryJpaRepository.save(
			new UserHistoryEntity(
				userId, userRole, clientIp, reqMethod, reqUrl, reqHeader, reqPayload)
		);
	}
}