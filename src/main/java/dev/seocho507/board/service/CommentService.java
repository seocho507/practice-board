package dev.seocho507.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.seocho507.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;


}
