package backend.redisguardrailbackend.service;

import backend.redisguardrailbackend.entities.User;
import backend.redisguardrailbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User createUser(String username, boolean isPremium){
       User user= new User();
       user.setUsername(username);
       user.setPremium(isPremium);
       return userRepo.save(user);
    }
    public User getUserById(Long id){
        return userRepo.findById(id).get();
    }
}
