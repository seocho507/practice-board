package dev.seocho507.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dev.seocho507.board.domian.Board;

@RepositoryRestResource
public interface BoardRepository extends JpaRepository<Board, Long> {

	Page<Board> findAll(Pageable pageable);
}
