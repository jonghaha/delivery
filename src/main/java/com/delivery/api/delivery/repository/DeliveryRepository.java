package com.delivery.api.delivery.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.api.delivery.entity.Delivery;
import com.delivery.api.member.entity.Member;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
	List<Delivery> findAllByMemberEqualsAndDeliveryDateTimeIsGreaterThanEqualAndDeliveryDateTimeIsLessThan(Member member, LocalDateTime startDatetime, LocalDateTime endDateTime);
	List<Delivery> findAllByMemberEqualsAndDeliveryFlagIsTrue(Member member);
}
