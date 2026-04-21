package backend.redisguardrailbackend.service;

import backend.redisguardrailbackend.entities.Comment;
import backend.redisguardrailbackend.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private RedisService redisService;

    public Comment addComment(Long postId, Long authorId,String content, int depthLevel,
                              boolean isBot , Long botId){
        if(depthLevel>20){
            throw new RuntimeException("Depth Limit Reached");
        }
        if(isBot && redisService.isBotLimitReached(postId)){
            throw new RuntimeException("Bot limit reached");
        }
        if(isBot && botId!=null){
            if(redisService.isCoolDownActive(botId,authorId)){
                throw new RuntimeException("Cooldown active");
            }
        }
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setAuthorId(authorId);
        comment.setContent(content);
        comment.setDepthLevel(depthLevel);
        comment.setCreatedAt(LocalDateTime.now());

        if(isBot){
            redisService.incrementBotCount(postId);
            redisService.incrementVirality(postId,1);
            if(botId !=null){
                redisService.setCooldown(botId,authorId);
            }
            if(redisService.isNotificationCooldownActive(authorId)){
                redisService.addPendingNotification(authorId,"Bot replied to your post");
            }else{
                System.out.println("Push Notification Sent to User : "+ authorId);
                redisService.setNotificationCooldown(authorId);
            }
        }else{
            redisService.incrementVirality(postId,50);
        }
        return commentRepo.save(comment);
    }
}
