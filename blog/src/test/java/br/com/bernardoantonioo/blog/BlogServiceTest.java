package br.com.bernardoantonioo.blog;

import br.com.bernardoantonioo.blog.model.Comment;
import br.com.bernardoantonioo.blog.model.Post;
import br.com.bernardoantonioo.blog.model.User;
import br.com.bernardoantonioo.blog.repository.CommentRepository;
import br.com.bernardoantonioo.blog.repository.PostRepository;
import br.com.bernardoantonioo.blog.repository.UserRepository;
import br.com.bernardoantonioo.blog.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class BlogServiceTest  {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BlogService blogService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCommentToPost() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Título do Post");
        post.setContent("Conteúdo do Post");

        Comment comment = new Comment();
        comment.setContent("Comentário de teste");

        when(postRepository.save(post)).thenReturn(post);
        when(commentRepository.save(comment)).thenReturn(comment);

        blogService.addCommentToPost(post, comment);

        assert(post.getComments().contains(comment));

        verify(postRepository, times(1)).save(post);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void testAssignAuthorToPost(){
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Título do Post");
        post.setContent("Conteúdo do Post");

        User author = new User();
        author.setPassword("bernardoantonio");
        author.setEmail("bernardoantonio49@gmail.com");
        author.setUsername("bernardomerlo");

        blogService.assignAuthorToPost(post, author);
        verify(postRepository, times(1)).save(post);
    }
}
