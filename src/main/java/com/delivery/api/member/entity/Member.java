package com.delivery.api.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	@Id
	@GeneratedValue
	private Integer memberSeq;
	private String id;
	private String name;
	private String password;

	public static Member of(Integer memberSeq) {
		return memberSeq == null ? null : Member.builder().memberSeq(memberSeq).build();
	}
}
