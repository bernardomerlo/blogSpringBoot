package br.com.bernardoantonioo.blog.repository;

import br.com.bernardoantonioo.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
