package backend.redisguardrailbackend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Bot {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String personaDescription;
}
