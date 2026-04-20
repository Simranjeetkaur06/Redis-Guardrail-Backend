package backend.redisguardrailbackend.service;

import backend.redisguardrailbackend.entities.Post;
import backend.redisguardrailbackend.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private RedisService redisService;
    public Post createpost(Long authorId, String content){
        Post post = new Post();
        post.setAuthorId(authorId);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        postRepo.save(post);
        return post;
    }
    public Post getPostById(Long id){
        return postRepo.findById(id).get();
    }
}
