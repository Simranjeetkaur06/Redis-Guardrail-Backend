package backend.redisguardrailbackend.dto;

import lombok.Data;

@Data
public class PostReq {
    private Long authorId;
    private String content;
}
