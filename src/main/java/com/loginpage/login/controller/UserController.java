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

import java.security.Principal;

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

//    @GetMapping("/admin_page")
//    public String getAdminPage(Model model, Principal principal, UserModel userModel) {
//        userModel.setRole("Admin");
//        UserModel authenticatedAdmin = userService.authenticateAdmin(userModel.getRole());
//        if ("Admin".equals(authenticatedAdmin)) {
//            return "admin_page";
//        } else {
//            return "error_page";
//        }
//    }


//    @GetMapping("/admin_page")
//    public String getAdminPage(@ModelAttribute UserModel userModel) {
////        UserModel userModel = new UserModel();
//        userModel.setRole("Admin");
////        model.addAttribute("adminRequest", new UserModel());
//        System.out.println("admin request: " + userModel);
//        UserModel authenticatedAdmin = userService.authenticateAdmin(userModel.getRole());
//        if (authenticatedAdmin != null) {
////            model.addAttribute("userRole", authenticatedAdmin.getRole());
//            return "admin_page";
//        } else {
//            return "error_page";
//        }
//    }

    @GetMapping("/admin_page")
    public String getAdminPage(Model model, UserModel userModel) {
        UserModel authenticateAdmin = userService.authenticateAdmin(userModel.getRole());
        userModel.setRole("Admin");
        System.out.println("admin request:" + userModel);
        if (userModel != null && userModel.getRole().equals("Admin")) {
            model.addAttribute("userRole", userModel.getRole());
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


}
