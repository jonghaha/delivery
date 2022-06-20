package com.delivery.api.member.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	@Id
	private Integer MemberSeq;
	private String id;
	private String name;
	private String password;
}
