package com.delivery.api.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.api.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByIdEquals(String id);
}
