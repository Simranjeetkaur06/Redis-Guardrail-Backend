package backend.redisguardrailbackend.Repository;

import backend.redisguardrailbackend.Entities.Bot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotRepo extends JpaRepository<Bot,Long> {
}
