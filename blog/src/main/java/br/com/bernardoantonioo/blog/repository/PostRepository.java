package br.com.bernardoantonioo.blog.repository;

import br.com.bernardoantonioo.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
