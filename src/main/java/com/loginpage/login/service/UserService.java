package com.loginpage.login.service;

import com.loginpage.login.model.UserModel;
import com.loginpage.login.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            if(userRepository.findFirstByLogin(login).isPresent()){
                System.out.println("This username already exists.");
                return null;
            }
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            userModel.setRole(role);
            return userRepository.save(userModel);
        }
    }

    public UserModel authenticate(String login, String password){
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }

//    public UserModel authenticateAdmin(String login, String password, String role) {
//        return (UserModel) userRepository.findByRole(String.valueOf(role == "Admin")).orElse(null);
//    }

    public UserModel authenticateAdmin(String login, String password, String role) {
        Optional<UserModel> optionalUser = userRepository.findByLogin(login);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            if (user.getPassword().equals(password) && user.getRole().equals("Admin")) {
                return user;
            }
        }
        return null;
    }
}
