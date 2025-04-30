package com.hw.netplix.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.hw.netplix.token.FetchTokenUseCase;
import com.hw.netplix.user.response.UserResponse;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	private final FetchTokenUseCase fetchTokenUseCase;

	public Authentication getAuthentication(String accessToken) {
		UserResponse user = fetchTokenUseCase.findUserByAccessToken(accessToken);
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
		UserDetails principal = new User(
			user.getUsername(),
			StringUtils.isBlank(user.getPassword()) ? "password" : user.getPassword(),
			authorities
		);
		return new UsernamePasswordAuthenticationToken(principal, user.getUserId(), authorities);

	}
}
