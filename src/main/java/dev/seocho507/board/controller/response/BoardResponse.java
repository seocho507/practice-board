package dev.seocho507.board.controller.response;

import dev.seocho507.board.domian.Board;
import lombok.Getter;

@Getter
public class BoardResponse {

	private Long id;
	private String title;
	private String content;
	private String writer;

	protected BoardResponse() {
	}

	private BoardResponse(Long id, String title, String content, String writer) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public static BoardResponse from(Board board) {
		return new BoardResponse(board.getId(),
								 board.getTitle(),
								 board.getContent(),
								 board.getWriter().getUserName());
	}
}
