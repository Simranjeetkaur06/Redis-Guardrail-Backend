package backend.redisguardrailbackend.controller;

import backend.redisguardrailbackend.entities.User;
import backend.redisguardrailbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public User createUser(@RequestBody Map<String,Object> request){
        String username= (String) request.get("username");
        boolean isPremium= (boolean) request.get("isPremium");
        return userService.createUser(username,isPremium);
    }
}
