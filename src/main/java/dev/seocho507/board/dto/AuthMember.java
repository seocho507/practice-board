package dev.seocho507.board.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.seocho507.board.domian.Member;
import dev.seocho507.board.domian.enums.UserRole;
import lombok.Getter;

@Getter
public class AuthMember implements UserDetails {

	private String userId;
	private String password;
	private String username;
	private UserRole role;

	protected AuthMember() {
	}

	private AuthMember(String userId, String password, String username, UserRole role) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.role = role;
	}

	public static AuthMember from(Member member) {
		return new AuthMember(member.getUserId(),
							  member.getPassword(),
							  member.getUserName(),
							  member.getRole());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.toString()));
	}

	// TODO : implement
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
