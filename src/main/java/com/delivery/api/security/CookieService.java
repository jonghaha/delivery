package com.delivery.api.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class CookieService {

	public Cookie createCookie(String cookieName, String value) {
		Cookie token = new Cookie(cookieName, value);
		token.setHttpOnly(true);
		token.setMaxAge(TokenType.ACCESS_TOKEN.getValidTime().intValue());
		token.setPath("/");
		return token;
	}

	public Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				return cookie;
			}
		}

		return null;
	}
}
