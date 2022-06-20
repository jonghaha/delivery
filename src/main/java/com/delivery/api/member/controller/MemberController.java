package com.delivery.api.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.api.member.entity.MemberDTO;
import com.delivery.api.member.service.MemberService;
import com.delivery.api.response.ResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Member"})
@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {
	private final MemberService memberService;

	@Operation(summary = "회원가입")
	@PostMapping(value = "/signup")
	public ResponseDTO<MemberDTO> signup(@RequestBody MemberDTO memberDTO) {
		return memberService.signup(memberDTO);
	}

	@Operation(summary = "로그인")
	@PostMapping(value = "/login")
	public ResponseDTO<MemberDTO> login(@RequestBody MemberDTO memberDTO) {
		return memberService.login(memberDTO);
	}
}
