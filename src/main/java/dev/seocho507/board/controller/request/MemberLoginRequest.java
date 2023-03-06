package dev.seocho507.board.controller.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginRequest {
	@NotNull
	private String userId;
	@NotNull
	private String password;
}
