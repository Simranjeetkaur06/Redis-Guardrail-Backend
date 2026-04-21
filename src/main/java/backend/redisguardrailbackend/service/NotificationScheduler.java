package backend.redisguardrailbackend.service;

import jdk.jfr.Enabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@EnableScheduling
public class NotificationScheduler {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Scheduled(fixedRate=300000)
    public void sweepNotifications(){
        Set<String> keys = redisTemplate.keys("user:*:pending_notifs");
        if(keys==null || keys.isEmpty()) return;
        for(String key:keys){
            String userId=key.split(":")[1];

            List<String> notifs=redisTemplate.opsForList().range(key,0,-1);
            if(notifs!=null && !notifs.isEmpty()){
                System.out.println("Summarized Push Notification: " + notifs.get(0) + "and" + (notifs.size()-1)
                +"others interacted with your posts. UserId: " + userId);

                redisTemplate.delete(key);
            }
        }
    }
}
