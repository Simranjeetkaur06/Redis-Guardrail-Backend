package backend.redisguardrailbackend.repository;

import backend.redisguardrailbackend.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {
}
