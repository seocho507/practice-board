package dev.seocho507.board.controller.response;

import dev.seocho507.board.domian.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
	private String userId;
	private String userName;

	protected MemberResponse() {
	}

	private MemberResponse(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public static MemberResponse from(Member member) {
		return new MemberResponse(member.getUserId(),
								  member.getUserName());
	}
}
