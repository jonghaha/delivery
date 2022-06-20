package com.delivery.api.delivery.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.api.delivery.entity.Address;
import com.delivery.api.delivery.entity.DeliveryDTO;
import com.delivery.api.delivery.service.DeliveryService;
import com.delivery.api.response.ResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Delivery"})
@RequiredArgsConstructor
@RequestMapping("/delivery")
@RestController
public class DeliveryController {
	private final DeliveryService deliveryService;

	@Operation(summary = "배달 조회", description = "날짜를 입력받아 최대 3일 배달 조회합니다.")
	@GetMapping(value = "/search")
	public ResponseDTO<List<DeliveryDTO>> searchDelivery(@RequestParam(value = "token") String token, @RequestParam(value = "startDate") LocalDate startDate,
		@RequestParam(value = "endDate") LocalDate endDate) {
		return deliveryService.search(token, startDate, endDate);
	}

	@Operation(summary = "배달 주소 변경", description = "변경 가능한 배달 주소 변경합니다.")
	@PostMapping (value = "/address/change")
	public ResponseDTO<List<DeliveryDTO>> changeAddress(@RequestParam(value = "token") String token, @RequestBody Address address) {
		return deliveryService.changeAddress(token, address);
	}
}
