package dev.seocho507.board.controller.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

public class CommentRequest {

	@Getter
	@Setter
	@NotEmpty
	@Length(min = 2)
	private String content;

}
