package backend.redisguardrailbackend.Repository;

import backend.redisguardrailbackend.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
