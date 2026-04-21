package backend.redisguardrailbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    public StringRedisTemplate redisTemplate;

    public boolean isBotLimitReached(Long postId) {
        String key = "post:" + postId + ":bot_count";
        String value = redisTemplate.opsForValue().get(key);
        if(value==null) return false;
        return Long.parseLong(value)>=100;
    }

    public boolean isCoolDownActive(Long botId, Long userId) {
        String key = "cooldown:bot" + botId + ":user" + userId;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public Long incrementBotCount(Long postId) {
        String key = "post: " + postId + ":bot_count";
        return redisTemplate.opsForValue().increment(key);
    }

    public Long incrementVirality(Long postId, int points) {
        String key = "post:"+postId+ ":virality_score";
        return redisTemplate.opsForValue().increment(key,points);
    }

    public void setCooldown(Long botId, Long userId) {
        String key= "cooldown:bot" + botId + ":user" + userId;
        redisTemplate.opsForValue().set(key, "true" ,10 , TimeUnit.MINUTES);
    }

    public boolean isNotificationCooldownActive(Long userId) {
        String key = "notif:cooldown:"+ userId;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void addPendingNotification(Long userId, String message) {
        String key = "user:" + userId + ":pending_notifs";
        redisTemplate.opsForList().rightPush(key, message);
    }

    public void setNotificationCooldown(Long userId) {
        String key = "notif:cooldown:"+userId;
        redisTemplate.opsForValue().set(key,"true",15,TimeUnit.MINUTES);
    }
}
