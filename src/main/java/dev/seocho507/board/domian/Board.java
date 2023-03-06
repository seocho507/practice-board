package dev.seocho507.board.domian;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Getter
@Entity
public class Board extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member writer;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;

	protected Board() {
	}

	private Board(Member writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public static Board of(Member writer, String title, String content) {
		return new Board(writer, title, content);
	}
}
