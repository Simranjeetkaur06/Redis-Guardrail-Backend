package backend.redisguardrailbackend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private Long authorId;
    private String content;
    private LocalDateTime createdAt;

}
