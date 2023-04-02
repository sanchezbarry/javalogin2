package com.loginpage.login.service;

import com.loginpage.login.model.UserModel;
import com.loginpage.login.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String login, String password, String email, String role){
        if (login == null || password == null) {
            return null;
        } else {
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            userModel.setRole("User");
            return userRepository.save(userModel);
        }
    }

    public UserModel authenticate(String login, String password){
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}