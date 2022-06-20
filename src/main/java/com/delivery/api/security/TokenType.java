package com.delivery.api.security;

import lombok.Getter;

@Getter
public enum TokenType {
	ACCESS_TOKEN("accessToken", 1000L * 60 * 60),
	REFRESH_TOKEN("refreshToken", 1000L * 60 * 60 * 24 * 30);

	private String name;
	private Long validTime;

	TokenType(String name, Long validTime) {
		this.name = name;
		this.validTime = validTime;
	}

	public static boolean isAcessToken(String type) {
		return ACCESS_TOKEN.getName().equals(type);
	}
}
