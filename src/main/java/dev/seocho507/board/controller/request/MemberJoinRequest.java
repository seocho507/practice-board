package dev.seocho507.board.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.UniqueElements;

import dev.seocho507.board.domian.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinRequest {
	@NotEmpty
	private String userId;

	@NotEmpty
	private String password;

	@NotEmpty
	private String userName;

	@NotEmpty
	@UniqueElements
	@Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}")
	private String phoneNumber;

	public MemberJoinRequest() {
	}

	private MemberJoinRequest(String userId, String password, String userName, String phoneNumber) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
	}

	public static MemberJoinRequest of(String userId, String password, String userName, String phoneNumber) {
		return new MemberJoinRequest(userId, password, userName, phoneNumber);
	}

	public static Member toEntity(MemberJoinRequest request) {
		return Member.of(request.userId, request.password, request.userName, request.phoneNumber);
	}

}
