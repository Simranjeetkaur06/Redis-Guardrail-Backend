package backend.redisguardrailbackend.repository;

import backend.redisguardrailbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
