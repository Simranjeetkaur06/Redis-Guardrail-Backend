package backend.redisguardrailbackend.dto;

import lombok.Data;

@Data
public class CommentReq {
    private Long authorId;
    private String content;
    private int depthLevel;
    private boolean isBot;
    private Long botId;

}
