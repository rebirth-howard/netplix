package com.hw.netplix.user;

public interface LogUserAuditHistoryUseCase {
	void log(String userId, String userRole, String clientIp, String reqMethod,
		String reqUrl, String reqHeader, String reqPayload);
}
