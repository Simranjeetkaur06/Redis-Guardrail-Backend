package backend.redisguardrailbackend.Repository;

import backend.redisguardrailbackend.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {
}
