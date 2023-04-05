package com.loginpage.login.repository;

import com.loginpage.login.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {


    Optional<UserModel> findByLoginAndPassword(String login, String password);

    Optional<UserModel> findFirstByLogin(String login);

    Optional<UserModel> findByRole(String role);

    UserModel findByLoginAndPasswordAndRole(String login, String password, String role);
}
