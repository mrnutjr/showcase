package com.example.showcase.service;

import com.example.showcase.model.LoginRequest;
import com.example.showcase.model.LoginResponse;
import com.example.showcase.repository.UserRepository;
import com.example.showcase.utils.AESUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class LoginService {

    private final RedisTemplate<String, String> template;
    private final UserRepository userRepository;
    private final LoggerService loggerService;

    @Value("user-key")
    private String USER_KEY_PREFIX;
    @Value("secret-key")
    private String secretKey;
    public LoginService(RedisTemplate<String, String> template, UserRepository userRepository, LoggerService loggerService) {
        this.template = template;
        this.userRepository = userRepository;
        this.loggerService = loggerService;
    }

    public ResponseEntity<LoginResponse> login(LoginRequest request) throws Exception {
        loggerService.processLogger("Log in by user : "+request.getUsername());
        var passEncrypt = AESUtils.encrypt(request.getPassword() , secretKey);
        System.out.println(passEncrypt);
        var user = userRepository.findByUsernameAndPassword(request.getUsername(), passEncrypt);
        if(user.isPresent()){
            var sessionId = UUID.randomUUID().toString();
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(sessionId);
            template.opsForValue().set(USER_KEY_PREFIX + ":" +sessionId , user.get().getUsername());
            loggerService.processLogger("Log in : SUCCESS");
            return new ResponseEntity<>(
                    loginResponse, HttpStatus.OK);
        }
        loggerService.processLogger("Log in : FAILED");
        return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
    }

    public void logout(String token){
        loggerService.processLogger("Log out by token : "+token);
        template.opsForValue().getAndDelete(USER_KEY_PREFIX + ":" +token);
    }

    public void checkToken(String token){
        loggerService.processLogger("checkToken by token : "+token);
        String value = template.opsForValue().get(USER_KEY_PREFIX + ":" +token);
        if (value == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "token not found");
        }

    }
}
