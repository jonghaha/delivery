package com.delivery.api.delivery.entity;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.delivery.api.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Delivery {
	@Id
	@GeneratedValue
	private Integer deliverySeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_seq")
	private Member member;
	private String item;
	@Embedded
	private Address address;
	private LocalDateTime deliveryDateTime;
	private Boolean deliveryFlag;

	public void changeAddress(Address address) {
		this.address = address;
	}
}
