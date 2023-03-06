package dev.seocho507.board.domian.enums;

import lombok.Getter;

@Getter
public enum UserRole {
	USER("일반"),
	ADMIN("관리자");

	private String description;

	UserRole(String description) {
		this.description = description;
	}
}
