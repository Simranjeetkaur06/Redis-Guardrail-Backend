package backend.redisguardrailbackend.service;

import backend.redisguardrailbackend.entities.Bot;
import backend.redisguardrailbackend.repository.BotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotService {
    @Autowired
    private BotRepo botRepo;
    public Bot createBot(String name,String personaDescription){
        Bot bot =new Bot();
        bot.setName(name);
        bot.setPersonaDescription(personaDescription);
        return botRepo.save(bot);
    }
    public Bot getBotbyId(Long id){
        return botRepo.findById(id).get();
    }
}
