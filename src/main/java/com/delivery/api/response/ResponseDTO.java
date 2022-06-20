package com.delivery.api.response;

import org.springframework.lang.Nullable;

import lombok.Builder;

@Builder
public class ResponseDTO<T> {
	private String message;
	private T data;

	public static <T> ResponseDTO<T> ok(@Nullable T data, String message) {
		return ResponseDTO.<T>builder().data(data).message(message).build();
	}
}
