package com.loginpage.login.controller;

import com.loginpage.login.model.UserModel;
import com.loginpage.login.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public  UserController(UserService userService){
        this.userService =  userService;
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UserModel());
        return "register";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return "login";
    }

    @GetMapping("/admin_page")
    public String getAdminPage(@ModelAttribute UserModel userModel, Model model){
        model.addAttribute("userRole", new UserModel());
        UserModel authenticatedAdmin = userService.authenticateAdmin(userModel.getLogin(), userModel.getPassword(), userModel.getRole());
        if (authenticatedAdmin != null){
            return "admin_page";
        } else {
            return "error_page";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel){
        System.out.println("register request:" + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(), userModel.getEmail(), userModel.getRole());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model){
        System.out.println("login request:" + userModel);
        UserModel authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
        if(authenticated != null) {
            model.addAttribute("userLogin", authenticated.getLogin());
            model.addAttribute("userRole", authenticated.getRole());
            return "account_page";
        } else {
            return "error_page";
        }
    }

//    @PostMapping("/admin_page")
//    public String getAdminPage(@ModelAttribute UserModel userModel, Model model){
//        System.out.println("admin request:" + userModel);
//        UserModel authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
//        if(authenticated != null) {
//            model.addAttribute("userLogin", authenticated.getLogin());
//            model.addAttribute("userRole", authenticated.getRole());
//            return "admin_page";
//        } else {
//            return "error_page";
//        }
//    }
}
