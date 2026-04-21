package backend.redisguardrailbackend.controller;

import backend.redisguardrailbackend.entities.Bot;
import backend.redisguardrailbackend.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/bots")
public class BotController {
    @Autowired
    private BotService botService;
    @PostMapping
    public Bot createBot(@RequestBody Map<String, Object> request){
        String name= (String) request.get("name");
        String persona= (String)request.get("personaDescription");
        return botService.createBot(name,persona);
    }
}
