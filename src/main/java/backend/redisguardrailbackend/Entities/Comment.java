package backend.redisguardrailbackend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private Long postId;
    private Long authorId;
    private String content;
    private int depthLevel;
    private LocalDateTime createdAt;
}
