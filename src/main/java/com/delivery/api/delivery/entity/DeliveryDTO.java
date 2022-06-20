package com.delivery.api.delivery.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DeliveryDTO {

	private Integer deliverySeq;
	private Integer memberSeq;
	private String item;
	private Address address;
	private LocalDateTime deliveryDateTime;
	private Boolean deliveryFlag;

	public static DeliveryDTO of(Delivery delivery) {
		return DeliveryDTO.builder()
			.deliverySeq(delivery.getDeliverySeq())
			.memberSeq(delivery.getMember().getMemberSeq())
			.item(delivery.getItem())
			.address(delivery.getAddress())
			.deliveryDateTime(delivery.getDeliveryDateTime())
			.deliveryFlag(delivery.getDeliveryFlag())
			.build();
	}
}
