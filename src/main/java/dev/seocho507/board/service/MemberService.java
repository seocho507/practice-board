package dev.seocho507.board.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.seocho507.board.domian.Member;
import dev.seocho507.board.dto.AuthMember;
import dev.seocho507.board.exception.ErrorCode;
import dev.seocho507.board.exception.MyCustomException;
import dev.seocho507.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public Member join(Member member) {
		memberRepository.findByUserId(member.getUserId()).ifPresent(it -> {
			throw new MyCustomException(ErrorCode.DUPLICATED_USER_NAME);
		});
		final String rawPassword = member.getPassword();
		member.encodePassword(passwordEncoder.encode(rawPassword));
		return memberRepository.save(member);
	}

	public AuthMember login(String userId, String password) {
		final Member entity = memberRepository.findByUserId(userId)
											  .orElseThrow(() -> new MyCustomException(ErrorCode.USER_NOT_FOUND));
		final AuthMember authMember = AuthMember.from(entity);

		if (!passwordEncoder.matches(password, authMember.getPassword())) {
			throw new MyCustomException(ErrorCode.INVALID_PASSWORD);
		}

		return authMember;
	}

	public boolean existsUserId(String userId) {
		return memberRepository.findByUserId(userId)
							   .map(member -> true)
							   .orElse(false);
	}
}
