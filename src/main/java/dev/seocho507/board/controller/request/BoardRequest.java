package dev.seocho507.board.controller.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequest {

	@NotEmpty
	@Length(min = 2, max = 30)
	private String title;

	@NotEmpty
	@Length(min = 10, max = 500)
	private String content;

}
