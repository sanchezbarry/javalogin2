package com.loginpage.login.repository;

import com.loginpage.login.model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {


    Optional<UserModel> findByLoginAndPassword(String login, String password);

}
