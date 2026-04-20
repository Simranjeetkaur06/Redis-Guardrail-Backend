package backend.redisguardrailbackend.service;

import backend.redisguardrailbackend.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private RedisService redisService;


}
