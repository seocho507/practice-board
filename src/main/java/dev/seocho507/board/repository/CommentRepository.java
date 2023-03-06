package dev.seocho507.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import dev.seocho507.board.domian.Comment;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
