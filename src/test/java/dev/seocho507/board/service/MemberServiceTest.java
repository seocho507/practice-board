package dev.seocho507.board.service;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dev.seocho507.board.controller.request.MemberJoinRequest;
import dev.seocho507.board.domian.Member;
import dev.seocho507.board.repository.MemberRepository;

@SpringBootTest
public class MemberServiceTest {

	private final MemberService memberService;
	@MockBean
	MemberRepository memberRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public MemberServiceTest(@Autowired MemberService memberService) {
		this.memberService = memberService;
	}

	@Test
	void 회원가입_정상작동() {
		MemberJoinRequest request = new MemberJoinRequest();
		request.setUserId("test1234");
		request.setPassword("123456789");
		request.setUserId("lee");
		request.setPhoneNumber("010-8588-9207");

		final Member entity = MemberJoinRequest.toEntity(request);

		Assertions.assertDoesNotThrow(() -> memberService.join(entity));
	}

	@Test
	void 로그인_정상작동() {
		Member test = Member.of("test1234", "123456789", "lee", "010-8588-9207");
		test.encodePassword(passwordEncoder.encode(test.getPassword()));
		memberRepository.save(test);

		when(memberRepository.findByUserId(test.getUserId())).thenReturn(Optional.of(test));

		Assertions.assertDoesNotThrow(() -> memberService.login(test.getUserId(), "123456789"));
	}
}
