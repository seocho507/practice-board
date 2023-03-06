package dev.seocho507.board.domian;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import dev.seocho507.board.domian.enums.UserRole;
import lombok.Getter;

@Getter
@Entity
public class Member extends BaseEntity {
	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String userName;

	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	protected Member() {
	}

	private Member(String userId, String password, String userName, String phoneNumber, UserRole role) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public static Member of(String userId, String password, String userName, String phoneNumber) {
		return new Member(userId, password, userName, phoneNumber, UserRole.USER);
	}

	public void encodePassword(String encodedPassword) {
		this.password = encodedPassword;
	}

}
