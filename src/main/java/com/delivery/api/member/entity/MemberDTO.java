package com.delivery.api.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MemberDTO {
	Integer memberSeq;
	String id;
	String password;
	String name;
	String token;

	public static MemberDTO of(Member member) {
		return MemberDTO.builder()
			.memberSeq(member.getMemberSeq())
			.id(member.getId())
			.password(member.getPassword())
			.name(member.getName())
			.build();
	}
}
