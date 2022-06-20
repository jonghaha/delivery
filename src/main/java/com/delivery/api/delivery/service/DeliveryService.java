package com.delivery.api.delivery.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delivery.api.delivery.entity.Address;
import com.delivery.api.delivery.entity.Delivery;
import com.delivery.api.delivery.entity.DeliveryDTO;
import com.delivery.api.delivery.repository.DeliveryRepository;
import com.delivery.api.member.entity.Member;
import com.delivery.api.response.ResponseDTO;
import com.delivery.api.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryService {

	private static final long DATE_DIFF = 3;
	private final DeliveryRepository deliveryRepository;
	private final JwtProvider jwtProvider;

	public ResponseDTO<List<DeliveryDTO>> search(String token, LocalDate startDate, LocalDate endDate) {

		Integer memberSeq = Integer.valueOf(jwtProvider.getMemberSeq(token));

		if (startDate.plusDays(DATE_DIFF).isAfter(endDate)) {
			return ResponseDTO.ok(null, "조회기간이 최대 3일입니다.");
		}

		List<Delivery> deliveryList = deliveryRepository.findAllByMemberEqualsAndDeliveryDateTimeIsGreaterThanEqualAndDeliveryDateTimeIsLessThan(
			Member.of(memberSeq), startDate.atStartOfDay(), endDate.plusDays(1L).atStartOfDay());

		return ResponseDTO.ok(deliveryList.stream().map(DeliveryDTO::of).collect(Collectors.toList()), "조회 성공.");
	}

	public ResponseDTO<List<DeliveryDTO>> changeAddress(String token, Address address) {

		Integer memberSeq = Integer.valueOf(jwtProvider.getMemberSeq(token));

		List<Delivery> deliveryList = deliveryRepository.findAllByMemberEqualsAndDeliveryFlagIsTrue(Member.of(memberSeq));
		if (deliveryList.isEmpty()) {
			return ResponseDTO.ok(null, "변경 가능한 배달 건이 없습니다.");
		}
		for (Delivery delivery : deliveryList) {
			delivery.changeAddress(address);
		}

		return ResponseDTO.ok(deliveryList.stream().map(DeliveryDTO::of).collect(Collectors.toList()), "변경 완료.");
	}
}
