package backend.redisguardrailbackend.controller;

import backend.redisguardrailbackend.dto.CommentReq;
import backend.redisguardrailbackend.dto.PostReq;
import backend.redisguardrailbackend.entities.Comment;
import backend.redisguardrailbackend.entities.Post;
import backend.redisguardrailbackend.service.CommentService;
import backend.redisguardrailbackend.service.PostService;
import backend.redisguardrailbackend.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RedisService redisService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostReq request){
        Post post = postService.createpost(request.getAuthorId(),request.getContent());
        return ResponseEntity.ok(post);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long postId, @RequestBody CommentReq request){
        try{
            Comment comment = commentService.addComment(postId,request.getAuthorId(),
                    request.getContent(),request.getDepthLevel(),request.isBot(),request.getBotId());
            return ResponseEntity.ok(comment);
        }catch(RuntimeException e){
            return ResponseEntity.status(429).body(e.getMessage());
        }
    }
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId, @RequestParam Long userId){
        redisService.incrementVirality(postId,20);
        return ResponseEntity.ok("Post Liked! virality updated");
    }
}
