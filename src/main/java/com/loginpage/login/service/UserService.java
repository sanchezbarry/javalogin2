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



//    public UserModel authenticate(String login, String password, String role){
//        UserModel userModel = new UserModel();
//
//        return userRepository.findByLoginAndPassword(login, password){
//            userModel.setRole(role); // set the role field
//            return userModel;
//        }).orElse(null);
//    }

//    public UserModel authenticateAdmin(String login, String password, String role) {
//        Optional<Object> adminUser = userRepository.findByRole("Admin").stream().findFirst();
//        return adminUser.isPresent() ? (UserModel) adminUser.get() : null;
//    }

    public UserModel authenticateAdmin(String role) {
        return userRepository.findByRole(role).orElse(null);
    }


    public UserModel authenticate(String login, String password){
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }

    public UserModel getUserRole(String role) {
        return userRepository.findByRole(role).orElse(null);
    }
//
//    public UserModel authenticateAdmin(String login, String password, String role) {
//        return (UserModel) userRepository.findByRole(String.valueOf(role.equals("Admin"))).orElse(null);
//    }

//    public UserModel authenticateAdmin(String login, String password, String role) {
//        return userRepository.findByLoginAndPasswordAndRole(login, password, role);
//    }

}
