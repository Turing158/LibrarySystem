package com.libSystem.controller;

import com.libSystem.entity.Result;
import com.libSystem.entity.User;
import com.libSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String userList(
            HttpSession session,
            @RequestParam(defaultValue = "1",required = false) int page
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        if (user.getUser_permission() != 1){
            return "redirect:/home";
        }
        userService.userList(session,page);
        return "userList";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/reg")
    public String reg(){
        return "reg";
    }
    @PostMapping("/userLogin")
    public String userLogin(
            @RequestParam String user,
            @RequestParam String password,
            @RequestParam String code,
            HttpSession session
    ){

        String status = userService.userLogin(user,password,code,session);
        if(status.equals("success")){
            return "redirect:/home";
        }
        return "redirect:/login";
    }
    @PostMapping("/userReg")
    public String userReg(
            @RequestParam String user,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String code,
            HttpSession session
    ){
        String status = userService.UserRegister(user,password,name,code,session);
        if(status.equals("success")){
            return "redirect:/login";
        }
        return "redirect:/reg";
    }

}
