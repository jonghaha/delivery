package com.delivery.api.member.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.delivery.api.member.entity.Member;
import com.delivery.api.member.entity.MemberDTO;
import com.delivery.api.member.repository.MemberRepository;
import com.delivery.api.response.ResponseDTO;
import com.delivery.api.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	private static final int PASSWORD_LENGTH = 12;
	private static final int TYPE_COUNT = 3;
	private final MemberRepository memberRepository;
	private final JwtProvider jwtProvider;

	// 회원가입
	@Transactional
	public ResponseDTO<MemberDTO> signup(MemberDTO memberDTO) {

		Member member = memberRepository.findByIdEquals(memberDTO.getId()).orElse(null);
		if (member != null) {
			return ResponseDTO.ok(null, "이미 존재하는 ID입니다.");
		}

		if (isValidPassword(memberDTO.getPassword())) {
			return ResponseDTO.ok(null, "알맞은 비밀번호를 입력해주세요.");
		}

		Member signupMember = Member.builder()
			.id(memberDTO.getId())
			.password(memberDTO.getPassword())
			.name(memberDTO.getName())
			.build();
		memberRepository.save(signupMember);

		return ResponseDTO.ok(MemberDTO.of(signupMember), "회원 가입 완료.");
	}

	// 비밀번호 유효성 체크
	public boolean isValidPassword(String password) {
		if (checkRange(password)) {
			return false;
		} else {
			return containWord(password);
		}
	}

	// 비밀번호 영어 대문자, 영어 소문자, 숫자, 특수문자 3개이상 사용 체크
	private boolean containWord(String password) {
		int typeCount = 0;

		final String upper = "[A-Z]";
		final String lower = "[a-z]";
		final String number = "[0-9]";
		final String character = "[#?!@$ %^&*-]";

		if (password.matches(upper)) {
			typeCount += 1;
		}
		if (password.matches(lower)) {
			typeCount += 1;
		}
		if (password.matches(number)) {
			typeCount += 1;
		}
		if (password.matches(character)) {
			typeCount += 1;
		}

		return TYPE_COUNT <= typeCount;
	}

	// 비밀번호 12자리 이상
	private boolean checkRange(String password) {
		return PASSWORD_LENGTH <= password.length();
	}

	public ResponseDTO<MemberDTO> login(MemberDTO memberDTO) {
		Member member = memberRepository.findByIdEquals(memberDTO.getId()).orElse(null);
		if (member == null) {
			return ResponseDTO.ok(null, "아이디, 비밀번호를 확인해주세요.");
		}
		if (!memberDTO.getPassword().equals(member.getPassword())) {
			return ResponseDTO.ok(null, "아이디, 비밀번호를 확인해주세요.");
		}

		MemberDTO loginMember = MemberDTO.of(member);
		loginMember.setToken(jwtProvider.issueToken(loginMember.getMemberSeq()));

		return ResponseDTO.ok(memberDTO, "로그인 성공.");
	}
}
