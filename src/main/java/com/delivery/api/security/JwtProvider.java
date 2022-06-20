package com.delivery.api.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	public static final String AUTH_TOKEN = "X-AUTH-TOKEN";

	@Value("${spring.jwt.secret}")
	private String secretKey;

	private Key getKey(String secretKey) {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	public String createToken(String memberSeq, TokenType tokenType) {
		Claims claims = Jwts.claims();
		claims.put("memberSeq", memberSeq);
		claims.put("type", tokenType.getName());
		Date now = new Date();
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + tokenType.getValidTime()))
			.signWith(getKey(secretKey), SignatureAlgorithm.HS256)
			.compact();
	}

	public String issueToken(int memberSeq) {
		return createToken(Integer.toString(memberSeq), TokenType.ACCESS_TOKEN);
	}

	public String issueRefreshToken(int memberSeq) {
		return createToken(Integer.toString(memberSeq), TokenType.REFRESH_TOKEN);
	}

	public String getMemberSeq(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(getKey(secretKey))
			.build()
			.parseClaimsJws(token)
			.getBody()
			.get("memberSeq", String.class);
	}

	public boolean isExpired(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(getKey(secretKey))
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getExpiration()
			.before(new Date());
	}
}
