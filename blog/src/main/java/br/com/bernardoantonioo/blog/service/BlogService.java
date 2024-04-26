package br.com.bernardoantonioo.blog.service;

import br.com.bernardoantonioo.blog.model.Comment;
import br.com.bernardoantonioo.blog.model.Post;
import br.com.bernardoantonioo.blog.model.User;
import br.com.bernardoantonioo.blog.repository.CommentRepository;
import br.com.bernardoantonioo.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService    {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public BlogService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void addCommentToPost(Post post, Comment comment) {
        post.getComments().add(comment);
        comment.setPost(post);
        postRepository.save(post);
        commentRepository.save(comment);
    }

    public void assignAuthorToPost(Post post, User author) {
        post.setAuthor(author);
        savePost(post);
    }

}
