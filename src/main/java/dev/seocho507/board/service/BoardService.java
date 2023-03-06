package dev.seocho507.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.seocho507.board.controller.request.BoardRequest;
import dev.seocho507.board.domian.Board;
import dev.seocho507.board.domian.Member;
import dev.seocho507.board.exception.ErrorCode;
import dev.seocho507.board.exception.MyCustomException;
import dev.seocho507.board.repository.BoardRepository;
import dev.seocho507.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;

	public Board create(BoardRequest request, String name) {
		final Member writer = memberRepository.findByUserId(name)
										.orElseThrow(() -> new MyCustomException(ErrorCode.USER_NOT_FOUND));
		final Board board = Board.of(writer, request.getTitle(), request.getContent());
		return boardRepository.save(board);
	}

	public Page<Board> findAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public Board findById(Long boardId) {
		return boardRepository.findById(boardId).orElseThrow(() -> new MyCustomException(ErrorCode.POST_NOT_FOUND));
	}
}
